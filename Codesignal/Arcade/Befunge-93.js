
// get names of the keyboard symbols:
// https://www.quora.com/What-are-the-names-of-the-keyboard-symbols

// follow x, y on matrix
const CONVERT_DIRECT_TO_COORD = {
    ">": [1, 0],
    "<": [-1, 0],
    "v": [0, 1],
    "^": [0, -1]
}

const OUTPUT_INSTRUCTIONS = [".", ","]
const [DOT_INSTRUCT, COMMA_INSTRUCT] = OUTPUT_INSTRUCTIONS
const STACK_INSTRUCTIONS = [":", "\\", "$"]
const [COLON_INSTRUCT, BACKSLASH_INSTRUCT, DOLLAR_INSTRUCT] = STACK_INSTRUCTIONS

const LOGICAL_OPERATORS = ["!", "`"]
const [NEGATIVE_OPERATOR, BACKQUOTE_OPERATOR] = LOGICAL_OPERATORS

const DIRECT_INSTRUCTIONS = [">", "<", "v", "^", "#"]
const [MOVE_RIGHT_INSTRUCT, MOVE_LEFT_INSTRUCT, MOVE_DOWN_INSTRUCT, MOVE_UP_INSTRUCT, BRIDGE_INSTRUCT] = DIRECT_INSTRUCTIONS

const MATH_OPERATORS = ["+", "-", "*", "/", "%"]
const [ADD_OPERATOR, MINUS_OPERATOR, MULTI_OPERATOR, DIVISION_OPERATOR, MODULO_OPERATOR] = MATH_OPERATORS

const CONDITIONAL_INSTRUCTIONS = ["_", "|"]
const [UNDERCORE_INSTRUCT, PIPE_INSTRUCT] = CONDITIONAL_INSTRUCTIONS

const STRING_MODE = "\"";
const EMPTY_CHARACTER = "";
const END_PROGRAM_SIGNAL = "@";
const WHITESPACE_CHARACTER = " ";
const BREAK_PROGRAM_COMMAND_LIMIT = 10000;
const BREAK_PROGRAM_OUTPUT_SYMBOL_LIMIT = 100;

isNotNull = val => val !== null && val !== undefined

isNull = val => !isNotNull(val) 

isEmpty = val => isNotNull(val) && `${val}` === EMPTY_CHARACTER

isNotEmpty = val =>  isNotNull(val) && `${val}` !== EMPTY_CHARACTER

isASCIIChar = char => /^[\x00-\x7F]$/.test(char)

isASCIIChar = (char, extended)=> (extended ? /^[\x00-\xFF]$/ : /^[\x00-\x7F]$/).test(char)

isNumber = value => !isEmpty(value) && /^-?\d+(\.\d+)?$/.test(value)

isNotaNumber = value => !isNumber(value)

validCoordInMaxtrixRange = ({
    row,
    col
}, programRange) => {
    if (col < 0)   col = programRange.base
    else           col %= (programRange.base + 1)
    if (row < 0)   row = programRange.side
    else           row %= (programRange.side + 1)

    return {
        row,
        col
    }
}

//currentStatus = {row, col, direct, programRange}
nextCoordBaseOnDirect = (currentStatus) => {
    let {
        row,
        col,
        direct,
        programRange
    } = currentStatus
    let coordDirect = CONVERT_DIRECT_TO_COORD[direct]
    row += coordDirect[1]
    col += coordDirect[0]
    let validC = validCoordInMaxtrixRange({
        row,
        col
    }, programRange)

    return {
        row: validC.row,
        col: validC.col
    }
}

// currentStatus = {stack, signal}
processLogicalOp = currentStatus => {
    let {
        stack,
        signal
    } = currentStatus,
    topVal = stack.pop() || 0;

    topVal = typeof topVal === "number" ? topVal : topVal.charCodeAt(0)
    if (signal == NEGATIVE_OPERATOR) {
        if (parseInt(topVal) == 0)   stack.push(1)
        else stack.push(0)
    }

    if (signal == BACKQUOTE_OPERATOR) {
        let nextVal = stack.pop() || 0
        nextVal = typeof nextVal === "number" ? nextVal : nextVal.charCodeAt(0)
        if (parseInt(nextVal) > parseInt(topVal))  stack.push(1)
        else  stack.push(0)
    }

    return {
        stack
    }
}

processMathOp = currentStatus => {
    let {
        stack,
        signal
    } = currentStatus;

    let a = stack.pop() || 0,
        b = stack.pop() || 0;
    a = parseInt(isNumber(a) ? a : a.charCodeAt())
    b = parseInt(isNumber(b) ? b : b.charCodeAt())

    switch (signal) {
        case ADD_OPERATOR:
            stack.push(a + b)
            break;

        case MINUS_OPERATOR:
            stack.push(b - a)
            break;

        case MULTI_OPERATOR:
            stack.push(a * b)
            break;

        case DIVISION_OPERATOR:
            stack.push(Math.floor(b / a))
            break;

        case MODULO_OPERATOR:
            stack.push(b % a)
            break;

        default:
            break;
    }

    return {
        stack
    }
}

// currentStatus = {signal, stack, row, col, {base, side }}
processConditionalInstruct = currentStatus => {
    let {
        signal,
        stack,
        row,
        col,
        programRange, 
        direct
    } = currentStatus;

    let topVal = stack.pop() || 0
    if (signal == UNDERCORE_INSTRUCT) {
        if (isNumber(topVal) && parseInt(topVal) == 0) {
            col += 1
            direct = MOVE_RIGHT_INSTRUCT
        } else {
            col -= 1
            direct = MOVE_LEFT_INSTRUCT
        }
    }

    if (signal == PIPE_INSTRUCT) {
        if (isNumber(topVal) && parseInt(topVal) == 0) {
            row += 1
            direct = MOVE_DOWN_INSTRUCT
        } else {
            row -= 1
            direct = MOVE_UP_INSTRUCT
        }
    }

    let validC = validCoordInMaxtrixRange({
        row,
        col
    }, programRange)

    return {
        row: validC.row,
        col: validC.col,
        stack,
        direct
    }
}

// currentStatus = {stack, row, col, program, direct}
processStringMode = currentStatus => {
    let {
        stack,
        row,
        col,
        direct,
        program
    } = currentStatus,
    coordDirect = CONVERT_DIRECT_TO_COORD[direct]
    let countStringModeSymbol = 0

    while (countStringModeSymbol < 2) {
        if (program[row][col] == STRING_MODE) {
            countStringModeSymbol++
        } else if(isASCIIChar(program[row][col])){
            stack.push(program[row][col].charCodeAt())
        }
        row += coordDirect[1];
        col += coordDirect[0];
        let validC = validCoordInMaxtrixRange({
            row,
            col
        }, {
            side: program.length - 1,
            base: program[0].length - 1
        })
        row = validC.row
        col = validC.col
    }

    return {
        stack,
        row,
        col 
    }
}

// currentStatus = {signal, stack}
processDigit = currentStatus => {
    let {
        signal,
        stack
    } = currentStatus
    stack.push(parseInt(signal))
    return {
        stack
    }
}

// currentStatus = {signal, stack}
processStackInstruct = currentStatus => {
    let {
        signal,
        stack
    } = currentStatus,
    topVal = stack.pop() || 0;

    if (signal == COLON_INSTRUCT) {
        stack.push(topVal)
        stack.push(topVal)
    }

    if (signal == BACKSLASH_INSTRUCT) {
        let nextVal = stack.pop() || 0;
        stack.push(topVal)
        stack.push(nextVal)
    }

    return {
        stack
    }
}

// currentStatus = {signal, stack, output}
processOutputInstruct = currentStatus => {
    let {
        signal,
        stack,
        output
    } = currentStatus
    let topVal = stack.pop() || 0
    if (signal == DOT_INSTRUCT) {
        topVal = isNumber(topVal) ? topVal : topVal.charCodeAt()
        output.push(parseInt(topVal) + WHITESPACE_CHARACTER)
    }
    if (signal == COMMA_INSTRUCT) {
        let convertVal = topVal
        if(isNumber(topVal) && parseInt(topVal) > 9){
            convertVal = String.fromCharCode(parseInt(topVal))
        }

        output.push(convertVal+EMPTY_CHARACTER)
    }

    return {
        stack,
        output
    }
}

// currentStatus = {signal, row, col, {base, side }
processDirectInstruct = currentStatus => {
    let {
        signal,
        row,
        col,
        programRange,
        direct
    } = currentStatus
    let convertSignalToCoord = CONVERT_DIRECT_TO_COORD[direct]

    if (signal == BRIDGE_INSTRUCT) {
        row += convertSignalToCoord[1] * 2;
        col += convertSignalToCoord[0] * 2;
    } else {
        row += convertSignalToCoord[1];
        col += convertSignalToCoord[0];
        direct = signal;
    }

    let validC = validCoordInMaxtrixRange({
        row,
        col
    }, programRange)

    return {
        row: validC.row,
        col: validC.col,
        direct
    }
}

// currentStatus = {row, col, direct, programRange}
// go to next position
processWhitespaceC = currentStatus => {
    let {
        row,
        col,
        direct,
        programRange
    } = currentStatus;
    let validC = nextCoordBaseOnDirect({
        row,
        col,
        direct,
        programRange
    })

    return {
        row: validC.row,
        col: validC.col
    }
}

befunge93 = program => {
    let programRange = {
            side: program.length - 1,
            base: program[0].length - 1
        },
        signal = WHITESPACE_CHARACTER,
        row = 0,
        col = 0,
        count = 0,
        direct = MOVE_RIGHT_INSTRUCT,
        stack = [],
        output = [],
        o;

    while (signal != END_PROGRAM_SIGNAL) {
        signal = program[row][col];

        if(count++ >= BREAK_PROGRAM_COMMAND_LIMIT 
            || output.join('').length >= BREAK_PROGRAM_OUTPUT_SYMBOL_LIMIT){
            break;
        }

        if (DIRECT_INSTRUCTIONS.includes(signal)) {
            // change direct immediately
            if(CONVERT_DIRECT_TO_COORD[signal]){
                direct = signal;
            }
            o = processDirectInstruct({
                signal,
                row,
                col,
                direct,
                programRange
            });
            row = o.row
            col = o.col
            direct = o.direct
        }

        if (CONDITIONAL_INSTRUCTIONS.includes(signal)) {
            o = processConditionalInstruct({
                signal,
                stack,
                row,
                col,
                direct,
                programRange
            });
            row = o.row
            col = o.col
            direct = o.direct
            stack = o.stack
        }

        if (MATH_OPERATORS.includes(signal)) {
            o = processMathOp({
                stack,
                signal
            })
            stack = o.stack
            o = nextCoordBaseOnDirect({
                row,
                col,
                direct,
                programRange
            })
            row = o.row
            col = o.col
        }

        if (LOGICAL_OPERATORS.includes(signal)) {
            o = processLogicalOp({
                stack,
                signal
            })
            stack = o.stack
            o = nextCoordBaseOnDirect({
                row,
                col,
                direct,
                programRange
            })
            row = o.row
            col = o.col
        }

        if (STACK_INSTRUCTIONS.includes(signal)) {
            o = processStackInstruct({
                stack,
                signal
            })
            stack = o.stack
            o = nextCoordBaseOnDirect({
                row,
                col,
                direct,
                programRange
            })
            row = o.row
            col = o.col
        }

        if (OUTPUT_INSTRUCTIONS.includes(signal)) {
            o = processOutputInstruct({
                stack,
                signal,
                output
            })
            stack = o.stack
            output = o.output
            o = nextCoordBaseOnDirect({
                row,
                col,
                direct,
                programRange
            })
            row = o.row
            col = o.col
        }

        if (isNumber(signal)) {
            o = processDigit({
                signal,
                stack
            })
            stack = o.stack
            o = nextCoordBaseOnDirect({
                row,
                col,
                direct,
                programRange
            })
            row = o.row
            col = o.col
        }

        if (signal == STRING_MODE) {
            o = processStringMode({
                stack,
                row,
                col,
                program,
                direct
            })
            stack = o.stack
            row = o.row
            col = o.col
        }

        if (signal == WHITESPACE_CHARACTER) {
            o = processWhitespaceC({
                row,
                col,
                direct,
                programRange
            })
            row = o.row
            col = o.col
        }

       /*
		 * console.log({ row, col, direct, signal, stack: stack.join(','),
		 * output })
		 */ 
    }

    return output.join('')
}
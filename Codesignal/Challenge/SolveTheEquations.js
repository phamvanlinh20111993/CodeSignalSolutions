/**
 * Url: https://app.codesignal.com/challenge/YGH9PGaAyDdfjfvRh
 * 
 * Solve the given equations.
 * 
 * Examples: 2 + 2 = 4 3(2+2) = 3*(2+2) = (2+2)3 = 12 32^2 = 12 (Note: ^ is
 * symbol for exponent)
 * 
 * Operator precedence order: ^ , / +, -
 * 
 * It is guaranteed that there will no negative exponents (e.g 2^-2).
 * 
 * [execution time limit] 4 seconds (js)
 * 
 * [input] string arg1
 * 
 * [output] integer
 */

// test case:
// test1: "4-1+2+3*(3-7*(5*5-8^3-(45-11*22-(4*(12^5+7*8)))/3))*22+5(24-6*7+5/2)7"
// test2: "1+2+3*(3 - 7*(5*5 - 8 - (45 - 11 * 22)/3))*22"
// test3: "5*5+-8-8^3/3"
// test4: "5*5+8-8^3/3"
// test5: "(5*(14/6 - 7)*(6-7))8"
// test6: "7*5*5*5+-8-8^3/3*5/2"
// test7: "5*5-8^3-(45-11*22-(4*(12^5+7*8)))"
// test8: "(3*(2+2+2)*4)*7"
// test9: "(5*(14/6-7)*(6-7))5"
// test10:  "4-1+2+3.4534*(-3.3434-7*(5*5.7-8^3-(45-11*22-(4*(12^5+7*8)))/3))*22+5(24-6*7+5/2)7", out: -153120951
// test11: "-1.2+-6.7000002", out: -7
solution = a => {

	const OPERATORS = ['^', '*', '/', '+', '-']
	const OPEN_PARENTHESES = '('
	const CLOSE_PARENTHESES = ')'
	const NUMSTR = '0123456789'


	const reformatInput = inp => {

		// ignore space
		inp = inp.split(/\s+/).join('')

		// reformat to add *: 3(2+2) => 3*(2+2)
		let res = inp,
			offset = 0
		for (let ind = 0; ind < inp.length; ind++) {
			if (inp[ind] == OPEN_PARENTHESES && ind - 1 >= 0 && NUMSTR.includes(inp[ind - 1])) {
				res = res.substring(0, ind + offset) + '*' + res.substring(ind + offset)
				offset++
			}

			if (inp[ind] == CLOSE_PARENTHESES && ind + 1 < inp.length && NUMSTR.includes(inp[ind + 1])) {
				res = res.substring(0, ind + offset + 1) + '*' + res.substring(ind + offset + 1)
				offset++
			}
		}

		return res
	}


	a = reformatInput(a)

	const cal = (l, o, r) => {
		l = parseInt(l)
		r = parseInt(r)

		if (o === OPERATORS[0]) return l ** r
		else if (o === OPERATORS[1]) return l * r
		else if (o === OPERATORS[4]) return l - r
		else if (o === OPERATORS[3]) return l + r
		else if (o === OPERATORS[2]) return parseInt(l / r)
		else throw Error('Not valid operator')
	}

	// convert expression string to array
	// ex: inp: 6*7+-1.24*4 => out: ['6', '*', '7', '+', '-1.24', '*', '4']
	const toArrExpressions = expressions => {
		const MATCH_NUMS_EXPRESSION = /(?:(?<=[\-\*\+\/\^]|^)-)?\d+(?:\.\d+)?/g
		const nums = expressions.match(MATCH_NUMS_EXPRESSION) || []

		const MATCH_OPERATORS_EXPRESSION = /(?:[\*\+\/\^]|(?<=\d)\-)/g
		const operators = expressions.match(MATCH_OPERATORS_EXPRESSION) || []

		let exArr = [],
			numPushFirst = nums[0] == expressions.substring(0, nums[0].length)
		let ind = 0
		while (ind < nums.length || ind < operators.length) {
			if (numPushFirst) {
				ind < nums.length && exArr.push(nums[ind])
				ind < operators.length && exArr.push(operators[ind])
			} else {
				ind < operators.length && exArr.push(operators[ind])
				ind < nums.length && exArr.push(nums[ind])
			}
			ind++
		}

		return exArr
	}

	// calulate the expression.
	// ex: inp: 6*7 - 5/2 + 4 => out: 43.5 <=> 43
	const calExpresion = expressions => {

		const NUMBER_PATTERN = /^-?\d+(\.\d+)?$/
		if (NUMBER_PATTERN.test(expressions)) {
			return parseInt(expressions)
		}

		let exArr = toArrExpressions(expressions) || []
		const ORDER_OPERATORS = {
			'^': 3,
			'*': 2,
			'/': 2,
			'+': 1,
			'-': 1
		}

		let operators = []
		exArr.map((ex, ind) => OPERATORS.includes(ex) && operators.push({
			op: ex,
			ind
		}))
		operators = operators.sort((a, b) => {
			if (ORDER_OPERATORS[b.op] == ORDER_OPERATORS[a.op]) {
				return a.ind - b.ind
			} else {
				return ORDER_OPERATORS[b.op] - ORDER_OPERATORS[a.op]
			}
		})

		const MARK_CHAR = '?'
		let res = 0,
			markV = 0,
			markD = new Array(exArr.length).fill(MARK_CHAR)
		for (let ind = 0; ind < operators.length; ind++) {
			let opp = operators[ind].ind
			if (opp + 1 < exArr.length && opp >= 1) {
				let num = cal(exArr[opp - 1], exArr[opp], exArr[opp + 1])
				exArr[opp - 1] = num
				exArr[opp] = num
				exArr[opp + 1] = num
				res = num

				let oldprV = markD[opp - 1],
					oldnxV = markD[opp + 1]
				markD[opp - 1] = markV
				markD[opp] = markV
				markD[opp + 1] = markV

				let pr = opp - 2
				while (oldprV != MARK_CHAR && pr >= 0 && markD[pr] === oldprV) {
					exArr[pr] = num
					markD[pr] = markV
					pr--
				}
				let nx = opp + 2
				while (oldnxV != MARK_CHAR && nx < exArr.length && markD[nx] === oldnxV) {
					exArr[nx] = num
					markD[nx] = markV
					nx++
				}
				markV++
			}
		}

		return res
	}

	// Using stack to ignore () and cal by order
	let res = 0,
		ind = 0,
		exLv0 = '',
		stack = []
	while (ind < a.length) {
		if (a[ind] == OPEN_PARENTHESES) {
			let p = ind + 1,
				exp = ''
			while (a[p] != OPEN_PARENTHESES && a[p] != CLOSE_PARENTHESES) {
				exp += a[p]
				p++
			}
			ind = p
			stack.push(exp)
		} else if (a[ind] == CLOSE_PARENTHESES) {
			data = stack.pop()
			if (stack.length > 0) {
				stack[stack.length - 1] += calExpresion(data)
			} else {
				exLv0 += calExpresion(data)
			}
			ind++
		} else {
			if (stack.length > 0) {
				stack[stack.length - 1] += a[ind++]
			} else {
				exLv0 += a[ind++]
			}
		}
	}

	return calExpresion(exLv0)

}
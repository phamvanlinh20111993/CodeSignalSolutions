/* 
 *) normal piece coordinates
           0
         1 2 3
       4 5 6 7 8
  
*) reverse piece coordinates
       0 1 2 3 4
         5 6 7
           8
  
*/
class Piece {

    _coords = new Array(9)

    constructor(oColor) {
        this._coords = this._coords.fill(oColor)
    }

    changeLayerColorAt(layer, pos, colors) {
        this._validatePos(pos)
        this._validateLayer(layer)
        if (layer === 1) {
            this._coords[pos === 0 ? pos : 4 * pos] = colors
            return
        }

        switch (pos) {
            case 0:
                this._coords[0] = colors[0]
                this._coords[1] = colors[1]
                this._coords[2] = colors[2]
                this._coords[3] = colors[3]
                break;
            case 1:
                this._coords[4] = colors[0]
                this._coords[5] = colors[1]
                this._coords[1] = colors[2]
                this._coords[6] = colors[3]

                break;
            case 2:
                this._coords[8] = colors[0]
                this._coords[7] = colors[1]
                this._coords[6] = colors[2]
                this._coords[3] = colors[3]
                break;
            default:
                break;
        }
    }

    getCurrentColorAt(pos, layer) {
        this._validatePos(pos)
        this._validateLayer(layer)

        if (layer === 1) {
            return this._coords[pos === 0 ? pos : 4 * pos]
        }

        let colors = ['', '', '', '']
        const c = this._coords
        switch (pos) {
            case 0:
                colors = [c[0], c[1], c[2], c[3]]
                break;
            case 1:
                colors = [c[4], c[5], c[1], c[6]]
                break;
            case 2:
                colors = [c[8], c[7], c[6], c[3]]
                break;
            default:
                break;
        }

        return colors
    }

    getCoords() {
        return this._coords
    }

    _validatePos(pos) {
        if (![0, 1, 2].includes(pos)) {
            throw Error('invalid pos value. Valid values is [0, 1, 2]')
        }
    }

    _validateLayer(layer) {
        if (![1, 2].includes(layer)) {
            throw Error('invalid layer value. Valid values is [1, 2]')
        }
    }

    toString() {
        return this._coords.join('')
    }
}

class LPiece extends Piece {

    constructor(oColor) {
        super(oColor);
    }

    getCoords() {
        const c = this._coords
        return [c[8], c[3], c[7], c[6], c[0], c[2], c[1], c[5], c[4]]
    }
}

class RPiece extends Piece {
    constructor(oColor) {
        super(oColor);
    }

    getCoords() {
        const c = this._coords
        return [c[4], c[6], c[5], c[1], c[8], c[7], c[3], c[2], c[0]]
    }
}

class BPiece extends Piece {

    constructor(oColor) {
        super(oColor);
    }

    changeLayerColorAt(layer, pos, colors) {
        this._validatePos(pos)
        this._validateLayer(layer)

        if (layer === 1) {
            this._coords[pos === 0 ? pos : 4 * pos] = colors
            return
        }

        switch (pos) {
            case 0:
                this._coords[0] = colors[0]
                this._coords[1] = colors[1]
                this._coords[2] = colors[2]
                this._coords[5] = colors[3]
                break;
            case 1:
                this._coords[4] = colors[0]
                this._coords[3] = colors[1]
                this._coords[7] = colors[2]
                this._coords[2] = colors[3]

                break;
            case 2:
                this._coords[8] = colors[0]
                this._coords[6] = colors[1]
                this._coords[5] = colors[2]
                this._coords[7] = colors[3]
                break;
            default:
                break;
        }
    }

    getCurrentColorAt(pos, layer) {
        this._validatePos(pos)
        this._validateLayer(layer)

        if (layer === 1) {
            return this._coords[pos === 0 ? pos : 4 * pos]
        }

        let colors = ['', '', '', '']
        const c = this._coords
        switch (pos) {
            case 0:
                colors = [c[0], c[1], c[2], c[5]]
                break;
            case 1:
                colors = [c[4], c[3], c[7], c[2]]
                break;
            case 2:
                colors = [c[8], c[6], c[5], c[7]]
                break;
            default:
                break;
        }

        return colors
    }

    getCoords() {
        return this._coords.reverse()
    }
}

class PyraminxRubikCube {

    #leftFace
    #rightFace
    #fontFace
    #bottomFace

    constructor(faceColors) {
        this.#fontFace = new Piece(faceColors[0])
        this.#bottomFace = new BPiece(faceColors[1])
        this.#leftFace = new LPiece(faceColors[2])
        this.#rightFace = new RPiece(faceColors[3])
    }

    #moveUp(move) {
        const direct = {
            'U': 1,
            'u': 2,
            "U'": 1,
            "u'": 2
        }

        let lColor = this.#leftFace.getCurrentColorAt(0, direct[move]),
            uColor = this.#fontFace.getCurrentColorAt(0, direct[move]),
            rColor = this.#rightFace.getCurrentColorAt(0, direct[move])

        const colorOrder = {
            'U': [lColor, uColor, rColor],
            'u': [lColor, uColor, rColor],
            "U'": [rColor, lColor, uColor],
            "u'": [rColor, lColor, uColor]
        }

        this.#fontFace.changeLayerColorAt(direct[move], 0, colorOrder[move][0])
        this.#rightFace.changeLayerColorAt(direct[move], 0, colorOrder[move][1])
        this.#leftFace.changeLayerColorAt(direct[move], 0, colorOrder[move][2])
    }

    #moveLeft(move) {
        const direct = {
            'L': 1,
            'l': 2,
            "L'": 1,
            "l'": 2
        }

        let lColor = this.#leftFace.getCurrentColorAt(2, direct[move]),
            uColor = this.#fontFace.getCurrentColorAt(1, direct[move]),
            bColor = this.#bottomFace.getCurrentColorAt(0, direct[move])

        const colorOrder = {
            'L': [uColor, bColor, lColor],
            'l': [uColor, bColor, lColor],
            "L'": [bColor, lColor, uColor],
            "l'": [bColor, lColor, uColor]
        }

        this.#leftFace.changeLayerColorAt(direct[move], 2, colorOrder[move][0])
        this.#fontFace.changeLayerColorAt(direct[move], 1, colorOrder[move][1])
        this.#bottomFace.changeLayerColorAt(direct[move], 0, colorOrder[move][2])
    }

    #moveRight(move) {
        const direct = {
            'R': 1,
            'r': 2,
            "R'": 1,
            "r'": 2
        }

        let uColor = this.#fontFace.getCurrentColorAt(2, direct[move]),
            bColor = this.#bottomFace.getCurrentColorAt(1, direct[move]),
            rColor = this.#rightFace.getCurrentColorAt(1, direct[move])

        const colorOrder = {
            'R': [rColor, uColor, bColor],
            'r': [rColor, uColor, bColor],
            "R'": [bColor, rColor, uColor],
            "r'": [bColor, rColor, uColor]
        }

        this.#fontFace.changeLayerColorAt(direct[move], 2, colorOrder[move][0])
        this.#bottomFace.changeLayerColorAt(direct[move], 1, colorOrder[move][1])
        this.#rightFace.changeLayerColorAt(direct[move], 1, colorOrder[move][2])
    }

    #moveBottom(move) {
        const direct = {
            'B': 1,
            'b': 2,
            "B'": 1,
            "b'": 2
        }

        let lColor = this.#leftFace.getCurrentColorAt(1, direct[move]),
            rColor = this.#rightFace.getCurrentColorAt(2, direct[move]),
            bColor = this.#bottomFace.getCurrentColorAt(2, direct[move])

        const colorOrder = {
            'B': [bColor, lColor, rColor],
            'b': [bColor, lColor, rColor],
            "B'": [rColor, bColor, lColor],
            "b'": [rColor, bColor, lColor]
        }

        this.#leftFace.changeLayerColorAt(direct[move], 1, colorOrder[move][0])
        this.#rightFace.changeLayerColorAt(direct[move], 2, colorOrder[move][1])
        this.#bottomFace.changeLayerColorAt(direct[move], 2, colorOrder[move][2])
    }

    changeStep(move) {
        switch (move[0].toLowerCase()) {
            case 'u':
                this.#moveUp(move)
                break;
            case "b":
                this.#moveBottom(move)
                break;
            case 'r':
                this.#moveRight(move)
                break
            case "l":
                this.#moveLeft(move)
                break;
            default:
                break;
        }
    }

    getAssembleStep() {
        return [this.#fontFace.getCoords(),
            this.#bottomFace.getCoords(),
            this.#leftFace.getCoords(),
            this.#rightFace.getCoords()
        ]
    }

    toString() {
        return [this.#leftFace.toString(),
            this.#fontFace.toString(),
            this.#rightFace.toString(),
            this.#bottomFace.toString()
        ]
    }
}

solution = (faceColors, moves) => {
    const pyraminxRubikCube = new PyraminxRubikCube(faceColors)
    // turn into init step
    const mapReDirection = {
        "U": "U'",
        "U'": "U",
        "u": "u'",
        "u'": "u",

        "R": "R'",
        "R'": "R",
        "r": "r'",
        "r'": "r",

        "L": "L'",
        "L'": "L",
        "l": "l'",
        "l'": "l",

        "B": "B'",
        "B'": "B",
        "b": "b'",
        "b'": "b",
    }
    moves.reverse().map(move => {
        pyraminxRubikCube.changeStep(mapReDirection[move])
        console.log('move', mapReDirection[move], '-', pyraminxRubikCube.toString())
    })

    return pyraminxRubikCube.getAssembleStep()
}
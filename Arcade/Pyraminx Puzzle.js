
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

    constructor(oColor){
        this._coords = this._coords.fill(oColor)
    }
    
    changeFirstLayerColor(pos, color){
        this._validatePos(pos)
        this._coords[pos === 0 ? pos : 4*pos] = color
    }
    
    changeSecondLayerColor(pos, colors){
        this._validatePos(pos)
        switch(pos){
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
    
    getCurrentColorAt(pos, layer){
        this._validatePos(pos)
        this._validateLayer(layer)
        
        if(layer === 1){
            return this._coords[pos === 0 ? pos : 4*pos] 
        }
    
        let colors = ['', '', '', '']
        const c = this._coords
        switch(pos){
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
    
    getCoords(){
        return this._coords
    }
    
    _validatePos(pos){
        if(![0, 1, 2].includes(pos)){
            throw Error('invalid pos value. Valid values is [0, 1, 2]')
        }
    }
    
    _validateLayer(layer){
         if(![1, 2].includes(layer)){
            throw Error('invalid layer value. Valid values is [1, 2]')
        }
    }
    
    toString(){
        return this._coords.join('')
    }
}

class LPiece extends Piece {
    
    constructor(oColor) {
        super(oColor);
    }
    
    getCoords(){
        const c = this._coords
        return [c[8], c[3], c[7], c[6], c[0], c[2], c[1], c[5], c[4]]
    }
}   

class RPiece extends Piece {
    constructor(oColor) {
        super(oColor);
    }
    
    getCoords(){
        const c = this._coords
        return [c[4], c[6], c[5], c[1], c[8], c[7], c[3],c[2], c[0]]
    }

}

class BPiece extends Piece {
    
    constructor(oColor) {
        super(oColor);
    }
    
    changeSecondLayerColor(pos, colors){
        this._validatePos(pos)
       
        switch(pos){
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
    
    getCurrentColorAt(pos, layer){
        this._validatePos(pos)
        this._validateLayer(layer)
        
        if(layer === 1){
            return this._coords[pos === 0 ? pos : 4*pos] 
        }
        
        let colors = ['', '', '', '']
        const c = this._coords
        switch(pos){
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
    
    getCoords(){
        return this._coords.reverse()
    }
}


class PyraminxRubikCube {
    
    #leftFace
    #rightFace
    #fontFace
    #bottomFace
    
    constructor(faceColors){
        this.#fontFace = new Piece(faceColors[0])
        this.#bottomFace = new BPiece(faceColors[1])
        this.#leftFace = new LPiece(faceColors[2])
        this.#rightFace = new RPiece(faceColors[3])
    }
    
    changeStep(move){
        let uColor, lColor, rColor, bColor;
        switch(move){
            // Up    
            case 'U':
                uColor = this.#fontFace.getCurrentColorAt(0, 1)
                lColor = this.#leftFace.getCurrentColorAt(0, 1)
                rColor = this.#rightFace.getCurrentColorAt(0, 1)
                this.#fontFace.changeFirstLayerColor(0, lColor)
                this.#rightFace.changeFirstLayerColor(0, uColor)
                this.#leftFace.changeFirstLayerColor(0, rColor)  
                break;            
            case "U'":
                uColor = this.#fontFace.getCurrentColorAt(0, 1)
                lColor = this.#leftFace.getCurrentColorAt(0, 1)
                rColor = this.#rightFace.getCurrentColorAt(0, 1)
                this.#fontFace.changeFirstLayerColor(0, rColor)
                this.#rightFace.changeFirstLayerColor(0, lColor)  
                this.#leftFace.changeFirstLayerColor(0, uColor)  
                break;
            case 'u':
                uColor = this.#fontFace.getCurrentColorAt(0, 2)
                lColor = this.#leftFace.getCurrentColorAt(0, 2)
                rColor = this.#rightFace.getCurrentColorAt(0, 2)
                this.#fontFace.changeSecondLayerColor(0, lColor)
                this.#rightFace.changeSecondLayerColor(0, uColor)
                this.#leftFace.changeSecondLayerColor(0, rColor) 
                break;      
            case "u'":
                uColor = this.#fontFace.getCurrentColorAt(0, 2)
                lColor = this.#leftFace.getCurrentColorAt(0, 2)
                rColor = this.#rightFace.getCurrentColorAt(0, 2)
                this.#fontFace.changeSecondLayerColor(0, rColor)
                this.#rightFace.changeSecondLayerColor(0, lColor)  
                this.#leftFace.changeSecondLayerColor(0, uColor) 
                break;  
            // Bottom        
            case 'B':
                lColor = this.#leftFace.getCurrentColorAt(1, 1)
                rColor = this.#rightFace.getCurrentColorAt(2, 1)
                bColor = this.#bottomFace.getCurrentColorAt(2, 1)
                this.#leftFace.changeFirstLayerColor(1, bColor)
                this.#rightFace.changeFirstLayerColor(2, lColor)
                this.#bottomFace.changeFirstLayerColor(2, rColor)
                break;
            case "B'":
                lColor = this.#leftFace.getCurrentColorAt(1, 1)
                rColor = this.#rightFace.getCurrentColorAt(2, 1)
                bColor = this.#bottomFace.getCurrentColorAt(2, 1)
                this.#leftFace.changeFirstLayerColor(1, rColor)
                this.#rightFace.changeFirstLayerColor(2, bColor) 
                this.#bottomFace.changeFirstLayerColor(2, lColor)
                break;
            // have trouble with this
            case 'b':
                lColor = this.#leftFace.getCurrentColorAt(1, 2)
                rColor = this.#rightFace.getCurrentColorAt(2, 2)
                bColor = this.#bottomFace.getCurrentColorAt(2, 2)
                this.#leftFace.changeSecondLayerColor(1, bColor)
                this.#rightFace.changeSecondLayerColor(2, lColor)
                this.#bottomFace.changeSecondLayerColor(2, rColor)
                break; 
            case "b'":
                lColor = this.#leftFace.getCurrentColorAt(1, 2)
                rColor = this.#rightFace.getCurrentColorAt(2, 2)
                bColor = this.#bottomFace.getCurrentColorAt(2, 2)
                this.#leftFace.changeSecondLayerColor(1, rColor)
                this.#rightFace.changeSecondLayerColor(2, bColor)
                this.#bottomFace.changeSecondLayerColor(2, lColor)
                break;
            // Left    
            case 'L':
                lColor = this.#leftFace.getCurrentColorAt(2, 1)
                uColor = this.#fontFace.getCurrentColorAt(1, 1)
                bColor = this.#bottomFace.getCurrentColorAt(0, 1)
                this.#leftFace.changeFirstLayerColor(2, uColor)
                this.#fontFace.changeFirstLayerColor(1, bColor)
                this.#bottomFace.changeFirstLayerColor(0, lColor) 
                break;
            case "L'":
                lColor = this.#leftFace.getCurrentColorAt(2, 1)
                uColor = this.#fontFace.getCurrentColorAt(1, 1)
                bColor = this.#bottomFace.getCurrentColorAt(0, 1)
                this.#leftFace.changeFirstLayerColor(2, bColor)
                this.#fontFace.changeFirstLayerColor(1, lColor)
                this.#bottomFace.changeFirstLayerColor(0, uColor) 
                break;
            case 'l':
                lColor = this.#leftFace.getCurrentColorAt(2, 2)
                uColor = this.#fontFace.getCurrentColorAt(1, 2)
                bColor = this.#bottomFace.getCurrentColorAt(0, 2)
                this.#leftFace.changeSecondLayerColor(2, uColor)
                this.#fontFace.changeSecondLayerColor(1, bColor)
                this.#bottomFace.changeSecondLayerColor(0, lColor) 
                break;
            case "l'":
                lColor = this.#leftFace.getCurrentColorAt(2, 2)
                uColor = this.#fontFace.getCurrentColorAt(1, 2)
                bColor = this.#bottomFace.getCurrentColorAt(0, 2)
                this.#leftFace.changeSecondLayerColor(2, bColor)
                this.#fontFace.changeSecondLayerColor(1, lColor)
                this.#bottomFace.changeSecondLayerColor(0, uColor) 
                break;
            // Right      
            case 'R':
                uColor = this.#fontFace.getCurrentColorAt(2, 1)
                bColor = this.#bottomFace.getCurrentColorAt(1, 1)
                rColor = this.#rightFace.getCurrentColorAt(1, 1)
                this.#fontFace.changeFirstLayerColor(2, rColor)
                this.#bottomFace.changeFirstLayerColor(1, uColor)
                this.#rightFace.changeFirstLayerColor(1, bColor)
                break;
            case "R'":
                uColor = this.#fontFace.getCurrentColorAt(2, 1)
                bColor = this.#bottomFace.getCurrentColorAt(1, 1)
                rColor = this.#rightFace.getCurrentColorAt(1, 1)
                this.#fontFace.changeFirstLayerColor(2, bColor)
                this.#bottomFace.changeFirstLayerColor(1, rColor)
                this.#rightFace.changeFirstLayerColor(1, uColor)
                break;
            case 'r':
                uColor = this.#fontFace.getCurrentColorAt(2, 2)
                bColor = this.#bottomFace.getCurrentColorAt(1, 2)
                rColor = this.#rightFace.getCurrentColorAt(1, 2)
                this.#fontFace.changeSecondLayerColor(2, rColor)
                this.#bottomFace.changeSecondLayerColor(1, uColor)
                this.#rightFace.changeSecondLayerColor(1, bColor)
                break;
            case "r'":
                uColor = this.#fontFace.getCurrentColorAt(2, 2)
                bColor = this.#bottomFace.getCurrentColorAt(1, 2)
                rColor = this.#rightFace.getCurrentColorAt(1, 2)
                this.#fontFace.changeSecondLayerColor(2, bColor)
                this.#bottomFace.changeSecondLayerColor(1, rColor)
                this.#rightFace.changeSecondLayerColor(1, uColor)
                break;
            default:
                break;
        }
    }
    
    getAssembleStep(){
        return [this.#fontFace.getCoords(), 
                this.#bottomFace.getCoords(), 
                this.#leftFace.getCoords(), 
                this.#rightFace.getCoords()]
    }
    
    toString(){
        return [this.#leftFace.toString(),
                this.#fontFace.toString(), 
                this.#rightFace.toString(),
                this.#bottomFace.toString()]
    }
}

solution = (faceColors, moves) => {
    const pyraminxRubikCube = new PyraminxRubikCube(faceColors)
    
    // turn into init step
    const mapDirection = {
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
    moves.reverse().map(move =>{
         pyraminxRubikCube.changeStep(mapDirection[move])
         console.log('move', move,'-',  pyraminxRubikCube.toString())
    })
    
    return pyraminxRubikCube.getAssembleStep()
}

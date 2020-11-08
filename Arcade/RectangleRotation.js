/**
 *
 */

function rectangleRotation(a, b) {
    // origin: A(-a/2, b/2), B(a/2, b/2), C(-a/2, -b/2), D(a/2, -b/2)
    // After rotate 45 degree:
    let A = [-a/2*Math.cos(Math.PI / 4) - b/2*Math.sin(Math.PI / 4),
             -a/2*Math.sin(Math.PI / 4) + b/2*Math.cos(Math.PI / 4)],
        B = [a/2*Math.cos(Math.PI / 4) - b/2*Math.sin(Math.PI / 4),
             a/2*Math.sin(Math.PI / 4) + b/2*Math.cos(Math.PI / 4)],
        C = [-a/2*Math.cos(Math.PI / 4) + b/2*Math.sin(Math.PI / 4),
             -a/2*Math.sin(Math.PI / 4) - b/2*Math.cos(Math.PI / 4)],
        D = [a/2*Math.cos(Math.PI / 4) + b/2*Math.sin(Math.PI / 4),
             a/2*Math.sin(Math.PI / 4) - b/2*Math.cos(Math.PI / 4)]

        let xRange = [parseInt(A[0]), parseInt(D[0])]
        let yRange = [parseInt(B[1]), parseInt(C[1])]

        let getNormalVector = key => {
            let normalVector = []
            switch (key){
                case 'AB':
                    normalVector = [A[1] - B[1], B[0]- A[0]]
                    break;
                case 'BD':
                    normalVector = [B[1] - D[1], D[0]- B[0]]
                    break;
                case 'CD':
                    normalVector = [C[1] - D[1], D[0]- C[0]]
                    break;
                case 'AC':
                    normalVector = [A[1] - C[1], C[0]- A[0]]
                    break;
                default:
                    console.error('Wrong key.')
                    break;
            }

            return normalVector;
        }

        let countInIntersection = slide => {
            let upY = 0,
                downY = 0;

            //up
            if(slide < B[0]){
                // intersection is AB
                let AB = getNormalVector('AB')
                upY = -AB[0]*(slide - A[0])/AB[1] + A[1]
            }else{
                // intersection is BD
                let BD = getNormalVector('BD')
                upY = -BD[0]*(slide - B[0])/BD[1] + B[1]
            }
            //down
            if(slide < C[0]){
                // intersection is AC
                let AC = getNormalVector('AC')
                downY = -AC[0]*(slide - A[0])/AC[1] + A[1]
            }else{
                // intersection is CD
                 let CD = getNormalVector('CD')
                downY = -CD[0]*(slide - C[0])/CD[1] + C[1]
            }
      //      console.log(slide, upY, downY, Math.abs(parseInt(upY)-parseInt(downY)))
            let addition = upY * downY < 0 ? 1 : 0
            return Math.abs(parseInt(upY) - parseInt(downY)) + addition
        }

      //  console.log('A-', A, 'B-', B, 'C-', C, 'D-', D)
        let count = 0
        for(let start = xRange[0]; start <= xRange[1]; start++){
            count += countInIntersection(start)
        }

        return count;
}

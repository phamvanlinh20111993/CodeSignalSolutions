

 /*Cách làm:
    0, Kiểm tra đi từ đầu về cuối có thỏa mãn maxtime k, có thì return, ngược lại chuyển sang step 1
    Kiểm tra toàn bộ các tuyến đường có thể đi thời gian maxtime
    1, Từ đầu về cuối (Lưu mảng 1 chiều 3 tham số [x, y, time, mana])
    2, Từ cuối về đầu (Lưu mảng 1 chiều khác)
    3, So sánh chọn 2 giá trị mana nhỏ nhất của mảng (step) 1 và mảng (step ) 2 và so sánh time, nằm trong khoảng time cho phép không
    4, Kết thúc
 */

 /*How to Making:
     0, Check whether going from beginning to end satisfies maxtime k, yes then return, vice versa move to step 1
     Check out the entire possible travel time maxtime
     1, From beginning to end (Save 1-dimensional array with 3 parameters [x, y, time, mana])
     2, From end to beginning (Save array with another dimension)
     3, Compare and select 2 smallest mana values of array (step) 1 and array (step) 2 and compare time, which is within the allowed time range.
     4, Finish
*/
function portals(maxTime, manacost) {

    let isVisited = new Array(manacost.length).fill([])
    isVisited = isVisited.map(v => new Array(manacost[0].length).fill(0))

    isInRange = (x, y) => x >= 0 && y >= 0 && x < manacost.length
                          && y < manacost[0].length

    isDupCoord = ([x, y], [a, b]) => x == a && y == b

    const direction = [[-1, 0], [0, 1], [1, 0], [0, -1]]

    bfs = ([x, y], signal) => {
        let res = []

        let queue = [{x, y, time: 0, manaCost: manacost[x][y]}]
        isVisited[x][y] = 1
        while(queue.length > 0){
            let data = queue.shift()

            if(data.time > maxTime)
                continue;

            if(data.x == manacost.length-1 && data.y == manacost[0].length-1 && signal)
                return 0

             res.push(data)

            for(let d of direction){
                let dX = d[0] + data.x
                let dY = d[1] + data.y
                if(isInRange(dX, dY) && isVisited[dX][dY] == 0 && manacost[dX][dY] > -1){
                    isVisited[dX][dY] = 1
                    queue.push({x: dX, y: dY, time: data.time + 1, manaCost: manacost[dX][dY]})
                }
            }
        }


        return res
    }

    let topToDown = bfs([0, 0], true)
    if(topToDown == 0) return 0

    isVisited = isVisited.map(v => v.map(t => 0))
    let downToTop = bfs([manacost.length-1, manacost[0].length-1], false)

    topToDown = topToDown.sort((a, b) => a.manaCost - b.manaCost)
    downToTop = downToTop.sort((a, b) => a.manaCost - b.manaCost)

    let portal = 1000000000

    console.log(topToDown, downToTop)

    for(let ttd of topToDown){
        for(let dtt of downToTop){
            if(ttd.time + dtt.time <= maxTime && !isDupCoord([ttd.x, ttd.y], [dtt.x, dtt.y])){
                portal  = portal > ttd.manaCost + dtt.manaCost ? ttd.manaCost + dtt.manaCost  : portal
            }
        }
    }

    return portal
}

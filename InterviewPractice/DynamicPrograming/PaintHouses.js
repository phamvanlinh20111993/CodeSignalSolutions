/**
 * 
 */


// Let L[i, j] be the minimum cost for painting in house i with color j
// we have formula:
//    L[i, j] = Min(L[i-1, j] + a[t]) with t != j and (t, j) ~[0, 2]
//                                         i ~[0, c.length]
// L[0, j] = Min(a[0], a[1], a[2]) with j ~[0, 2]

let storeCost;
chooseBest = (c, houseNum, colorNum) => {
    if(houseNum < 0) return 0;

    if(storeCost[houseNum][colorNum])
        return storeCost[houseNum][colorNum];
    
    let arr = [];
    for(let p = 0; p < 3; p++){
        if(p != colorNum){
            let v = chooseBest(c, houseNum-1, p);
            arr.push(v +  c[houseNum][colorNum]);
        }
    }
    let min = Math.min(...arr)
    storeCost[houseNum][colorNum] = min
    return min;
}

paintHouses = c => {

    storeCost = new Array(c.length);
    for (let i = 0; i < storeCost.length; i++)
       storeCost[i] =  [0, 0];

    let r = [chooseBest(c, c.length-1, 0), 
             chooseBest(c, c.length-1, 1), 
             chooseBest(c, c.length-1, 2)];
   
    return Math.min(...r)
}

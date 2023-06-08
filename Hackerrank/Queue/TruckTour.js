'use strict';

/**
 * @link https://www.hackerrank.com/challenges/truck-tour/problem?isFullScreen=true
 * 
 * Suppose there is a circle. There are  petrol pumps on that circle. Petrol pumps are numbered  to  (both inclusive). You have two pieces of information corresponding to each of the petrol pump: (1) the amount of petrol that particular petrol pump will give, and (2) the distance from that petrol pump to the next petrol pump.

Initially, you have a tank of infinite capacity carrying no petrol. You can start the tour at any of the petrol pumps. Calculate the first point from where the truck will be able to complete the circle. Consider that the truck will stop at each of the petrol pumps. The truck will move one kilometer for each litre of the petrol.

Input Format

The first line will contain the value of .
The next  lines will contain a pair of integers each, i.e. the amount of petrol that petrol pump will give and the distance between that petrol pump and the next petrol pump.

Constraints:


Output Format

An integer which will be the smallest index of the petrol pump from which we can start the tour.

Sample Input

3
1 5
10 3
3 4
Sample Output

1
Explanation

We can start the tour from the second petrol pump.
 */



const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', function(inputStdin) {
    inputString += inputStdin;
});

process.stdin.on('end', function() {
    inputString = inputString.split('\n');

    main();
});

function readLine() {
    return inputString[currentLine++];
}

/*
 * Complete the 'truckTour' function below.
 * 
 * The function is expected to return an INTEGER. The function accepts
 * 2D_INTEGER_ARRAY petrolpumps as parameter.
 */

function truckTour(petrolpumps) {
    // Write your code here
    let d = petrolpumps.map((v, ind) =>[v[0] - v[1], ind])
    let res = -1
    for(let ind = 0; ind < d.length;){
        
        if(d[ind][0] >= 0){
            res = d[ind][1]
            let p = ind, t = 0
            while(p < d.length && t >= 0){
                t += d[p++][0]
            }
            ind = p
        }else{
            ind++
        }
    }

    return res
}

function main() {
    const ws = fs.createWriteStream(process.env.OUTPUT_PATH);

    const n = parseInt(readLine().trim(), 10);

    let petrolpumps = Array(n);

    for (let i = 0; i < n; i++) {
        petrolpumps[i] = readLine().replace(/\s+$/g, '').split(' ').map(petrolpumpsTemp => parseInt(petrolpumpsTemp, 10));
    }

    const result = truckTour(petrolpumps);

    ws.write(result + '\n');

    ws.end();
}

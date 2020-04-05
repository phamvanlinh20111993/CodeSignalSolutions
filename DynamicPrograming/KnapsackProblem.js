/**
 * 
 */

const data = [
  {weight: 1, value: 1},
  {weight: 2, value: 3},
  {weight: 3, value: 4},
  {weight: 4, value: 7},
  {weight: 5, value: 8},
  {weight: 6, value: 11},
  {weight: 7, value: 9},
  {weight: 8, value: 15},
  {weight: 9, value: 13},
  {weight: 13, value: 18},
  {weight: 16, value: 24},
  {weight: 18, value: 34}
  ]
  const backpackWeight = 17;

  //output: 31

/*const data = [
  {weight: 12, value: 4},
  {weight: 2, value: 2},
  {weight: 1, value: 1},
  {weight: 1, value: 2},
  {weight: 4, value: 10},
] 
  const backpackWeight = 15;

 // output:36
*/


/*const data = [
  {weight: 2, value: 1},
  {weight: 4, value: 10},
  {weight: 7, value: 10},
  {weight: 4, value: 7},
  {weight: 1, value: 2},
  {weight: 9, value: 12},
]
const backpackWeight = 15;
//output:24
*/

let arr;
backpackProblem = (data, backpack) =>{
  arr = new Array(backpack).fill(0)
  let r = dpGetMaxResult(data, backpack);
  console.log(r, arr)
}

dpGetMaxResult = (data, backpackWeight) => {
  if(backpackWeight <= 0) return 0
  if(arr[backpackWeight] > 0){
    return arr[backpackWeight]
  }
  let max = 0;
  for(let ind = 0; ind < data.length; ind++){
    if(backpackWeight - data[ind].weight >= 0 ){
      let tempResult = data[ind].value + dpGetMaxResult(data, backpackWeight - data[ind].weight);
      max = tempResult > max ? tempResult : max
    }
  }
  arr[backpackWeight] = max;
  return max;

}

backpackProblem(data, backpackWeight);
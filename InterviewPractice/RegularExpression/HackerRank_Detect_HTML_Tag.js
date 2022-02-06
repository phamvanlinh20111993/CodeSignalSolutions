/**
 * https://www.hackerrank.com/challenges/detect-html-tags/problem?isFullScreen=true
 */


function processData(input) {
    // Enter your code here
   const data = input.match(/<\w+\s*.*?>/g)
   
   let set = new Set()
   data.map(v => set.add(v.match(/^<(\w+).*>$/)[1]))
   
   const res = Array.from(set)
   .sort((a, b) => a.localeCompare(b))
   .join(";")
   
   console.log(res)
} 

process.stdin.resume();
process.stdin.setEncoding("ascii");
_input = "";
process.stdin.on("data", function (input) {
    _input += input;
});

process.stdin.on("end", function () {
   processData(_input);
});

/**
 * https://www.hackerrank.com/challenges/ide-identifying-comments/problem
 * Using *? for each get first boundary
 */
function processData(input) {
    // Enter your code here
   const data = input.match(/(?:\/\*\**(?:.|\n|\r\n)*?\**\*\/)|(?:\/\/[^\r\n]*)/g) || []
   data.map(v => {
    const formatV = v.split('\n').map(v => v.trim()).join('\n')
    console.log(formatV)
   })
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


/**
 * https://www.hackerrank.com/challenges/find-a-word/problem?isFullScreen=true
 */

function processData(input) {
    // Enter your code here
    /** * get input break to words and data input** */
    let data = input.split(/\n+/g)

    let words = data.slice(parseInt(parseInt(data[0]))+2)
    data = data.slice(1, parseInt(parseInt(data[0])+1))
    
   /** process it */
   words.map(word => {
       // const wordPattern = /(?<=(^|\W+))foo(?=($|\W+))/g
        const wordPattern = new RegExp('(?<=(^|\\W+))'+word+'(?=($|\\W+))', 'g')
       let count = 0
       data.map(sentence => {
        // console.log(sentence, sentence.match(wordPattern))
           count += (sentence.match(wordPattern)|| []).length
       })
       console.log(count)
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

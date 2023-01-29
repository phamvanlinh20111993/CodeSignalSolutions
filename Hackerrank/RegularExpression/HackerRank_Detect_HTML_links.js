/**
 * https://www.hackerrank.com/challenges/detect-html-links/problem?isFullScreen=true
 * 
 */

function processData(input) {
    // Enter your code here
    const data = input.match(/<a.*?>.*?<\/a>/g)
    let res = []
    data.map(v => {
        const matchs = v.match(/href=\"(.*?)\".*?>(.*)<\/a>/)   
        const formatLinkText = matchs[2]
        .trim()
        .replace(/<\/?\w+.*?\/?>/g, '')
     
        res.push(matchs[1].trim() + "," + formatLinkText)
    })
    
    res.map(v => console.log(v))
    
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


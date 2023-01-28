/**
 * https://www.hackerrank.com/challenges/html-attributes/problem?isFullScreen=true
 */
function processData(input) {
    // Enter your code here
    const headTag = input.match(/<\w+(?:\s+\w+=(?:\"|')[^\>]*?(?:\"|'))*?\s*\/?>/g)
    
   // console.log('headTag', headTag)
    const mapTagAttr = new Map()
    headTag.map(headT => {
        const tagName = headT.match(/\w+/)[0]
        const attr = headT.match(/(?<=\s)\w+?\s*(?==\s*(?:\"|'))/g) || []
       
        if(!mapTagAttr.has(tagName)) {
            mapTagAttr.set(tagName, new Set([...attr]))
        }else{
            const disAttr = mapTagAttr.get(tagName)
            attr.map(v => disAttr.add(v))
            mapTagAttr.set(tagName, disAttr)
        }
    })
    
    const mapSorted = new Map([...mapTagAttr.entries()]
    .sort((a, b) => a[0].localeCompare(b[0])))
    
    for (const [key, value] of mapSorted.entries()) {
        const sortAttr = Array.from(value)
        .sort((a, b) => a.localeCompare(b))
        .join(',')
        
        console.log(`${key}:${sortAttr}`)
    }
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

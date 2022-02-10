/**
 * https://www.hackerrank.com/challenges/detect-the-domain-name/problem?isFullScreen=true
 */

// the description like a shit, i have to change my solution
/*function processData(input) {
    // Enter your code here
	 const fragmentPattern = /<\w+(?:\s*\w+=(?:'|").*?(?:'|"))+\s*\/?>/g
	 const fragments = input.match(fragmentPattern)
	 console.log('fragmentPattern', fragments.length)
		    
	 const domains = []
     fragments.map(fragment => {
		 const data = fragment.match(/(?<=https?:\/\/)\w+(?:\.\w+)*/g) || []
/*		 data.map(dt => domains.push(dt))
	 } )
		    
	let uniqueDomains = new Set()
    domains.map(domain => {
		 uniqueDomains.add(domain.replace(/^\s*(www|ww2)\./, '').replace(/[^a-zA-Z0-9\.]*$/, ''))
	})
		    
    console.log('uniqueDomains', uniqueDomains.size)
		   
    console.log(Array.from(uniqueDomains)
	.sort()
	.join(';'))
}  */
		 
function processData(input) {
	// Enter your code here
	const domains = input.match(/(?<=https?:\/\/)[A-Za-z0-9\-]+(?:\.[A-Za-z0-9\-]+)+/g) || []
	let uniqueDomains = new Set()

	domains.map(domain => {
	    uniqueDomains.add(domain.replace(/^\s*(www|ww2)\./, ''))
	})

	console.log(Array.from(uniqueDomains)
	    .sort()
	    .join(';'))
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

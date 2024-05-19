/**
A quincunx is a tilted board with pegs forming a triangular shaped pattern. Balls are dropped from the top, where they strike a peg and is redirected either to the left or to the right. At the very bottom, there are holes where the balls eventually end up.

    o
   o o
  o o o
 o o o o
= = = = =

With advances in nanotechnology, a quincunx creator managed to perfectly rig a board so that its pegs are guaranteed to split balls in any percentage he specifies.

For example, if there were only one peg and two holes (one to the left and one to the right), and the rigging is 10% (left) and 90% (right), then, out of 10 balls, 1 would end up in the left hole, and 9 in the right hole.

Given the left-hand percentages of pegs on the board as values from 0 to 100 (the right-hand side is simply 100 minus the left-hand side), starting from the top, then from left to right, return an array representing how many balls will fall into the holes.

In every case, there are a 1,000,000 (one million) balls being dropped from the top.

Example:

Consider the list of percentages [10, 30, 50, 40, 10, 70].

As this list represents 6 pegs, the board must have three levels of pegs (rows of 1, 2, and 3 pegs) and 4 holes. The percentage of balls that fall to the left are shown below:

      10
    30  50
  40  10  70
==  ==  ==  ==

Let's name the pegs A to F, and the holes, 0 to 3:

      A
    B   C
  D   E   F
0   1   2   3

Working by hand, out of the 1,000,000 balls starting from the top, 100,000 go to the left, and 900,000 go to the right.

Then, out of the 100,000 that strike B, 30%, or 30,000, go towards D. 70,000 go towards E.

Out of the 900,000 striking C, 50%, or 450,000, go towards E, and 450,000 go towards F.

E has a total of 520,000 balls going towards it.

In the final level, 40% of D (12,000) end up in hole 0.

60% of D (18,000) and 10% of E (52,000) end up in hole 1 (70,000)

90% of E (468,000) and 70% of F (315,000) go inside hole 2 (783,000)

Finally, 30% of F (135,000) end up in hole 3.

Return [12000, 70000, 783000, 135000]

    [execution time limit] 4 seconds (js)

    [memory limit] 1 GB

    [input] array.integer ps

    Percentage of balls hitting this peg that will go towards the left.

    [output] array.integer

    Total number of balls falling into each hole.

*/

/*
// solution 1, using tree
solution = p => {
    let a = new Array(p.length),
        q = [{l: 1, v : 0}]
    
    while(true){
        let d = q.shift(),
        left = {l: d.l+1, v: d.v + d.l},
        right = {l: d.l+1, v: d.v + d.l + 1}
        
        if(d.v >= p.length) break
        
        a[d.v] = [left, right]
        q.push(left)
        q.push(right)
    }
    
    a = a.map(v => [0, v[0].v, v[1].v])
    a[0][0] = 1000000
    // n*(n-1)/2 = k
    const calN = (1 + Math.sqrt(1 + 8*p.length))/2 - 1
    for(let i = 0; i <= calN; i++){
        a.push([0])
    }
    
    q = [0]
    let v = new Set()
    while(true){
        const da = q.shift()
        if(da >= p.length) break
        const cl = a[da][1],
              cr = a[da][2]

        a[cl][0] += a[da][0] * p[da]/100
        a[cr][0] += a[da][0] * (1 - p[da]/100)
        
        if(!v.has(cl)) {
            q.push(cl)
            v.add(cl)
        }
      
        if(!v.has(cr)) {
            q.push(cr)
            v.add(cr)
        }
    }
    a.map((v, i) => console.log(i, v))
    
    let r = []
    for(let ind = a.length - calN - 1; ind < a.length; ind++){
       r.push(parseInt(a[ind][0]))
    }
    
    return  r
}

*/
// solution 2, using basic cal
solution = p => {
    let h = 1,
     // n*(n-1)/2 = k
     calN = (1 + Math.sqrt(1 + 8*p.length))/2 - 1,
     a = new Array(p.length+calN+1).fill(0)
    
    a[0] = 1000000
    let st = 0
    while(1){
        st += h
     //   console.log('border', st, st + h)
        if(st + h + 1 > a.length) break
        
        a[st] = a[st - h] * p[st - h]/100
     //   console.log('child of', st, 'is',st - h)
        a[st + h] = a[st-1] * (1 - p[st-1]/100)
      //  console.log('child of', st+h, 'is',st-1)
       
        for(let i = st + 1; i <= st + h - 1; i++){
        //    console.log(a[i], 'child of', i, 'is', i-h-1, 'and',  i-h)
            a[i] = a[i-h-1] * (1 - p[i-h-1]/100)
            a[i] += a[i-h] * p[i-h]/100
        }
        
        h++
    }
    
   // console.log(a)
    
    let r = []
    for(let ind = a.length - calN - 1; ind < a.length; ind++){
       r.push(parseInt(a[ind]))
    }
    
    return r
}
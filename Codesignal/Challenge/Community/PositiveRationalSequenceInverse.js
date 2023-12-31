/*
 * This is the second part of the PositiveRationalSequence problem that started here https://app.codesignal.com/challenge/3bZSgBdkpeEARDBuD

--- Here is the original description of the sequence that Herbert and Neil are constructing ---

Herbert and Neil are studying queues containing rational numbers. They have started with a queue that contains a single element 1 and repeatedly performing the following set of operations:

Herbert removes one element from the front of the queue and adds 1 to it. Neil copies Herbert's number and takes its reciprocal. Herbert puts his number to the back of the queue and then Neil puts his number to the back of the queue.

They continue the process indefinitely and at every step they record the number they are taking from the queue forming an infinite sequence.

For example, here are the first steps of the process:

Initially the queue is [1].

Herbert takes 1 out of the queue, so that the first element of the sequence is 1. Herbert adds 1 to it obtaining 2. Neil takes its reciprocal and gets 1/2. Herbert pushes 2 to the back of the queue and Neil pushes 1/2.

Now the queue has two elements: [2, 1/2].

Herbert takes the front element 2out of the queue, so that the second element of the sequence is 2. He adds 1 to it obtaining 3. Neil takes its reciprocal, thus getting 1/3. Herbert adds '3' to the back of the queue and Neil adds 1/3.

Now the queue has three elements: [1/2, 3, 1/3].

Herbert takes the front element 1/2 out of the queue, so that the third element of the sequence is 1/2. He adds 1 to it obtaining 3/2. Neil takes its reciprocal, thus getting 2/3. Herbert adds '3/2' to the back of the queue and Neil adds 2/3.

Now the queue has three elements: [3, 1/3, 3/2, 2/3].

One can see that the first elements of the sequence formed by the numbers taken out of the queue will be 1, 2, 1/2, 3, 1/3, 3/2, 2/3.

--- End of the original sequence description ---

In the previous problem you were asked to write a function that for a given positive integer n returns the [numerator, denominator] pair of the nth element of sequence.

Now Herbert and Neil would like to have a function that does the reverse. Given a positive rational number fraction represented as a pair of positive integers [numerator, denominator], return the index of the sequence element which is equal to fraction. Since the value of the result can be quite large, please return the result modulo the value of modulus supplied as a second argument.

In a case when multiple elements of the sequence are equal to the input fraction, return the smallest index of such element.

In a case when no such index can be found, i.e. no element of the sequence equals to the input fraction, return -1.

For example:

With fraction = [1, 1] and modulus = 7 the result should be solution(fraction) = 1

With fraction = [1, 2] and modulus = 7 the result should be solution(fraction) = 3

With fraction = [2, 3] and modulus = 7 the result should be solution(fraction) = 0

[execution time limit] 4 seconds (js)

[memory limit] 1 GB

[input] array.integer fraction

A pair of positive integers [numerator, denominator] representing a positive rational number.

Guaranteed constraints:

fraction.length = 2
1 <= fraction[0] <= 10^9
1 <= fraction[1] <= 10^9

[input] integer modulus

The value of modulus, modulo which the output value should be computed.

Guaranteed constrains:

2 <= modulus <= 10^9

[output] integer

The smallest index taken modulo modulus of an element of the described sequence whose value equals to that of fraction or -1 if no such element exists.
 * 
 */

/**
 **) Example for: 6/29
 *  6 29 => n-1
	23 6 => (n-1)/2
	17 6 => (n-1)/4
	11 6 => (n-1)/8
	5 6 => (n-1)/16
	1 5 => ((n-1)/16 - 1)/2
	4 1 => (((n-1)/16 - 1)/2 - 1)/2
	3 1 => (((n-1)/16 - 1)/2 - 1)/4
	2 1 => (((n-1)/16 - 1)/2 - 1)/8
	1 1 => (((n-1)/16 - 1)/2 - 1)/16
	
=> (((n-1)/16 - 1)/2 - 1)/16 = 1, n = 561
=> [-1, 2, 4, 8, 16, -1, 2, -1, 2, 4, 8, 16] = [-1, 16, -1, 2, -1, 16]
                                                561 560  35 34  17 16
                                                
 **) Example for 

 */
solution = (f, m) => {
    let n = f[0], d = f[1], a = []
    while(true){
        if(n == d || n < 0 || d < 0) break
        if(n/d - 1 > 0){
            a.push(a.length == 0 || a[a.length -1] == -1 ? 2 : a[a.length -1] * 2)
            n = n - d
        }else{
            a.push(-1)
            a.push(2)
            let k = n
            n = d - k
            d = k
        }
    }
    
    let b = [], ind = 0
    while(ind < a.length){
        if(a[ind] > 0){
            let p = ind
            while(p < a.length && a[p] > 0) p++
            b.push(a[p-1])
            ind = ind == p ? ind+1 : p
        }else{
            b.push(a[ind])
            ind++
        }
    }
    
    let t = 1
    for(let ind = b.length -1; ind > -1; ind--){
        if(b[ind] < 0) t += 1
        else t *= b[ind]
        t = t%m
    }
    
    return t
}



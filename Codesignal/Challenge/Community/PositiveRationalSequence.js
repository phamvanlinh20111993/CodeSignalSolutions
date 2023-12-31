/**
 * Herbert and Neil are studying queues containing rational numbers. They have started with a queue that contains a single element 1 and repeatedly performing the following set of operations:

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

Since performing this process manually is slow, Herbert and Neil would like to have a function that for a positive integer n returns the nth element of the sequence they are constructing. The elements of the sequence are rational numbers and should be described by a pair of relatively prime integers representing the numerator and denominator of a reduced fraction. Integers should be represented by fractions having denominator equal to 1. Thus the function that you need to write needs to return an array of two integers, the first one being the numerator and the second one being the denominator of the nth element of the sequence.

For example:

With n = 1 the result should be solution(n) = [1, 1]

With n = 3 the result should be solution(n) = [1, 2]

With n = 7 the result should be solution(n) = [2, 3]

[execution time limit] 4 seconds (js)

[memory limit] 1 GB

[input] integer64 n

The index of the sequence element to be returned.

Guaranteed constraints:

1 <= n <= 10^16

[output] array.integer64

A list of two integers the first of which is the numerator and the second one is the denominator of the resulted fraction.
**/

/**
 * Analysis:
 * 
[1],
[2, 1/2], 1
[1/2, 3, 1/3], 1/2
[3, 1/3, 3/2, 2/3], 3
[1/3, 3/2, 2/3, 4, 1/4], 1/3
[3/2, 2/3, 4, 1/4, 4/3, 3/4], 3/2
[2/3, 4, 1/4, 4/3, 3/4, 5/2, 2/5], 2/3
[4, 1/4, 4/3, 3/4, 5/2, 2/5, 5/3, 3/5], 4
[1/4, 4/3, 3/4, 5/2, 2/5, 5/3, 3/5, 5, 1/5], 1/4
[4/3, 3/4, 5/2, 2/5, 5/3, 3/5, 5, 1/5, 5/4, 4/5], 4/3
[3/4, 5/2, 2/5, 5/3, 3/5, 5, 1/5, 5/4, 4/5, 7/3, 3/7], 3/4
[5/2, 2/5, 5/3, 3/5, 5, 1/5, 5/4, 4/5, 7/3, 3/7, 7/4, 4/7], 5/2
[2/5, 5/3, 3/5, 5, 1/5, 5/4, 4/5, 7/3, 3/7, 7/4, 4/7, 7/2, 2/7],


1, 2, 1/2, 3, 1/3, 3/2, 2/3, 4, 1/4, 4/3, 3/4, 5/2, 2/5, 5/3, 3/5, 5, 1/5, 5/4, 4/5, 7/3, 3/7, 7/4, 4/7, 7/2, 2/7
1, 2, 3    4,  5,   6,  7, ...


1 => 2,3
2, => 4,5
3, => 6,7
4, => 8,9
5 => 10, 11
6 => 12, 13


from n => n/2 if n is add odd number else (n/2 - 1)

 * 
 * 
 */


solution = n =>  {
    if(n == 1) return [1, 1]
	if(n == 2) return [2, 1]
	if(n == 3) return [1, 2]
	if(n > 3){
		if(n%2 == 0){
            let s = solution(n/2)
            return [s[0] + s[1], s[1]]
        } 
		if(n%2 != 0){
            let s = solution((n-1)/2)
            return [s[1], s[0] + s[1]]
        }
	}
}


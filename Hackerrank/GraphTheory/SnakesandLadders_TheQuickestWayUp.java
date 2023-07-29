package Hackerrank.GraphTheory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 
 * @author PhamLinh
 * @url https://www.hackerrank.com/challenges/the-quickest-way-up/problem?h_r=internal-search
 * 
 * Markov takes out his Snakes and Ladders game, stares at the board and wonders: "If I can always roll the die to whatever number I want, what would be the least number of rolls to reach the destination?"

Rules The game is played with a cubic die of  faces numbered  to .

Starting from square , land on square  with the exact roll of the die. If moving the number rolled would place the player beyond square , no move is made.

If a player lands at the base of a ladder, the player must climb the ladder. Ladders go up only.

If a player lands at the mouth of a snake, the player must go down the snake and come out through the tail. Snakes go down only.

Function Description

Complete the quickestWayUp function in the editor below. It should return an integer that represents the minimum number of moves required.

quickestWayUp has the following parameter(s):

ladders: a 2D integer array where each  contains the start and end cell numbers of a ladder
snakes: a 2D integer array where each  contains the start and end cell numbers of a snake
Input Format

The first line contains the number of tests, .

For each testcase:
- The first line contains , the number of ladders.
- Each of the next  lines contains two space-separated integers, the start and end of a ladder.
- The next line contains the integer , the number of snakes.
- Each of the next  lines contains two space-separated integers, the start and end of a snake.

Constraints



The board is always  with squares numbered  to .
Neither square  nor square  will be the starting point of a ladder or snake.
A square will have at most one endpoint from either a snake or a ladder.

Output Format

For each of the t test cases, print the least number of rolls to move from start to finish on a separate line. If there is no solution, print -1.

Sample Input

2
3
32 62
42 68
12 98
7
95 13
97 25
93 37
79 27
75 19
49 47
67 17
4
8 52
6 80
26 42
2 72
9
51 19
39 11
37 29
81 3
59 5
79 23
53 7
43 33
77 21 
Sample Output

3
5
Explanation

For the first test:

The player can roll a  and a  to land at square . There is a ladder to square . A roll of  ends the traverse in  rolls.

For the second test:

The player first rolls  and climbs the ladder to square . Three rolls of  get to square . A final roll of  lands on the target square in  total rolls.
 */
public class SnakesandLadders_TheQuickestWayUp {

    public static int bfs(Map<Integer, Integer> ladders, Map<Integer, Integer> snakes) {

	Queue<List<Integer>> queue = new LinkedList<>();

	queue.add(List.of(1, 0));

	while (queue.size() > 0) {

	    List<Integer> moveStep = queue.poll();
	    Integer step = moveStep.get(1);

	    // max step can happen with worst case is 100 / 6
	    if (step > 100 / 6 + 1) {
		return -1;
	    }

	    if (moveStep.get(0) == 100) {
		return moveStep.get(1);
	    }

	    // roll the dice
	    boolean isAdd = false;
	    for (int ind = 6; ind > 0; ind--) {
		int nextStep = ind + moveStep.get(0);

		if (nextStep > 100)
		    continue;

		if (ladders.containsKey(nextStep)) {
		    queue.add(List.of(ladders.get(nextStep), step + 1));
		} else if (snakes.containsKey(nextStep)) {
		    queue.add(List.of(snakes.get(nextStep), step + 1));
		} else {
		    if (!isAdd) {
			queue.add(List.of(nextStep, step + 1));
			isAdd = true;
		    }
		}
	    }

	}

	return -1;
    }

    /*
     * Complete the 'quickestWayUp' function below.
     *
     * The function is expected to return an INTEGER. The function accepts following
     * parameters: 1. 2D_INTEGER_ARRAY ladders 2. 2D_INTEGER_ARRAY snakes
     */

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {

	// Check the Consecutive coordinates of snakes is larger than 5, if true we
	// never win this game

	snakes = snakes.stream().sorted(new Comparator<List<Integer>>() {
	    @Override
	    public int compare(List<Integer> o1, List<Integer> o2) {
		return o1.get(0) - o2.get(0);
	    }

	}).collect(Collectors.toList());
	for (int ind = 0; ind < snakes.size() - 1;) {
	    int p = ind + 1, c = 1;
	    while (p < snakes.size() && (snakes.get(ind).get(0) + c) == snakes.get(p).get(0)) {
		p++;
		c++;
	    }

	    if (p - ind + 1 >= 6)
		return -1;

	    ind = p;
	}

	Map<Integer, Integer> ladderMap = new HashMap<Integer, Integer>(ladders.size());
	for (List<Integer> l : ladders)
	    ladderMap.put(l.get(0), l.get(1));

	Map<Integer, Integer> snakeMap = new HashMap<Integer, Integer>(snakes.size());
	for (List<Integer> s : snakes) {
	    snakeMap.put(s.get(0), s.get(1));
	}

	return bfs(ladderMap, snakeMap);
    }

    public static void main(String[] args) {
	System.out.println("################### test 1 ########################");
	List<List<Integer>> ladders = new ArrayList<>();
	ladders.add(List.of(32, 62));
	ladders.add(List.of(42, 68));
	ladders.add(List.of(12, 98));

	List<List<Integer>> snakes = new ArrayList<>();
	snakes.add(List.of(95, 13));
	snakes.add(List.of(97, 25));
	snakes.add(List.of(93, 37));
	snakes.add(List.of(79, 27));
	snakes.add(List.of(75, 19));
	snakes.add(List.of(49, 47));
	snakes.add(List.of(67, 17));

	System.out.println(quickestWayUp(ladders, snakes) == 3);

	System.out.println("################### test 2 ########################");
	List<List<Integer>> ladders1 = new ArrayList<>();
	ladders1.add(List.of(8, 52));
	ladders1.add(List.of(6, 80));
	ladders1.add(List.of(26, 42));
	ladders1.add(List.of(2, 72));

	List<List<Integer>> snakes1 = new ArrayList<>();
	snakes1.add(List.of(51, 19));
	snakes1.add(List.of(39, 11));
	snakes1.add(List.of(37, 29));
	snakes1.add(List.of(81, 3));
	snakes1.add(List.of(59, 5));
	snakes1.add(List.of(79, 23));
	snakes1.add(List.of(53, 7));
	snakes1.add(List.of(53, 7));
	snakes1.add(List.of(43, 33));
	snakes1.add(List.of(77, 21));

	System.out.println(quickestWayUp(ladders1, snakes1) == 5);

    }

}

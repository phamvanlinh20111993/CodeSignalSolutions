package Arcade;

import java.util.ArrayList;
import java.util.LinkedList;

public class QuantumLabBooking {

	static int quantumLabBooking(int[][] friends, int firstInLine, int k) {

		int i = firstInLine, j, temp = 0;
		ArrayList<Integer> Queue = new ArrayList<>();
		
		Queue.add(i);
		
		while (temp <= k) {
			if (Queue.size() < 1) {
				i = -1;
				break;
			}
			i = Queue.get(0);
			Queue.remove(0);
			
			System.out.print(i  + "    ");
			
			for (j = 0; j < friends[i].length; j++) {
				if (friends[i][j] > 0) {
					Queue.add(j);
				}
			}

			temp++;
		}
		
		System.out.println();

		return i;
	}
	
	static int quantumLabBooking1(int[][] friends, int firstInLine, int k) {

		int i = firstInLine, j, temp = 0;
		
		LinkedList<Integer> Queue = new LinkedList<Integer>();
		
		Queue.add(i);
		
		while (temp <= k) {
			if (Queue.size() < 1) {
				i = -1;
				break;
			}
			i = Queue.get(0);
			Queue.remove(0);
			
			System.out.print(i  + "    ");
			
			for (j = 0; j < friends[i].length; j++) {
				if (friends[i][j] > 0) {
					Queue.add(j);
				}
			}

			temp++;
		}
		
		System.out.println();

		return i;
	}

	public static void main(String[] args) {
		System.out.println("############### test 1 ####################");
		int [][] friends = {{1,1,1}, 
		                    {1,0,0}, 
		                    {0,1,1}};
		int firstInLine = 1, k = 10;
		System.out.println(quantumLabBooking1(friends, firstInLine, k));
		
	}

}

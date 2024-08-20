package Hackerrank.Greedy;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LargestPermutation {
	
	 // test
    //       10 7
    //       9 10 1 6 4 3 8 2 5 7
    // outp: 10 9 8 7 6 5 4 2 3 1
    
    // test2
    //       7 4
    //       1 7 5 6 3 4 2
    // outp: 7 6 5 4 3 1 2
    
    // test3
    //       12 7
    //       9 10 1 6 4 3 8 2 5 7 11 12
    // outp: 12 11 10 9 8 7 6 2 5 3 1 4 
	public static List<Integer> largestPermutation(int k, List<Integer> arr) {
		Map<Integer, Integer> keepIndexes = new HashMap<>();
		for (int ind = 0; ind < arr.size(); ind++) {
			// keep key-value, value-array's index value
			keepIndexes.put(arr.get(ind), ind);
		}

		int max = arr.size(), count = 0;
		for (int ind = 0; ind < arr.size(); ind++) {
			if (count >= k)
				break;
			Integer val = arr.get(ind);
			if (!val.equals(max)) {
				arr.set(ind, max);
				arr.set(keepIndexes.get(max), val);

				keepIndexes.replace(val, keepIndexes.get(max));
				keepIndexes.replace(max, ind);
				count++;
			}

			max--;
		}

		return arr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("<your input txt path>"));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("<your output txt path>"));

		String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

		int n = Integer.parseInt(firstMultipleInput[0]);

		int k = Integer.parseInt(firstMultipleInput[1]);

		List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
				.map(Integer::parseInt).collect(toList());

		List<Integer> result = LargestPermutation.largestPermutation(k, arr);

		bufferedWriter.write(result.stream().map(Object::toString).collect(joining(" ")) + "\n");

		bufferedReader.close();
		bufferedWriter.close();

	}

}

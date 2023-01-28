package Codesignal.CompanyChallenge.PureStorage;

/**
 * https://app.codesignal.com/company-challenges/purestorage/vfaESus49J4N9JHim
 * @author PhamLinh
 * 
 * When migrating data from a source storage system to a target storage system, the number one focus is avoiding data corruption at all cost. In order to meet these high standards, various rounds of tests are run checking for corrupted blocks as well as successfully migrated lengthy regions.
We are going to represent the source storage system and target storage system as sequential arrays sourceArray and destinationArray respectively, where sourceArray[i] represents binary data of the ith source block as an integer, and destinationArray[i] represents binary data of the ith destination block, where the data should be migrated, also as an integer. Given the content of the source and the migrated content of the target, find the length and the starting block of the longest uncorrupted data segment (segment = subsequent blocks).

If there is no uncorrupted segment, return an array containing 0 and 0 respectively. If there are multiple longest uncorrupted segments, return the one with the minimum value of its left border (the one that's present earlier in the source array).

Example

For sourceArray = [33531593, 96933415, 28506400, 39457872, 29684716, 86010806] and destinationArray = [33531593, 96913415, 28506400, 39457872, 29684716, 86010806], the output should be
solution(sourceArray, destinationArray) = [4, 2].

The only corrupted element is located by index 1, where sourceArray[1] = 96933415 != 96913415 = destinationArray[1], all other data blocks were transfered uncorrupted. So the longest uncorrupted segment starts on second index and goes to the end of the array, thus having length 4.

Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer sourceArray

The source data as sequential array.

Guaranteed constraints:
1 ≤ sourceArray.length ≤ 105,
107 ≤ sourceArray[i] < 108.

[input] array.integer destinationArray

The migrated data as sequential array.

Guaranteed constraints:
destinationArray.length = sourceArray.length,
107 ≤ destinationArray[i] < 108.

[output] array.integer

An array of two elements - first is the length of the longest uncorrupted data segment and second is the starting block.
 *
 */

public class LongestUncorruptedSegment {

	int[] solution(int[] sourceArray, int[] destinationArray) {
	    int [] res = {0, 0};
	    int max = 0, j, i;
	    
	    for(i = 0; i < sourceArray.length;){
	        j = i;
	        while(j < sourceArray.length && destinationArray[j] == sourceArray[j]) j++;
	        
	        if(max < j - i ){
	            max = j - i;
	            res[0] = j - i;
	            res[1] = i;
	        }
	        
	        i = j + 1;
	    }
	    
	    return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

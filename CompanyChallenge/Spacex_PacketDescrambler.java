package CompanyChallenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Spacex_PacketDescrambler {

	static String packetDescrambler(int[] seq, char[] fragmentData, int n) {
		
		Map<Integer, List<Character>> mapFragmentData = new TreeMap<>();
		Map<Character, Integer> loopFrament = new HashMap<>();
		List<Character> frData;
		String result = "";
		int i, j, maxSize;
		boolean isBackupSuccess = true;
		
		for(i = 0; i < seq.length; i++) {
			if(!mapFragmentData.containsKey(seq[i])) {
				frData = new ArrayList<>();
				frData.add(fragmentData[i]);
			}else {
				frData = mapFragmentData.get(seq[i]);
				frData.add(fragmentData[i]);
			}
			mapFragmentData.put(seq[i], frData);
		}
		
		maxSize = mapFragmentData.size();
		i = 0;
		for(Integer sequenceNum : mapFragmentData.keySet()) {
			if(sequenceNum != i ) {
				isBackupSuccess = false;
				break;
			}
			
			frData = mapFragmentData.get(sequenceNum);
			
			for(j = 0; j < frData.size(); j++) {
				if(!loopFrament.containsKey(frData.get(j))) {
					loopFrament.put(frData.get(j), 1);
				}else {
					loopFrament.put(frData.get(j), loopFrament.get(frData.get(j))+1);
				}
			}
			
			boolean isGood = false;
			for(j = 0; j < frData.size(); j++) {
				if(i == maxSize - 1) {
					if( frData.get(j) == '#' && (float)loopFrament.get(frData.get(j)) / n > 0.5) {
						result += "#";
						isGood = true;
						break;
					}
				}else {
					if( frData.get(j) == '#' && (float)loopFrament.get(frData.get(j)) / n > 0.5) {
						break;
					}
					if((float)loopFrament.get(frData.get(j)) / n > 0.5) {
						result += frData.get(j);
						isGood = true;
						break;
					}
				}
			}
			
			if(!isGood) {
				isBackupSuccess = false;
				break;
			}
			
			loopFrament.clear();
			i++;
		}

		return isBackupSuccess == true ? result : "";
	}

	public static void main(String[] args) {

		// A+#
		System.out.println("############ Test 1 ################");
		int[] seq = { 1, 1, 0, 0, 0, 2, 2, 2 };
		char[] fragmentData = { '+', '+', 'A', 'A', 'B', '#', '#', '#' };
		int n = 3;
		packetDescrambler(seq, fragmentData, n);

		// null
		System.out.println("################## Test 2 ##################");
		int[] seq1 = { 1, 1, 0, 0, 0, 2, 2, 2 };
		char[] fragmentData1 = { '+', '+', 'A', 'A', 'B', '#', '#', '#' };
		int n1 = 4;
		packetDescrambler(seq1, fragmentData1, n1);

		// null
		System.out.println("################# Test 3 #####################");
		int[] seq2 = { 1, 1, 2, 2, 2, 0, 0, 0 };
		char[] fragmentData2 = { '+', '+', 'A', 'A', 'B', '#', '#', '#' };
		int n2 = 3;
		packetDescrambler(seq2, fragmentData2, n2);
		System.out.println("################# Test 3 #####################");
		
		// ???#
		System.out.println("################### Test 9 ####################");
		int[] seq3 = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3 };
		char[] fragmentData3 = { '#', '?', '?', '?', '?', '?', '?', '?', '#', '?', '#', '#' };
		int n3 = 3;
		packetDescrambler(seq3, fragmentData3, n3);
	}

}

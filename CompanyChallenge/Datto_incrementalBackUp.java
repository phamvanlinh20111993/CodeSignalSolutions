package CompanyChallenge;

import java.util.Iterator;
import java.util.TreeSet;

public class Datto_incrementalBackUp {
	
	static int[] incrementalBackups(int lastBackupTime, int[][] changes) {
		TreeSet<Integer> ts = new TreeSet<>();
		int i;
		for(i = 0; i < changes.length; i++) {
			if(lastBackupTime < changes[i][0])
				ts.add(changes[i][1]);
		}
		
		int[] arr =  new int[ts.size()];
		i = 0;
		Iterator<Integer> temp = ts.iterator();
		while(temp.hasNext()) {
			arr[i] = temp.next();
			i++;
		}
		
	    return arr;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

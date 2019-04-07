package Challenge;

import java.util.HashMap;
import java.util.Map;

public class PlayListLongestInterval {
	static int convertTimeToSeconds(String time) {
		String[] splitTime = time.split(":");
		return Integer.parseInt(splitTime[0].substring(1, splitTime[0].length())) * 60
				+ Integer.parseInt(splitTime[1].substring(0, splitTime[1].length() - 1));
	}

	static int playlistLongestInterval(String[] songs) {
		int total = 0, index = 0, max = 0, time = 0, posNext = -1;
		Map<String, Integer> mapInd = new HashMap<>();
		int[] times = new int[songs.length];

		for (; index < songs.length; index++) {
			String[] getTime = songs[index].split(" ");
			time += convertTimeToSeconds(getTime[1]);
			times[index] = time;

			if (mapInd.get(songs[index]) == null) {
				mapInd.put(songs[index], index);
			} else {
				max = times[index - 1] - (posNext > -1 ? times[posNext] : 0);
				posNext = posNext < mapInd.get(songs[index]) ? mapInd.get(songs[index]) : posNext;
				if (total < max)
					total = max;

			//	System.out.println(getTime[0] + "  " + max + " ( " + times[index - 1] + " , " + posNext + ")");
				mapInd.put(songs[index], index);
			}
		}
		
		max = times[index - 1] - (posNext > -1 ? times[posNext] : 0);
	    
        if (total < max)
		total = max;

		System.out.println();
		for (index = 0; index < songs.length; index++)
			System.out.print(times[index] + "  ");

		System.out.println("\n" + total);

		return total;
	}

	public static void main(String[] args) {
		System.out.println("############### Test 1 ####################");
		String[] songs = { "HEOIG (1:52)", "F (9:24)", "IXDK (0:42)", "F (9:24)", "D (2:11)", "HEOIG (1:52)",
				"IXDK (0:42)", "GEAA (2:19)", "D (2:11)", "IDNQ (9:10)", "VNWBLVNUEZ (0:13)", "UHHZILNA (9:47)",
				"UZVZ (5:42)", "IXDK (0:42)", "VNWBLVNUEZ (0:13)", "LY (2:48)", "UZVZ (5:42)", "IDNQ (9:10)",
				"G (3:02)", "G (3:02)", "IYW (4:26)", "UHHZILNA (9:47)", "E (4:05)", "QNYZXPC (0:59)", "UZVZ (5:42)" };
		playlistLongestInterval(songs);

		System.out.println("############### Test 2 ####################");
		String[] songs1 = { "V (5:13)", "V (5:13)", "L (7:32)", "A (6:43)", "A (6:43)", "A (6:43)", "X (1:02)",
				"B (2:05)", "X (1:02)", "A (6:43)", "X (1:02)", "V (5:13)", "X (1:02)", "R (3:27)", "R (3:27)" };
		playlistLongestInterval(songs1);

		System.out.println("############### Test 3 ####################");
		String[] songs2 = { "F (8:26)", "W (8:35)", "F (8:26)", "A (0:59)", "F (8:26)" };
		playlistLongestInterval(songs2);
	}

}
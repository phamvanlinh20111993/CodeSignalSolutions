/**
 * 
 */
package Codesignal.CompanyChallenge.Kik;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PhamVanLinh
 *
 */
public class Kik_KikCode {

	static final int[] CIRCUMFERENCE = { 3, 4, 8, 10, 12, 15 };
	static final int totalBits = 52;

	/**
	 * 
	 * @param sectorBit
	 * @return
	 */
	static boolean checkSectorCoversAllSegments(int[] sectorBit) {
		for (int i = 0; i < sectorBit.length; i++)
			if (sectorBit[i] == 0)
				return false;
		return true;
	}

	/**
	 * 
	 * @param sectorBit
	 * @return
	 */
	static boolean checkPolarCoordsSysStartPoint(int[] sectorBit) {
		String sectorBitStr = "";
		for (int v : sectorBit)
			sectorBitStr += v | 0;

		return sectorBitStr.matches("^1+0+1+$");
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	static List<Integer> convertStringToBit(long n) {
		List<Integer> res = new ArrayList<Integer>();
		while (n > 0) {
			res.add((int) (n % 2));
			n >>= 1;
		}

		int pos = res.size();
		if (pos < totalBits) {
			while (pos++ < totalBits)
				res.add(0);
		}
		// for(int i = 0; i < res.size(); i++) { System.out.print(res.get(i)); }
		// System.out.println();

		return res;
	}

	/**
	 * 
	 * @param CircumFerenceList
	 * @return
	 */
	static List<int[]> spitToCircumFerence(List<Integer> circumFerenceList) {
		List<int[]> res = new ArrayList<>();
		int pos = 0;

		for (int i = 0; i < CIRCUMFERENCE.length; i++) {
			int[] child = new int[CIRCUMFERENCE[i]];
			for (int j = pos; j < CIRCUMFERENCE[i] + pos; j++)
				child[j - pos] = circumFerenceList.get(j);
			pos += CIRCUMFERENCE[i];
			res.add(child);
		}
		// for(int i = 0; i < res.size(); i++) { for(int j = 0; j < res.get(i).length;
		// j++) System.out.print(res.get(i)[j]); System.out.print(" "); }
		// System.out.println();

		return res;
	}

	/**
	 * 
	 * @param listBitMap
	 * @return
	 */
	static List<int[]> convertBitMapToCoordinates(int[] listBitMap) {
		List<int[]> res = new ArrayList<>();
		if (checkSectorCoversAllSegments(listBitMap)) {
			res.add(new int[] { 0, 360 });
		} else if (checkPolarCoordsSysStartPoint(listBitMap)) {
			int bit1Length = 0, startPoint = 0, each = 360 / listBitMap.length;
			for (int v : listBitMap) {
				// System.out.print(v);
				if (v == 1)
					bit1Length++;
				else
					startPoint = (bit1Length + 1) * each;
			}
			res.add(new int[] { startPoint, startPoint + each * bit1Length });
		} else {
			int startCord = 0, each = 360 / listBitMap.length, runtime = 0;
			boolean contiguous = false;
			for (int bit : listBitMap) {
				// System.out.print(bit);
				if (bit == 0) {
					if (contiguous)
						res.add(new int[] { startCord, runtime });
					contiguous = false;
				} else {
					if (!contiguous)
						startCord = runtime;
					contiguous = true;
				}
				runtime += each;
			}
			if (contiguous) {
				res.add(new int[] { startCord, runtime });
			}
			// System.out.print(" ");
		}
		// for (int i = 0; i < res.size(); i++) {
		// for (int j = 0; j < res.get(i).length; j++)
		// System.out.print(res.get(i)[j] + " ");
		// System.out.print(" / ");
		// }
		// System.out.println();

		return res;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	static int[][][] kikCode(String userId) {
		long userIdNumber = Long.parseLong(userId);
		List<int[][]> res = new ArrayList<>();
		List<int[]> circumFerence = spitToCircumFerence(convertStringToBit(userIdNumber));
		int index = 1;
		for (int[] bitMap : circumFerence) {
			List<int[]> data = convertBitMapToCoordinates(bitMap);
			for (int[] coord : data) {
				int[][] coords = new int[2][2];
				coords[0][0] = coords[1][0] = index;
				
				coords[0][1] = coord[0];
				coords[1][1] = coord[1];
				res.add(coords);
			}
			index++;
				
		}
		
		for (int[][] vroot : res) {
			for (int[] vchild : vroot) {
				System.out.print(vchild[0] + ", " + vchild[1] + " | ");
			}
			System.out.println();
		}
		return res.toArray(new int[res.size()][][]);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * {{{1,0},{1,120}}, {{2,270},{2,540}}, {{3,45},{3,135}}, {{3,180},{3,225}},
		 * {{3,270},{3,360}}, {{4,0},{4,72}}, {{4,108},{4,180}}, {{4,216},{4,252}},
		 * {{4,288},{4,324}}, {{5,0},{5,360}}, {{6,0},{6,48}}, {{6,72},{6,120}},
		 * {{6,168},{6,192}}, {{6,240},{6,264}}, {{6,288},{6,336}}}
		 */
		System.out.println("############################## test 1 ###############################");
		kikCode("1851027803204441");
		/*
		 * []
		 */
		System.out.println("############################## test 2 ###############################");
		kikCode("0");
		/**
		 * [[[1,0],[1,360]], [[2,0],[2,360]], [[3,0],[3,360]], [[4,0],[4,360]],
		 * [[5,0],[5,360]], [[6,0],[6,360]]]
		 */
		System.out.println("############################## test 3 ###############################");
		kikCode("4503599627370495");
		/**
		 * [[[1,0],[1,240]], [[2,0],[2,90]], [[3,45],[3,90]], [[3,135],[3,225]],
		 * [[3,270],[3,360]], [[4,36],[4,144]], [[4,180],[4,216]], [[4,324],[4,360]],
		 * [[5,90],[5,120]], [[5,180],[5,210]], [[5,240],[5,270]], [[5,330],[5,360]],
		 * [[6,48],[6,192]], [[6,216],[6,240]], [[6,312],[6,336]]]
		 */
		System.out.println("############################## test 4 ###############################");
		kikCode("1230983010938123");
		
		System.out.println("############################## test custom ###############################");
		kikCode("12312");
	}

}

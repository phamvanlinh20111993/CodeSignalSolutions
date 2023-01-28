package Codesignal.CompanyChallenge.Datto;

/**
 * 
 * 
 * dich de cho t
 * 
 * @author PhamVanLinh
 *
 */
public class Datto_troubleFiles {

	static final String _SPLIT = "###";

	static int[] troubleFiles(int[][] files, int[] backups) {

		int[] result = new int[backups.length];
		int i, j;

		return result;
	}

	public static void main(String[] args) {

		// [2, 0, 0, 0, 1]
		System.out.println("########### test 1 #############");
		int[][] files = { { 461618501, 3 }, 
				{ 461618502, 1 }, 
				{ 461618504, 2 }, 
				{ 461618506, 5 }, 
				{ 461618507, 6 } };
		int[] backups = { 461618501, 461618502, 461618504, 461618505, 461618506 };
		troubleFiles(files, backups);

		// [0, 0, 0, 0, 0]
		System.out.println("########### test 2 #############");
		int[][] files1 = { { 461618504, 2 } };
		int[] backups1 = { 461618501, 461618502, 461618504, 461618505, 461618506 };
		troubleFiles(files1, backups1);

		// [2]
		System.out.println("########### test 3 #############");
		int[][] files2 = { { 461618501, 3 }, 
				{ 461618502, 1 }, 
				{ 461618504, 2 }, 
				{ 461618506, 5 }, 
				{ 461618507, 6 } };
		int[] backups2 = { 461618504 };
		troubleFiles(files2, backups2);
		
		//[2, 0]
		System.out.println("########### test 4 #############");
		int[][] files3 = { { 461618501,3}, 
				 {461618502,1}, 
				 {461618504,2}, 
				 {461618506,5}, 
				 {461618507,6}, 
				 {461618509,1}, 
				 {461618509,4}, 
				 {461618599,4 } };
		int[] backups3 = { 461618502, 461618509 };
		troubleFiles(files3, backups3);
		
		//[2, 0]
		System.out.println("########### test 5 #############");
		int[][] files4 = { { 461618500,7}, 
				 {461618505,9}, 
				 {461618510,4}, 
				 {461618515,5}, 
				 {461618518,9 } };
		int[] backups4 = { 461618504, 461618509 };
		troubleFiles(files4, backups4);

	}

}

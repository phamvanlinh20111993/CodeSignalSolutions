package Codesignal.CompanyChallenge.Ascend;

public class Ascend_GroupingPipelineNodes {

	static boolean groupingPipelineNodes(int n, int[][] g, int[] v) {
		return false;

	}

	public static void main(String[] args) {
		
		System.out.println("########### test 1 ################");
		int n = 6;
		int[][] g = { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 4, 6 }, { 5, 6 } };
		int[] v = { 4, 5 };
		groupingPipelineNodes(n, g, v);

		System.out.println("########### test 2 ################");
		int n1 = 6;
		int[][] g1 = { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 4, 6 }, { 5, 6 } };
		int[] v1 = { 4, 5 };
		groupingPipelineNodes(n1, g1, v1);

		System.out.println("########### test 3 ################");
		int n2 = 6;
		int[][] g2 = { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 4, 6 }, { 5, 6 } };
		int[] v2 = { 1, 2, 4 };
		groupingPipelineNodes(n2, g2, v2);
	}

}

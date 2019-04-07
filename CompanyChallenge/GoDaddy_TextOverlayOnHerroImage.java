package CompanyChallenge;

public class GoDaddy_TextOverlayOnHerroImage {
	
	static int[] textOverlayOnHeroImage(final int[][] image, int height, int width) {
		  class Helper {

		    int columnSum(int x, int y1, int y2) {
		      int result = 0;
		      for (int y = y1; y < y2; y++) {
		        result += image[y][x];
		      }
		      return result;
		    };

		    int rowSum(int y, int x1, int x2) {
		      int result = 0;
		      for (int x = x1; x < x2; x++) {
		        result += image[y][x];
		      }
		      return result;
		    }
		  };

		  int bestSum = -1;
		  int sum = 0;
		  int lastRowLeftmostSum = 0;
		  int[] bestPos = null;

		  Helper h = new Helper();

		  for (int i = 0; i + height <= image.length; i++) {
		    for (int j = 0; j + width <= image[0].length; j++) {
		      if (i == 0 && j == 0) {
		        for (int y = 0; y < height; y++) {
		          sum += h.rowSum(y, 0, width);
		        }
		        lastRowLeftmostSum = sum;
		        System.out.println("sum is  " + sum);
		      }
		      else if (j == 0) {
		       // ... code im add in this problem
		    	  sum = lastRowLeftmostSum - h.rowSum(i - 1, j, j + width)
		    			    + h.rowSum(i + height - 1, j, j + width);
		    	  lastRowLeftmostSum = sum;
		      }
		      else {
		        sum = sum - h.columnSum(j - 1, i, i + height)
		                  + h.columnSum(j + width - 1, i, i + height);
		      }
		      if (sum > bestSum) {
		        bestSum = sum;
		        bestPos = new int[] {i, j};
		      }
		    }
		  }
		  System.out.println( bestPos[0] + ", " + bestPos[1]);
		  return bestPos;
		}

	
	public static void main(String[] args) {
		
		int [][] image = {{10,  50,  90, 65},
		                  {10, 200, 255, 30},
		                  {10, 150,  30, 25}};
		int height = 2;
		int width = 3;
		textOverlayOnHeroImage(image, height, width);
		
	}

}

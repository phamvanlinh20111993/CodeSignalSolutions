package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * url: https://leetcode.com/problems/image-overlap/description/?envType=daily-question&envId=2026-09-13
 * 
 * You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.

We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.

Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.

Return the largest possible overlap.

 

Example 1:


Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
Output: 3
Explanation: We translate img1 to right by 1 unit and down by 1 unit.

The number of positions that have a 1 in both images is 3 (shown in red).

Example 2:

Input: img1 = [[1]], img2 = [[1]]
Output: 1
Example 3:

Input: img1 = [[0]], img2 = [[0]]
Output: 0
 

Constraints:

n == img1.length == img1[i].length
n == img2.length == img2[i].length
1 <= n <= 30
img1[i][j] is either 0 or 1.
img2[i][j] is either 0 or 1.
 */
public class ImageOverlap_835 {
	
	  record C(int x, int y){}

	    // refer: https://leetcode.com/problems/image-overlap/solutions/8518373/4ms-beats-9865-easy-approach-and-step-by-81pb/?envType=daily-question&envId=2026-09-13
	    // i read the idea and have implement by myself, the solution may differ.
	    // solution quite simple but i cant not find it by myself. :(((
	    /*
	    public int largestOverlap(int[][] img1, int[][] img2) {
	        int l = img2.length;
	        List<C> img21s = new ArrayList<>();
	        Set<C> s21 = new HashSet<>();
	        for(int y = 0; y < l; y++){
	            for(int x = 0; x < l; x++){
	                if(img2[y][x] == 1){
	                    C c = new C(x, y); 
	                    img21s.add(c);
	                    s21.add(c);
	                }
	            }
	        }

	        List<C> img11s = new ArrayList<>();
	        for(int y = 0; y < l; y++){
	            for(int x = 0; x < l; x++){
	                if(img1[y][x] == 1){
	                    img11s.add(new C(x, y));
	                }
	            }
	        }

	        int max = 0;
	        for(C c2 : img21s){
	            int dx, dy;
	            for(C c1 : img11s){
	                dx = c2.x - c1.x;
	                dy = c2.y - c1.y;
	                int currentC = 0;
	                for(C c1T : img11s){
	                    C newC = new C(c1T.x + dx, c1T.y + dy);
	                    if(s21.contains(newC)){
	                       currentC++;
	                    }
	                }
	                max = Math.max(max, currentC);
	            }
	        }

	    
	        return max;
	    } */

		public int largestOverlap(int[][] img1, int[][] img2) {
			int l = img2.length;
			List<C> img21s = new ArrayList<>();
			Set<C> s21 = new HashSet<>();
			for (int y = 0; y < l; y++) {
				for (int x = 0; x < l; x++) {
					if (img2[y][x] == 1) {
						C c = new C(x, y);
						img21s.add(c);
						s21.add(c);
					}
				}
			}

			List<C> img11s = new ArrayList<>();
			for (int y = 0; y < l; y++) {
				for (int x = 0; x < l; x++) {
					if (img1[y][x] == 1) {
						img11s.add(new C(x, y));
					}
				}
			}

			Map<C, Integer> m = new HashMap<>();
			int max = 0;
			for (C c2 : img21s) {
				int dx, dy;
				for (C c1 : img11s) {
					dx = c2.x - c1.x;
					dy = c2.y - c1.y;
					C dS = new C(dx, dy);
					int count = m.getOrDefault(dS, 0);
					m.put(dS, count + 1);
					max = Math.max(m.get(dS), max);
				}
			}

			return max;
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub

		}

}

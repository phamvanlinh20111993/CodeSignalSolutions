package CompanyChallenge;
	
import java.util.TreeMap;

public class Mz_allianceVersurMonster {

	static int allianceVersusMonster(int[] healthPoints, int[] attackDamage) {
		int result = 0, i, numofdame = 0, j;
		final String _SPLIT = "###";
		
		TreeMap<Integer, String> map = new TreeMap<>();
		for(i = 1; i < healthPoints.length; i++) {
			if(map.get(attackDamage[i]) == null) {
				map.put(attackDamage[i], Integer.toString(healthPoints[i]));
			}else {
				map.put(attackDamage[i], map.get(attackDamage[i]) + _SPLIT + Integer.toString(healthPoints[i]));
			}
		}
		
		i = 0;
		int [] temp = new int[healthPoints.length - 1], temp1 =  new int[healthPoints.length - 1];
		for(Integer key : map.keySet()) {
			String[] splarr = map.get(key).split(_SPLIT);
			for(j = 0; j < splarr.length; j++) {
				temp[i] = key;
				temp1[i] = Integer.parseInt(splarr[j]);
				i++;
			}
		}
		
		j = 1;
		for(i = temp.length-1; i >= 0;i--) {
			healthPoints[j] = temp1[i];
			attackDamage[j] = temp[i];
			j++;
		}
		
		for(i = 1; i < healthPoints.length; i++) {
			numofdame = (int) Math.floor((float)healthPoints[i]/attackDamage[0]);
			if(healthPoints[i] - numofdame*attackDamage[0] > 0) {
				healthPoints[i] -=  numofdame*attackDamage[0];
				healthPoints[0] -= attackDamage[i]*numofdame;
			}else {
				if(numofdame - 1 > 0) {
					healthPoints[i] -=  (numofdame-1)*attackDamage[0];
					healthPoints[0] -= attackDamage[i]*(numofdame-1);
				}
			}
		}
		
		for(i = 1; i < healthPoints.length; i++) {
			if(healthPoints[i] > 0) {
				healthPoints[0] -= attackDamage[i];
				if(healthPoints[0] > 0) {
					healthPoints[i] -= attackDamage[0];
				}
			}
		}
		
		for(i = 1; i < healthPoints.length; i++) {
			if(healthPoints[i] > 0) result++;
		}
		
		System.out.println(healthPoints[0] +"  " + result);
		
		return result;
	}

	public static void main(String[] args) {
		/**
		 * test 1
		 */
		System.out.println("####################### test 1 #######################");
		int [] healthPoints = {110, 30, 50},
				attackDamage = {12, 11, 20};
		
		allianceVersusMonster(healthPoints, attackDamage);
		
		System.out.println("####################### test 2 #######################");
		int [] healthPoints1 = {4, 10, 10, 10},
				attackDamage1 = {10, 1, 1, 1};
		
		allianceVersusMonster(healthPoints1, attackDamage1);
		/**
		 * test 3
		 */
		System.out.println("####################### test 3 #######################");
		int [] healthPoints2 =  {10, 3, 3, 3}, //3 1 1 1     
 				attackDamage2 =  {2, 1, 5, 1};// 2 1 5 1
		allianceVersusMonster(healthPoints2, attackDamage2);
		
		System.out.println("######################### test 4 ######################");
		int [] healthPoints3 =  {11, 4, 4, 4},//2 2 2 2
				attackDamage3 =  {1, 1, 1, 1};
		allianceVersusMonster(healthPoints3, attackDamage3);
	}

}

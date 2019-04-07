package InterviewPractice;

import java.util.TreeMap;

public class groupingDishes {
	
	static final String _SPLIT = "###";
	
	static String[][] groupingDishes1(String[][] dishes) {

		int i, j;
		TreeMap<String, String> map = new TreeMap<>(), temp = new TreeMap<>();

		for (i = 0; i < dishes.length; i++) {
			for (j = 1; j < dishes[i].length; j++) {
				if (temp.get(dishes[i][j]) == null)
					temp.put(dishes[i][j], dishes[i][0]);
				else {
					if (map.get(dishes[i][j]) == null)
						map.put(dishes[i][j], temp.get(dishes[i][j]) + _SPLIT + dishes[i][0]);
					else
						map.put(dishes[i][j], map.get(dishes[i][j]) + _SPLIT + dishes[i][0]);
				}
			}
		}
		
//		for (String tmp : map.keySet()) {
//			System.out.println(tmp + "   " + map.get(tmp));
//		}
		
		String[][] result = new String[map.size()][];
		i = 0;
		for (String tmp : map.keySet()) {
			temp.clear();
			String [] tmp1 = map.get(tmp).split(_SPLIT);
			result[i] = new String[tmp1.length+1];
			for(j = 0; j < tmp1.length; j++) {
				temp.put(tmp1[j], "");
			}
			result[i][0] = tmp;
			j = 1;
			for(String tmp2 : temp.keySet()) {
				result[i][j] = tmp2;
				j++;
			}
			i++;
		}
		
		for (i = 0; i < result.length; i++) {
			for (j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "   ");
			}
			System.out.println();
		}

		return result;
	}

	public static void main(String[] args) {

		String[][] dishes = { { "Salad", "Tomato", "Cucumber", "Salad", "Sauce" },
				{ "Pizza", "Tomato", "Sausage", "Sauce", "Dough" }, { "Quesadilla", "Chicken", "Cheese", "Sauce" },
				{ "Sandwich", "Salad", "Bread", "Tomato", "Cheese" } };
		groupingDishes1(dishes);
	}

}

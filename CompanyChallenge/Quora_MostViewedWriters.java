package CompanyChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Quora_MostViewedWriters {

	public static int[][][] mostViewedWriters(int[][] topicIds, int[][] answerIds, int[][] views) {

		TreeMap<Integer, String> allTopicsQuestions = new TreeMap<>(), 
				                 rankOfViewes = new TreeMap<>();
		Set<Integer> getTopicIdsWasAnswered = new TreeSet<Integer>();
		HashMap<Integer, Integer> totalViewOfTopicIds = new HashMap<>();
		int i, j;

		for (i = 0; i < topicIds.length; i++) {
			for (j = 0; j < topicIds[i].length; j++) {
				if (!allTopicsQuestions.containsKey(topicIds[i][j])) {
					allTopicsQuestions.put(topicIds[i][j], Integer.toString(i));
				} else {
					allTopicsQuestions.put(topicIds[i][j], allTopicsQuestions.get(topicIds[i][j]) + "-" + i);
				}
			}
		}

		int[][][] mostViewedWriters = new int[allTopicsQuestions.size()][][];
		int pos = 0;
		for (Integer key : allTopicsQuestions.keySet()) {
			String[] splitQuestions = allTopicsQuestions.get(key).split("-");
			// get answers id for all topics id
			for (i = 0; i < splitQuestions.length; i++) {
				for (j = 0; j < answerIds[Integer.parseInt(splitQuestions[i])].length; j++) {
					getTopicIdsWasAnswered.add(answerIds[Integer.parseInt(splitQuestions[i])][j]);
				}
			}

			// check user id and answer
			for (Integer topicId : getTopicIdsWasAnswered) {
				for (j = 0; j < views.length; j++) {
					if (views[j][0] == topicId) {
						if (!totalViewOfTopicIds.containsKey(views[j][1])) {
							totalViewOfTopicIds.put(views[j][1], views[j][2]);
						} else {
							totalViewOfTopicIds.put(views[j][1], totalViewOfTopicIds.get(views[j][1]) + views[j][2]);
						}
					}
				}
			}

			int length = 0;
			for (Integer keyTmp : totalViewOfTopicIds.keySet()) {
				if (!rankOfViewes.containsKey(totalViewOfTopicIds.get(keyTmp))) {
					rankOfViewes.put(totalViewOfTopicIds.get(keyTmp), Integer.toString(keyTmp));
					length++;
				} else {
					rankOfViewes.put(totalViewOfTopicIds.get(keyTmp),
							rankOfViewes.get(totalViewOfTopicIds.get(keyTmp)) + "-" + Integer.toString(keyTmp));
					length++;
				}
			}

			mostViewedWriters[pos] = new int[length > 10 ? 10 : length][2];
			int tmp = length > 10 ? 9 : length - 1;
			for (Integer key1 : rankOfViewes.keySet()) {
				String[] splitViews = rankOfViewes.get(key1).split("-");

				if (splitViews.length < 2) {
					mostViewedWriters[pos][tmp][0] = Integer.parseInt(splitViews[0]);
					mostViewedWriters[pos][tmp][1] = key1;
					tmp--;
				} else {
					Arrays.sort(splitViews);
					for (i = splitViews.length - 1; i >= 0; i--) {
						mostViewedWriters[pos][tmp][0] = Integer.parseInt(splitViews[i]);
						mostViewedWriters[pos][tmp][1] = key1;
						tmp--;
						if (tmp < 0)
							break;
					}
				}

				if (tmp < 0)
					break;
			}

			pos++;
			getTopicIdsWasAnswered.clear();
			totalViewOfTopicIds.clear();
			rankOfViewes.clear();
		}
		
		//print 
		for (i = 0; i < mostViewedWriters.length; i++) {
			System.out.print("[");
			for (j = 0; j < mostViewedWriters[i].length; j++) {
				System.out.print("[");
				for (pos = 0; pos < mostViewedWriters[i][j].length; pos++) {
					System.out.print(mostViewedWriters[i][j][pos] + ", ");
				}
				System.out.print("], ");

			}
			System.out.println("],");
		}

		return mostViewedWriters;
	}

	public static void main(String[] args) {
		System.out.println("############# Test 1 #############");
		int[][] topicIds = { { 1, 2, 3 }, { 2, 3, 4 }, { 1, 4 }, { 2, 3 } },
				answerIds = { { 6, 4 }, { 1, 2 }, { 5 }, { 3 } },
				views = { { 2, 1, 2 }, { 6, 3, 5 }, { 3, 3, 0 }, { 5, 1, 1 }, { 4, 2, 3 }, { 1, 4, 2 } };
		mostViewedWriters(topicIds, answerIds, views);

		System.out.println("############# Test 2 #############");
		int[][] topicIds1 = { { 1, 2, 3 } }, answerIds1 = { { 1 } }, views1 = { { 1, 5, 3 } };
		mostViewedWriters(topicIds1, answerIds1, views1);

		System.out.println("############# Test 3 #############");
		int[][] topicIds2 = { {}, {}, { 1, 2 }, {}, { 2, 3 } },
				answerIds2 = { {}, { 4 }, { 6, 7, 8 }, { 3 }, { 1, 2 } }, views2 = { { 7, 3, 2 }, { 3, 1, 10 },
						{ 1, 10, 10 }, { 4, 8, 3 }, { 6, 1, 1 }, { 2, 1, 0 }, { 8, 10, 1 } };
		mostViewedWriters(topicIds2, answerIds2, views2);

		System.out.println("############# Test 4 #############");
		int[][] topicIds3 = { { 555, 666, 777 }, { 8, 1, 239 }, { 239, 566, 1000 } },
				answerIds3 = { { 1, 2, 3 }, { 239, 567 }, { 566, 30, 8 } }, views3 = { { 1, 18, 5 }, { 239, 23, 37 },
						{ 567, 23, 0 }, { 566, 1, 23 }, { 30, 18, 18 }, { 8, 7, 20 }, { 3, 239, 1 }, { 2, 18, 1 } };
		mostViewedWriters(topicIds3, answerIds3, views3);

		System.out.println("############# Test 5 #############");
		int[][] topicIds4 = { { 5, 6, 81 }, { 1, 3, 2 }, { 10, 12, 34 }, { 13, 14, 23, 43 }, { 11, 22, 17 } },
				answerIds4 = { { 1, 2, 3 }, {}, {}, { 4, 5, 6, 7 }, { 8, 9, 10, 11 } },
				views4 = { { 4, 18, 5 }, { 5, 23, 37 }, { 8, 1, 23 }, { 11, 18, 18 }, { 1, 7, 20 }, { 9, 239, 10 },
						{ 2, 239, 1 }, { 10, 18, 1 }, { 3, 239, 5 }, { 6, 169, 2 }, { 7, 800, 1 } };
		mostViewedWriters(topicIds4, answerIds4, views4);

		System.out.println("############# Test Custom #############");
		int[][] topicIds5 = { { 5, 6, 81 }, { 1, 3, 2 }, { 10, 12, 34 }, { 13, 14, 23, 43 }, { 11, 22, 17 } },
				answerIds5 = { { 1, 2, 3 }, {}, {}, { 4, 5, 6, 7 }, { 8, 9, 10, 11 } },
				views5 = { { 4, 18, 5 }, { 5, 23, 37 }, { 8, 1, 23 }, { 11, 18, 18 }, { 1, 7, 20 }, { 9, 239, 10 },
						{ 2, 239, 1 }, { 10, 18, 1 }, { 3, 239, 5 }, { 6, 169, 2 }, { 7, 800, 1 }, { 2, 239, 1 },
						{ 2, 239, 1 }, { 2, 239, 1 }, { 2, 239, 1 }, { 2, 239, 1 }, { 2, 239, 1 }, { 2, 239, 1 },
						{ 2, 239, 1 }, { 2, 239, 1 }, { 2, 239, 1 }, { 2, 239, 1 }, { 2, 239, 1 } };
		mostViewedWriters(topicIds5, answerIds5, views5);

	}

}

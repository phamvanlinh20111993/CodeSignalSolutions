package CompanyChallenge.Kik;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class Kik_RateLimit {

	static int[] rateLimit(int[][] sentBatches, int[][] receivedMessages, int startingAllowance) {

		HashMap<Integer, Integer> users = new HashMap<>(),
		                          times = new HashMap<>();
		int i = 0, j = 0, k = 0;
		ArrayList<Integer> re = new ArrayList<>();
		boolean isFull = false;

		// set user
		for (i = 0; i < sentBatches.length; i++) {
			for (j = 1; j < sentBatches[i].length; j++) {
				users.put(sentBatches[i][j], startingAllowance);
			}
		}
		for (k = 0; k < receivedMessages.length; k++) {
			users.put(receivedMessages[k][1], startingAllowance);
		}

		// start
		j = 0;
		for (i = 0; i < sentBatches.length; i++) {
			// check timestamp is 00:00?
			Date date = new Date((long) sentBatches[i][0] * 1000);
			TimeZone tz = TimeZone.getTimeZone("GMT+00");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			sdf.setTimeZone(tz);
			String[] time = sdf.format(date).toString().split(":");

			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);
			int second = Integer.parseInt(time[2]);

			// reset ratelimit
			if (hour == 0 && minute == 0 && second == 0) {
				if (!isFull) {
					while (j < receivedMessages.length && receivedMessages[j][0] < sentBatches[i][0]) {
						users.put(receivedMessages[j][1], users.get(receivedMessages[j][1]) + 1);
						j++;
					}
				}

				if (j == receivedMessages.length) {
					isFull = true;
					j--;
				}
				if (times.get(sentBatches[i][0]) == null) {
					for (Integer temp : users.keySet()) {
						users.put(temp, startingAllowance);
					}
					times.put(sentBatches[i][0], 1);
				}
			}

			// caculator add batch with index = 0
			boolean isAdd = true;

			if (sentBatches[i][0] < receivedMessages[j][0]) {
				for (k = 1; k < sentBatches[i].length; k++) {
					if (users.get(sentBatches[i][k]) <= 0) {
						isAdd = false;
						break;
					}
				}

				if (isAdd) {
					for (k = 1; k < sentBatches[i].length; k++) {
						users.put(sentBatches[i][k], users.get(sentBatches[i][k]) - 1);
					}
				} else
					re.add(i);
			} else if (sentBatches[i][0] == receivedMessages[j][0]) {
				// j is not full
				if (!isFull) {
					while (j < receivedMessages.length && receivedMessages[j][0] == sentBatches[i][0]) {
						users.put(receivedMessages[j][1], users.get(receivedMessages[j][1]) + 1);
						j++;
					}
				}

				if (j == receivedMessages.length) {
					isFull = true;
					j--;
				}

				for (k = 1; k < sentBatches[i].length; k++) {
					if (users.get(sentBatches[i][k]) <= 0) {
						isAdd = false;
						break;
					}
				}
				if (isAdd) {
					for (k = 1; k < sentBatches[i].length; k++) {
						users.put(sentBatches[i][k], users.get(sentBatches[i][k]) - 1);
					}
				} else
					re.add(i);
			} else {
				// j is not full
				if (!isFull) {
					while (j < receivedMessages.length && receivedMessages[j][0] <= sentBatches[i][0]) {
						users.put(receivedMessages[j][1], users.get(receivedMessages[j][1]) + 1);
						j++;
					}
				}

				if (j == receivedMessages.length) {
					isFull = true;
					j--;
				}

				for (k = 1; k < sentBatches[i].length; k++) {
					if (users.get(sentBatches[i][k]) <= 0) {
						isAdd = false;
						break;
					}
				}

				if (isAdd) {
					for (k = 1; k < sentBatches[i].length; k++) {
						users.put(sentBatches[i][k], users.get(sentBatches[i][k]) - 1);
					}
				} else
					re.add(i);
			}
		}

		int[] result = new int[re.size()];
		for (i = 0; i < re.size(); i++) {
			System.out.print(re.get(i) + "   ");
			result[i] = re.get(i);
		}
		System.out.println();

		return result;
	}

	public static void main(String[] args) {

		/**
		 * test 1
		 */
		System.out.println("#######################################################");
		int[][] sentBatches = { { 1471040000, 736273, 827482, 2738283 }, { 1471040005, 736273, 2738283 },
				{ 1471040010, 827482, 2738283 }, { 1471040015, 2738283 }, { 1471040025, 827482 },
				{ 1471046400, 736273, 827482, 2738283 } },
				receivedMessages = { { 1471040001, 2738283 }, { 1471040002, 2738283 }, { 1471040010, 827482 },
						{ 1471040020, 2738283 } };
		int startingAllowance = 1;
		rateLimit(sentBatches, receivedMessages, startingAllowance);

		/**
		 * test 2
		 */
		System.out.println("#######################################################");
		int[][] sentBatches1 = { { 1471046400, 736273, 827482, 2738283 }, { 1471046405, 736273, 2738283 },
				{ 1471046410, 827482, 2738283 }, { 1471046415, 2738283 }, { 1471046425, 827482, 2738283 },
				{ 1471046430, 736273, 827482, 2738283 } },
				receivedMessages1 = { { 1471046401, 2738283 }, { 1471046402, 2738283 }, { 1471046410, 827482 },
						{ 1471046420, 2738283 }, { 1471046420, 2938289 } };
		int startingAllowance1 = 1;
		rateLimit(sentBatches1, receivedMessages1, startingAllowance1);

		/**
		 * test 3
		 */
		System.out.println("#######################################################");
		int[][] sentBatches3 = { { 1471046400, 1, 2, 3 }, { 1471046400, 1, 2 }, { 1471046400, 1, 3 }, { 1471046400, 1 },
				{ 1471046400, 1 }, { 1471046400, 2, 3, 6 } },
				receivedMessages3 = { { 1471046400, 3 }, { 1471046400, 3 }, { 1471046400, 1 }, { 1471046400, 3 },
						{ 1471046400, 4 } };
		int startingAllowance3 = 2;
		rateLimit(sentBatches3, receivedMessages3, startingAllowance3);

		/**
		 * test 4
		 */
		System.out.println("#######################################################");
		int[][] sentBatches4 = { { 1471040000, 736273, 827482, 2738283 }, { 1471040005, 736273, 2738283 },
				{ 1471040010, 827482, 2738283 }, { 1471040015, 827482, 2738283 }, { 1471040025, 827482, 2738283 },
				{ 1471046400, 736273, 827482, 2738283 }, { 1471050010, 827482 } },
				receivedMessages4 = { { 1471040001, 2738283 }, { 1471040002, 2738283 }, { 1471040020, 2738283 },
						{ 1471040021, 2738283 }, { 1471040022, 2738283 }, { 1471040030, 827482 },
						{ 1471040030, 2738283 } };
		int startingAllowance4 = 1;
		rateLimit(sentBatches4, receivedMessages4, startingAllowance4);

		System.out.println("#######################################################");
		int[][] sentBatches5 = { { 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
				50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75,
				76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99 },
				{ 2, 1, 2 }, { 3, 1, 25 }, { 4, 12, 15, 25, 35, 55 }, { 86400, 1, 2 }, { 86401, 1, 2, 3, 77, 88, 99 },
				{ 1471046399, 1, 2, 3, 55, 77, 88, 99 }, { 1471046400, 1, 2, 3, 77, 88, 99 }, { 1471046401, 2, 3, 6 } },
				receivedMessages5 = { { 4, 1 }, { 1471046432, 3 }, { 1471046433, 55 }, { 1471046434, 1 },
						{ 1471046435, 99 }, { 1471046436, 4 } };
		int startingAllowance5 = 2;

		rateLimit(sentBatches5, receivedMessages5, startingAllowance5);

	}

}

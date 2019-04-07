package Arcade;

public class NewYearCelebrations {

	static int newYearCelebrations(String takeOffTime, int[] minutes) {

		String[] temp = takeOffTime.split(":");
		int result = 0, i = 0, hour = Integer.parseInt(temp[0]), minute = Integer.parseInt(temp[1]);

		boolean isNewYear = false;

		for (i = 0; i < minutes.length; i++) {

			if (hour == 0 && minute == 0) {
				isNewYear = true;
				result++;
			}
			
			int currentMinute = (minute + minutes[i] - (i > 0 ? minutes[i - 1] : 0)) % 60;
			int currentHour = (minute + minutes[i] - (i > 0 ? minutes[i - 1] : 0)) / 60;

			hour += currentHour;
			minute = currentMinute;

			System.out.println("before " + hour + " : " + minute);

			if (hour >= 24) {
				isNewYear = true;
				result++;
			}
			
			hour = hour % 24;

			System.out.println("current Time " + hour + " : " + minute);
			if(hour-1 < 0) isNewYear = false;
			
			hour = hour - 1 < 0 ? 23 : (hour-1 == 0 && minute == 0) ? 0 : hour - 1;
		}

		if (hour == 0 && minute == 0 || !isNewYear)
			result++;

		return result;
	}

	public static void main(String[] args) {

		System.out.println("##################(1) - 3########################");

		String takeOffTime1 = "23:35";
		int[] minutes1 = { 60, 90, 140 };
		System.out.println(newYearCelebrations(takeOffTime1, minutes1));

		System.out.println("######################(2) -- 4#########################");
		String takeOffTime5 = "00:00";
		int[] minutes5 = { 60, 120, 180, 250 };
		System.out.println(newYearCelebrations(takeOffTime5, minutes5));

		System.out.println("###################(3) -- 1#########################");

		String takeOffTime2 = "13:36";
		int[] minutes2 = { 23, 33, 45, 56, 66, 88 };
		System.out.println(newYearCelebrations(takeOffTime2, minutes2));

		System.out.println("#####################(4) -- 1 ########################");
		String takeOffTime3 = "22:50";
		int[] minutes3 = {};
		System.out.println(newYearCelebrations(takeOffTime3, minutes3));
		//
		System.out.println("######################(5) -- 3 #########################");
		String takeOffTime4 = "20:18";
		int[] minutes4 = { 222, 342 };
		System.out.println(newYearCelebrations(takeOffTime4, minutes4));

		System.out.println("######################(6) -- 1 #########################");
		String takeOffTime6 = "12:05";
		int[] minutes6 = { 1, 109, 113, 344, 345, 478, 545, 565, 809, 814, 838, 883, 971, 1007, 1029, 1053, 1133, 1271,
				1314, 1500 };
		System.out.println(newYearCelebrations(takeOffTime6, minutes6));

		System.out.println("######################(10)--3#########################");
		String takeOffTime9 = "21:13";
		int[] minutes9 = { 52, 257, 323, 515, 579, 600, 703, 707, 1127, 1298 };
		System.out.println(newYearCelebrations(takeOffTime9, minutes9));

		System.out.println("#######################(11) - 1 ######################");
		String takeOffTime11 = "19:44";
		int[] minutes11 = { 545, 1320 };
		System.out.println(newYearCelebrations(takeOffTime11, minutes11));

		System.out.println("########################### (12) - 1 #####################");
		String takeOffTime12 = "18:15";
		int[] minutes12 = { 117, 241, 364, 375, 545, 1317 };
		System.out.println(newYearCelebrations(takeOffTime12, minutes12));

	}

}

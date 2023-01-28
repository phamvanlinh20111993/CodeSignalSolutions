package Codesignal.CompanyChallenge.Asana;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Asana_RecurringTask1 {

	static Date getDate(String date) {
		DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return sourceFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	static int calculateMonth(int year, int month) {
		// 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31.
		int[] dayMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		dayMonths[1] = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0) ? dayMonths[1] + 1 : dayMonths[1];
		return dayMonths[(month - 1) % 12];
	}

	static String[] recurringTask(String firstDate, int k, String[] daysOfTheWeek, int n) {
		Map<String, Integer> dayOfWeekMap = new HashMap<>();
		dayOfWeekMap.put("Sunday", 1);
		dayOfWeekMap.put("Monday", 2);
		dayOfWeekMap.put("Tuesday", 3);
		dayOfWeekMap.put("Wednesday", 4);
		dayOfWeekMap.put("Thursday", 5);
		dayOfWeekMap.put("Friday", 6);// Friday
		dayOfWeekMap.put("Saturday", 7);

		Date d = getDate(firstDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		String[] splitDate = firstDate.split("/"), result = new String[n];
		int day = Integer.parseInt(splitDate[0]), month = Integer.parseInt(splitDate[1]),
				year = Integer.parseInt(splitDate[2]), monthOrigin = month, yearOrigin = year, dayTmp = day, j = 0,
				start = 0, i;
		int[] dayOfWeeks = new int[daysOfTheWeek.length];

		for (i = 0; i < daysOfTheWeek.length; i++) {
			dayOfWeeks[i] = dayOfWeekMap.get(daysOfTheWeek[i]);
			if (cal.get(Calendar.DAY_OF_WEEK) == dayOfWeeks[i]) {
				start = i;
			}
		}

		System.out.println("start " + start + ", day " + firstDate);

		int previousDay = 0, nextWeek = 0, loop = 0;
		while (j < n) {
			// System.out.println(dayTmp + " " + nextWeek);
			dayTmp += previousDay + nextWeek;

			if (dayTmp > calculateMonth(year, month)) {
				dayTmp = dayTmp - calculateMonth(year, month);
				month++;
				if (month > 12) {
					month = 1;
					year++;
				}
			}

			result[j++] = (dayTmp < 10 ? "0" + dayTmp : dayTmp) + "/" + (month < 10 ? "0" + month : month) + "/" + year;
			nextWeek = 0;

			start++;
			if (start == dayOfWeeks.length) {
				nextWeek = 7 + dayOfWeeks[0] - dayOfWeeks[start - 1];
				start = 0;
				previousDay = 0;
			} else {
				previousDay = start > 0 ? dayOfWeeks[start] - dayOfWeeks[start - 1] : 0;
			}

			loop++;
			if (loop == dayOfWeeks.length) {
				loop = 0;
				dayTmp = day + 7 * (k == 0 ? 1 : k);
				while (dayTmp > calculateMonth(yearOrigin, monthOrigin)) {
					dayTmp = dayTmp - calculateMonth(yearOrigin, monthOrigin);
					monthOrigin++;
					if (monthOrigin > 12) {
						monthOrigin = 1;
						yearOrigin++;
					}
				}
				day = dayTmp;
				month = monthOrigin;
				year = yearOrigin;

				previousDay = 0;
				nextWeek = 0;
			}
		}

		for (j = 0; j < result.length; j++) {
			System.out.println(result[j]);
			// if (j > 0 && (j + 1) % dayOfWeeks.length == 0)
			// System.out.println("next");
		}

		return result;
	}

	public static void main(String[] args) {

		System.out.println("############### test 1 #################");
		String firstDate = "1/1/2015";
		int k = 2;
		String[] daysOfTheWeek = { "Sunday", "Monday", "Thursday" };
		int n = 6;
		recurringTask(firstDate, k, daysOfTheWeek, n);
		//
		// System.out.println("############### test 4 #################");
		// String firstDate3 = "01/02/2100";
		// int k3 = 4;
		// String[] daysOfTheWeek3 = { "Sunday", "Monday"};
		// int n3 = 4;
		// recurringTask(firstDate3, k3, daysOfTheWeek3, n3);

		// System.out.println("############### test 5 #################");
		// String firstDate4 = "01/12/2018";
		// int k4 = 1;
		// String[] daysOfTheWeek4 = { "Monday", "Tuesday", "Friday", "Saturday" };
		// //String[] daysOfTheWeek4 = { "Saturday" };
		// int n4 = 30;
		// recurringTask(firstDate4, k4, daysOfTheWeek4, n4);

		System.out.println("############### test 5 #################");
		String firstDate5 = "03/12/2018";
		int k5 = 1;
		// String[] daysOfTheWeek5 = { "Sunday", "Monday", "Tuesday", "Wednesday",
		// "Thursday", "Friday", "Saturday" };
		String[] daysOfTheWeek5 = { "Sunday", "Monday" };
		int n5 = 14;
		recurringTask(firstDate5, k5, daysOfTheWeek5, n5);
	}

}

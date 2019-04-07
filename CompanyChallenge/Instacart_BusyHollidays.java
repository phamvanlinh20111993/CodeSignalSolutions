package CompanyChallenge;

public class Instacart_BusyHollidays {

	static boolean busyHolidays(String[][] shoppers, String[][] orders, int[] leadTime) {

		return false;
	}

	public static void main(String[] args) {

		System.out.println("################### Test 1 ######################");
		String[][] shoppers = { { "15:10", "16:00" }, { "17:40", "22:30" } };
		String[][] orders = { { "17:30", "18:00" }, { "15:00", "15:45" } };
		int[] leadTime = { 15, 30 };
		System.out.println(busyHolidays(shoppers, orders, leadTime));
		
		
		System.out.println("################### Test 5 ######################");
		String[][] shoppers5 = { { "01:00","02:00" }, { "01:01","01:30" } };
		String[][] orders5 = { { "01:00","02:00" }, { "01:11","02:00" } };
		int[] leadTime5 = { 20, 20 };
		System.out.println(busyHolidays(shoppers5, orders5, leadTime5));
		

		System.out.println("################### Test 8 ######################");
		String[][] shoppers1 = { { "23:00", "23:20" }, { "23:10", "23:30" }, { "23:15", "23:35" } };
		String[][] orders1 = { { "23:05", "23:30" }, { "23:05", "23:30" }, { "23:05", "23:30" } };
		int[] leadTime1 = { 16, 16, 16 };
		System.out.println(busyHolidays(shoppers1, orders1, leadTime1));
		

		System.out.println("################### Test 10 ######################");
		String[][] shoppers2 = { { "23:00", "23:20" }, { "23:10", "23:30" }, { "23:16", "23:35" } };
		String[][] orders2 = { { "23:05", "23:30" }, { "23:05", "23:30" }, { "23:05", "23:30" } };
		int[] leadTime2 = { 15, 15, 15 };
		System.out.println(busyHolidays(shoppers2, orders2, leadTime2));
		
		
	}

}

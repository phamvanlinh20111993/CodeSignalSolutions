package CodeJavaEx;

public class Main {

	public static void main(String[] args) {
		ConvertDashboardNumberToBase temp = new ConvertNumberToBinaryNumber();

		System.out.println("############# test 1, 1000 ################");
		System.out.println(temp.converNumberToBaseBinary(1000));

		System.out.println("############# test 2, 1120 ################");
		System.out.println(temp.converNumberToBaseBinary(1120));

		System.out.println("############# test 3, -1000 ################");
		System.out.println(temp.converNumberToBaseBinary(-1000));

		System.out.println("############# test 4, 1000.24234 ################");
		System.out.println(temp.converNumberToBaseBinary(1000.24234F));

		System.out.println("############# test 5, 3.56125 ################");
		System.out.println(temp.converNumberToBaseBinary(3.56125D));

		System.out.println("############# test 6, 23.34375 ################");
		System.out.println(temp.converNumberToBaseBinary("23.34375"));

		System.out.println("############# test 7, 15.11 ################");
		System.out.println(temp.converNumberToBaseBinary(15.11));

		System.out.println("############# test 8, -15.11 ################");
		System.out.println(temp.converNumberToBaseBinary(-15.11));

		System.out.println("############# test 9, 128 ################");
		System.out.println(temp.converNumberToBaseBinary(128));

		System.out.println("############# test 10, -128 ################");
		System.out.println(temp.converNumberToBaseBinary(-128));

		System.out.println("############# test 11, 65438 ################");
		System.out.println(temp.converNumberToBaseBinary(65438));

		System.out.println("############# test 12, -65438 ################");
		System.out.println(temp.converNumberToBaseBinary(-65438));

		System.out.println("############# test 13, -45 ################");
		System.out.println(temp.converNumberToBaseBinary(-45));

		System.out.println("############# test 14, -5 ################");
		System.out.println(temp.converNumberToBaseBinary(-5));

		System.out.println("############# test 15, 65536 ################");
		System.out.println(temp.converNumberToBaseBinary(65536));

		System.out.println("############# test 16, -65536 ################");
		System.out.println(temp.converNumberToBaseBinary(-65536));

		System.out.println("############# test 17, 32768 ################");
		System.out.println(temp.converNumberToBaseBinary(32768));

		System.out.println("############# test 18, -32768 ################");
		System.out.println(temp.converNumberToBaseBinary(-32768));

		System.out.println("############# test random ################");
		System.out.println(temp.converNumberToBaseBinary("4242342423.423423421312"));

		System.out.println("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

		ConvertDashBoardToRomanNumber test = new ConvertNumberToRomanNumer();

		System.out.println("############# test 1, 350 ####################");
		System.out.println(test.converNumberToRomanNumber("350"));

		System.out.println("############# test 2, 480 ####################");
		System.out.println(test.converNumberToRomanNumber("480"));

		System.out.println("############# test 3, 780 ####################");
		System.out.println(test.converNumberToRomanNumber("780"));

		System.out.println("############# test 4, 2499 ####################");
		System.out.println(test.converNumberToRomanNumber(2499));

		System.out.println("############# test 5, 2780 ####################");
		System.out.println(test.converNumberToRomanNumber("2780"));

		System.out.println("############# test 6, 41231312 ####################");
		System.out.println(test.converNumberToRomanNumber("41231312"));

		System.out.println("################## test in range -1, 2999 ############");
		for (int i = -1; i <= 2999; i++) {
			System.out.println(i + " = " + test.converNumberToRomanNumber(i));
		}
		
		System.out.println("############# test 7, 2231312 ####################");
		System.out.println(test.converNumberToRomanNumber("2231312"));
	}

}

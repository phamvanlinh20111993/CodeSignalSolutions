package Codesignal.Arcade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CuriousClock {

	static String curiousClock(String someTime, String leavingTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String formattedDate = "";
		try {
			Date someD = sdf.parse(someTime);
			Date leavingD = sdf.parse(leavingTime);
			long period = leavingD.getTime() - someD.getTime();
			Date result = new Date(someD.getTime() - period);
			formattedDate = sdf.format(result);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		
		return formattedDate;
	}

	public static void main(String[] args) {

		String someTime = "2016-08-26 22:40", leavingTime = "2016-08-29 10:00";
		curiousClock(someTime, leavingTime);
	}

}

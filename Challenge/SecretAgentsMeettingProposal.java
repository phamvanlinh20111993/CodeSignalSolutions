package Challenge;

import java.util.HashMap;

public class SecretAgentsMeettingProposal {

	static boolean strIsNumber(String str) {
		return str.matches("-?\\d+");
	}

	static String checkCondition(String day, String time, String place) {
		if ((day.equals("today") && place.equals("park"))
				|| (day.equals("tomorrow") && ((time.equals("night") && place.equals("bar"))
						|| (time.equals("afternoon") && place.equals("park"))))
				|| (day.equals("twodays")) && time.equals("morning") && place.equals("restaurant"))
			return "5.9.12";

		return "13.7";
	}

	static String[] secretAgentsMeetingProposal(String incomingMessage, int codeNumberDiff) {

		String[] result = new String[2];
		HashMap<String, String> enCode = new HashMap<>();
		enCode.put("0", "a");
		enCode.put("9", "e");
		enCode.put("8", "i");
		enCode.put("7", "o");
		enCode.put("6", "u");
		enCode.put("5", "y");
		enCode.put("4", "w");
		enCode.put("10", "t");
		enCode.put("11", "d");
		enCode.put("12", "s");
		enCode.put("13", "n");
		enCode.put("14", "m");
		enCode.put("15", "r");
		enCode.put("16", "b");
		enCode.put("17", "k");
		enCode.put("18", "p");
		enCode.put("*", "morning");
		enCode.put("@", "afternoon");
		enCode.put("#", "night");
		enCode.put("?", "-");

		int i, j = 0;
		String[] splitStr = incomingMessage.split("\\.");
		result[0] = "";
		for (i = 0; i < splitStr.length; i++) {
			String code = splitStr[i];

			if (strIsNumber(splitStr[i]))
				code = Integer.toString(Integer.parseInt(splitStr[i]) + j);

			if (enCode.containsKey(code)) {
				result[0] += enCode.get(code);
			} else {
				if (splitStr[i].equals("_"))
					j += codeNumberDiff;
			}
		}

		splitStr = result[0].split("-");
		result[1] = checkCondition(splitStr[0], splitStr[1], splitStr[2]);

		System.out.println(result[0] + "    " + result[1]);

		return result;
	}

	public static void main(String[] args) {

		String incomingMessage = "10.7.11.0.5.?.#.?._.15.-1.14";
		int codeNumberDiff = 1;
		System.out.println("################# test 1 ##################");
		secretAgentsMeetingProposal(incomingMessage, codeNumberDiff);

		String incomingMessage1 = "10.4.7.11.0.5._.10.?.*.?._.11.5.8.6.-4.2.11.-4.9.6";
		int codeNumberDiff1 = 2;
		System.out.println("################# test 2 ##################");
		secretAgentsMeetingProposal(incomingMessage1, codeNumberDiff1);

		String incomingMessage2 = "_._._._.18.12.15.19.8.13.20.?._.#.?.25.19.22.20.10.16.25.10.23.20";
		int codeNumberDiff2 = -2;
		System.out.println("################# test 3 ##################");
		secretAgentsMeetingProposal(incomingMessage2, codeNumberDiff2);

		String incomingMessage3 = "10.7.11._.-37._.-69.?.#.?.-56.-74._.-96._.-131";
		int codeNumberDiff3 = 37;
		System.out.println("################# test 4 ##################");
		secretAgentsMeetingProposal(incomingMessage3, codeNumberDiff3);

		String incomingMessage4 = "10.7.14.7.15.15.7.4.?.*._.?._.8.-10.5._.2";
		int codeNumberDiff4 = 5;
		System.out.println("################# test 5 ##################");
		secretAgentsMeetingProposal(incomingMessage4, codeNumberDiff4);
	}

}

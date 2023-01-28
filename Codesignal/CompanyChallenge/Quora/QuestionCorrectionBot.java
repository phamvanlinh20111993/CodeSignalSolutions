package Codesignal.CompanyChallenge.Quora;

public class QuestionCorrectionBot {

	static String questionCorrectionBot(String question) {

		question = question.trim();
		StringBuffer re = new StringBuffer("");

		re.append(Character.toString(question.charAt(0)).toUpperCase());

		int i = 1, temp;
		for (; i < question.length();) {
			if (question.charAt(i) == ' ') {
				temp = i + 1;
				while (question.charAt(temp) == ' ')
					temp++;
				i = temp;
				if (question.charAt(i) == ',')
					re.append(Character.toString(question.charAt(i)));
				else
					re.append(" " + Character.toString(question.charAt(i)));
			} else {
				if (i < question.length() - 1) {
					if (question.charAt(i) == ',') {
						if (question.charAt(i + 1) != ' ')
							re.append(", ");
					} else {
						if (i > 0 && re.charAt(re.length() - 1) == ',')
							re.append(" ");
						re.append(Character.toString(question.charAt(i)));
					}
				} else {
					if (question.charAt(i) == ',')
						re.append(Character.toString(question.charAt(i)) + " ");
					else
						re.append(Character.toString(question.charAt(i)));
				}
			}

			i++;
		}

		i = re.length() - 1;
		while (re.charAt(i) == '?')
			i--;
		re.delete(i + 1, re.length());

		return re.toString() + "?";

	}

	public static void main(String[] args) {
		String question = "a , b    , c    , d  , e?";
		String question1 = " this isn't a relevant question , is it??? ";
		String question2 = "This isn't a relevant question, is it?";
		String question3 = "  Is,it    correct    ,    question";
		String question4 = "questionword ,anotherquestionword,";

		System.out.println(questionCorrectionBot(question));
		System.out.println(questionCorrectionBot(question1));
		System.out.println(questionCorrectionBot(question2));
		System.out.println(questionCorrectionBot(question3));
		System.out.println(questionCorrectionBot(question4));
	}

}

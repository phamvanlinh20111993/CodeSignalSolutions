package CompanyChallenge.Asana;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Asana_HtmlToLuna {

	static String PATTERN_HTML_OPEN = "(<(\\/(\\s)*)*([a-z]+)(\\s\\/)*>)\\s*";
	//

	public static String htmlToLuna(String html) {
		String res = "";
		html = html.trim();
		// Using stack check is html syntax
		Stack<String> savePath = new Stack<>();
		Pattern p = Pattern.compile(PATTERN_HTML_OPEN);
		Matcher m = p.matcher(html);
		// boolean isPrevious = false;

		while (m.find()) {
			// System.out.println("Found value: " + m.group(0));
			// System.out.println("Found value: " + m.group(1));
			// System.out.println("Found value: " + m.group(2));//close tag (if not null)
			// System.out.println("Found value: " + m.group(3));//space
			// System.out.println("Found value: " + m.group(4));//value of html tag like p,
			// div, b, ...
			// System.out.println("Found value: " + m.group(5));// image tag (if not null)
			// System.out.println();

			// close tag. Ex: </p>
			if (m.group(2) != null) {
				if (!m.group(4).equals(savePath.peek())) {
					return "Error";
				}
				savePath.pop();
				res += "])";

			} else if (m.group(5) != null) {// is image tag
				if (res.length() > 0 && res.charAt(res.length() - 1) == ')' && savePath.size() > 0) {
					res += ", ";
				}
				res += "IMG({})";

			} else {
				if (res.length() > 0 && res.charAt(res.length() - 1) == ')' && savePath.size() > 0) {
					res += ", ";
				}
				savePath.push(m.group(4));
				res += m.group(4).toString().toUpperCase() + "([";

			}
		}

		if (!savePath.empty()) {
			return "Error";
		}

		System.out.println(res);
		return res;
	}

	public static void main(String[] args) {

		// DIV([IMG({})])
		System.out.println("###################### test 1 ##################");
		String html = "<div> <img /> </ div>";
		htmlToLuna(html);

		// DIV([P([]), P([]), P([])])
		System.out.println("###################### test 3 ##################");
		String html2 = "<div><p></p><p></p><p></p></div>";
		htmlToLuna(html2);

		// DIV([P([IMG({})]), B([])])
		System.out.println("###################### test 2 ##################");
		String html3 = "<div><p><img /></p><b></b></div>";
		htmlToLuna(html3);

		// DIV([IMG({}), B([]), IMG({})])
		System.out.println("###################### test 4 ##################");
		String html4 = "<div><img /><b></b><img /></div>";
		htmlToLuna(html4);

		// IMG({})
		System.out.println("###################### test 5 ##################");
		String html5 = "<img />";
		htmlToLuna(html5);

		// ""
		System.out.println("###################### test 6 ##################");
		String html6 = "";
		htmlToLuna(html6);

		// DIV([])P([])B([])IMG({})
		System.out.println("###################### test 7 ##################");
		String html7 = "<div></div><p></p><b></b><img />";
		htmlToLuna(html7);

		// DIV([DIV([IMG({})])])
		System.out.println("###################### test 8 ##################");
		String html8 = "<div><div><img /></div></div>";
		htmlToLuna(html8);

		// P([IMG({}), DIV([B([]), IMG({})]), IMG({}), IMG({})])IMG({})
		System.out.println("###################### test 9 ##################");
		String html1 = "<p><img /><div><b></b><img /></div><img /><img /></p><img />";
		htmlToLuna(html1);
	}

}

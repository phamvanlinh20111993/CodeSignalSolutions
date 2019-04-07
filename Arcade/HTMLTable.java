package Arcade;

public class HTMLTable {
	public static String htmlTable(String table, int row, int column) {
		int i = 0, r = 0, c = 0;
		String res = "No such cell";
		StringBuffer resTmp = new StringBuffer("");

		for (i = 0; i < table.length();) {
			if (r < row) {
				if (table.charAt(i) == '<') {
					if (i + 3 < table.length() && table.charAt(i + 1) == 't' && table.charAt(i + 2) == 'r'
							&& table.charAt(i + 3) == '>') {
						r++;
						i += 3;
					}
				}
			} else {
				if (c < column) {
					if (table.charAt(i) == '<') {
						if (i + 3 < table.length() && table.charAt(i + 1) == 't' && table.charAt(i + 2) == 'd'
								&& table.charAt(i + 3) == '>') {
							c++;
							i += 3;
						}
					}
				} else {

					if (table.charAt(i) == '<') {
						if (i + 4 < table.length() && table.charAt(i + 1) == '/' && table.charAt(i + 2) == 't'
								&& table.charAt(i + 3) == 'd' && table.charAt(i + 4) == '>') {
							break;
						}
					} else {
						resTmp.append(table.charAt(i));
					}
				}
			}

			i++;
		}

		if (!resTmp.toString().equals("")) {
			return resTmp.toString();
		}

		return res;
	}

	public static void main(String[] args) {
		// System.out.println("########## test 1 ###############");
		// String table =
		// "<table><tr><td>jQu9ABs8l</td><td>9alQS</td><td>6j</td><td>x0C</td><td>VJwINu0wjE</td></tr><tr><td>52K</td><td>w5P</td><td>K0HTHBB</td><td>76H</td><td>2Up4kl</td></tr><tr><td>d7J9bn7lx</td><td>unJT</td><td>mdICgjl</td><td>v0</td><td>LKvS1LbYBo</td></tr><tr><td>eld9</td><td>O</td><td>Yqe184E9</td><td>b45QX0313A</td><td>4M02</td></tr><tr><td>6XKiOf96</td><td>wb7</td><td>HW5535kri</td><td>81U</td><td>V64O2502a</td></tr><tr><td>o8</td><td>col7G7g</td><td>y92s3R</td><td>q1</td><td>zl0LizILrm</td></tr></table>";
		// System.out.println(htmlTable(table, 5, 4));

		System.out.println("########## test 2 ###############");
		String table1 = "<table><tr><th>CIRCUMFERENCE</th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th></tr><tr><td>BITS</td><td>3</td><td>4</td><td>8</td><td>10</td><td>12</td><td>15</td></tr></table>";
		System.out.println(htmlTable(table1, 0, 6));

		// System.out.println("########## test 3 ###############");
		// String table2 =
		// "<table><tr><th>CIRCUMFERENCE</th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th>6</th></tr><tr><td>BITS</td><td>3</td><td>4</td><td>8</td><td>10</td><td>12</td><td>15</td></tr></table>";
		// System.out.println(htmlTable(table2, 1, 6));
	}

}

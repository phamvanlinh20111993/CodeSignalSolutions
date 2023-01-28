package Codesignal.Challenge;

public class TheSmallestStringCipher {

	public static String theSmallestStringCipher(String key, String message) {
		StringBuffer res = new StringBuffer();
		int i = 0, j = 0;
		
		while (i < key.length() || j < message.length()) {

			if (j == message.length()) {
				res.append(key.charAt(i++));
			} else if (i == key.length()) {
				res.append(message.charAt(j++));
			} else {
				if (key.charAt(i) > message.charAt(j)) {
					res.append(message.charAt(j++));
				}else if(key.charAt(i) < message.charAt(j)) {
					res.append(key.charAt(i++));
				} else {
					if (i < key.length() - 1 && j < message.length() - 1) {
						if (key.charAt(i + 1) < message.charAt(j + 1)) {
							res.append(key.charAt(i++));
						} else {
							res.append(message.charAt(j++));
						}
					} else {
						if (i < key.length() - 1) {
							res.append(key.charAt(i++));
						} else {
							res.append(message.charAt(j++));
						}
					}

				} 
			}

		}

		System.out.println(res.toString());

		return res.toString();

	}

	public static void main(String[] args) {
		System.out.println("############# Test 1 ###############");
		String key = "gdmz", message = "hello";
		theSmallestStringCipher(key, message);

		System.out.println("############# Test 2 ###############");
		String key4 = "abcba", message4 = "abcdcba";
		theSmallestStringCipher(key4, message4);

		System.out.println("############# Test 3 ###############");
		String key3 = "cbede", message3 = "cbede";
		theSmallestStringCipher(key3, message3);

		System.out.println("############# Test 5 ###############");
		String key2 = "b", message2 = "baa";
		theSmallestStringCipher(key2, message2);

		System.out.println("############# Test 9 ###############");
		String key5 = "zswrmvwydkgvfbmbwfhl", message5 = "uzreoxzxhufwdplhxacb";
		theSmallestStringCipher(key5, message5);

		System.out.println("############# Test 10 ###############");
		String key1 = "ggzhotybqcjwbekrxlgs", message1 = "lneevzpnpbtptiraomss";
		theSmallestStringCipher(key1, message1);
		
		System.out.println("############# Test random ###############");
		String key12 = "abcdefgh",
			   message12 = "hgfedcba";
		theSmallestStringCipher(key12, message12);
		
		System.out.println("############# Test random1 ###############");
		String key121 = "abcdeabcd",
			   message121 = "abcdabcd";
		theSmallestStringCipher(key121, message121);

	}

}

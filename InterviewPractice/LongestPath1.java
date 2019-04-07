package InterviewPractice;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GoogleTree1 {
	public String value;
	public Integer length;
	public ArrayList<GoogleTree1> childrens;

	GoogleTree1() {
		this.childrens = new ArrayList<GoogleTree1>();
		this.length = 0;
		this.value = "";
	}

	GoogleTree1(String val) {
		this.childrens = new ArrayList<GoogleTree1>();
		this.value = val;
		this.length = this.value.length();
	}

	GoogleTree1(String val, Integer length) {
		this.childrens = new ArrayList<GoogleTree1>();
		this.value = val;
		this.length = val.length() + length;
	}

	public void setGoogleTreeValue(String val) {
		this.value = val;
		this.length = val.length();
	}
}

public class LongestPath1 {
	
	static String PATTERN_SPLIT_STR = "\\f((\\t)*|((\\s){4})*)";
	static String PATTERN_COUNT_LEVEL = "\\f((\\t)*)";
	
	public static boolean isFile(String value) {
		String[] check = value.split("\\.");
		return check.length > 1;
	}

	public static int addValue(GoogleTree1 googleTree, String val, Integer level, int max) {

		Integer i = 1;
		GoogleTree1 googleTreeTmp = googleTree;
		Integer rootLength = googleTreeTmp.length;

		while (i < level) {
			Integer length = googleTreeTmp.childrens.size();
			if (length == 0) {
				throw new ArrayIndexOutOfBoundsException("It is out of level");
			}
			googleTreeTmp = googleTreeTmp.childrens.get(length - 1);
			rootLength = googleTreeTmp.length;
			i++;
		}

		rootLength++;
		googleTreeTmp.childrens.add(new GoogleTree1(val, rootLength));
		if (isFile(val) && max < (rootLength + val.length())) {
			max = rootLength + val.length();
		}
		
		return max;
	}

	static int longestPath(String fileSystem) {
		int max = 0, max1 = 0;
		GoogleTree1 googleTree;
		String[] split = fileSystem.split(PATTERN_SPLIT_STR);

		googleTree = new GoogleTree1(split[0]);
		if (isFile(split[0])) {
			max = split[0].length();
		}

		String replaceTabBySpace = fileSystem.replaceAll("    ", "\\t");
		Pattern r = Pattern.compile(PATTERN_COUNT_LEVEL);
		Matcher m = r.matcher(replaceTabBySpace);

		int length = 0, pos = 1;
		while (m.find()) {
			//new tree folder
			if (m.group(1) == null || m.group(1).toCharArray().length == 0) {
				max1 = max1 < max ? max : max1;
				googleTree = new GoogleTree1(split[pos]);
				max = isFile(split[pos]) ? split[pos].length() : 0;
				pos++;
			} else {
				length = m.group(1).toCharArray().length;
				max = addValue(googleTree, split[pos++], length, max);
			}
		}
		System.out.println(max1 < max ? max : max1);
		return max1 < max ? max : max1;
	}

	public static void main(String[] args) {
		System.out.println("############# test1 ######################");
		String fileSystem = "user\f\tpictures\f\tdocuments\f\t\tnotes.txt";
		longestPath(fileSystem);

		System.out.println("############### test2 ################## ");
		String fileSystem1 = "user\f\tpictures\f\t\tphoto.png\f\t\tcamera\f\tdocuments\f\t\tlectures\f\t\t\tnotes.txt";
		longestPath(fileSystem1);

		System.out.println("############### test3 ################## ");
		String fileSystem2 = "a";
		longestPath(fileSystem2);

		System.out.println("############### test4 ################## ");
		String fileSystem3 = "a.txt";
		longestPath(fileSystem3);

		System.out.println("############### test5 ################## ");
		String fileSystem5 = "a.tar.gz";
		longestPath(fileSystem5);

		System.out.println("############### test6 ################## ");
		String fileSystem6 = "ReadME.TXT";
		longestPath(fileSystem6);

		System.out.println("############### test7 ################## ");
		String fileSystem7 = "file name with  space.txt";
		longestPath(fileSystem7);

		System.out.println("############### test8 ################## ");
		String fileSystem8 = "a\f\tb\f\t\tc";
		longestPath(fileSystem8);

		System.out.println("############### test9 ################## ");
		String fileSystem9 = "a\f\tb\f\t\tc.txt";
		longestPath(fileSystem9);

		System.out.println("############### test10 ################## ");
		String fileSystem10 = "dir\f    file.txt";
		longestPath(fileSystem10);

		System.out.println("############### test11 ################## ");
		String fileSystem11 = "dir\f\tfile.txt";
		longestPath(fileSystem11);

		System.out.println("############### test12 ################## ");
		String fileSystem12 = "dir\f\tfile.txt\f\tfile2.txt";
		longestPath(fileSystem12);

		System.out.println("############### test13 ################## ");
		String fileSystem13 = "a\f\tb1\f\t\tf1.txt\f\taaaaa\f\t\tf2.txt";
		longestPath(fileSystem13);

		System.out.println("############### custom ################## ");
		String fileSystem4 = "dir\f      file.txt\f\tdirChild\f\t\tvalue\f\t\t\texample\f\t\t\t\tmy.tag.gz\f\t\t\taaa\f\t\t\t\tblabla.doc";
		longestPath(fileSystem4);

		System.out.println("############### custom1 ################## ");
		String fileSystem41 = "a\f\tb\f\t\tc\f\t\t\td\f\t\t\t\te.txt\f\t\t\t\talsdkjf.txt\f\t\tskdjfl.txtlsdkjflsdjflsajdflkjasklfjkasljfklas\f\tlskdjflkajsflj.txt";
		longestPath(fileSystem41);

		System.out.println("################# test 17 ############");
		String fileSystem22 = "rzzmf\f\taaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.txt\fv\f\tix\f\t\tiklav\f\t\t\ttqse\f\t\t\t\ttppzf\f\t\t\t\t\tzav\f\t\t\t\t\t\tkktei\f\t\t\t\t\t\t\thhmav\f\t\t\t\t\t\t\t\tbzvwf.txt";
		longestPath(fileSystem22);
		
		System.out.println("################# test 14 ############");
		String fileSystem23 = "a\f\tb.txt\fa2\f\tb2.txt";
		longestPath(fileSystem23);
		
		System.out.println("############ test 15 #############");
		String fileSystem24 = "a\f\taa\f\t\taaa\f\t\t\tfile1.txt\faaaaaaaaaaaaaaaaaaaaa\f\tsth.png";
		longestPath(fileSystem24);
	}

}

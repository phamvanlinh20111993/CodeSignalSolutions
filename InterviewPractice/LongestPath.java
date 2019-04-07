package InterviewPractice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GoogleTree {
	public String value;
	public Integer length;
	public ArrayList<GoogleTree> childrens;

	GoogleTree() {
		this.childrens = new ArrayList<GoogleTree>();
		this.length = 0;
		this.value = "";
	}

	GoogleTree(String val) {
		this.childrens = new ArrayList<GoogleTree>();
		this.value = val;
		this.length = this.value.length();
	}

	GoogleTree(String val, Integer length) {
		this.childrens = new ArrayList<GoogleTree>();
		this.value = val;
		this.length = val.length() + length;
	}

	public void setGoogleTreeValue(String val) {
		this.value = val;
		this.length = val.length();
	}

}

class ConstructGoogleTree {
	protected GoogleTree googleTree;
	protected static Integer max;

	ConstructGoogleTree(String val) {
		googleTree = new GoogleTree(val);
		if(this.isFile(val)) {
			ConstructGoogleTree.max = val.length();
			System.out.println(val + " --- " + ConstructGoogleTree.max);
		}
	}

	public void addValue(String val, Integer level) {

		Integer i = 1;
		GoogleTree googleTreeTmp = this.googleTree;
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
		googleTreeTmp.childrens.add(new GoogleTree(val, rootLength));
		if (this.isFile(val) && max < (rootLength + val.length())) {
			max = rootLength + val.length();
		}

	}

	public GoogleTree getGoogleTree() {
		return googleTree;
	}

	public void setGoogleTree(GoogleTree googleTree) {
		this.googleTree = googleTree;
	}

	public static Integer getMax() {
		return ConstructGoogleTree.max;
	}

	public static void setMax(Integer max) {
		ConstructGoogleTree.max = max;
	}

	public boolean isFile(String value) {
		String[] check = value.split("\\.");

		if (check.length > 1) {
			return true;
			// if(check[check.length-2].charAt(check[check.length-2].length()-1) != ' ' &&
			// check[check.length-1].charAt(0) != ' ') {
			// return true;
			// }
		}

		return false;
	}

	public static boolean isFilenameValid(String file) {
		File f = new File(file);
		try {
			f.getCanonicalPath();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	// bfs find
	public void showTreeBFS() {

		Queue<GoogleTree> queue = new LinkedList<>();
		queue.add(googleTree);

		while (!queue.isEmpty()) {
			GoogleTree googleTreeTmp = queue.poll();

			System.out.print(googleTreeTmp.value + "-" + googleTreeTmp.length + " ");

			for (int i = 0; i < googleTreeTmp.childrens.size(); i++) {
				queue.add(googleTreeTmp.childrens.get(i));
			}
		}
		System.out.println();
	}

	// bfs find
	public void showTreeDFS() {

		Stack<GoogleTree> stack = new Stack<>();
		stack.add(googleTree);

		while (!stack.isEmpty()) {
			GoogleTree googleTreeTmp = stack.pop();

			System.out.print(googleTreeTmp.value + "-" + googleTreeTmp.length + " ");

			for (int i = googleTreeTmp.childrens.size() - 1; i >= 0; i--) {
				stack.add(googleTreeTmp.childrens.get(i));
			}
		}
		System.out.println();
	}

}

public class LongestPath {

	static String PATTERN_SPLIT_STR = "\\f((\\t)+|(\\s){4})*";
	static String PATTERN_COUNt_LEVEL = "\\f((\\s{4})+)";
	static int longestPath(String fileSystem) {

		int i = 0;
		String[] split = fileSystem.split(PATTERN_SPLIT_STR);
//		for (i = 0; i < split.length; i++) {
//			System.out.println(split[i]);
//		}

		// check level
		ConstructGoogleTree.setMax(0);
		ConstructGoogleTree constructTree = new ConstructGoogleTree(split[0]);
		
		String replaceTabBySpace = fileSystem.replaceAll("\\t", "    ");
//		for(i = 0; i < replaceTabBySpace.length(); i++) {
//			System.out.print(replaceTabBySpace.charAt(i));
//		}
//		System.out.println();
		Pattern r = Pattern.compile(PATTERN_COUNt_LEVEL);
		// Now create matcher object.
		Matcher m = r.matcher(replaceTabBySpace);
				
		int length = 0, pos = 1;
		while(m.find()) {
	         length = m.group(1).toCharArray().length/4;
	         constructTree.addValue(split[pos++], length);
		}
		constructTree.showTreeDFS();
		
		System.out.println(ConstructGoogleTree.getMax());
		return ConstructGoogleTree.getMax();
	}
	

	public static void main(String[] args) {
		
//		System.out.println("################### Test1 ####################");
//		
//		ConstructGoogleTree constructTree = new ConstructGoogleTree("user");
//		ConstructGoogleTree.setMax(0);
//		constructTree.addValue("pictures", 1);
//		constructTree.addValue("photo.png", 2);
//		constructTree.addValue("camera", 2);
//		constructTree.addValue("documents", 1);
//		constructTree.addValue("lectures", 2);
//		constructTree.addValue("notes.txt", 3);
//		constructTree.addValue("myfilm", 3);
//		constructTree.addValue("abc.xxl", 4);
//		/**
//		 * *) Structure folder:
//		 *      user                
//		 * 			pictures
//		 * 				photo.png	
//		 *       	    camera
//		 * 			documents
//		 * 				lectures
//		 * 					notes.txt
//		 * 
//		 */
//		constructTree.showTreeBFS();
//		constructTree.showTreeDFS();
//		System.out.println(ConstructGoogleTree.getMax());
//		
//		System.out.println("################### Test2 ####################");
//		ConstructGoogleTree.setMax(0);
//		constructTree = new ConstructGoogleTree("user");
//		constructTree.addValue("pictures", 1);
//		constructTree.addValue("documents", 1);
//		constructTree.addValue("notes.txt", 2);
//		/**
//		 * *) Structure folder:
//		 *      user                
//		 * 			pictures
//		 * 			documents
//		 * 				note.txt
//		 * 
//		 */
//		constructTree.showTreeBFS();
//		constructTree.showTreeDFS();
//		System.out.println(ConstructGoogleTree.getMax());
//		
//		System.out.println("################### Test3 ####################");
//		ConstructGoogleTree.setMax(0);
//		constructTree = new ConstructGoogleTree("a");
//		constructTree.addValue("b", 1);
//		constructTree.addValue("c", 2);
//		/**
//		 * *) Structure folder:
//		 *      a                
//		 * 			b
//		 * 				c
//		 * 				
//		 * 
//		 */
//		constructTree.showTreeBFS();
//		constructTree.showTreeDFS();
//		System.out.println(ConstructGoogleTree.getMax());
//		
//		//System.out.println(ConstructGoogleTree.isFilenameValid("abc  txt   txxx"));
		
		
		
		
		System.out.println("##############################################");

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
		String fileSystem4 = "dir\f    file.txt\f\tdirChild\f\t\tvalue\f\t\t\texample\f\t\t\t\tmy.tag.gz\f\t\t\taaa\f\t\t\t\tblabla.doc";
		longestPath(fileSystem4);
		
		System.out.println("############### custom1 ################## ");
		String fileSystem41 = "a\f\tb\f\t\tc\f\t\t\td\f\t\t\t\te.txt\f\t\t\t\talsdkjf.txt\f\t\tskdjfl.txtlsdkjflsdjflsajdflkjasklfjkasljfklas\f\tlskdjflkajsflj.txt";
		longestPath(fileSystem41);
		
		
	}

}

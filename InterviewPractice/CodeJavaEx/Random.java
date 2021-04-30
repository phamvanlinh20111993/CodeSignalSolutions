package InterviewPractice.CodeJavaEx;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author PhamVanLinh
 * created at 19/8/2018
 *
 */
public class Random {
	
	/**
	 * tạo 1 mảng random có độ dài trong khoảng [_MIN_ARR_LENGTH, _MIN_ARR_LENGTH]
	 */
	static final int _MAX_ARR_LENGTH = 100;
	static final int _MIN_ARR_LENGTH = 60;
	
	/**
	 * In ra _RANDOM_INDEX_ARR vị trí khác nhau trong mảng
	 */
	static final int _RANDOM_INDEX_ARR = 5;
	
	public static void main(String [] args) {
		
		int range = (_MAX_ARR_LENGTH - _MIN_ARR_LENGTH) + 1,
			randomArrLength = (int) ((Math.random() * range) + _MIN_ARR_LENGTH);
		int i;
		/**
		 * lưu trữ vị trí các phần tử khác nhau trong mảng
		 */
		HashSet<Integer> storageIndexArr = new HashSet<>();
		int [] arr = new int[randomArrLength];
		
		/**
		 * tạo 1 mảng có giá trị các phần tử nằm trong khoảng [-1000, 1000]
		 */
		for(i = 0; i < randomArrLength; i++) {
			arr[i] = (int)(Math.random() * 2000) + -1000;
			System.out.println(arr[i]);
		}
		
		/**
		 * In ra 5 vị trí khác nhau, random vị trí bất kì trong mảng
		 */
		while(storageIndexArr.size() < _RANDOM_INDEX_ARR) {
			int randomIndex = (int)(Math.random() * randomArrLength);
			storageIndexArr.add(randomIndex);
		}
		
		/**
		 * xuất kết quả
		 */
		
		System.out.println("################Vị trí với mảng có độ dài "+randomArrLength+": ####################");
		Iterator<Integer> scan = storageIndexArr.iterator();
		while(scan.hasNext()) {
			System.out.print(scan.next() + "  ");
		}
		System.out.println("\n################Giá trị tương ứng trong mảng: ####################");
		scan = storageIndexArr.iterator();
		while(scan.hasNext()) {
			System.out.print(arr[scan.next()] + "  ");
		}
	}
}

package ConstrucArray;

public class ConstrucArray {
	
	
	public static int [] construcArr(int size) {
		
		int [] arr = new int[size];
		int i, t = 1, k = 0;
		for(i = 0; i < size; i++) {
			if(i%2 == 0) {
				arr[i] = t;
				t++;
			}else {
				arr[i] = size-k;
				k++;
			}
		}
		
		return arr;
		
	}
	
	
	public static void main(String [] args)
	{
		int [] t =  construcArr(7);
		for(int i = 0; i < 7; i++)
			System.out.print(t[i] + "  ");
		
		int aaa = 1000;
		String a = Integer.toString(aaa);
	}
}

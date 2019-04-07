package InterviewPractice;

public class FindProfession {
	/**
	 * 
	 * @param level
	 * @param pos
	 * example:
	 * 	h=7, pos = 50
	 *	origin = 0, 2^7 = 128
	 *	h = 0,
	 *	h = 1,  ta có 2^7/2 = 64, pos < 64=> nhánh 1
	 *	h = 2, ta có 64/2 = 32 , pos = 50> 32=> nhánh 2 , origin = 32
	 *	h = 3, ta có 32/2=16, pos = 50 > 32+16=> nhánh 2, origine = 16+32=48
	 *	h = 4, ta có 16/2=8, pos = 50 < 48+8=> nhánh 1,
	 *	h = 5, ta có 8/2=4, pos = 50 < 48+4=> nhánh 1,
	 *	h = 6, ta có 4/2 = 2, pos = 50 = 48+2, nhánh 2
	 *	h = 7, ta có h=6 chọn nhánh 2=> output cần tìm
	 *
	 * @return
	 */
	static String findProfession(int level, int pos) {
		if(level == 0) return "Engineer";
		if(level == 1){
			if(pos == 1) return "Engineer"; 
			else return "Doctor";
		}
		
		int totalElement = (int) Math.pow(2, level), origin = 0, temp = 1;
		String root = "Engineer";
		
		while(temp < level) {
			
			totalElement /= 2;
			int branch = 1;
			//System.out.print(origin + "  " + totalElement + " branch ");
			//chon nhanh 2
			if(totalElement + origin <= pos) {
				branch = 2;
				if(root.equals("Doctor")) {
					root = "Engineer";
				}else {
					root = "Doctor";
				}
				
				if(totalElement + origin == pos) {
					int a = temp;
					while(a++ <= level) {
						if(root.equals("Doctor")) {
							root = "Engineer";
						}else {
							root = "Doctor";
						}
					}
					break;
				}
				
				origin += totalElement;
			//chon nhanh 1
			}
			
		//	System.out.println(branch);
			
			temp++;
		}
		
		
		return root;
	}
	
	public static void main(String[] args) {
		System.out.println("########## test 1 ############");
		System.out.println(findProfession(3, 3));
		
		System.out.println("########## test 2 ############");
		System.out.println(findProfession(4, 2));
		
		System.out.println("########## test 4 ############");
		System.out.println(findProfession(8, 100));
		
		System.out.println("########## test 5 ############");
		System.out.println(findProfession(10, 470));
		
		System.out.println("########## test 6 ############");
		System.out.println(findProfession(17, 5921));
		
		System.out.println("########## test 7 ############");
		System.out.println(findProfession(20, 171971));
		
		System.out.println("########## test 8 ############");
		System.out.println(findProfession(25, 16777216));
		
		System.out.println("########## test 9 ############");
		System.out.println(findProfession(30, 163126329));
		
		System.out.println("########## test custom ############");
		System.out.println(findProfession(7, 50));
		
	}

}

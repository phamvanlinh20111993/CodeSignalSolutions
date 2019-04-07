package InterviewPractice;

public class BankRequests {

	static boolean checkId(int id, int length) {
		return id > length || id < 0 ? true : false;
	}
	
	static int[] bankRequests(int[] accounts, String[] requests) {
	    
	    int i, flag = 0;
	    int [] result = new int[1];
	    
	    for(i = 0; i < requests.length; i++){
	        String [] temp = requests[i].split(" ");
	        
	        if(temp[0].equals("withdraw")) {
	        	int id = Integer.parseInt(temp[1]);
	        	int money = Integer.parseInt(temp[2]);
	        	if(checkId(id, accounts.length)) {
	        		flag = 1;
	        		result[0] = -(i+1);
	        		break;
	        	}else {
	        		if(money > accounts[id-1]) {
	        			flag = 1;
	        			result[0] = -(i+1);
		        		break;
	        		}else {
	        			accounts[id-1] -= money;
	        		}
	        	}
	        	
	        }else if(temp[0].equals("transfer")) {
	        	int send = Integer.parseInt(temp[1]),
	        		receive = Integer.parseInt(temp[2]),
	        		money = Integer.parseInt(temp[3]);
	        	
	        	if(checkId(send, accounts.length) || checkId(receive, accounts.length)) {
	        		flag = 1;
        			result[0] = -(i+1);
	        		break;
	        	}else {
	        		if(money > accounts[send-1]) {
	        			flag = 1;
	        			result[0] = -(i+1);
		        		break;
	        		}else {
	        			accounts[send-1] -= money;
	        			accounts[receive - 1] += money;
	        		}
	        	}
	        		
	        }else if(temp[0].equals("deposit")) {
	        	int id = Integer.parseInt(temp[1]);
	        	int money = Integer.parseInt(temp[2]);
	        	if(checkId(id, accounts.length)) {
	        		flag = 1;
	        		result[0] = -(i+1);
	        		break;
	        	}else {
	        		accounts[id-1] += money;
	        	}
	        }
	    }  
	    
	     return flag == 1 ? result : accounts;   
	}
	
	public static void main(String[] args) {
		
//		int [] accounts = {10, 100, 20, 50, 30};
//		String[] requests = { "withdraw 2 10", "transfer 5 1 20", "deposit 5 20", "transfer 3 4 15" };
		
		
//		int [] accounts = {20, 1000, 500, 40, 90};
//		String[] requests = { "deposit 3 400", "transfer 1 2 30", "draw 4 50 " };
		
		int [] accounts = {93451};
		String[] requests = { "withdraw 1 23140" };
		
		
		int [] arr = bankRequests(accounts, requests);
		System.out.println("# result #");
		for(int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + "   ");
	}

}

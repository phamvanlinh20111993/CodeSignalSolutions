package Codesignal.Arcade;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LRCToSubRip {
	
	static String convertT(String MM, String SS, String xx) {
		String hour = Integer.toString(Integer.parseInt(MM) / 60), 
			   minute = Integer.toString(Integer.parseInt(MM) % 60);
		
		return (hour.length() < 2 ? "0" + hour : hour) + ":" +
			   (minute.length() < 2 ? "0" + minute: minute) + 
			   ":" + SS + "," + xx + "0";
	}
	
	static String[] lrc2subRip(String[] lrcLyrics, String songLength) {
	    
	    int i, temp = 1, pos = -1;
	    String time = "", time1 = "";
	    ArrayList<String> res = new ArrayList<>();
	    final String _PATTERN = "^(\\d{2})\\:(\\d{2})\\.(\\d{2})$";
	    Pattern r = Pattern.compile(_PATTERN);    
	    
	    for(i = 0; i < lrcLyrics.length+1; i++) {
	    	if(i != lrcLyrics.length) {
	    		pos = lrcLyrics[i].indexOf(']');
	    	}
	    	
	    	time = ""; time1 = "";
	    	if(i > 0) {
	    		time = lrcLyrics[i-1].substring(1, pos);
			    Matcher m = r.matcher(time);
				res.add(Integer.toString(temp));
				
				if(i != lrcLyrics.length) {
					time1 = lrcLyrics[i].substring(1, pos);
					Matcher m1 = r.matcher(time1);
					if(m.find() && m1.find()) {
						res.add(convertT(m.group(1), m.group(2), m.group(3)) 
								+ " --> "
								+convertT(m1.group(1), m1.group(2), m1.group(3))
								);
				    }
				}else {
					if(m.find()){
						res.add(convertT(m.group(1), m.group(2), m.group(3)) 
								+ " --> "
								+songLength + ",000"
								);
					}
				}
				
				if(pos+2 <= lrcLyrics[i-1].length())
					res.add(lrcLyrics[i-1].substring(pos+2,  lrcLyrics[i-1].length()));
				else
					res.add("");
				
				if(i < lrcLyrics.length)
					res.add("");
				temp++;
	    	}    	
	    }
	    
	    String[] result = new String[res.size()];
	    result = res.toArray(result);
	    
	    for(i = 0; i < res.size(); i++) {
	    	System.out.println(res.get(i));
	    }
	    
	    return result;
	}
	
	public static void main(String[] args) {
		
//		System.out.println("###################### test 1 ######################");
//		String [] lrcLyrics = {"[00:12.00] Happy birthday dear coder,",
//		                         "[00:17.20] Happy birthday to you!"};
//		String songLength = "00:00:20";
//		
//		lrc2subRip(lrcLyrics, songLength);
		
		System.out.println("##################### test 2 ######################");
		String [] lrcLyrics1 = {"[00:09.01]", 
		                      "[00:10.01] Sweet dreams are made of this", 
		                      "[00:13.26] Who am I to disagree?", 
		                      "[00:17.01] Travel the world and the seven seas", 
		                      "[00:20.95] Everybodys looking for something", 
		                      "[00:24.57]", 
		                      "[00:24.82] Some of them want to use you", 
		                      "[00:28.64] Some of them want to get used by you", 
		                      "[00:32.45] Some of them want to abuse you", 
		                      "[00:36.32] Some of them want to be abused", 
		                      "[00:40.32]", 
		                      "[00:52.00] Sweet dreams are made of this", 
		                      "[00:55.37] Who am I to disagree?", 
		                      "[00:59.18] Travel the world and the seven seas", 
		                      "[01:03.00] Everybodys looking for something", 
		                      "[01:48.34] Some of them want to use you", 
		                      "[01:52.16] Some of them want to get used by you", 
		                      "[01:55.97] Some of them want to abuse you", 
		                      "[01:59.72] Some of them want to be abused", 
		                      "[02:03.58]", 
		                      "[01:18.17]", 
		                      "[01:29.17] Hold your head up, movin on", 
		                      "[01:19.18] Hold your head up", 
		                      "[01:31.11] Keep your head up", 
		                      "[01:19.92] Keep your head up, movin on", 
		                      "[01:21.86] Hold your head up, movin on", 
		                      "[01:23.74] Keep your head up, movin on", 
		                      "[01:25.67] Hold your head up, movin on", 
		                      "[01:27.55] Keep your head up, movin on"};
		String songLength1 = "00:09:32";
	    lrc2subRip(lrcLyrics1, songLength1);
	}
	

}

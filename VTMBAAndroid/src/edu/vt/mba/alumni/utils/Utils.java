package edu.vt.mba.alumni.utils;

import java.util.List;

public class Utils {
	
    public static boolean listsAreEqualAndNotNull(List<String> firstList,
			List<String> secondList) {
		if((firstList == null)||(secondList == null)) {
			return false;
		}
		if(firstList.size() != secondList.size()) {
			return false;
		}
		for(int i=0;i<firstList.size();++i) {
			if( !firstList.get(i).equals( secondList.get(i)) ){
				return false;
			}
		}
		
		
		
		return true;
	}
    
    public static boolean isNotNullOrEmptyOrWhitespace(String string) {
    	if(string != null && !string.isEmpty() && (string.trim().length() > 0)) {
    		return true;
    	} else {
    		return false;
    	}
    }

}

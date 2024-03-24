package com.demo.helpers;

import java.util.UUID;

public class GeneraionFileName {
	public final static String changeFileName(String file) {
		
		int indexOfDot = file.lastIndexOf(".");
		
		String cut = file.substring(indexOfDot);
		
		return UUID.randomUUID().toString().replace("-", "") + cut;
	}
}

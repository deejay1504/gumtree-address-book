package com.gumtree.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class AddressBookReader {
	
	int maleCount;
	
	public void readAddressBook(String fileName) throws ParseException {
		try {
	        BufferedReader in = new BufferedReader(new FileReader("./" + fileName));
	        String str = null;
	        while ((str = in.readLine()) != null) {
	            String[] addressBookRow = str.split(",");
	            if (Constants.MALE.equals(addressBookRow[1].trim())) {
	            	maleCount++;
	            }
	        }
	        in.close();
	    } catch (IOException e) {
	        System.out.println("Error reading file " + fileName);
	    }
	}
	
	public int getMaleCount() {
		return maleCount;
	}

}

package com.gumtree.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.gumtree.model.Person;

public class AddressBookReader {
	
	private int maleCount;
	
	private String[] addressBookRow;
	
	private List<Person> people = new ArrayList<Person>();
	
	public void readAddressBook(String fileName) throws ParseException {
		try {
	        BufferedReader in = new BufferedReader(new FileReader("./" + fileName));
	        String row = null;
	        Person person = new Person();
	        while ((row = in.readLine()) != null) {
	            addressBookRow = row.split(",");
	            if (Constants.MALE.equals(addressBookRow[1].trim())) {
	            	maleCount++;
	            }
	            person.setName(addressBookRow[0].trim());
	            person.setSex(addressBookRow[1].trim());
	            
	            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
	            Date dob = formatter.parse(addressBookRow[2].trim());
	            person.setDob(dob);
	            
	            people.add(person);
	        }
	        in.close();
	    } catch (IOException e) {
	        System.out.println("Error reading file " + fileName);
	    }
	}
	
	public int getMaleCount() {
		return maleCount;
	}
	
	public Person getOldestPerson() {
		Collections.sort(people, new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
		      return o1.getDob().compareTo(o2.getDob());
			}
		});
		return people.get(people.size() - 1);
	}

}

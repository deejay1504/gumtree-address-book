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

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.gumtree.model.Person;

public class AddressBookReader {
	
	private int maleCount;
	
	private String[] addressBookRow;
	
	private List<Person> people = new ArrayList<Person>();
	
	/**
	 * Read a file of address info and put it into a Person object for querying.
	 * Also count the number of male records as this is required.
	 * @param fileName - The name of the file to read. (This could be changed to read from a properties file containing a full path name)
	 * @throws ParseException
	 */
	public void readAddressBook(String fileName) throws ParseException {
		try {
	        BufferedReader in = new BufferedReader(new FileReader(fileName));
	        String row = null;
	        while ((row = in.readLine()) != null) {
	        	Person person = new Person();
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
	
	/**
	 * @return - The number of male records.
	 */
	public int getMaleCount() {
		return maleCount;
	}
	
	/**
	 * Sort the people object into date order so the oldest person comes first.
	 * @return
	 */
	public Person getOldestPerson() {
		Collections.sort(people, new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
		      return o1.getDob().compareTo(o2.getDob());
			}
		});
		return people.get(0);
	}

	/**
	 * Use JodaTime to get the difference in ages for two specified names.
	 * @param name1 - Assume the youngest name is entered first.
	 * @param name2 - Assume the older person is entered second.
	 * @return the difference in days
	 */
	public int getAgeDifference(String name1, String name2) {
		Date firstPersonDob = null;
		Date secondPersonDob = null;;
		for (Person person : people) {
			if (person.getName().toLowerCase().indexOf(name1.toLowerCase()) != -1) {
				firstPersonDob = person.getDob();
			}
			if (person.getName().toLowerCase().indexOf(name2.toLowerCase()) != -1) {
				secondPersonDob = person.getDob();
			}
		}
		DateTime firstDob = new DateTime(firstPersonDob);
		DateTime secondDob = new DateTime(secondPersonDob);
		Days daysBetween = Days.daysBetween(secondDob, firstDob);
		return daysBetween.getDays();
	}
}

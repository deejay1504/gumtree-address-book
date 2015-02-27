package com.gumtree.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.gumtree.model.Person;
import com.gumtree.util.AddressBookReader;

public class AddressBookReaderTest {
	
	private AddressBookReader addressBookReader;
	
	@Before
	public void setUp() throws Exception {
		addressBookReader = new AddressBookReader();
	}

    @Test
    public void shouldReturnThreeMaleRecords() throws ParseException {
    	// Given
    	addressBookReader.readAddressBook("AddressBook.txt"); 
    	
    	// When
    	int maleCount = addressBookReader.getMaleCount();
    	
    	// Then
    	assertThat(maleCount, is(3));
    }
    
    @Test
    public void shouldReturnOldestPerson() throws ParseException {
    	// Given we have a book reader
    	addressBookReader.readAddressBook("AddressBook.txt"); 
    	
    	// And we create the oldest date from the sample
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Date dob = formatter.parse("14/08/74");
    	
    	// When
    	Person oldestPerson = addressBookReader.getOldestPerson();
    	
    	// Then
    	assertThat(oldestPerson.getDob(), is(dob));
    	
    }
    
}

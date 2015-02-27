package com.gumtree.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

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
    
}

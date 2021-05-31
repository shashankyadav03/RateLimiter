package com.techmojo.application.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SearchTest {
	
	
	@Test
	void placeTest() {
		Search search=new Search();
		search.setPlace("Goa");
		assertEquals(search.getPlace(),"Goa");
	}

	
}

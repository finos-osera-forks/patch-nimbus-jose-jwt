package com.nimbusds.jose.util;


import java.text.ParseException;

import junit.framework.TestCase;


/**
 * Tests that JSON object parsing rejects a literal 0x1A after the object.
 */
public class JSONObjectUtilsTrailingDataTest extends TestCase {


	private static final char SUB = 0x1A;


	public void testParseRejectsSubAfterTheObject() {

		try {
			JSONObjectUtils.parse("{\"a\":1}" + SUB);
			fail();
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("Invalid JSON: "));
		}
	}


	public void testParseRejectsSubAndDataAfterTheObject() {

		try {
			JSONObjectUtils.parse("{\"a\":1}" + SUB + "garbage");
			fail();
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("Invalid JSON: "));
		}
	}


	public void testParseAcceptsTrailingSpace()
		throws ParseException {

		assertEquals(1, ((Number) JSONObjectUtils.parse("{\"a\":1} ").get("a")).intValue());
	}
}

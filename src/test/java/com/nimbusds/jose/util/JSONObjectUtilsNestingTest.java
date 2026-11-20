package com.nimbusds.jose.util;


import java.text.ParseException;
import java.util.Map;

import junit.framework.TestCase;


/**
 * Tests that JSON object parsing rejects nesting deeper than the JSON parser's limit.
 */
public class JSONObjectUtilsNestingTest extends TestCase {


	private static String nested(final int depth) {

		StringBuilder sb = new StringBuilder("{\"a\":");
		for (int i = 0; i < depth; i++) {
			sb.append('[');
		}
		for (int i = 0; i < depth; i++) {
			sb.append(']');
		}
		return sb.append('}').toString();
	}


	public void testParseAcceptsNestingAtTheLimit()
		throws ParseException {

		Map<String, Object> jsonObject = JSONObjectUtils.parse(nested(399));
		assertTrue(jsonObject.containsKey("a"));
	}


	public void testParseRejectsNestingOverTheLimit() {

		try {
			JSONObjectUtils.parse(nested(401));
			fail();
		} catch (ParseException e) {
			assertTrue(e.getMessage().startsWith("Invalid JSON: "));
		}
	}
}

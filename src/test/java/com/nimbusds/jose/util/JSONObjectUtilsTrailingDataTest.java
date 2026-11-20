/*
 * nimbus-jose-jwt
 *
 * Copyright 2012-2016, Connect2id Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

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
			assertEquals("Invalid JSON object", e.getMessage());
		}
	}


	public void testParseRejectsSubAndDataAfterTheObject() {

		try {
			JSONObjectUtils.parse("{\"a\":1}" + SUB + "garbage");
			fail();
		} catch (ParseException e) {
			assertEquals("Invalid JSON object", e.getMessage());
		}
	}


	public void testParseAcceptsTrailingSpace()
		throws ParseException {

		assertEquals(1, ((Number) JSONObjectUtils.parse("{\"a\":1} ").get("a")).intValue());
	}
}

package com.apirest.webflux.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

	@Test
	void getOnlyDigits() {
		assertEquals("001002", StringUtils.getOnlyDigits("001-002"));
	}

}
package com.bitsplease.dbms;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseProcessingTest {

	@Test
	public void testCodeToNum() {
		Assert.assertEquals("failure - wrong result", DatabaseProcessing.codeToNum("A"), 0);
	}

}

/*
 Class: CMSC203 CRN 40398
 Program: Assignment 3
 Instructor: Farnaz Eivazi
 Summary of Description: 
 Due Date: 07/11/2023 
 Integrity Pledge: I pledge that I have completed the programming assignment independently.
 I have not copied the code from a student or any source.
Shawn Parmhans
*/

package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CryptoManagerTestStudent{

	@Test
	public void testStringInBounds() {
		assertTrue(CryptoManager.isStringInBounds("SUCCESSFUL"));
	}

	@Test
	public void testEncryptCaesar() {
		assertEquals("TIBXO!QBSNIBOT", CryptoManager.caesarEncryption("SHAWN PARMHANS", 1));
	}

	@Test
	public void testDecryptCaesar() {
		assertEquals("SHAWN PARMHANS", CryptoManager.caesarDecryption("TIBXO!QBSNIBOT", 1));
	}

	@Test
	public void testEncryptBellaso() {
		assertEquals("BGNBNK@SD", CryptoManager.bellasoEncryption("CHOCOLATE", "CMSC"));

	}

	@Test
	public void testDecryptBellaso() {
		assertEquals("CHOCOLATE", CryptoManager.bellasoDecryption("BGNBNK@SD", "CMSC"));
		
	}
}
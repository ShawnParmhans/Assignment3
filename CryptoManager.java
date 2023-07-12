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

/**
 * This is a utility class that encrypts and decrypts a phrase using two
 * different approaches. The first approach is called the Caesar Cipher and is a
 * simple �substitution cipher� where characters in a message are replaced by a
 * substitute character. The second approach, due to Giovan Battista Bellaso,
 * uses a key word, where each character in the word specifies the offset for
 * the corresponding character in the message, with the key word wrapping around
 * as needed.
 * 
 * @author Farnaz Eivazi
 * @version 7/16/2022
 */
public class CryptoManager {
	
	private static final char LOWER_RANGE = ' ';
	private static final char UPPER_RANGE = '_';
	private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_RANGE and UPPER_RANGE characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean isStringInBounds (String plainText) {
		boolean inRange;
		inRange = false;
		for(int i = 0; i < plainText.length(); i++)
		{
			if(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)
				inRange = true;
		}
		return inRange;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String caesarEncryption(String plainText, int key) {
		if (!isStringInBounds(plainText))
			return "The selected string is not in bounds, Try again.";
		while(key > 95)
			key -= 64;
		
		StringBuilder encryptedString = new StringBuilder(plainText);
		String encryptedChar = " ";
		for(int i = 0; i < plainText.length(); i++)
		{
			char currentLetter = plainText.charAt(i);
			currentLetter += key;
			encryptedChar = String.valueOf(currentLetter);
			encryptedString.replace(i, i+1, encryptedChar);
		}
		return encryptedString.toString();
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String bellasoEncryption (String plainText, String bellasoStr) {
		if (!isStringInBounds(plainText))
			return "The selected string is not in bounds, Try again.";
		
		StringBuilder newBellasoStr = new StringBuilder(bellasoStr);
		String encryptedBellasoChar = " ";
		//check if bellaso letter in bounds
		for (int i = 0; i < bellasoStr.length(); i++)
		{	
			char bellaChar = bellasoStr.charAt(i);
			if(Character.getNumericValue(bellaChar) > 95)
				bellaChar -= 65;
			
			encryptedBellasoChar = String.valueOf(bellaChar);
			newBellasoStr.replace(i,  i+1, encryptedBellasoChar);
			
		}
		bellasoStr = newBellasoStr.toString();
		
		StringBuilder longerBell = new StringBuilder();
		//extend bellasoStr if needed
		int lengthBellasoStr = bellasoStr.length();
		for(int i = 0; i < plainText.length(); i++)
		{
			char currentLetter = plainText.charAt(i);
			char bellasoChar = bellasoStr.charAt(i % lengthBellasoStr);
			char encryptedCharBell;
			encryptedCharBell = (char)((currentLetter + bellasoChar) % 128);
			longerBell.append(encryptedCharBell);
		}
		bellasoStr = longerBell.toString();
		
		StringBuilder encryptedString = new StringBuilder(plainText);
		String encryptedChar = " ";
		
		for(int i = 0; i < plainText.length(); i++)
		{
			char currentLetter = plainText.charAt(i);
			currentLetter += Character.getNumericValue(bellasoStr.charAt(i));
			encryptedChar = String.valueOf(currentLetter);
			encryptedString.replace(i, i+1, encryptedChar);
		}
		return encryptedString.toString();
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String caesarDecryption (String encryptedText, int key) {
		
		while(key > 95)
			key -= 64;
		
		StringBuilder encryptedString = new StringBuilder(encryptedText);
		String encryptedChar = " ";
		for(int i = 0; i < encryptedText.length(); i++)
		{
			char currentLetter = encryptedText.charAt(i);
			currentLetter -= key;
			encryptedChar = String.valueOf(currentLetter);
			encryptedString.replace(i, i+1, encryptedChar);
		}
		return encryptedString.toString();
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String bellasoDecryption(String encryptedText, String bellasoStr) {
		
		StringBuilder newBellasoStr = new StringBuilder(bellasoStr);
		String encryptedBellasoChar = " ";
		//check if bellaso letter in bounds
		for (int i = 0; i < bellasoStr.length(); i++)
		{	
			char bellaChar = bellasoStr.charAt(i);
			if(Character.getNumericValue(bellaChar) > 95)
				bellaChar -= 65;
			
			encryptedBellasoChar = String.valueOf(bellaChar);
			newBellasoStr.replace(i,  i+1, encryptedBellasoChar);
			
		}
		bellasoStr = newBellasoStr.toString();
		
		StringBuilder longerBell = new StringBuilder();
		//extend bellasoStr if needed
		int lengthBellasoStr = bellasoStr.length();
		for(int i = 0; i < encryptedText.length(); i++)
		{
			char currentLetter = encryptedText.charAt(i);
			char bellasoChar = bellasoStr.charAt(i % lengthBellasoStr);
			char encryptedCharBell;
			encryptedCharBell = (char)((currentLetter + bellasoChar) % 128);
			longerBell.append(encryptedCharBell);
		}
		bellasoStr = longerBell.toString();
		
		StringBuilder encryptedString = new StringBuilder(encryptedText);
		String encryptedChar = " ";
		
		for(int i = 0; i < encryptedText.length(); i++)
		{
			char currentLetter = encryptedText.charAt(i);
			currentLetter -= Character.getNumericValue(bellasoStr.charAt(i));
			encryptedChar = String.valueOf(currentLetter);
			encryptedString.replace(i, i+1, encryptedChar);
		}
		return encryptedString.toString();
	}
}

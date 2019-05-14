package nl;

import java.util.HashMap;

/**
 * A "static" utility class for performing methods on multiple StringNumbers
 * @author bigba
 *
 */
public final class StringNumberUtils {
		
	private StringNumberUtils () {}
	
	private static final HashMap<CharacterType, HashMap<String, Integer>> characterMap = 
			new HashMap<CharacterType, HashMap<String, Integer>>();
	
	private static final String BASE = "base";
	private static final String START = "start";
	
	public static void init() {
		HashMap<String, Integer> digitMap = new HashMap<String, Integer>();
		digitMap.put(BASE, 10);
		digitMap.put(START, (int)'0');
		characterMap.put(CharacterType.DIGIT, digitMap);
		
		HashMap<String, Integer> lowercaseMap = new HashMap<String, Integer>();
		lowercaseMap.put(BASE, 26);
		lowercaseMap.put(START, (int)'a');
		characterMap.put(CharacterType.LOWER, lowercaseMap);
		
		HashMap<String, Integer> uppercaseMap = new HashMap<String, Integer>();
		uppercaseMap.put(BASE, 26);
		uppercaseMap.put(START, (int)'A');
		characterMap.put(CharacterType.UPPER, uppercaseMap);
		
		HashMap<String, Integer> otherMap = new HashMap<String, Integer>();
		otherMap.put(BASE, 127);
		otherMap.put(START, 0);
		characterMap.put(CharacterType.OTHER, otherMap);
	}
	
	/**
	 *  This method adds together two instances of StringNumber by incrementing the first argument (base string) by the second argument (increment string)
	 *  
	 *  If the increment string character is an digit, the dominant string character will be incremented forward by the integer value of the digit.
	 *  If the increment string character is a lower case letter, the dominant string character will be incremented forward by the relative position of the increment string from 'a'.
	 *  If the increment string character is an upper case letter, the dominant string character will be incremented forward by the relative position of the increment string from 'A'.
	 *  If the increment string character is any other value, the dominant string character will be incremented forward by the ASCII value of the increment character.
	 *  
	 *  If the base string character is a digit, the string will roll over at '9' back to '0'. 
	 *  If the base string character is a lower case letter, the string will roll over at 'z' back to 'a'.
	 *  If the base string character is an upper case letter, the string will roll over at 'Z' back to 'A'.
	 *  If the base string character is any other value, the string will roll over at 127 (DEL) back to 0 (NUL)
	 *  
	 *  The number of times a value rolls over will be added as an integer to the sum of the next characters in each string
	 *  
	 * @param baseSN
	 * @param incSN
	 * @return
	 */
	public static StringNumber add(StringNumber baseSN, StringNumber incSn) {
		String ret = "";

		Result res = new Result("", 0);
		int carry = 0;
		
		int i = baseSN.getLength() - 1;
		int j = incSn.getLength() - 1;
		while(i > -1 && j > -1) {
			char baseChar = baseSN.charAt(i);
			char incChar = incSn.charAt(j);
			res = addChars(baseChar, incChar, carry);
			ret = res.val + ret;
			carry = res.carry;
			i--;
			j--;
		}
		
		while (i > -1) {
			char baseChar = baseSN.charAt(i);
			res = addChars(baseChar, '0', carry);
			ret = res.val + ret;
			carry = res.carry;
			i--;
		}
		
		while (j > -1) {
			char incChar = incSn.charAt(j);
			res = addChars(incChar, '0', carry);
			ret = res.val + ret;
			carry = res.carry;
			j--;
		}
		
		if (carry != 0)
			ret = carry + ret;
			
		return new StringNumber(ret);
		
	}
	
	/**
	 *  This method multiplies together two instances of StringNumber by multiplying the first argument (base string) by the second argument (increment string)
	 *  
	 *  If the increment string character is an digit, the dominant string character will be multiplied by the integer value of the digit.
	 *  If the increment string character is a lower case letter, the dominant string character will be multiplied by the relative position of the increment string from 'a'.
	 *  If the increment string character is an upper case letter, the dominant string character will be multiplied by the relative position of the increment string from 'A'.
	 *  If the increment string character is any other value, the dominant string character will be multiplied by the ASCII value of the increment character.
	 *  
	 *  If the base string character is a digit, the string will roll over at '9' back to '0'. 
	 *  If the base string character is a lower case letter, the string will roll over at 'z' back to 'a'.
	 *  If the base string character is an upper case letter, the string will roll over at 'Z' back to 'A'.
	 *  If the base string character is any other value, the string will roll over at 127 (DEL) back to 0 (NUL)
	 *  
	 *  The number of times a value rolls over will be added as an integer to the product of the next characters in each string
	 *  
	 * @param baseSN
	 * @param incSN
	 * @return
	 */
	public static StringNumber multiply(StringNumber baseSN, StringNumber incSn) {
		String ret = "";

		Result res = new Result("", 0);
		int carry = 0;
		
		int i = baseSN.getLength() - 1;
		int j = incSn.getLength() - 1;
		while(i > -1 && j > -1) {
			char baseChar = baseSN.charAt(i);
			char incChar = incSn.charAt(j);
			res = multiplyCharacters(baseChar, incChar, carry);
			ret = res.val + ret;
			carry = res.carry;
			i--;
			j--;
		}
		
		while (i > -1) {
			char baseChar = baseSN.charAt(i);
			res = multiplyCharacters(baseChar, '1', carry);
			ret = res.val + ret;
			carry = res.carry;
			i--;
		}
		
		while (j > -1) {
			char incChar = incSn.charAt(j);
			res = multiplyCharacters(incChar, '1', carry);
			ret = res.val + ret;
			carry = res.carry;
			j--;
		}
		
		if (carry != 0)
			ret = carry + ret;
			
		return new StringNumber(ret);
	}
	
	private static Result incrementCharacter(char a, char b, int carry) {
		CharacterType typeA = typeOf(a);
		CharacterType typeB = typeOf(b);
		
		int sum = (int)a - characterMap.get(typeA).get(START)  + (int)b - characterMap.get(typeB).get(START) + carry;
		int resVal = sum % characterMap.get(typeA).get(BASE) + characterMap.get(typeA).get(START);
		int resCarry = sum / characterMap.get(typeA).get(BASE);
		return new Result(Character.toString((char) resVal)  + "", resCarry);
	}
	
	private static Result multiplyCharacters(char a, char b, int carry) {
		CharacterType typeA = typeOf(a);
		CharacterType typeB = typeOf(b);
		
		int product = ((int)a - characterMap.get(typeA).get(START)) * ((int)b - characterMap.get(typeB).get(START)) + carry;
		int resVal = product % characterMap.get(typeA).get(BASE) + characterMap.get(typeA).get(START);
		int resCarry = product / characterMap.get(typeA).get(BASE);
		return new Result(Character.toString((char) resVal)  + "", resCarry);
	}
	
	private static CharacterType typeOf(char a) {
		if (Character.isDigit(a))
			return CharacterType.DIGIT;
		else if (Character.isUpperCase(a))
			return CharacterType.UPPER;
		else if (Character.isLowerCase(a))
			return CharacterType.LOWER;
		else 
			return CharacterType.OTHER;
	}

	private static Result addChars(char a, char b, int carry)
	{
		Result ret = null;
		
		ret = incrementCharacter(a, b, carry);

		return ret;
	}
	
	static class Result {
		public String val;
		public int carry;
		
		public Result(String val, int carry) {
			this.val = val;
			this.carry = carry;
		}
		
		public boolean equals(Object obj) {
			if (this == obj) {
	            return true;
	        }
			
	        if (obj == null || getClass() != obj.getClass()) {
	            return false;
	        }
	        
	        Result r = (Result) obj;
	        
			return this.val == r.val && this.carry == r.carry;
		}
		
	}
}

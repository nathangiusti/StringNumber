package nltest;

import nl.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringNumberMultiplyTests {

	@Test
	void testDigitMultiplication() {
		StringNumberUtils.init();
		StringNumber s1 = new StringNumber("12");
		StringNumber s2 = new StringNumber("9");
		StringNumber ans = new StringNumber("28");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("12");
		s2 = new StringNumber("0009");
		ans = new StringNumber("0018");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("1");
		s2 = new StringNumber("a");
		ans = new StringNumber("0");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("1");
		s2 = new StringNumber("B");
		ans = new StringNumber("1");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("1");
		s2 = new StringNumber("-");
		ans = new StringNumber("45");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
	}

	@Test
	void testCharacterMultiplication() {
		StringNumberUtils.init();
		
		StringNumber s1 = new StringNumber("a");
		StringNumber s2 = new StringNumber("a");
		StringNumber ans = new StringNumber("a");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("a");
		s2 = new StringNumber("b");
		ans = new StringNumber("a");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("AAAAAA");
		s2 = new StringNumber("b");
		ans = new StringNumber("AAAAAA");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("AAAAAA");
		s2 = new StringNumber("1");
		ans = new StringNumber("AAAAAA");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
		
		s1 = new StringNumber("a");
		s2 = new StringNumber("-");
		ans = new StringNumber("a");
		
		s1 = new StringNumber("b");
		s2 = new StringNumber("-");
		ans = new StringNumber("1t");
		assertTrue(ans.equals(StringNumberUtils.multiply(s1, s2)));
	}
}


package nltest;

import nl.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringNumberAdditionTests {

	@Test
	void testDigitAddition() {
		StringNumberUtils.init();
		StringNumber s1 = new StringNumber("123");
		StringNumber s2 = new StringNumber("9");
		StringNumber ans = new StringNumber("132");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("123");
		s2 = new StringNumber("0009");
		ans = new StringNumber("0132");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("1231");
		s2 = new StringNumber("00090");
		ans = new StringNumber("01321");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("0000");
		s2 = new StringNumber("0000000");
		ans = new StringNumber("0000000");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("1111111111");
		s2 = new StringNumber("2222222222");
		ans = new StringNumber("3333333333");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("01");
		s2 = new StringNumber("999");
		ans = new StringNumber("1000");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("10");
		s2 = new StringNumber("0BB");
		ans = new StringNumber("021");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("1");
		s2 = new StringNumber("-");
		ans = new StringNumber("46");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
	}
	
	
	@Test
	void testCharacterAddition() {
		StringNumberUtils.init();
		
		StringNumber s1 = new StringNumber("a");
		StringNumber s2 = new StringNumber("a");
		StringNumber ans = new StringNumber("a");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("aaaaaa");
		s2 = new StringNumber("bbbbbb");
		ans = new StringNumber("bbbbbb");
		
		s1 = new StringNumber("zzzzz");
		s2 = new StringNumber("b");
		ans = new StringNumber("1aaaaa");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("AAAAAA");
		s2 = new StringNumber("bbbbbb");
		ans = new StringNumber("BBBBBB");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("a");
		s2 = new StringNumber("-");
		ans = new StringNumber("1t");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
	}
	
	@Test
	void testPunctuationAddition() {
		StringNumberUtils.init();
		
		StringNumber s1 = new StringNumber("-");
		StringNumber s2 = new StringNumber("a");
		StringNumber ans = new StringNumber("-");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
		
		s1 = new StringNumber("-");
		s2 = new StringNumber("5");
		ans = new StringNumber("2");
		assertTrue(ans.equals(StringNumberUtils.add(s1, s2)));
	}
}

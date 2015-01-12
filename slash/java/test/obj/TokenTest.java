package obj;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TokenTest {

	Token single, stream;
	@Before
	public void setUp() throws Exception {
		single = new Token("Single", POSTag.Adjective);
		TokenStream tokens= new TokenStream();
		tokens.add(new Token("These",POSTag.Noun));
		tokens.add(new Token("are",POSTag.Verb));
		tokens.add(new Token("tokens",POSTag.Noun));
		stream = new Token(tokens);
	}

	@Test
	public void testTokenStringPOSTag() {
		for(POSTag tag: POSTag.values()){
			if(tag!= POSTag.Exception){
				assertTrue(tag+" success",new Token("test",tag)!=null);
			}
		}
		try{
			new Token("test",POSTag.Exception);
			fail("POSTag.Exception reject");
		}catch(IllegalArgumentException e){
			assertTrue("POSTag.Exception reject", true);
		}
		try{
			new Token("test",null);
			fail("Null reject");
		}catch(IllegalArgumentException e){
			assertTrue("Null reject", true);
		}
	}

	@Test
	public void testSize() {
		assertTrue("Single size =1", single.size()==1);
		assertTrue("Streams size = 3", stream.size() ==3);
	}

	@Test
	public void testToString() {
		assertTrue(single.toString().equalsIgnoreCase("single"));
		assertTrue(stream.toString().equalsIgnoreCase("These are tokens"));
	}

	@Test
	public void testEqualsObject() {
		assertTrue(single.equals(new Token("Single", POSTag.Verb)));
		assertTrue(single.equals(new Token("single", POSTag.Adverb)));
		assertTrue(single.equals(new Token("SIngle", POSTag.Adverb)));
		assertFalse(single.equals(new Token("Singles", POSTag.Adverb)));
		assertFalse(single.equals(new Token("Singl", POSTag.Adverb)));
		assertFalse(single.equals(new Token("ngl", POSTag.Adverb)));
		
		TokenStream tokens= new TokenStream();
		tokens.add(new Token("These",POSTag.Noun));
		tokens.add(new Token("are",POSTag.Verb));
	//	
		Token teststream = new Token(tokens);
		assertFalse(stream.equals(teststream));
		tokens.add(new Token("tokens",POSTag.Noun));
		assertTrue(stream.equals(teststream));
		tokens.add(new Token("tokens",POSTag.Noun));
		assertFalse(stream.equals(teststream));
		
	}

}

package Ver1.T9Implementation;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class T9Test extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public T9Test(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(T9Test.class);
	}

	public void testgetDigit() {
		assertEquals('1',T9.getDigit('1'));
		assertEquals('2',T9.getDigit('a'));
		assertEquals('1',T9.getDigit('.'));
		assertEquals('9',T9.getDigit('9'));
		assertEquals('9',T9.getDigit('x'));
	}
	
	public void testgenerateSeq() {
		
		assertEquals("4663", T9.generateSeq("good"));
		assertEquals("4663", T9.generateSeq("gone"));
		assertEquals("4663", T9.generateSeq("home"));
		assertEquals("4663", T9.generateSeq("goof"));

	}
	
	public void testinsert() {
		

		T9.insert("4663", "good");
		T9.insert("4663", "gone");
		T9.insert("12", "go");
		T9.insert("1", "g");
		T9.insert("4663", "gone");
		T9.insert("1", "g");

		T9.insert("4663", "gone");
		T9.insert("4663", "good");
		T9.insert("4663", "good");

		T9.insert("4663", "goog");
		T9.insert("4663", "good");

		T9.insert("4663", "go");
		T9.insert("4663", "go");
		T9.insert("4663", "go");
		T9.insert("4663", "go");
		T9.insert("4663", "go");
		T9.insert("4663", "goog");
		
		
		System.out.println(T9.T9dictionary);

		System.out.println(T9.T9dictionary.get("4663").element());
		System.out.println(T9.T9dictionary.get("4663").remove(T9.T9dictionary.get("4663").element()));
		System.out.println(T9.T9dictionary);
		
		System.out.println(T9.T9dictionary.get("4663").element());
		System.out.println(T9.T9dictionary.get("4663").remove(T9.T9dictionary.get("4663").element()));
		System.out.println(T9.T9dictionary);

		
		System.out.println(T9.T9dictionary.get("4663").element());
		System.out.println(T9.T9dictionary.get("4663").remove(T9.T9dictionary.get("4663").element()));
		System.out.println(T9.T9dictionary);

		
		System.out.println(T9.T9dictionary.get("4663").element());
		System.out.println(T9.T9dictionary.get("4663").remove(T9.T9dictionary.get("4663").element()));
		System.out.println(T9.T9dictionary);

	}
	
	public void testreadDict() {
		T9.makeDictionary();
		System.out.println(T9.T9dictionary);
		
		System.out.println(T9.T9dictionary.get("4663"));
		System.out.println(T9.T9dictionary.get("4663").contains(new Word("home")));
		System.out.println(T9.T9dictionary.get("4663").contains(new Word("gone")));
		System.out.println(T9.T9dictionary.get("4663").contains(new Word("goof")));
		System.out.println(T9.T9dictionary.get("4663").contains(new Word("hoof")));
		System.out.println(T9.T9dictionary.get("4663").contains(new Word("good")));

	}
	
	public void testrespondQuery() throws SequenceNotFoundException{
		T9.makeDictionary();
		System.out.println(T9.T9dictionary);
		String args[] = {"4663","2","69"};
		String respond = T9.respondQuery(args);
		System.out.println(T9.notThisWord("466",3));
		System.out.println(respond);
		
	}

}

/**
 * Tester for ex3.
 * Created by:Evyatar
 * version 1.0
 *
 * You are more then welcome to improve the tester - add tests, fix bugs, improve the current ones or make
 * the tester more accessible. If you do so, update the changelog below.
 *
 * Changelog:
 * 29/4/18 - Evyatar - Created.
 */

//הסבר על הטסטר -
// א. בטסטר זה נשתמש בפריימוורק של junit, בשלב הראשון אתם צריכים לייבא את הספרייה של junit.
// איך מייבאים? - שמים את הסמן על השגיעה בשורה 17 ואז alt+enter
// אחרי זה פשוט תריצו את המחלקה הזאת

// יש לנו 3*3  טסטים - שלושה טסטים לכל סוג של מחלקה (open, close, fasad), הטסטים עבור כל מחלקה הם:
// sanity test - משחק עם הוספה ומחיקה של איבר אחד מהקבוצה
// big numbers -  משחק עם הוספה, מחיקה, ושאר הפונציות על מספר גדול של מחרוזות, אתם יכולים שם לשנות את המספר
// random  -  משחק עם הרבה סטרינגים רנדומליים

// * כל טסט מנסה את כל הסוגים של הבנאים
// עבור fasad כל טסט רץ על שלושת מבני הנתונים שאמורים להיות נתמכים (Treeset, linkedList, HashSet)

// * שימו לב שהטסטר לא בודק כל מני דברים:
// 1. את המחלקה SimpleSetPerformaceAnalayzer.
// 2. את כל הנושא הreHashing, אין לי יכולת לבדוק אם הגדלתם והקטנתם את הקבוצות כמו שצריך.

// אם אתם רוצים לוותר על אחד מהטסטים יש לכם שתי אופציות:
// או להסתיר את הפונקציה באמצצאות "//" השין והטוב
// או להחליף את test@ ב Ignore@


import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;


import java.nio.charset.Charset;
import java.util.*;


public class Tester_OOP_EX3 {


	@Ignore
	public void exsampleToIgnoredTest(){
		// אם תחליפו את test@ ב Ignore@ כמו כאן אז הטסט לא יבוצע
	}


	// openHashSet tests
	@Test
	public void sanity_check_OpenHashSet() {
		sanityTest_SimpleHashSet(new OpenHashSet());
		sanityTest_SimpleHashSet(new OpenHashSet(0.60f, 0.40f));
		java.lang.String[] data1 = {};
		sanityTest_SimpleHashSet(new OpenHashSet(data1));
	}

	@Test
	public void big_numbers_OpenHashSet() {
		bigNumbers_SimpleHashSet(new OpenHashSet());
		bigNumbers_SimpleHashSet(new OpenHashSet(0.75f, 0.25f));
		java.lang.String[] data1 = {};
		bigNumbers_SimpleHashSet(new OpenHashSet(data1));
	}

	@Test
	public void random_and_big_numbers_OpenHashSet() {
		random_one_by_one_SimpleHash(new OpenHashSet());
		random_insert_in_constructor_SimpleHash(0);
	}

	// closedHashSet tests
	@Test
	public void sanity_check_ClosedHashSet() {

		sanityTest_SimpleHashSet(new ClosedHashSet());
		sanityTest_SimpleHashSet(new ClosedHashSet(0.60f, 0.40f));
		java.lang.String[] data1 = {};
		sanityTest_SimpleHashSet(new ClosedHashSet(data1));
	}

	@Test
	public void big_numbers_ClosedHashSet() {

		bigNumbers_SimpleHashSet(new ClosedHashSet());
		bigNumbers_SimpleHashSet(new ClosedHashSet(0.75f, 0.25f));
		java.lang.String[] data1 = {};
		bigNumbers_SimpleHashSet(new ClosedHashSet(data1));
	}

	@Test
	public void random_and_big_numbers_ClosedHashSet() {
		random_one_by_one_SimpleHash(new ClosedHashSet());
		random_insert_in_constructor_SimpleHash(1);
	}

	 //fasad tests

	@Test
	public void sanity_check_Fasad() {
		sanityTest_SimpleHashSet(new CollectionFacadeSet(new HashSet<>()));
		sanityTest_SimpleHashSet(new CollectionFacadeSet(new LinkedList<>()));
		sanityTest_SimpleHashSet(new CollectionFacadeSet(new TreeSet<>()));
	}

	@Test
	public void big_numbers_Fasad() {
		bigNumbers_SimpleHashSet(new CollectionFacadeSet(new HashSet<>()));
		bigNumbers_SimpleHashSet(new CollectionFacadeSet(new LinkedList<>()));
		bigNumbers_SimpleHashSet(new CollectionFacadeSet(new TreeSet<>()));
	}


	@Test
	public void random_and_big_numbers_Fasad() {
		random_one_by_one_SimpleHash(new CollectionFacadeSet(new HashSet<>()));
		random_one_by_one_SimpleHash(new CollectionFacadeSet(new LinkedList<>()));
		random_one_by_one_SimpleHash(new CollectionFacadeSet(new TreeSet<>()));
	}


	// helper functions
	private void sanityTest_SimpleHashSet(SimpleSet hashSet) {
		final String s = "";

		//add empty string test
		assertEquals("there is nothing inside", 0, hashSet.size());
		assertFalse("there is nothing inside", hashSet.contains(s));
		assertTrue("add an item", hashSet.add(s));
		assertTrue("now the string should exists", hashSet.contains(s));
		assertEquals("after we added an item, the size should be 1", 1, hashSet.size());
		assertFalse("try to add the string when its already inside", hashSet.add(s));
		assertTrue("delete the string", hashSet.delete(s));
		assertFalse("now, the set should not contain the string", hashSet.contains(s));
		assertFalse("try to delete it again, should return false", hashSet.delete(s));
		assertEquals("the size should return to 0.", 0, hashSet.size());
	}

	/*
	add 1000 strings to the given SimpleSet and then remove them. the strings are "1", "2", "3"....
	 */
	private void bigNumbers_SimpleHashSet(SimpleSet hashSet) {
		int iterations;  // try different values, I found that sometimes you can find problems this way.
//		iterations = 3;
//		iterations = 5;
//		iterations = 23;
//		iterations = 100;
		iterations = 1000;

		//addition
		for (int i = 0; i < iterations; i++) {
			//(Integer.toString(i)): 1-> "1"

			// size
			assertEquals(i, hashSet.size());

			// should not contain it now
			assertFalse("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// should be able to add it now
			assertTrue("problem with the string " + i, hashSet.add(Integer.toString(i)));

			// should not be able to add it now
			assertFalse("problem with the string " + i, hashSet.add(Integer.toString(i)));

			// should contain it now
			assertTrue("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// size
			assertEquals(i + 1, hashSet.size());
		}

		// deletion
		for (int i = 0; i < iterations; i++) {
			// size
			assertEquals("problem with the string " + i, iterations - i, hashSet.size());

			// should contain it now
			assertTrue("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// should be able to delete it now
			assertTrue("problem with the string " + i, hashSet.delete(Integer.toString(i)));

			// should not be able to delete it now
			assertFalse("problem with the string " + i, hashSet.delete(Integer.toString(i)));

			// should not contain it now
			assertFalse("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// size
			assertEquals("problem with the string " + i, iterations - i - 1, hashSet.size());
		}
	}

	/*
	add 1000 strings to the given SimpleSet and then remove them. the strings are generated randomly.
	to test if the functions behave as they should we compare the results to a java's HashSet.
	 */
	private void random_one_by_one_SimpleHash(SimpleSet myHashSet) {

		int numOfStrings = 1000;

		// generate an array of random strings
		String[] randomStrings = generateRandomStrings(numOfStrings);

		// a regular HashSet (implemented by java) to compare wish "myHashSet".
		HashSet<String> classicSet = new HashSet<>();

		// add the string one by one
		for (int i = 0; i < numOfStrings; i++) {
			assertEquals("problem with string number " + i,
					classicSet.add(randomStrings[i]), myHashSet.add(randomStrings[i]));
			assertEquals(classicSet.size(), myHashSet.size());
		}

		// check if the set contains them
		for (int i = 0; i < numOfStrings; i++) {
			assertTrue("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}

		// remove the string one by one
		for (int i = 0; i < numOfStrings; i++) {
			assertEquals("problem with string number " + i,
					classicSet.remove(randomStrings[i]), myHashSet.delete(randomStrings[i]));
			assertEquals(classicSet.size(), myHashSet.size());
		}

		// check if the set contains them (now it should not)
		for (int i = 0; i < numOfStrings; i++) {
			assertFalse("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}
	}

	/*
	add 1000 strings to the given SimpleSet in the constructor. the strings are generated randomly.
	to test if the functions behave as they should we compare the results to a java's HashSet.
	 */
	private void random_insert_in_constructor_SimpleHash(int typeOfHashSet) {

		// generate an array of random strings
		int numOfStrings = 1000;
		String[] randomStrings = generateRandomStrings(numOfStrings);

		// a regular HashSet (implemented by java) to compare wish "myHashSet".
		HashSet<String> classicSet = new HashSet<>(Arrays.asList(randomStrings));


		SimpleSet myHashSet;
		if (typeOfHashSet == 0)
			myHashSet = new OpenHashSet(randomStrings);
		else if (typeOfHashSet == 1)
			myHashSet = new ClosedHashSet(randomStrings);
		else {
			myHashSet = null;
			fail("you changed something in the tester.\n " +
					"when calling 'random_insert_in_constructor_SimpleHash',\n" +
					"typeOfHashSet must be 0 or 1");
		}


		// check if the size of the set is as it should be
		assertEquals(classicSet.size(), myHashSet.size());


		// check if the set contains them
		for (int i = 0; i < numOfStrings; i++) {
			assertTrue("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}

		// remove the string one by one
		for (int i = 0; i < numOfStrings; i++) {
			assertEquals("problem with string number " + i,
					classicSet.remove(randomStrings[i]), myHashSet.delete(randomStrings[i]));
			assertEquals(classicSet.size(), myHashSet.size());
		}

		// check if the set contains them (now it should not)
		for (int i = 0; i < numOfStrings; i++) {
			assertFalse("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}
	}

	/*
	helper function
	generate an array of random strings,
	 */
	private String[] generateRandomStrings(int numOfStrings) {
		Random random = new Random();
		String[] randomStrings = new String[numOfStrings];
		for (int i = 0; i < numOfStrings; i++) {
			byte[] array = new byte[random.nextInt(10)];
			random.nextBytes(array);
			randomStrings[i] = new String(array, Charset.forName("UTF-8"));
		}
		return randomStrings;
	}


}

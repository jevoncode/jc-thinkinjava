package com.jc.annotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.jc.annotations.atunit.TestObjectCreate;
import com.jc.annotations.atunit.TestProperty;
import com.jc.io.util.OSExecute;

/**
 * 用@TestProperty注解表明哪些是测试数据
 * 
 * 这个测试方法有点乱，跟业务耦合太高了
 * @author jevoncode
 *
 */
public class AtUnitExample4 {
	static String theory = "All brontosauruses " + "are thin at one end, much MUCH thicker in the "
			+ "middle, and then thin again at the far end.";
	private String word;
	private Random rand = new Random(); // Time-based seed

	public AtUnitExample4(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public String scrambleWord() {
		List<Character> chars = new ArrayList<Character>();
		for (Character c : word.toCharArray())
			chars.add(c);
		Collections.shuffle(chars, rand);
		StringBuilder result = new StringBuilder();
		for (char ch : chars)
			result.append(ch);
		return result.toString();
	}

	@TestProperty
	static List<String> input = Arrays.asList(theory.split(" "));
	@TestProperty
	static Iterator<String> words = input.iterator();

	@TestObjectCreate
	static AtUnitExample4 create() {
		if (words.hasNext())
			return new AtUnitExample4(words.next());
		else
			return null;
	}

	@Test
	boolean words() {
		System.out.println("'" + getWord() + "'");
		return getWord().equals("are");
	}

	@Test
	boolean scramble1() {
		// Change to a specific seed to get verifiable results:
		rand = new Random(47);
		System.out.println("'" + getWord() + "'");
		String scrambled = scrambleWord();
		System.out.println(scrambled);
		return scrambled.equals("lAl");
	}

	@Test
	boolean scramble2() {
		rand = new Random(74);
		System.out.println("'" + getWord() + "'");
		String scrambled = scrambleWord();
		System.out.println(scrambled);
		return scrambled.equals("tsaeborornussu");
	}

	public static void main(String[] args) throws Exception {
		System.out.println("starting");
		OSExecute.command("java -cp E:/git/jc-thinkinjava/target/classes com.jc.annotations.atunit.AtUnit E:/git/jc-thinkinjava/target/classes/com/jc/annotations/AtUnitExample4");
	}
}

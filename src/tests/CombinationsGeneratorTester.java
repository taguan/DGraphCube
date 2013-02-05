package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;

import materialization.CombinationsGenerator;

import org.junit.Test;

public class CombinationsGeneratorTester {


	@Test
	public void testComb() {
		LinkedList<String> combinations = CombinationsGenerator.comb(3, 5);
		assertEquals(combinations.size(),10);
		assertEquals(combinations.pop(),"0,1,2");
		assertEquals(combinations.pop(),"0,1,3");
		assertEquals(combinations.pop(),"0,1,4");
		assertEquals(combinations.pop(),"0,2,3");
		assertEquals(combinations.pop(),"0,2,4");
		assertEquals(combinations.pop(),"0,3,4");
		assertEquals(combinations.pop(),"1,2,3");
		assertEquals(combinations.pop(),"1,2,4");
		assertEquals(combinations.pop(),"1,3,4");
		assertEquals(combinations.pop(),"2,3,4");
	}

}

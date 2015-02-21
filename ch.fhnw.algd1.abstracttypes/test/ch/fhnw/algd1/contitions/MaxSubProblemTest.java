package ch.fhnw.algd1.contitions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd1.conditions.MaxSubProblem;

public class MaxSubProblemTest {

	private MaxSubProblem ms;
	
	@Before
	public void init(){
		ms = new MaxSubProblem();
	}
	
	@Test
	public void test1(){
		int[] numbers = new int[]{1, 3, -5, 3, 3, 2, -9, -2};
		assertEquals(8, ms.maxSub(numbers));
	}
	
	@Test
	public void test2(){
		int[] numbers = new int[]{31, -41, 59, 26, -53, 58, 97, -93, -23};
		assertEquals(187, ms.maxSub(numbers));
	}

	@Test
	public void testMaxSeqStartingAt0(){
		int[] numbers = new int[]{41, -31, 59, -97, -53, -58, 26};
		assertEquals(69, ms.maxSub(numbers));
	}
	
	@Test
	public void testMaxSeqEndingAtEnd(){
		int[] numbers = new int[]{31, -41, 59, 26, -53, 58, 97};
		assertEquals(187, ms.maxSub(numbers));
	}
	
}

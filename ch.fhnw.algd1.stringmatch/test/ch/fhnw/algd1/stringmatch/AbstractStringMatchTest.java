package ch.fhnw.algd1.stringmatch;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.algd1.stringmatch.framework.IStringMatch;

public abstract class AbstractStringMatchTest {
	
	private IStringMatch matcher;
	
	protected abstract IStringMatch getMatcher();
	
	@Before
	public void init(){
		matcher = getMatcher();
	}

	
	/*
	 * Test-Cases
	 */
	@Test
	public void testEqualPatternAndText() {
		assertEquals(0, matcher.firstMatch("Algorithmen", "Algorithmen"));
	}
	
	@Test(timeout=1000)
	public void testMatchAtStart() {
		assertEquals(0, matcher.firstMatch("Algorithmen und Datenstrukturen", "Algorithmen"));
	}
	
	@Test(timeout=1000)
	public void testMatchAtEnd() {
		assertEquals(20, matcher.firstMatch("Datenstrukturen und Algorithmen", "Algorithmen"));
	}
	
	@Test(timeout=1000)
	public void testNoMatch() {
		assertEquals(-1, matcher.firstMatch("Das Modul Algd1 findet statt.", "Algorithmen"));
	}
	
	@Test(timeout=1000)
	public void testFindFirstMatch() {
		assertEquals(8, matcher.firstMatch("Es gibt Algorithmen1 und Algorithmen2.", "Algorithmen"));
	}
	
	@Test(timeout=1000)
	public void testLargerPatternThanText(){
		assertEquals(-1, matcher.firstMatch("Hallo", "HalloWelt"));
	}
	
	@Test(timeout=1000)
	public void testSentence1(){
		assertEquals(31, matcher.firstMatch("hin oder her, suchen ist nicht schwer!", "schwer"));
	}
	
	@Test(timeout=1000)
	public void testSentence2(){
		assertEquals(-1, matcher.firstMatch("hin oder her, suchen ist nicht schwer!", "hin und her"));
	}
	
	@Test(timeout=1000)
	public void testBMResetOfPatternPos(){
		assertEquals(-1, matcher.firstMatch("abdcba", "cbd"));
	}
	
}

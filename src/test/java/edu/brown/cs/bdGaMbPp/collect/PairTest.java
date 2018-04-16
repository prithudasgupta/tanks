package edu.brown.cs.bdGaMbPp.collect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.brown.cs.bdGaMbPp.Collect.Pair;

public class PairTest {

  @Test
  public void pairConstruction() {
    Pair<String, String> testPair = new Pair<String, String>("a", "b");
    Pair<String, Integer> otherPair = new Pair<String, Integer>("c", 1);

    assertNotNull(testPair);
    assertEquals(testPair.getFirst(), "a");
    assertEquals(testPair.getSecond(), "b");

    assertNotNull(otherPair);
    assertEquals(otherPair.getFirst(), "c");
    assertEquals(otherPair.getSecond(), new Integer(1));
  }

  @Test
  public void pairEquality() {
    Pair<String, String> testPair = new Pair<String, String>("a", "b");
    Pair<String, Integer> otherPair = new Pair<String, Integer>("c", 1);
    Pair<String, String> anotherPair = new Pair<String, String>("a", "b");

    assertTrue(testPair.equals(anotherPair));
    assertFalse(otherPair.equals(anotherPair));
    assertFalse(testPair.equals(otherPair));
    assertEquals(testPair.toString(), "(a, b)");
  }

}

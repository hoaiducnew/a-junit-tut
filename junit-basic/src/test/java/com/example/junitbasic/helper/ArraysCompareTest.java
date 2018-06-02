package com.example.junitbasic.helper;

import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;
import org.junit.Test;

public class ArraysCompareTest {

  @Test
  public void testArraySort_RandomArray() {
    int[] numbers = {12, 3, 4, 1};
    int[] expected = {1, 3, 4, 12};
    
    Arrays.sort(numbers);
    assertArrayEquals(expected, numbers);
  }

  @Test(expected = NullPointerException.class)
  public void testArraySort_NullArray() {
    int[] numbers = null;
    Arrays.sort(numbers);
  }
  
  @Test
  public void testArraySort_EmptyArray() {
    int[] numbers = {};
    Arrays.sort(numbers);
  }
  
  @Test(timeout = 100)
  public void testArraySort_Performance() {
    int[] numbers = {12, 3, 4, 1};
    for (int i = 1; i <= 1000000; i++) {
      numbers[0] = i;
      Arrays.sort(numbers);
    }
  }
}

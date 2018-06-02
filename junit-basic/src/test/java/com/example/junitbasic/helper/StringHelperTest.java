package com.example.junitbasic.helper;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StringHelperTest {

  StringHelper helper = new StringHelper();

  @Test
  public void testTruncateAInFirst2Positions_AinFirst2Positions() {
    assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
  }

  @Test
  public void testTruncateAInFirst2Positions_AinFirstPosition() {
    assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
  }
  
}

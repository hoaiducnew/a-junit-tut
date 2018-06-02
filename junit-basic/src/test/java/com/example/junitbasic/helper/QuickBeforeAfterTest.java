package com.example.junitbasic.helper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuickBeforeAfterTest {

  @BeforeClass
  public static void beforeClass() {
    System.out.println("Before Class");
  }
  
  @AfterClass
  public static void afterClass() {
    System.out.println("After Class");
  }
  
  @Before
  public void setUp() {
    System.out.println("Before Test");
  }

  @After
  public void tearDown() {
    System.out.println("After Test");
  }

  @Test
  public void test1() {
    System.out.println("test1 executed");
  }

  @Test
  public void test2() {
    System.out.println("test2 executed");
  }
  
}

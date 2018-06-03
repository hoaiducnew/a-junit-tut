package com.example.junitbasic.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.Test;

public class ListTest {

  @Test
  public void letsMockListSizeMethod() {
    List listMock = mock(List.class);
    when(listMock.size()).thenReturn(2);
    assertEquals(2, listMock.size());
    assertEquals(2, listMock.size());
    assertEquals(2, listMock.size());
  }

  @Test
  public void letsMockListSize_ReturnMultipleValues() {
    List listMock = mock(List.class);
    when(listMock.size()).thenReturn(2).thenReturn(3);
    assertEquals(2, listMock.size());
    assertEquals(3, listMock.size());
  }

  @Test
  public void letsMockListGet() {
    List listMock = mock(List.class);
    when(listMock.get(0)).thenReturn("Duke");
    assertEquals("Duke", listMock.get(0));
    assertNull(listMock.get(1));
  }

  @Test
  public void letsMockListGetWithAny() {
    List<String> listMock = mock(List.class);
    when(listMock.get(anyInt())).thenReturn("Duke");
    // If you are using argument matchers, all arguments
    // have to be provided by matchers.
    assertEquals("Duke", listMock.get(0));
    assertEquals("Duke", listMock.get(11));
  }
  
  @Test(expected = RuntimeException.class)
  public void letsMockList_throwAnException() {
    List<String> listMock = mock(List.class);
    when(listMock.get(anyInt())).thenThrow(new RuntimeException("An exception"));
    listMock.get(100);
  }
}

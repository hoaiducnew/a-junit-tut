package com.example.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SpyTest {

  @Test
  public void creatingASpyOnArrayList() {
    List<String> listSpy = spy(ArrayList.class);
    listSpy.add("Duke");
    listSpy.add("Mai");

    verify(listSpy).add("Duke");
    verify(listSpy).add("Mai");

    assertEquals(2, listSpy.size());
    assertEquals("Duke", listSpy.get(0));
  }

  @Test
  public void creatingASpyOnArrayList_overridingSpecificMethods() {
    List<String> listSpy = spy(ArrayList.class);
    listSpy.add("Duke");
    listSpy.add("Mai");

    stub(listSpy.size()).toReturn(-1);

    assertEquals(-1, listSpy.size());
    assertEquals("Ranga", listSpy.get(0));

    // @Spy Annotation
  }

}

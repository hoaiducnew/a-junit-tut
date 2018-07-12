package com.example.mockitotut01.data.stub.business;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import com.example.business.TodoBusinessImpl;
import com.example.data.api.TodoService;

public class TodoBusinessImplMockTest {

  @Test
  public void usingAMock() {
    TodoService todoServiceMock = mock(TodoService.class);
    
    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
    
    when(todoServiceMock.retrieveTodos("Duke")).thenReturn(todos);
    
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Duke");
    assertEquals(2, filteredTodos.size());
  }

  @Test
  public void usingBDD() {
    TodoService todoServiceMock = mock(TodoService.class);
    
    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
    
    // Given 
    given(todoServiceMock.retrieveTodos("Duke")).willReturn(todos);
    
    // When
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Duke");
    
    // Then
    assertThat(filteredTodos.size(), is(2));
  }
}

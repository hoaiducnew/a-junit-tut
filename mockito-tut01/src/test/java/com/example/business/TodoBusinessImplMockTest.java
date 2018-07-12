package com.example.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
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
    
    // given 
    given(todoServiceMock.retrieveTodos("Duke")).willReturn(todos);
    
    // when
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Duke");
    
    // then
    assertThat(filteredTodos.size(), is(2));
  }
  
  @Test
  public void testDeleteTodosNotRelatedToSpring_usingBDD() {
    TodoService todoServiceMock = mock(TodoService.class);

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    // given
    given(todoServiceMock.retrieveTodos("Duke")).willReturn(todos);

    // when
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Duke");

    // then
    verify(todoServiceMock).deleteTodo("Learn to Dance");

    verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");

    verify(todoServiceMock, never()).deleteTodo("Learn Spring");

    verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
    
    verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
    
    verify(todoServiceMock, atLeast(1)).deleteTodo("Learn to Dance");
    
    then(todoServiceMock).should().deleteTodo("Learn to Dance");
    
    then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
    then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
  }
  
  @Test
  public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
    // Declare Argument Captor
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

    // Given
    TodoService todoServiceMock = mock(TodoService.class);

    List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

    given(todoServiceMock.retrieveTodos("Duke")).willReturn(todos);

    // When
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Duke");
    
    // Then
    then(todoServiceMock).should().deleteTodo(argumentCaptor.capture());

    assertThat(argumentCaptor.getValue(), is("Learn to Dance"));
  }
  
  @Test
  public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture2() {
    // Declare Argument Captor
    ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
    
    // Given
    TodoService todoServiceMock = mock(TodoService.class);
    
    List<String> todos = Arrays.asList("Learn Hibernate", "Learn Spring", "Learn to Dance");
    
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
    
    given(todoServiceMock.retrieveTodos("Duke")).willReturn(todos);
    
    // When
    todoBusinessImpl.deleteTodosNotRelatedToSpring("Duke");
    
    // Then
    then(todoServiceMock).should(times(2)).deleteTodo(argumentCaptor.capture());
    
    assertThat(argumentCaptor.getAllValues().size(), is(2));
  }
}

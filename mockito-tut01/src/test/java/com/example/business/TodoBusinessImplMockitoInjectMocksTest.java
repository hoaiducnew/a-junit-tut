package com.example.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.example.data.api.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockitoInjectMocksTest {
  @Mock
  TodoService todoService;

  @InjectMocks
  TodoBusinessImpl todoBusinessImpl;

  @Captor
  ArgumentCaptor<String> stringArgumentCaptor;

  @Test
  public void usingMockito() {
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    when(todoService.retrieveTodos("Duke")).thenReturn(allTodos);

    List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Duke");
    assertEquals(2, todos.size());
  }

  @Test
  public void usingMockito_UsingBDD() {
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    // given
    given(todoService.retrieveTodos("Duke")).willReturn(allTodos);

    // when
    List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Duke");

    // then
    assertThat(todos.size(), is(2));
  }

  @Test
  public void letsTestDeleteNow() {

    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

    when(todoService.retrieveTodos("Duke")).thenReturn(allTodos);

    todoBusinessImpl.deleteTodosNotRelatedToSpring("Duke");

    verify(todoService).deleteTodo("Learn to Dance");

    verify(todoService, never()).deleteTodo("Learn Spring MVC");

    verify(todoService, never()).deleteTodo("Learn Spring");

    verify(todoService, times(1)).deleteTodo("Learn to Dance");
  }

  @Test
  public void captureArgument() {
    List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
    when(todoService.retrieveTodos("Duke")).thenReturn(allTodos);

    todoBusinessImpl.deleteTodosNotRelatedToSpring("Duke");
    verify(todoService).deleteTodo(stringArgumentCaptor.capture());

    assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
  }
}

package com.example.junitbasic.business;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import com.example.junitbasic.data.api.TodoService;
import com.example.junitbasic.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

  @Test
  public void testRetrieveTodosRelatedToSpring_usingAStub() {
    TodoService todoServiceStub = new TodoServiceStub();
    TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
    
    List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
    
    assertEquals(2, filteredTodos.size());
  }
}

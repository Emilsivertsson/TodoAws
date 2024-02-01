package org.codeforpizza.todoaws.service;

import org.codeforpizza.todoaws.models.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoServiceTest {

    @Mock
    private TodoService todoService;

    Todo todo1 = new Todo(1L,"First title", "First description", false);
    Todo todo2 = new Todo(2L,"Second title", "Second description", true);
    Todo todo3 = new Todo(3L,"Third title", "Third description", false);
    Todo todo4 = new Todo(4L,"Fourth title", "Fourth description", true);
    Todo todo5 = new Todo(5L,"Fifth title", "Fifth description", false);



    @BeforeEach
    void setUp() {
        todoService = Mockito.mock(TodoService.class);


    }


    @Test
    @DisplayName("1.Get all todos")
    void getAllTodos() {
        List<Todo> todos = Arrays.asList(todo1, todo2, todo3, todo4, todo5);

        when(todoService.getAllTodos()).thenReturn(ResponseEntity.ok(todos));
        ResponseEntity<List<Todo>> response = todoService.getAllTodos();

        assertEquals(5, response.getBody().size());

    }
    @Test
    @DisplayName("2.Get one todo")
    void getOneTodo() {
        when(todoService.getOneTodo(1L)).thenReturn(ResponseEntity.ok(todo1));
        Todo fromdb = todoService.getOneTodo(1L).getBody();

        assertEquals(todo1.getTitle(), fromdb.getTitle());
    }

    @Test
    @DisplayName("5.Delete todo")
    void deleteTodo() {
        when(todoService.deleteTodo (1L)).thenReturn(ResponseEntity.ok("Todo deleted"));
        String response = todoService.deleteTodo (1L).getBody();

        assertEquals("Todo deleted", response);
    }


    @Test
    @DisplayName("4.Update todo")
    void updateTodo() {
        when(todoService.getOneTodo(3L)).thenReturn(ResponseEntity.ok(todo3));
        Todo todoUpdated = new Todo(3L,"Third title", "Third description", false);
        when(todoService.updateTodo (3L, todo3)).thenReturn(ResponseEntity.ok(todoUpdated));

        Todo todo = todoService.getOneTodo(3L).getBody();
        todo.setTitle("Updated title");
        todoService.updateTodo(3L, todo);
        Todo todo1 = todoService.getOneTodo(3L).getBody();
        assertEquals(todo.getTitle(), todo1.getTitle());
    }

    @Test
    @DisplayName("3.Create todo")
    void createTodo() {
        when(todoService.getOneTodo(6L)).thenReturn(ResponseEntity.ok(todo1));
        Todo todo = new Todo("First title", "New description", false);
        todoService.createTodo(todo);
        Todo todofromdb = todoService.getOneTodo(6L).getBody();
        assertEquals(todo.getTitle(), todofromdb.getTitle());
    }
}
package org.codeforpizza.todoaws.controller;

import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoControllerTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    void getAllTodos() {
        // Arrange
        Todo todo1 = new Todo(1L, "Task 1", "Description 1", false);
        Todo todo2 = new Todo(2L, "Task 2", "Description 2", true);
        List<Todo> todos = Arrays.asList(todo1, todo2);

        when(todoService.getAllTodos()).thenReturn(ResponseEntity.ok(todos));

        // Act
        ResponseEntity<List<Todo>> responseEntity = todoController.getAllTodos();

        // Assert
        assertEquals(ResponseEntity.ok(todos), responseEntity);
    }

    @Test
    void getOneTodo() {
        // Arrange
        Long todoId = 1L;
        Todo todo = new Todo(todoId, "Task 1", "Description 1", false);

        when(todoService.getOneTodo(todoId)).thenReturn(ResponseEntity.ok(todo));

        // Act
        ResponseEntity<Todo> responseEntity = todoController.getOneTodo(todoId);

        // Assert
        assertEquals(ResponseEntity.ok(todo), responseEntity);
    }

    @Test
    void createTodo() {
        // Arrange
        Todo todoToCreate = new Todo("New Task", "New Description", false);
        when(todoService.createTodo(any(Todo.class))).thenReturn(ResponseEntity.ok(todoToCreate));

        // Act
        ResponseEntity<Todo> responseEntity = todoController.createTodo(todoToCreate);

        // Assert
        assertEquals(ResponseEntity.ok(todoToCreate), responseEntity);
        verify(todoService).createTodo(any(Todo.class));
    }

    @Test
    void updateTodo() {
        // Arrange
        Long todoId = 1L;
        Todo updatedTodo = new Todo(todoId, "Updated Task", "Updated Description", true);

        when(todoService.updateTodo(todoId, updatedTodo)).thenReturn(ResponseEntity.ok(updatedTodo));

        // Act
        ResponseEntity<Todo> responseEntity = todoController.updateTodo(todoId, updatedTodo);

        // Assert
        assertEquals(ResponseEntity.ok(updatedTodo), responseEntity);
        verify(todoService).updateTodo(todoId, updatedTodo);
    }

    @Test
    void deleteTodo() {
        // Arrange
        Long todoId = 1L;

        when(todoService.deleteTodo(todoId)).thenReturn(ResponseEntity.ok("Todo deleted successfully"));

        // Act
        ResponseEntity<String> responseEntity = todoController.deleteTodo(todoId);

        // Assert
        assertEquals(ResponseEntity.ok("Todo deleted successfully"), responseEntity);
        verify(todoService).deleteTodo(todoId);
    }
}

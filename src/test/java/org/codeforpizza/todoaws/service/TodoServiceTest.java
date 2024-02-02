package org.codeforpizza.todoaws.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.codeforpizza.todoaws.models.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TodoService.class})
@ExtendWith(SpringExtension.class)
class TodoServiceTest {
    @Autowired
    private TodoService todoService;

    /**
     * Method under test: {@link TodoService#getAllTodos()}
     */
    @Test
    void testGetAllTodos() {
        ResponseEntity<List<Todo>> actualAllTodos = todoService.getAllTodos();
        assertNull(actualAllTodos.getBody());
        assertEquals(400, actualAllTodos.getStatusCodeValue());
        assertTrue(actualAllTodos.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link TodoService#getOneTodo(Long)}
     */
    @Test
    void testGetOneTodo() {
        ResponseEntity<Todo> actualOneTodo = todoService.getOneTodo(1L);
        assertNull(actualOneTodo.getBody());
        assertEquals(400, actualOneTodo.getStatusCodeValue());
        assertTrue(actualOneTodo.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link TodoService#deleteTodo(Long)}
     */
    @Test
    void testDeleteTodo() {
        ResponseEntity<String> actualDeleteTodoResult = todoService.deleteTodo(1L);
        assertNull(actualDeleteTodoResult.getBody());
        assertEquals(400, actualDeleteTodoResult.getStatusCodeValue());
        assertTrue(actualDeleteTodoResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link TodoService#updateTodo(Long, Todo)}
     */
    @Test
    void testUpdateTodo() {
        Todo todo = new Todo();
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        ResponseEntity<Todo> actualUpdateTodoResult = todoService.updateTodo(1L, todo);
        assertNull(actualUpdateTodoResult.getBody());
        assertEquals(400, actualUpdateTodoResult.getStatusCodeValue());
        assertTrue(actualUpdateTodoResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link TodoService#updateTodo(Long, Todo)}
     */
    @Test
    void testUpdateTodo2() {
        Todo todo = mock(Todo.class);
        doNothing().when(todo).setCompleted(Mockito.<Boolean>any());
        doNothing().when(todo).setDescription(Mockito.<String>any());
        doNothing().when(todo).setId(Mockito.<Long>any());
        doNothing().when(todo).setTitle(Mockito.<String>any());
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        ResponseEntity<Todo> actualUpdateTodoResult = todoService.updateTodo(1L, todo);
        verify(todo).setCompleted(Mockito.<Boolean>any());
        verify(todo).setDescription(Mockito.<String>any());
        verify(todo).setId(Mockito.<Long>any());
        verify(todo).setTitle(Mockito.<String>any());
        assertNull(actualUpdateTodoResult.getBody());
        assertEquals(400, actualUpdateTodoResult.getStatusCodeValue());
        assertTrue(actualUpdateTodoResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link TodoService#createTodo(Todo)}
     */
    @Test
    void testCreateTodo() {
        Todo todo = new Todo();
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        ResponseEntity<Todo> actualCreateTodoResult = todoService.createTodo(todo);
        assertNull(actualCreateTodoResult.getBody());
        assertEquals(400, actualCreateTodoResult.getStatusCodeValue());
        assertTrue(actualCreateTodoResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link TodoService#createTodo(Todo)}
     */
    @Test
    void testCreateTodo2() {
        Todo todo = mock(Todo.class);
        doNothing().when(todo).setCompleted(Mockito.<Boolean>any());
        doNothing().when(todo).setDescription(Mockito.<String>any());
        doNothing().when(todo).setId(Mockito.<Long>any());
        doNothing().when(todo).setTitle(Mockito.<String>any());
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        ResponseEntity<Todo> actualCreateTodoResult = todoService.createTodo(todo);
        verify(todo).setCompleted(Mockito.<Boolean>any());
        verify(todo).setDescription(Mockito.<String>any());
        verify(todo).setId(Mockito.<Long>any());
        verify(todo).setTitle(Mockito.<String>any());
        assertNull(actualCreateTodoResult.getBody());
        assertEquals(400, actualCreateTodoResult.getStatusCodeValue());
        assertTrue(actualCreateTodoResult.getHeaders().isEmpty());
    }
}

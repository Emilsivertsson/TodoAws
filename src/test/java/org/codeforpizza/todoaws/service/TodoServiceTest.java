package org.codeforpizza.todoaws.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.codeforpizza.todoaws.models.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
}

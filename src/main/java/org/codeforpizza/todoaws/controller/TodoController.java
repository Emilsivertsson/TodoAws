package org.codeforpizza.todoaws.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The TodoController class is a REST controller that handles requests.
 * It is responsible for handling requests for creating, reading, updating, and deleting todos.
 */
@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @GetMapping("")
    public ResponseEntity <List<Todo>> getAllTodos () {
        log.info("Getting all todos");
        try {
            return todoService.getAllTodos();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <Todo> getOneTodo (@PathVariable Long id) {
        log.info("Getting one todo");
        try {
            return todoService.getOneTodo(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("")
    public ResponseEntity <Todo> createTodo (@Valid @RequestBody Todo todo) {
        log.info("Creating a new todo");
        try {
            return todoService.createTodo(todo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <Todo> updateTodo (@PathVariable Long id, @RequestBody Todo todo) {
        log.info("Updating a todo");
        log.info(todo.toString());
        try {
            return todoService.updateTodo(id, todo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity <Todo> toggleComplete (@PathVariable Long id) {
        log.info("toggle todo complete status");
        try {
            return todoService.toggleComplete(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteTodo (@PathVariable Long id) {
        log.info("Deleting a todo");
        try {
            return todoService.deleteTodo(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

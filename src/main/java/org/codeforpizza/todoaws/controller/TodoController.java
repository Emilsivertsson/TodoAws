package org.codeforpizza.todoaws.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/")
    public ResponseEntity <List<Todo>> getAllTodos () {
        try {
            return todoService.getAllTodos();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity <Todo> getOneTodo (@PathVariable Long id) {
        try {
            return todoService.getOneTodo(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity <Todo> createTodo (@Valid @RequestBody Todo todo) {
        try {
            return todoService.createTodo(todo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity <Todo> updateTodo (@PathVariable Long id, @RequestBody Todo todo) {
        try {
            return todoService.updateTodo(id, todo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteTodo (@PathVariable Long id) {
        try {
            return todoService.deleteTodo(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

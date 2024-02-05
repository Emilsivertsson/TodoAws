package org.codeforpizza.todoaws.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public ResponseEntity<List<Todo>> getAllTodos() {
        try {
            List<Todo> todos = todoRepository.findAll();
            if (todos.isEmpty()) {
                log.info("No todos found");
                return ResponseEntity.noContent().build();
            } else {
                log.info("Todos found");
                return ResponseEntity.ok(todos);
            }
        } catch (Exception e) {
            log.info("Error getting todos");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<Todo> getOneTodo(Long id) {
        try {
            if (todoRepository.existsById(id)) {
                log.info("Todo found");
                Todo todo = todoRepository.findById(id).get();
                return ResponseEntity.ok(todo);
            } else {
                log.info("Todo not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.info("Error getting todo");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<String> deleteTodo(Long id) {
        try {
            if (todoRepository.existsById(id)) {
                todoRepository.deleteById(id);
                log.info("Todo deleted successfully");
                return ResponseEntity.ok("Todo deleted successfully");
            } else {
                log.info("Todo not found");
                return ResponseEntity.status(404).body("Todo not found");
            }
        } catch (Exception e) {
            log.info("Error deleting todo");
            return ResponseEntity.status(400).build();
        }

    }

    public ResponseEntity<Todo> updateTodo(Long id, Todo todo) {
        try {
            Todo todoToUpdate = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            todoToUpdate.setTitle(todo.getTitle());
            todoToUpdate.setDescription(todo.getDescription());
            todoToUpdate.setCompleted(todo.getCompleted());
            log.info("Todo updated successfully");
            return ResponseEntity.ok(todoRepository.save(todoToUpdate));
        } catch (Exception e) {
            log.info("Error updating todo");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<Todo> createTodo(Todo todo) {
        try {
            log.info("Todo created successfully");
            return ResponseEntity.ok(todoRepository.save(todo));
        } catch (Exception e) {
            log.info("Error creating todo");
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<Todo> toggleComplete(Long id) {
        try {
            Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
            todo.setCompleted(!todo.getCompleted());
            log.info("Toggle completed successfully");
            return ResponseEntity.ok(todoRepository.save(todo));
        } catch (Exception e) {
            log.info("Error completing todo");
            return ResponseEntity.status(400).build();
        }
    }
}

package org.codeforpizza.todoaws.service;

import io.restassured.RestAssured;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.repository.TodoRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoServiceTest {

    @LocalServerPort
    private int port;

    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.26");

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;



    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        Todo todo1 = new Todo("First title", "First description", false);
        Todo todo2 = new Todo("Second title", "Second description", true);
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        System.out.println(todoRepository.findAll());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void getAllTodos() {
        Todo todo1 = new Todo(1L,"First title", "First description", false);
        Todo todo2 = new Todo(2L,"Second title", "Second description", true);
        List<Todo> expectedTodos = List.of(todo1, todo2);

        List<Todo> actualTodos = todoService.getAllTodos().getBody();

        assert actualTodos != null;
        assertEquals(expectedTodos.size(), actualTodos.size());
    }

    @Test
    @Order(2)
    void getOneTodo() {
        Todo todo1 = new Todo("First title", "First description", false);
        todoRepository.save(todo1);
        Todo actualTodo = todoService.getOneTodo(1L).getBody();
        assert actualTodo != null;
        assertEquals(todo1.getTitle(), actualTodo.getTitle());
    }



    @Test
    @Order(3)
    void createTodo() {
        Todo todo = new Todo("Third title", "Third description", false);
        Todo actualTodo = todoService.createTodo(todo).getBody();
        assert actualTodo != null;
        assertEquals(todo.getTitle(), actualTodo.getTitle());
    }

    @Test
    @Order(4)
    void updateTodo() {
        Todo todo = new Todo(1L, "First title", "First description", false);
        Todo actualTodo = todoService.updateTodo(1L, todo).getBody();
        assert actualTodo != null;
        assertEquals(todo.getTitle(), actualTodo.getTitle());
    }

    @Test
    @Order(5)
    void toggleTodo() {
        Todo todo = new Todo(1L, "First title", "First description", false);
        Todo actualTodo = todoService.toggleComplete(1L).getBody();
        assert actualTodo != null;
        assertNotEquals(todo.getCompleted(), actualTodo.getCompleted());
    }

    @Test
    @Order(6)
    void deleteTodo() {
        List<Todo> todos = todoService.getAllTodos().getBody();
        todoService.deleteTodo(1L);
        assert todos != null;
        assertNotEquals(todoRepository.findAll().size(), todos.size());
    }
}
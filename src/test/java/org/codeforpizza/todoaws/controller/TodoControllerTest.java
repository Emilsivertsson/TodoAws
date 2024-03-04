package org.codeforpizza.todoaws.controller;

import io.restassured.RestAssured;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.repository.TodoRepository;
import org.codeforpizza.todoaws.service.TodoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * The TodoControllerTest class is a test class that tests the TodoController class.
 * testnames have been prefixed with a letter to ensure the order of execution.
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoControllerTest {

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
    private  TodoRepository todoRepository;

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
    void AshouldGetAllTodos() {
        given()
                .contentType("application/json")
                .when()
                .get("/todos")
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    @Order(2)
    void BshouldGetOneTodo() {
        given()
                .contentType("application/json")
                .when()
                .get("/todos/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("First title"));
    }

    @Test
    @Order(3)
    void CshouldCreateTodo() {
        Todo todo1 = new Todo();
        todo1.setTitle("new title");
        todo1.setDescription("new description");
        todo1.setCompleted(false);

        given()
                .contentType("application/json")
                .body(todo1)
                .when()
                .post("/todos")
                .then()
                .statusCode(200)
                .body("title", equalTo("new title"));
    }

    @Test
    @Order(4)
    void DshouldUpdateTodo() {
        Todo todoUpdated = new Todo();
        todoUpdated.setTitle("Updated title");
        todoUpdated.setDescription("Updated description");
        todoUpdated.setCompleted(true);

        given()
                .contentType("application/json")
                .body(todoUpdated)
                .when()
                .put("/todos/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated title"));
    }

    @Test
    @Order(5)
    void EshouldToggleTodoCompleted() {
        given()
                .contentType("application/json")
                .when()
                .put("/todos/complete/1")
                .then()
                .statusCode(200)
                .body("completed", equalTo(false));
    }

    @Test
    @Order(6)
    void FshouldDeleteTodo() {
        given()
                .contentType("application/json")
                .when()
                .delete("/todos/1")
                .then()
                .statusCode(200)
                .body(equalTo("Todo deleted successfully"));
    }

}
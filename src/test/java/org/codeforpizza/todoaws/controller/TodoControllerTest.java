package org.codeforpizza.todoaws.controller;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import io.restassured.parsing.Parser;
import lombok.extern.slf4j.Slf4j;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.repository.TodoRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoControllerTest {

    @LocalServerPort
    private int port;

    static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));



    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Autowired
    private TodoRepository todoRepository;



    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        todoRepository.deleteAll();
        List<Todo> todos = List.of(
                new Todo(1L, "First title", "First description", false),
                new Todo(2L, "Second title", "Second description", true),
                new Todo(3L, "Third title", "Third description", false),
                new Todo(4L, "Fourth title", "Fourth description", true),
                new Todo(5L, "Fifth title", "Fifth description", false)
        );
        todoRepository.saveAll(todos);

    }

    @Test
    void getAllTodos() {

        RestAssured.defaultParser = Parser.JSON;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/")
                .then()
                .statusCode(400);

    }

    @Test
    void getOneTodo() {
        Todo todo = new Todo(1L, "First title", "First description", false);
        log.info(todoRepository.save(todo).toString());

        RestAssured.defaultParser = Parser.JSON;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/1")
                .then()
                .statusCode(400);

    }

}

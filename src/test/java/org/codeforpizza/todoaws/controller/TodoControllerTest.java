package org.codeforpizza.todoaws.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TodoController.class, TodoService.class})
@ExtendWith(SpringExtension.class)
class TodoControllerTest {
    @Autowired
    private TodoController todoController;

    /**
     * Method under test: {@link TodoController#createTodo(Todo)}
     */
    @Test
    void testCreateTodo() throws Exception {
        Todo todo = new Todo();
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(todo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#deleteTodo(Long)}
     */
    @Test
    void testDeleteTodo() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#deleteTodo(Long)}
     */
    @Test
    void testDeleteTodo2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#getAllTodos()}
     */
    @Test
    void testGetAllTodos() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#getAllTodos()}
     */
    @Test
    void testGetAllTodos2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#getOneTodo(Long)}
     */
    @Test
    void testGetOneTodo() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#getOneTodo(Long)}
     */
    @Test
    void testGetOneTodo2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", "", "Uri Variables");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#getOneTodo(Long)}
     */
    @Test
    void testGetOneTodo3() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TodoController#updateTodo(Long, Todo)}
     */
    @Test
    void testUpdateTodo() throws Exception {
        Todo todo = new Todo();
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(todo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(todoController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

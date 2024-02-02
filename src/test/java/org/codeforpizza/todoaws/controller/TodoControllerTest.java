package org.codeforpizza.todoaws.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codeforpizza.todoaws.models.Todo;
import org.codeforpizza.todoaws.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TodoController.class})
@ExtendWith(SpringExtension.class)
class TodoControllerTest {
    @Autowired
    private TodoController todoController;

    @MockBean
    private TodoService todoService;

    /**
     * Method under test: {@link TodoController#getAllTodos()}
     */
    @Test
    void testGetAllTodos() throws Exception {
        when(todoService.getAllTodos()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TodoController#getOneTodo(Long)}
     */
    @Test
    void testGetOneTodo() throws Exception {
        when(todoService.getOneTodo(Mockito.<Long>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{id}", 1L);
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TodoController#createTodo(Todo)}
     */
    @Test
    void testCreateTodo() throws Exception {
        when(todoService.createTodo(Mockito.<Todo>any())).thenReturn(null);

        Todo todo = new Todo();
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(todo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TodoController#createTodo(Todo)}
     */
    @Test
    void testCreateTodo2() throws Exception {
        when(todoService.createTodo(Mockito.<Todo>any())).thenReturn(null);

        Todo todo = new Todo();
        todo.setCompleted(true);
        todo.setDescription("");
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
     * Method under test: {@link TodoController#updateTodo(Long, Todo)}
     */
    @Test
    void testUpdateTodo() throws Exception {
        when(todoService.updateTodo(Mockito.<Long>any(), Mockito.<Todo>any())).thenReturn(null);

        Todo todo = new Todo();
        todo.setCompleted(true);
        todo.setDescription("The characteristics of someone or something");
        todo.setId(1L);
        todo.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(todo);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link TodoController#deleteTodo(Long)}
     */
    @Test
    void testDeleteTodo() throws Exception {
        when(todoService.deleteTodo(Mockito.<Long>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{id}", 1L);
        MockMvcBuilders.standaloneSetup(todoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

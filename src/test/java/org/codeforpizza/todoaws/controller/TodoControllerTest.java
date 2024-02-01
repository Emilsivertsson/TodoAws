package org.codeforpizza.todoaws.controller;


import org.codeforpizza.todoaws.models.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    Gson gson = new Gson();

    Todo todo1 = new Todo(1L,"First title", "First description", false);
    Todo todo2 = new Todo(2L,"Second title", "Second description", true);
    Todo todo3 = new Todo(3L,"Third title", "Third description", false);
    Todo todo4 = new Todo(4L,"Fourth title", "Fourth description", true);
    Todo todo5 = new Todo(5L,"Fifth title", "Fifth description", false);


    @Test
    void getAllTodos() throws Exception {
        List<Todo> todos = Arrays.asList(todo1, todo2, todo3, todo4, todo5);

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(todos.size()));
    }

    @Test
    void getOneTodo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(todo1.getTitle()));
    }

    @Test
    void createTodo() throws Exception {
        Todo todo6 = new Todo("new title", "new description", false);
        String todoJson = gson.toJson(todo6);

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(todo6.getTitle()));

    }

    @Test
    void updateTodo() throws Exception {
        Todo todo6 = new Todo("new title", "new description", false);
        String todoJson = gson.toJson(todo6);

        mockMvc.perform(MockMvcRequestBuilders.put("/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(todo6.getTitle()));

    }

    @Test
    void deleteTodo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

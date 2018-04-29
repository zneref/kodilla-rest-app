package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.dto.TaskDto;
import com.crud.tasks.facade.TaskFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    TaskFacade taskFacade;

    @Test
    public void shouldFetchAllTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = Arrays.asList(new TaskDto(1L, "test title", "test content"));
        when(taskFacade.fetchAllTasks()).thenReturn(taskDtos);
        //When, Then
        mockMvc.perform(get("/v1/task/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test title")))
                .andExpect(jsonPath("$[0].content", is("test content")));
    }

    @Test
    public void shouldFetchTaskById() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        when(taskFacade.fetchTaskById(anyLong())).thenReturn(taskDto);
        //When, Then
        mockMvc.perform(get("/v1/task/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "test title", "test content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);
        when(taskFacade.createTask(any(TaskDto.class))).thenReturn(task);
        //When, Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));

    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(taskFacade.updateTask(any(TaskDto.class))).thenReturn(taskDto);
        //When, Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));

    }

    @Test
    public void testDeleteTask() throws Exception {
        //Given
        Task task = new Task(1L, "test title", "test content");
        taskFacade.deleteTask(task.getId());
        //When, Then
        verify(taskFacade, times(1)).deleteTask(task.getId());
        mockMvc.perform(delete("/v1/task/tasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
package com.crud.tasks.facade;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.dto.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskFacadeTestSuite {
    @InjectMocks
    private TaskFacade facade;
    @Mock
    private DbService service;
    @Mock
    private TaskMapper mapper;

    @Test
    public void fetchAllTasks() {
        // Given
        List<TaskDto> taskDtos = Arrays.asList(new TaskDto(1L, "test title", "test content"));
        List<Task> tasks = Arrays.asList(new Task(1L, "test title", "test content"));
        when(service.getAllTask()).thenReturn(tasks);
        when(mapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When
        List<TaskDto> resultDtos = facade.fetchAllTasks();

        //Then
        assertEquals(1, resultDtos.size());
        resultDtos.forEach(task -> {
            assertEquals("test title", task.getTitle());
            assertEquals("test content", task.getContent());
            assertEquals(1, (long) task.getId());
        });
    }

    @Test
    public void fetchTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "test title", "test content");
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.getTaskById(anyLong())).thenReturn(task);

        //When
        TaskDto resultTask = facade.fetchTaskById(1L);

        //Then
        assertNotNull(resultTask);
        assertEquals(1, (long) resultTask.getId());
        assertEquals("test title", resultTask.getTitle());
        assertEquals("test content", resultTask.getContent());
    }

    @Test
    public void updateTask() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        when(mapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(mapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);

        //When
        TaskDto resultTask = facade.updateTask(taskDto);

        // Then
        assertNotNull(resultTask);
        assertEquals(1, (long) resultTask.getId());
        assertEquals("test title", resultTask.getTitle());
        assertEquals("test content", resultTask.getContent());
    }

    @Test
    public void createTask() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");
        when(mapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(any(Task.class))).thenReturn(task);

        //When
        Task resultTask = facade.createTask(taskDto);

        //Then
        // Then
        assertNotNull(resultTask);
        assertEquals(1, (long) resultTask.getId());
        assertEquals("test title", resultTask.getTitle());
        assertEquals("test content", resultTask.getContent());
    }

    @Test
    public void deleteTask() {
        //Given
        Task task = new Task(1L, "test title", "test content");

        //When
        facade.deleteTask(task.getId());

        //Then
        verify(service, times(1)).deleteTask(1L);
    }
}
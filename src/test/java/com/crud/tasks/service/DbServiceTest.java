package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    public void getAllTask() {
        //Given
        List<Task> tasks = Arrays.asList(new Task(1L, "title", "content"));
        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> resultList = dbService.getAllTask();

        //Then
        assertEquals(1, resultList.size());
        resultList.forEach(task -> {
            assertEquals("title", task.getTitle());
            assertEquals("content", task.getContent());
            assertEquals(1L, (long) task.getId());
        });
    }

    @Test
    public void getTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "title", "content");
        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        //When
        Task result = dbService.getTaskById(1L);

        //Then
        assertNotNull(result);
        assertEquals("title", result.getTitle());
        assertEquals("content", result.getContent());
        assertEquals(1L, (long) result.getId());

    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(1L, "title", "content");
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        //When
        Task result = dbService.saveTask(task);

        //Then
        assertNotNull(result);
        assertEquals("title", result.getTitle());
        assertEquals("content", result.getContent());
        assertEquals(1L, (long) result.getId());
    }

    @Test
    public void deleteTask() {
        //Given, When
        dbService.deleteTask(anyLong());

        //Then
        verify(taskRepository, times(1)).deleteById(anyLong());
    }
}
package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.dto.TaskDto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        final TaskDto taskDto = new TaskDto(1L, "test title", "test content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertEquals(1L, (long)task.getId());
        assertEquals("test title", task.getTitle());
        assertEquals("test content", task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        final Task task = new Task(1L, "test title", "test content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(task);
        assertEquals(1L, (long)taskDto.getId());
        assertEquals("test title", taskDto.getTitle());
        assertEquals("test content", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = Arrays.asList(new Task(1L, "test title", "test content"));

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, taskDtos.size());
        taskDtos.forEach(dto -> {
            assertEquals(1L, (long) dto.getId());
            assertEquals("test title", dto.getTitle());
            assertEquals("test content", dto.getContent());
        });
    }
}
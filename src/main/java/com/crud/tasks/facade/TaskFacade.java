package com.crud.tasks.facade;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.dto.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskFacade {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper mapper;

    public List<TaskDto> fetchAllTasks() {
        return mapper.mapToTaskDtoList(service.getAllTask());
    }

    public TaskDto fetchTaskById(Long id) throws TaskNotFoundException {
        return mapper.mapToTaskDto(service.getTaskById(id));
    }

    public TaskDto updateTask(TaskDto taskDto) {
        return mapper.mapToTaskDto(service.saveTask(mapper.mapToTask(taskDto)));
    }

    public Task createTask(TaskDto taskDto) {
       return service.saveTask(mapper.mapToTask(taskDto));
    }

    public void deleteTask(Long taskId) {
        service.deleteTask(taskId);
    }
}

package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository repo;

    public List<Task> getAllTask() {
        return repo.findAll();
    }

    public Task getTaskById(final Long id) throws TaskNotFoundException {
        return repo.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    public Task saveTask(final Task task) {
        return repo.save(task);
    }

    public void deleteTask(final Long id) {
        repo.deleteById(id);
    }
}

package com.company.plutus.service;

import com.company.plutus.api.dto.CreateTaskRequest;
import com.company.plutus.api.dto.TaskResponse;
import com.company.plutus.domain.Task;
import com.company.plutus.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public TaskResponse create(CreateTaskRequest req) {
        Task task = new Task(req.title());
        Task saved = repo.save(task);

        return new TaskResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }

    public List<TaskResponse> list() {
        return repo.findAll().stream()
                .map(t -> new TaskResponse(t.getId(), t.getTitle(), t.getStatus(), t.getCreatedAt()))
                .toList();
    }
}


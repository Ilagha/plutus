package com.company.plutus.api;

import com.company.plutus.api.dto.CreateTaskRequest;
import com.company.plutus.api.dto.TaskResponse;
import com.company.plutus.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody CreateTaskRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<TaskResponse> list() {
        return service.list();
    }
}


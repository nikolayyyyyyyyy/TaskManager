package org.example.taskmanager.controllers;

import jakarta.validation.Valid;
import org.example.taskmanager.models.dto.request.TaskRequestDTO;
import org.example.taskmanager.models.dto.response.TaskResponseDTO;
import org.example.taskmanager.services.interfaces.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> getTasks(){
        return taskService.getTasks();
    }

    @PostMapping
    public TaskResponseDTO store(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        return this.taskService.store(taskRequestDTO);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable long id){
        return this.taskService.getById(id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<TaskResponseDTO> deleteTaskById(@PathVariable long id) {
        TaskResponseDTO taskResponseDTO = this.taskService.deleteById(id);
        return ResponseEntity.ok(taskResponseDTO);
    }

    @PutMapping("/{id}/update")
    public TaskResponseDTO updateTaskById(@PathVariable long id, @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        return this.taskService.updateById(id, taskRequestDTO);
    }
}

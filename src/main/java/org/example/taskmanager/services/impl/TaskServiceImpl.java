package org.example.taskmanager.services.impl;

import org.example.taskmanager.exceptions.TaskNotFoundException;
import org.example.taskmanager.models.Task;
import org.example.taskmanager.models.dto.request.TaskRequestDTO;
import org.example.taskmanager.models.dto.response.TaskResponseDTO;
import org.example.taskmanager.repositories.TaskRepository;
import org.example.taskmanager.services.interfaces.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskResponseDTO store(TaskRequestDTO taskRequestDTO) {
        Task task = modelMapper.map(taskRequestDTO, Task.class);
        this.taskRepository.save(task);

        return this.modelMapper.map(task, TaskResponseDTO.class);
    }

    @Override
    public TaskResponseDTO getById(long id) {
        Task task = this
                .taskRepository
                .findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    @Override
    public TaskResponseDTO updateById(long id, TaskRequestDTO taskRequestDTO) {
        Task task = this
                .taskRepository
                .findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        modelMapper.map(taskRequestDTO, task);

        Task updatedTask = this.taskRepository.save(task);
        return modelMapper.map(updatedTask, TaskResponseDTO.class);
    }

    @Override
    public TaskResponseDTO deleteById(long id) {
        Task task = this
                .taskRepository
                .findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        taskRepository.delete(task);
        return modelMapper.map(task, TaskResponseDTO.class);
    }

    @Override
    public List<TaskResponseDTO> getTasks() {
        return this.taskRepository.findAll()
                .stream()
                .map(task -> modelMapper.map(task, TaskResponseDTO.class))
                .toList();
    }
}

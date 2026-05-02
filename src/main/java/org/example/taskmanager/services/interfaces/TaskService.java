package org.example.taskmanager.services.interfaces;

import org.example.taskmanager.models.dto.request.TaskRequestDTO;
import org.example.taskmanager.models.dto.response.TaskResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    TaskResponseDTO store(TaskRequestDTO taskRequestDTO);
    TaskResponseDTO getById(long id);
    TaskResponseDTO updateById(long id, TaskRequestDTO taskRequestDTO);
    TaskResponseDTO deleteById(long id);
    List<TaskResponseDTO> getTasks();
}

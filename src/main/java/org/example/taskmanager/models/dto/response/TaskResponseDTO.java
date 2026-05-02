package org.example.taskmanager.models.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class TaskResponseDTO {
    private long id;
    private String title;
    private String description;
    private LocalDate deadline;
}

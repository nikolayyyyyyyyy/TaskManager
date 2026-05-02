package org.example.taskmanager.models.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class TaskRequestDTO {
    @NotBlank(message = "The title must have value.")
    private String title;

    @Size(min = 10, message = "Description have to be at least 10 characters.")
    private String description;

    @Future
    private LocalDate deadline;
}

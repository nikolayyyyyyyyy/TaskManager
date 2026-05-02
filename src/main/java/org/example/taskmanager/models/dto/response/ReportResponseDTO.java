package org.example.taskmanager.models.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ReportResponseDTO {
    private long id;
    private String content;
    private LocalDate workTime;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private TaskResponseDTO task;
}

package org.example.taskmanager.models.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ReportRequestDTO {
    private String content;
    private LocalDate workTime;
    private long task_id;
}

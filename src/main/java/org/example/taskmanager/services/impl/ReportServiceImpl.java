package org.example.taskmanager.services.impl;

import org.example.taskmanager.exceptions.ReportNotFoundException;
import org.example.taskmanager.exceptions.TaskNotFoundException;
import org.example.taskmanager.models.Report;
import org.example.taskmanager.models.Task;
import org.example.taskmanager.models.dto.request.ReportRequestDTO;
import org.example.taskmanager.models.dto.response.ReportResponseDTO;
import org.example.taskmanager.repositories.ReportRepository;
import org.example.taskmanager.repositories.TaskRepository;
import org.example.taskmanager.services.interfaces.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;
    private TaskRepository taskRepository;
    private ModelMapper modelMapper;

    public ReportServiceImpl(ReportRepository reportRepository, ModelMapper modelMapper, TaskRepository taskRepository) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public ReportResponseDTO store(ReportRequestDTO reportRequestDTO) {
        Task task = taskRepository
                .findById(reportRequestDTO.getTask_id()).orElseThrow(() -> new TaskNotFoundException("Task with id: %d not found".formatted(reportRequestDTO.getTask_id())));

        Report report = new Report();
        report.setWorkTime(reportRequestDTO.getWorkTime());
        report.setContent(reportRequestDTO.getContent());
        report.setTask(task);
        report.setCreatedAt(LocalDate.now());
        report.setUpdatedAt(LocalDate.now());

        this.reportRepository.save(report);
        return modelMapper.map(report, ReportResponseDTO.class);
    }

    @Override
    public ReportResponseDTO getById(long id) {
        Report report = this
                .reportRepository
                .findById(id).orElseThrow(() -> new ReportNotFoundException("Report with id: %d not found".formatted(id)));

        return modelMapper.map(report, ReportResponseDTO.class);
    }

    @Override
    public ReportResponseDTO updateById(long id, ReportRequestDTO reportRequestDTO) {
        Report report = this
                .reportRepository
                .findById(id).orElseThrow(() -> new ReportNotFoundException("Report with id: %d not found".formatted(id)));
        Task task = this
                .taskRepository
                .findById(reportRequestDTO.getTask_id()).orElseThrow(() -> new TaskNotFoundException("Task with id: %d not found".formatted(reportRequestDTO.getTask_id())));

        report.setContent(reportRequestDTO.getContent());
        report.setUpdatedAt(LocalDate.now());
        report.setTask(task);
        Report updatedReport = this.reportRepository.save(report);

        return modelMapper.map(updatedReport, ReportResponseDTO.class);
    }

    @Override
    public ReportResponseDTO deleteById(long id) {
        Report report = this
                .reportRepository
                .findById(id).orElseThrow(() -> new ReportNotFoundException("Report with id: %d not found".formatted(id)));

        this.reportRepository.delete(report);
        return modelMapper.map(report, ReportResponseDTO.class);
    }

    @Override
    public List<ReportResponseDTO> getReports() {
        return this.reportRepository
                .findAll()
                .stream()
                .map(r -> modelMapper.map(r, ReportResponseDTO.class))
                .toList();
    }
}

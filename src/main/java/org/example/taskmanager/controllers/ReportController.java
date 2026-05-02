package org.example.taskmanager.controllers;

import jakarta.validation.Valid;
import org.example.taskmanager.models.dto.request.ReportRequestDTO;
import org.example.taskmanager.models.dto.response.ReportResponseDTO;
import org.example.taskmanager.services.interfaces.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ReportResponseDTO> getReports() {
        return reportService.getReports();
    }

    @PostMapping
    public ReportResponseDTO store(@Valid @RequestBody ReportRequestDTO reportRequestDTO) {
        return reportService.store(reportRequestDTO);
    }

    @GetMapping("/{id}")
    public ReportResponseDTO getReportById(@PathVariable long id) {
        return this.reportService.getById(id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ReportResponseDTO> deleteReportById(@PathVariable long id) {
        return ResponseEntity.ok(this.reportService.deleteById(id));
    }

    @PutMapping("/{id}/update")
    public ReportResponseDTO updateReportById(@PathVariable long id, @Valid @RequestBody ReportRequestDTO reportRequestDTO) {
        return this.reportService.updateById(id, reportRequestDTO);
    }
}

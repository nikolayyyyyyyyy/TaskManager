package org.example.taskmanager.services.interfaces;

import org.example.taskmanager.models.dto.request.ReportRequestDTO;
import org.example.taskmanager.models.dto.response.ReportResponseDTO;

import java.util.List;

public interface ReportService {
    ReportResponseDTO store(ReportRequestDTO reportRequestDTO);
    ReportResponseDTO getById(long id);
    ReportResponseDTO updateById(long id, ReportRequestDTO reportRequestDTO);
    ReportResponseDTO deleteById(long id);
    List<ReportResponseDTO> getReports();
}

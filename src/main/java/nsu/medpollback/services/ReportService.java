package nsu.medpollback.services;

import nsu.medpollback.model.dto.ReportDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    Long addReport(UUID uuid, ReportDto reportDto) throws BadRequestException, AuthException;

    List<ReportDto> getReports(UUID uuid, Long prescriptionId);

    ReportDto getReport(Long id, UUID uuid);
}

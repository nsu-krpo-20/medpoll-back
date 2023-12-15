package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.ReportDto;
import nsu.medpollback.model.entities.Report;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.repositories.ReportRepository;
import nsu.medpollback.services.ReportService;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class ReportServiceImpl implements ReportService {
    private final ModelMapper mapper;
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ModelMapper mapper, ReportRepository reportRepository) {
        this.mapper = mapper;
        this.reportRepository = reportRepository;
    }

    @Override
    public Long addReport(UUID uuid, ReportDto reportDto) throws BadRequestException, AuthException {
        reportDto.setTime(Instant.now().toEpochMilli());
        Report report = mapper.map(reportDto, Report.class);
        if (reportDto.getPrescriptionId() == null) {
            throw new BadRequestException("Prescription id is required");
        }
        System.out.println("All report: " + report);
        System.out.println("Patient card: " + report.getPrescription().getPatientCard());
        if (!report.getPrescription().getPatientCard().getPatientToken().getToken().equals(uuid)) {
            throw new BadRequestException("UUID doesn't match prescription id");
        }
        return reportRepository.save(report).getId();
    }

    @Override
    public List<ReportDto> getReports(UUID uuid, Long prescriptionId) {
        return null;
    }

    @Override
    public ReportDto getReport(Long id, UUID uuid) {
        return null;
    }
}

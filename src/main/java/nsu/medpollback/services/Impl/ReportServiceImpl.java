package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.ReportDto;
import nsu.medpollback.model.entities.Prescription;
import nsu.medpollback.model.entities.Report;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.repositories.PrescriptionRepository;
import nsu.medpollback.repositories.ReportRepository;
import nsu.medpollback.security.util.AuthServiceCommon;
import nsu.medpollback.services.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {
    private final ModelMapper mapper;
    private final ReportRepository reportRepository;
    private final PrescriptionRepository prescriptionRepository;

    public ReportServiceImpl(ModelMapper mapper, ReportRepository reportRepository,
                             PrescriptionRepository prescriptionRepository) {
        this.mapper = mapper;
        this.reportRepository = reportRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public Long addReport(UUID uuid, ReportDto reportDto) throws BadRequestException, AuthException {
        reportDto.setTime(Instant.now().toEpochMilli());
        Report report = mapper.map(reportDto, Report.class);
        report.setId(null);
        if (reportDto.getPrescriptionId() == null) {
            throw new BadRequestException("Prescription id is required");
        }
        Prescription prescription = prescriptionRepository.findById(report.getPrescription().getId()).orElseThrow(
                () -> new BadRequestException("Couldn't find prescription with id: " + reportDto.getPrescriptionId()));

        if (!prescription.getPatientCard().getPatientToken().getToken().equals(uuid)) {
            throw new BadRequestException("UUID doesn't match prescription id");
        }
        return reportRepository.save(report).getId();
    }

    @Override
    public List<ReportDto> getReports(UUID uuid, Long prescriptionId) throws NotFoundException {
        String notFoundMsg =
                "Patient card with the provided UUID could not be found, or the found card didn't have " + "the " +
                        "provided prescription.";
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(
                () -> new NotFoundException(notFoundMsg));
        if (!prescription.getPatientCard().getPatientToken().getToken().equals(uuid)) {
            throw new NotFoundException(notFoundMsg);
        }
        return extractReportDtos(prescriptionId);
    }

    @Override
    public List<ReportDto> getPrescriptionReports(Long prescriptionId) throws NotFoundException {
        String notFoundMsg =
                "Patient card with the provided UUID could not be found, or the found card didn't have " + "the " +
                        "provided prescription.";
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(
                () -> new NotFoundException(notFoundMsg));

        return extractReportDtos(prescriptionId);
    }

    private List<ReportDto> extractReportDtos(Long prescriptionId) {
        List<Report> reports = reportRepository.findReportsByPrescriptionId(prescriptionId);
        for (Report report : reports) {
            report.setMedsTaken(null);
            report.setMetrics(null);
            report.setFeedback(null);
        }
        return reports.stream().map((element) -> mapper.map(element, ReportDto.class)).toList();
    }

    @Override
    public ReportDto getReport(Long id, UUID uuid) throws NotFoundException {
        String notFoundMsg = "Couldn't find report with id " + id;
        Report report = reportRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMsg));
        if (AuthServiceCommon.getAuthInfo() == null) {
            if (!report.getPrescription().getPatientCard().getPatientToken().getToken().equals(uuid)) {
                throw new NotFoundException(notFoundMsg);
            }
        }
        return mapper.map(report, ReportDto.class);
    }
}

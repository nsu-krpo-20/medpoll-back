package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.ReportDto;
import nsu.medpollback.model.entities.Prescription;
import nsu.medpollback.model.entities.Report;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.repositories.PatientCardRepository;
import nsu.medpollback.repositories.PrescriptionRepository;
import nsu.medpollback.repositories.ReportRepository;
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
        System.out.println("DTO: " + reportDto);
        Report report = mapper.map(reportDto, Report.class);
        report.setId(null);
        if (reportDto.getPrescriptionId() == null) {
            throw new BadRequestException("Prescription id is required");
        }
        Prescription prescription = prescriptionRepository.findById(report.getPrescription().getId()).orElseThrow(
                () -> new BadRequestException("Couldn't find prescription with id: " + reportDto.getPrescriptionId()));

        System.out.println("All report: " + report);
        System.out.println("Patient card: " + prescription.getPatientCard());
        if (!prescription.getPatientCard().getPatientToken().getToken().equals(uuid)) {
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

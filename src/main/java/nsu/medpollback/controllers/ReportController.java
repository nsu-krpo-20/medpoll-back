package nsu.medpollback.controllers;


import jakarta.validation.constraints.Positive;
import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.ReportDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.services.ReportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(Constants.BASE_API_PATH + "/reports")
@CrossOrigin(maxAge = 1440)
@Validated
@RestController
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    Long addReport(@RequestParam(value = "cardUUID") UUID uuid, @RequestBody ReportDto reportDto) throws
            BadRequestException, AuthException {
        return reportService.addReport(uuid, reportDto);
    }

    @GetMapping
    List<ReportDto> getReports(@RequestParam(value = "cardUUID") UUID uuid, @RequestParam Long prescriptionId) throws
            NotFoundException {
        return reportService.getReports(uuid, prescriptionId);
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @GetMapping(params = "prescriptionId")
    List<ReportDto> getReportsAuthed(@RequestParam Long prescriptionId)
            throws NotFoundException {
        return reportService.getPrescriptionReports(prescriptionId);
    }

    @GetMapping("/{id}")
    ReportDto getReport(@RequestParam(value = "cardUUID", required = false) UUID uuid, @PathVariable @Positive Long id) throws
            NotFoundException {
        return reportService.getReport(id, uuid);
    }
}

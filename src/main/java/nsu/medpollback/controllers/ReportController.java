package nsu.medpollback.controllers;


import jakarta.validation.constraints.Positive;
import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.ReportDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.services.ReportService;
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
    Long addReport(@RequestParam UUID uuid, @RequestBody ReportDto reportDto) throws BadRequestException,
            AuthException {
        return reportService.addReport(uuid, reportDto);
    }

    @GetMapping
    List<ReportDto> getReports(@RequestParam UUID uuid, @RequestParam Long prescriptionId) {
        return reportService.getReports(uuid, prescriptionId);
    }

    @GetMapping("/{id}")
    ReportDto getReport(@RequestParam UUID uuid, @PathVariable @Positive Long id) {
        return reportService.getReport(id, uuid);
    }
}

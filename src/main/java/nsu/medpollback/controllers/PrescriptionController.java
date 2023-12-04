package nsu.medpollback.controllers;

import jakarta.validation.constraints.Positive;
import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.services.PrescriptionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(Constants.BASE_API_PATH + "/prescriptions")
@CrossOrigin(maxAge = 1440)
@Validated
@RestController
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping
    public Long addPrescription(@RequestBody PrescriptionDto prescriptionDto) throws BadRequestException,
            AuthException {
        return prescriptionService.addPrescription(prescriptionDto);
    }

    @GetMapping(value = "/{id}")
    public PrescriptionDto getPrescriptionById(@PathVariable @Positive Long id, @RequestParam(required = false) UUID cardUUID) throws
            NotFoundException {
        return prescriptionService.getPrescriptionById(id, cardUUID);
    }
}

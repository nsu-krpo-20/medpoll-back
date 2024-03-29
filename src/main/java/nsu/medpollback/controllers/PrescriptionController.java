package nsu.medpollback.controllers;

import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.IdDto;
import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.services.PrescriptionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(Constants.BASE_API_PATH + "/prescriptions")
@CrossOrigin(maxAge = 1440)
@PreAuthorize("hasAuthority('DOCTOR')")
@Validated
@RestController
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public IdDto addPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        return prescriptionService.addPrescription(prescriptionDto);
    }
}

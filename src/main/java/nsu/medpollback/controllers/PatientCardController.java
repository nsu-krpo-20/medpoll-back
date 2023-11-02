package nsu.medpollback.controllers;

import jakarta.validation.constraints.Positive;
import nsu.medpollback.config.Constants;
import nsu.medpollback.model.dto.IdDto;
import nsu.medpollback.model.dto.PatientCardDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.services.PatientCardService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(Constants.BASE_API_PATH + "/cards")
@CrossOrigin(maxAge = 1440)
@PreAuthorize("hasAuthority('DOCTOR')")
@Validated
@RestController
public class PatientCardController {
    private final PatientCardService cardService;

    public PatientCardController(PatientCardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/{id}")
    public PatientCardDto getPatientCard(@PathVariable @Positive Long id) throws NotFoundException,
            AuthException {
        return cardService.getCard(id);
    }

    @PostMapping
    public Long createCard(@RequestBody PatientCardDto cardDto) throws AuthException {
        return cardService.createCard(cardDto);
    }

    @GetMapping(value = "/patientToken/{id}")
    public String getPatientToken(@PathVariable @Positive Long id) throws NotFoundException {
        return cardService.getToken(id);
    }
}

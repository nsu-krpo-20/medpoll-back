package nsu.medpollback.services;

import nsu.medpollback.model.dto.IdDto;
import nsu.medpollback.model.dto.PatientCardDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.NotFoundException;

public interface PatientCardService {
    Long createCard(PatientCardDto cardDto) throws AuthException;

    PatientCardDto getCard(Long id) throws NotFoundException, AuthException;

    String getToken(Long id) throws NotFoundException;
}

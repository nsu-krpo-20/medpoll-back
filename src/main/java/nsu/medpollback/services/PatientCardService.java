package nsu.medpollback.services;

import nsu.medpollback.model.dto.PatientCardDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.NotFoundException;

import java.util.List;

public interface PatientCardService {
    Long createCard(PatientCardDto cardDto) throws AuthException;

    PatientCardDto getCard(Long id) throws NotFoundException, AuthException;

    String getToken(Long id) throws NotFoundException;

    List<PatientCardDto> getCardsBy(String query, Integer limit, Integer offset);

    Long getCardCount() throws AuthException;
}

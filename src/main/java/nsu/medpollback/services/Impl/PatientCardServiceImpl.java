package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.IdDto;
import nsu.medpollback.model.dto.PatientCardDto;
import nsu.medpollback.model.entities.PatientCard;
import nsu.medpollback.model.entities.PatientToken;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.repositories.PatientCardRepository;
import nsu.medpollback.repositories.PatientTokenRepository;
import nsu.medpollback.security.util.AuthServiceCommon;
import nsu.medpollback.services.PatientCardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PatientCardServiceImpl implements PatientCardService {
    private final PatientCardRepository cardRepository;
    private final PatientTokenRepository tokenRepository;

    private final ModelMapper mapper;

    public PatientCardServiceImpl(PatientCardRepository cardRepository, PatientTokenRepository tokenRepository,
                                  ModelMapper mapper) {
        this.cardRepository = cardRepository;
        this.tokenRepository = tokenRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Long createCard(PatientCardDto cardDto) throws AuthException {
        if (!AuthServiceCommon.checkAuthorities(AuthServiceCommon.getUserLogin())) {
            throw new AuthException("Have no rights");
        }
        PatientCard card = mapper.map(cardDto, PatientCard.class);
        if (!card.getPhoneNumber().isEmpty()) {
            card.setPhoneNumber(convertPhoneNumberToUnified(card.getPhoneNumber()));
        }
        PatientToken token = new PatientToken();
        token.setPatientCard(card);
        token.setToken(UUID.randomUUID());
        card.setId(null);
        card.setPatientToken(token);
        tokenRepository.save(token);
        return cardRepository.save(card).getId();
    }

    private String convertPhoneNumberToUnified(String phoneNumber) {
        return phoneNumber.replaceFirst("^\\+7", "8");
    }

    @Override
    public PatientCardDto getCard(Long id) throws NotFoundException, AuthException {
        if (!AuthServiceCommon.checkAuthorities(AuthServiceCommon.getUserLogin())) {
            throw new AuthException("Have no rights");
        }
        PatientCard card = cardRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Couldn't find patient card with id: " + id));
        return mapper.map(card, PatientCardDto.class);
    }

    @Override
    public String getToken(Long id) throws NotFoundException {
        PatientToken token = tokenRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Couldn't find token with card id: " + id));
        return token.getToken().toString();
    }

    @Override
    public List<PatientCardDto> getCardsBy(String query, Integer limit, Integer offset) {
        List<PatientCard> cards = cardRepository.findByQuery(query, limit, offset);
        return cards.stream().map(card -> {
            PatientCardDto dto = mapper.map(card, PatientCardDto.class);
            dto.setPrescriptions(null);
            return dto;
        }).toList();
    }
}

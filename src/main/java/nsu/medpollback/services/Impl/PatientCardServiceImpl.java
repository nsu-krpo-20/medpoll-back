package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.IdDto;
import nsu.medpollback.model.dto.PatientCardDto;
import nsu.medpollback.model.entities.PatientCard;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.repositories.PatientCardRepository;
import nsu.medpollback.security.util.AuthServiceCommon;
import nsu.medpollback.services.PatientCardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientCardServiceImpl implements PatientCardService {
    private final PatientCardRepository cardRepository;

    private final ModelMapper mapper;

    public PatientCardServiceImpl(PatientCardRepository cardRepository, ModelMapper mapper) {
        this.cardRepository = cardRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public IdDto createCard(PatientCardDto cardDto) throws AuthException {
        if (!AuthServiceCommon.checkAuthorities(AuthServiceCommon.getUserLogin())) {
            throw new AuthException("Have no rights");
        }
        PatientCard card = mapper.map(cardDto, PatientCard.class);
        card.setId(null);
        PatientCard saved = cardRepository.save(card);
        return new IdDto().id(saved.getId());
    }

    @Override
    public PatientCardDto getCard(Long id) throws NotFoundException, AuthException {
        if (!AuthServiceCommon.checkAuthorities(AuthServiceCommon.getUserLogin())) {
            throw new AuthException("Have no rights");
        }
        PatientCard card = cardRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find patient card with id: " + id));
        return mapper.map(card, PatientCardDto.class);
    }


}

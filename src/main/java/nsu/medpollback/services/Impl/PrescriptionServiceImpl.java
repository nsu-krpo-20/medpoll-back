package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.IdDto;
import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.services.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final ModelMapper mapper;

    public PrescriptionServiceImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public IdDto addPrescription(PrescriptionDto prescriptionDto) {
        return null;
    }
}

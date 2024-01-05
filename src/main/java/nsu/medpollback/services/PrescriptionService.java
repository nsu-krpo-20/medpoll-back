package nsu.medpollback.services;

import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;

import java.util.UUID;

public interface PrescriptionService {
    PrescriptionDto getPrescriptionById(Long id, UUID cardUUID) throws NotFoundException;

    PrescriptionDto[] getCardPrescriptionsByUUID(UUID cardUUID) throws NotFoundException;
    PrescriptionDto[] getCardPrescriptions(Long id) throws NotFoundException;

    Long addPrescription(PrescriptionDto prescriptionDto) throws BadRequestException, AuthException;

    void makeInactive(Long id);
}

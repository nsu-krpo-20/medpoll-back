package nsu.medpollback.services;

import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;

public interface PrescriptionService {
    Long addPrescription(PrescriptionDto prescriptionDto) throws BadRequestException, AuthException;
}

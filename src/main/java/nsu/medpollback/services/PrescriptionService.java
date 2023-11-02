package nsu.medpollback.services;

import nsu.medpollback.model.dto.IdDto;
import nsu.medpollback.model.dto.PrescriptionDto;

public interface PrescriptionService {
    IdDto addPrescription(PrescriptionDto prescriptionDto);
}

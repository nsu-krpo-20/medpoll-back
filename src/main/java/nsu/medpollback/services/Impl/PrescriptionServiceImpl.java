package nsu.medpollback.services.Impl;

import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.model.entities.*;
import nsu.medpollback.model.exceptions.AuthException;
import nsu.medpollback.model.exceptions.BadRequestException;
import nsu.medpollback.model.exceptions.NotFoundException;
import nsu.medpollback.repositories.PatientCardRepository;
import nsu.medpollback.repositories.PrescriptionRepository;
import nsu.medpollback.repositories.UserRepository;
import nsu.medpollback.security.util.AuthServiceCommon;
import nsu.medpollback.services.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    private final ModelMapper mapper;
    private final PrescriptionRepository prescriptionRepository;
    private final PatientCardRepository patientCardRepository;
    private final UserRepository userRepository;

    public PrescriptionServiceImpl(ModelMapper mapper, PrescriptionRepository prescriptionRepository,
                                   PatientCardRepository patientCardRepository, UserRepository userRepository) {
        this.mapper = mapper;
        this.prescriptionRepository = prescriptionRepository;
        this.patientCardRepository = patientCardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PrescriptionDto getPrescriptionById(Long id, UUID cardUUID) throws NotFoundException {
        String notFoundMsg = "Couldn't find prescription with id " + id;
        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(
                () -> new NotFoundException(notFoundMsg));
        if (AuthServiceCommon.getAuthInfo() == null || !AuthServiceCommon.checkAuthorities(AuthServiceCommon.getUserLogin())) {
            if (!prescription.getPatientCard().getPatientToken().getToken().equals(cardUUID)) {
                throw new NotFoundException(notFoundMsg);
            }
        }
        return mapper.map(prescription, PrescriptionDto.class);
    }

    @Override
    @Transactional
    public Long addPrescription(PrescriptionDto prescriptionDto) throws BadRequestException, AuthException {
        User user = userRepository.findById(prescriptionDto.getCreatedBy()).orElseThrow(
                () -> new BadRequestException("Couldn't find user with id: " + prescriptionDto.getCreatedBy()));
        if (!AuthServiceCommon.isSamePerson(user.getLogin())) {
            throw new AuthException("Have no rights");
        }
        PatientCard card = patientCardRepository.findById(prescriptionDto.getPatientCardId()).orElseThrow(
                () -> new BadRequestException(
                        "Couldn't find patient card with id : " + prescriptionDto.getPatientCardId()));
        Prescription prescription = mapper.map(prescriptionDto, Prescription.class);
        if (prescription.getPatientCard() == null || !patientCardRepository.existsById(
                prescription.getPatientCard().getId())) {
            throw new BadRequestException("PatientCardId must be present");
        }
        prescription.setId(null);
        prescription.setUser(user);
        prescription.setPatientCard(card);
        prescription.setPrescriptionMeds(prescriptionDto.getMeds().stream().map(medDto -> {
            PrescriptionMed med = mapper.map(medDto, PrescriptionMed.class);
            if (!isPeriodTypeValid(med.getPeriodType())) {
                throw new RuntimeException("Invalid period time");
            }
            med.setPrescription(prescription);
            return med;
        }).toList());
        prescription.setPrescriptionMetrics(prescriptionDto.getMetrics().stream().map(metricDto -> {
            PrescriptionMetric metric = mapper.map(metricDto, PrescriptionMetric.class);
            if (!isPeriodTypeValid(metric.getPeriodType())) {
                throw new RuntimeException("Invalid period time");
            }
            metric.setPrescription(prescription);
            return metric;
        }).toList());

        return prescriptionRepository.save(prescription).getId();
    }

    private static boolean isPeriodTypeValid(PeriodType type) {
        return type != null && PeriodType.isPresent(type.getValue());
    }
}

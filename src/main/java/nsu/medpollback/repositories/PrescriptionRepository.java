package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.PatientCard;
import nsu.medpollback.model.entities.Prescription;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
    @Query(value = """
                SELECT prescriptions.* FROM patient_tokens
                LEFT JOIN prescriptions
                ON patient_tokens.card_id = prescriptions.card_id
                WHERE patient_tokens.token = :cardUUID
            """, nativeQuery = true)
    Optional<Prescription[]> findByPatientToken(UUID cardUUID);

    @Modifying
    @Transactional
    @Query("UPDATE Prescription p SET p.isActive = false WHERE p.id = :id")
    void makeInactive(Long id);
}

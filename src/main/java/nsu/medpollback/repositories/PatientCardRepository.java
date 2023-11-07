package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.PatientCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientCardRepository extends CrudRepository<PatientCard, Long> {
    @Query(value = """
                SELECT * FROM patient_cards
                WHERE (:query IS NULL OR
                        LOWER(full_name) LIKE CONCAT('%', LOWER(:query), '%') OR
                        snils LIKE CONCAT('%', LOWER(:query), '%') OR
                        phone_number LIKE CONCAT('%', LOWER(:query), '%'))
                ORDER BY id
                LIMIT :limit OFFSET :offset
            """, nativeQuery = true)
    List<PatientCard> findByQuery(String query, Integer limit, Integer offset);
}

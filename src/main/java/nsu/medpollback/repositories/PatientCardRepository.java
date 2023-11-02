package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.PatientCard;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCardRepository extends CrudRepository<PatientCard, Long> {
}

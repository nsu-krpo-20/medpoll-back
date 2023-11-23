package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.Prescription;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {

}

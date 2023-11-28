package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.PrescriptionMed;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionMedRepository extends CrudRepository<PrescriptionMed, Long> {

}

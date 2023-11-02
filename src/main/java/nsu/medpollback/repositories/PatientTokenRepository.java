package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.PatientToken;
import org.springframework.data.repository.CrudRepository;

public interface PatientTokenRepository extends CrudRepository<PatientToken, Long> {}

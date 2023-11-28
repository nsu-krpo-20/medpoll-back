package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.PrescriptionMetric;
import org.springframework.data.repository.CrudRepository;

public interface PrescriptionMetricRepository extends CrudRepository<PrescriptionMetric, Long> {

}
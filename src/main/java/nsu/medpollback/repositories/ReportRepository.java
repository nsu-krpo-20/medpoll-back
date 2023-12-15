package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {

}

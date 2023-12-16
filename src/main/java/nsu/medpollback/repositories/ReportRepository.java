package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

}

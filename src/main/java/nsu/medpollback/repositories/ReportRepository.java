package nsu.medpollback.repositories;

import nsu.medpollback.model.entities.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    @Query(value = """
                SELECT * FROM reports
                WHERE prescription_id = :id
            """, nativeQuery = true)
    List<Report> findReportsByPrescriptionId(Long id);

}

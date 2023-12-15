package nsu.medpollback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * DTO for {@link nsu.medpollback.model.entities.Report}
 */
@AllArgsConstructor
@Getter
@Setter
public class ReportDto implements Serializable {
    Long id;
    Long prescriptionId;
    String feedback;
    Long time;
    String medsTaken;
    String metrics;
}
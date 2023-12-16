package nsu.medpollback.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;


/**
 * DTO for {@link nsu.medpollback.model.entities.Report}
 */
@AllArgsConstructor
@Getter
@Setter
public class ReportDto {
    private Long id;
    private Long prescriptionId;
    private String feedback;
    private Long time;
    private Map<String, Boolean> medsTaken;

    private Map<String, String> metrics;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReportDto {\n");

        sb.append("    id: ").append(id).append("\n");
        sb.append("    prescriptionId: ").append(prescriptionId).append("\n");
        sb.append("    feedback: ").append(feedback).append("\n");
        sb.append("    time: ").append(time).append("\n");
        sb.append("    medsTaken: ").append(medsTaken).append("\n");
        sb.append("    metrics: ").append(metrics).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
package nsu.medpollback.model.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import nsu.medpollback.model.dto.MedDto;
import nsu.medpollback.model.dto.MetricDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * PrescriptionDto
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-30T16:31:01.869258105Z[GMT]")


public class PrescriptionDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("patientId")
  private Long patientId = null;

  @JsonProperty("meds")
  @Valid
  private List<MedDto> meds = null;

  @JsonProperty("metrics")
  @Valid
  private List<MetricDto> metrics = null;

  public PrescriptionDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
      @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PrescriptionDto patientId(Long patientId) {
    this.patientId = patientId;
    return this;
  }

  /**
   * Get patientId
   * @return patientId
   **/
      @NotNull

    public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

  public PrescriptionDto meds(List<MedDto> meds) {
    this.meds = meds;
    return this;
  }

  public PrescriptionDto addMedsItem(MedDto medsItem) {
    if (this.meds == null) {
      this.meds = new ArrayList<MedDto>();
    }
    this.meds.add(medsItem);
    return this;
  }

  /**
   * Get meds
   * @return meds
   **/
      @Valid
    public List<MedDto> getMeds() {
    return meds;
  }

  public void setMeds(List<MedDto> meds) {
    this.meds = meds;
  }

  public PrescriptionDto metrics(List<MetricDto> metrics) {
    this.metrics = metrics;
    return this;
  }

  public PrescriptionDto addMetricsItem(MetricDto metricsItem) {
    if (this.metrics == null) {
      this.metrics = new ArrayList<MetricDto>();
    }
    this.metrics.add(metricsItem);
    return this;
  }

  /**
   * Get metrics
   * @return metrics
   **/
      @Valid
    public List<MetricDto> getMetrics() {
    return metrics;
  }

  public void setMetrics(List<MetricDto> metrics) {
    this.metrics = metrics;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrescriptionDto prescriptionDto = (PrescriptionDto) o;
    return Objects.equals(this.id, prescriptionDto.id) &&
        Objects.equals(this.patientId, prescriptionDto.patientId) &&
        Objects.equals(this.meds, prescriptionDto.meds) &&
        Objects.equals(this.metrics, prescriptionDto.metrics);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, patientId, meds, metrics);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrescriptionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    patientId: ").append(toIndentedString(patientId)).append("\n");
    sb.append("    meds: ").append(toIndentedString(meds)).append("\n");
    sb.append("    metrics: ").append(toIndentedString(metrics)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

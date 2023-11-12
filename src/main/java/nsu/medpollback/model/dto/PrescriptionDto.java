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
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-06T10:22:42.327534818Z[GMT]")


public class PrescriptionDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("patientCardId")
  private Long patientCardId = null;

  @JsonProperty("meds")
  @Valid
  private List<MedDto> meds = null;

  @JsonProperty("metrics")
  @Valid
  private List<MetricDto> metrics = null;

  @JsonProperty("timestamp")
  private Long timestamp = null;

  @JsonProperty("doctorFullName")
  private String doctorFullName = null;

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

  public PrescriptionDto patientCardId(Long patientCardId) {
    this.patientCardId = patientCardId;
    return this;
  }

  /**
   * Get patientCardId
   * @return patientCardId
   **/
      @NotNull

    public Long getPatientCardId() {
    return patientCardId;
  }

  public void setPatientCardId(Long patientCardId) {
    this.patientCardId = patientCardId;
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

  public PrescriptionDto timestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Дата в виде количества секунд с момента 01/01/1970 00:00:00
   * @return timestamp
   **/
  
    public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  public PrescriptionDto doctorFullName(String doctorFullName) {
    this.doctorFullName = doctorFullName;
    return this;
  }

  /**
   * Get doctorFullName
   * @return doctorFullName
   **/
  
    public String getDoctorFullName() {
    return doctorFullName;
  }

  public void setDoctorFullName(String doctorFullName) {
    this.doctorFullName = doctorFullName;
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
        Objects.equals(this.patientCardId, prescriptionDto.patientCardId) &&
        Objects.equals(this.meds, prescriptionDto.meds) &&
        Objects.equals(this.metrics, prescriptionDto.metrics) &&
        Objects.equals(this.timestamp, prescriptionDto.timestamp) &&
        Objects.equals(this.doctorFullName, prescriptionDto.doctorFullName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, patientCardId, meds, metrics, timestamp, doctorFullName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrescriptionDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    patientCardId: ").append(toIndentedString(patientCardId)).append("\n");
    sb.append("    meds: ").append(toIndentedString(meds)).append("\n");
    sb.append("    metrics: ").append(toIndentedString(metrics)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    doctorFullName: ").append(toIndentedString(doctorFullName)).append("\n");
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

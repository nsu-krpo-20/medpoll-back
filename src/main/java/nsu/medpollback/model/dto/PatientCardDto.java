package nsu.medpollback.model.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import nsu.medpollback.model.dto.PrescriptionDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * PatientCardDto
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-06T10:22:42.327534818Z[GMT]")


public class PatientCardDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("fullName")
  private String fullName = null;

  @JsonProperty("snils")
  private String snils = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("prescriptions")
  @Valid
  private List<PrescriptionDto> prescriptions = null;

  public PatientCardDto id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PatientCardDto fullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  /**
   * Get fullName
   * @return fullName
   **/
      @NotNull

    public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public PatientCardDto snils(String snils) {
    this.snils = snils;
    return this;
  }

  /**
   * Get snils
   * @return snils
   **/
  
    public String getSnils() {
    return snils;
  }

  public void setSnils(String snils) {
    this.snils = snils;
  }

  public PatientCardDto phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
   **/
  
    public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public PatientCardDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   **/
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PatientCardDto prescriptions(List<PrescriptionDto> prescriptions) {
    this.prescriptions = prescriptions;
    return this;
  }

  public PatientCardDto addPrescriptionsItem(PrescriptionDto prescriptionsItem) {
    if (this.prescriptions == null) {
      this.prescriptions = new ArrayList<PrescriptionDto>();
    }
    this.prescriptions.add(prescriptionsItem);
    return this;
  }

  /**
   * Get prescriptions
   * @return prescriptions
   **/
      @Valid
    public List<PrescriptionDto> getPrescriptions() {
    return prescriptions;
  }

  public void setPrescriptions(List<PrescriptionDto> prescriptions) {
    this.prescriptions = prescriptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatientCardDto patientCardDto = (PatientCardDto) o;
    return Objects.equals(this.id, patientCardDto.id) &&
        Objects.equals(this.fullName, patientCardDto.fullName) &&
        Objects.equals(this.snils, patientCardDto.snils) &&
        Objects.equals(this.phoneNumber, patientCardDto.phoneNumber) &&
        Objects.equals(this.description, patientCardDto.description) &&
        Objects.equals(this.prescriptions, patientCardDto.prescriptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fullName, snils, phoneNumber, description, prescriptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatientCardDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fullName: ").append(toIndentedString(fullName)).append("\n");
    sb.append("    snils: ").append(toIndentedString(snils)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    prescriptions: ").append(toIndentedString(prescriptions)).append("\n");
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

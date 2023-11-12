package nsu.medpollback.model.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * MedDto
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-06T10:22:42.327534818Z[GMT]")


public class MedDto   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("dose")
  private String dose = null;

  @JsonProperty("periodType")
  private Long periodType = null;

  @JsonProperty("period")
  private String period = null;

  public MedDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MedDto dose(String dose) {
    this.dose = dose;
    return this;
  }

  /**
   * Get dose
   * @return dose
   **/
      @NotNull

    public String getDose() {
    return dose;
  }

  public void setDose(String dose) {
    this.dose = dose;
  }

  public MedDto periodType(Long periodType) {
    this.periodType = periodType;
    return this;
  }

  /**
   * Get periodType
   * @return periodType
   **/
      @NotNull

    public Long getPeriodType() {
    return periodType;
  }

  public void setPeriodType(Long periodType) {
    this.periodType = periodType;
  }

  public MedDto period(String period) {
    this.period = period;
    return this;
  }

  /**
   * Get period
   * @return period
   **/
      @NotNull

    public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MedDto medDto = (MedDto) o;
    return Objects.equals(this.name, medDto.name) &&
        Objects.equals(this.dose, medDto.dose) &&
        Objects.equals(this.periodType, medDto.periodType) &&
        Objects.equals(this.period, medDto.period);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, dose, periodType, period);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MedDto {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    dose: ").append(toIndentedString(dose)).append("\n");
    sb.append("    periodType: ").append(toIndentedString(periodType)).append("\n");
    sb.append("    period: ").append(toIndentedString(period)).append("\n");
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

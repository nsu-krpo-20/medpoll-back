package nsu.medpollback.model.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * MetricDto
 */
@Validated
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-10-30T16:31:01.869258105Z[GMT]")


public class MetricDto   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("periodType")
  private Long periodType = null;

  @JsonProperty("period")
  private String period = null;

  public MetricDto name(String name) {
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

  public MetricDto periodType(Long periodType) {
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

  public MetricDto period(String period) {
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
    MetricDto metricDto = (MetricDto) o;
    return Objects.equals(this.name, metricDto.name) &&
        Objects.equals(this.periodType, metricDto.periodType) &&
        Objects.equals(this.period, metricDto.period);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, periodType, period);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MetricDto {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

package nsu.medpollback.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PeriodType {
    N_TIMES_PER_DAY(0),
    ONCE_PER_N_DAYS(1),
    WEEK_SCHEDULE(2),
    CUSTOM(3);

    private final long value;

    PeriodType(long value) {
        this.value = value;
    }

    @JsonCreator
    public static PeriodType fromValue(long value) {
        for (PeriodType enumValue : PeriodType.values()) {
            if (enumValue.value == value) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }

    public static boolean isPresent(long value) {
        for (PeriodType enumValue : PeriodType.values()) {
            if (enumValue.value == value) {
                return true;
            }
        }
        return false;
    }

    @JsonValue
    public long getValue() {
        return value;
    }
}

package nsu.medpollback.config;


import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.model.entities.PeriodType;
import nsu.medpollback.model.entities.Prescription;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Mapper extends ModelMapper {
    public Mapper() {
        super();
        this.addConverter(new LongToPeriodTypeConverter());
        this.addConverter(new UnixTimeToTimestampConverter());
        this.addConverter(new PeriodTypeToLong());
    }

    private static class LongToPeriodTypeConverter extends AbstractConverter<Long, PeriodType> {
        @Override
        protected PeriodType convert(Long source) {
            if (source != null) {
                return PeriodType.fromValue(source);
            }
            return null;
        }
    }

    private static class PeriodTypeToLong extends AbstractConverter<PeriodType, Long> {
        @Override
        protected Long convert(PeriodType source) {
            return source.getValue();
        }
    }

    private static class UnixTimeToTimestampConverter extends AbstractConverter<Long, Timestamp> {
        @Override
        protected Timestamp convert(Long source) {
            return new Timestamp(source * 1000);
        }
    }
}


package nsu.medpollback.config;


import nsu.medpollback.model.dto.PatientCardDto;
import nsu.medpollback.model.dto.PrescriptionDto;
import nsu.medpollback.model.entities.PatientCard;
import nsu.medpollback.model.entities.PeriodType;
import nsu.medpollback.model.entities.Prescription;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Mapper extends ModelMapper {
    public Mapper() {
        super();
        this.addConverter(new LongToPeriodTypeConverter());
        this.addConverter(new UnixTimeToTimestampConverter());
        this.addConverter(new PeriodTypeToLong());
        this.addMappings(new PropertyMap<PatientCard, PatientCard>() {
            @Override
            protected void configure() {
                skip(destination.getPrescriptions());
                skip(destination.getPatientToken());
                skip(destination.getId());
            }
        });
        this.addMappings(new PropertyMap<PatientCardDto, PatientCard>() {
            @Override
            protected void configure() {
                skip(destination.getPrescriptions());
            }
        });
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


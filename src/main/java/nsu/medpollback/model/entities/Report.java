package nsu.medpollback.model.entities;


import jakarta.persistence.*;
import lombok.*;
import nsu.medpollback.model.converters.HashMapConverter;
import org.hibernate.proxy.HibernateProxy;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "time", insertable = false)
    private Timestamp time;

    @Column(name = "metrics")
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> metrics;

    @Column(name = "meds_taken")
    @Convert(converter = HashMapConverter.class)
    private Map<String, Object> medsTaken;

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        Report report = (Report) o;
        return getId() != null && Objects.equals(getId(), report.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

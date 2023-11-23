package nsu.medpollback.model.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "prescription_meds")
public class PrescriptionMed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dose")
    private String dose;

    @Column(name = "period")
    private String period;

    @Column(name = "period_type")
    private PeriodType periodType;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

}

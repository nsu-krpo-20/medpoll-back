package nsu.medpollback.model.entities;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "prescription_metrics")
public class PrescriptionMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "period")
    private String period;

    @Column(name = "period_type")
    private Integer periodType;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

}

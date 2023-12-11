package nsu.medpollback.model.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenerationTime;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private PatientCard patientCard;

    @Column(name = "created_time", insertable = false)
    private Timestamp createdTime;

    @Column(name = "edited_time", insertable = false)
    private Timestamp editedTime;

    @Column(name = "is_active")
    private Boolean isActive;

    @ToString.Exclude
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionMed> prescriptionMeds;

    @ToString.Exclude
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionMetric> prescriptionMetrics;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User user;
}

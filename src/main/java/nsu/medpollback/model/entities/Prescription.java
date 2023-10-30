package nsu.medpollback.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JoinColumn(name = "id")
    private PatientCard patientCard;

    @ToString.Exclude
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PrescriptionMed> prescriptionMeds = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<PrescriptionMetric> prescriptionMetrics = new LinkedHashSet<>();

}

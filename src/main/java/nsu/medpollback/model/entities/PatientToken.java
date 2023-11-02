package nsu.medpollback.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "patient_tokens")
public class PatientToken {
    @Id
    @Column(name = "card_id")
    private Long cardId;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @MapsId
    private PatientCard patientCard;

    @Column(name = "token", columnDefinition = "BINARY(16)")
    private UUID token;
}

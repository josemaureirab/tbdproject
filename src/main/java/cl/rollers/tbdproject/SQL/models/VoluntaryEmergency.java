package cl.rollers.tbdproject.SQL.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary_emergency")
public class VoluntaryEmergency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARYEMERGENCY_SEQ")
    @SequenceGenerator(name = "VOLUNTARYEMERGENCY_SEQ", sequenceName = "SEQ_VOLUNTARYEMERGENCY", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "voluntary_id")
    private Integer voluntary;

    @NonNull
    @Column(name = "emergency_id")
    private Integer emergency;
}
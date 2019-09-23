package cl.rollers.tbdproject.SQL.models;

import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToOne
    @JoinColumn
    private Voluntary voluntary;

    @ManyToOne
    @JoinColumn
    private Emergency emergency;
}
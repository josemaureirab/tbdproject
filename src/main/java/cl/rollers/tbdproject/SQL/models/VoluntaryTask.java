package cl.rollers.tbdproject.SQL.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary_task")
public class VoluntaryTask {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARYTASK_SEQ")
    @SequenceGenerator(name = "VOLUNTARYTASK_SEQ", sequenceName = "SEQ_VOLUNTARYTASK", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Voluntary voluntary;

    @ManyToOne
    @JoinColumn
    private Task task;
}
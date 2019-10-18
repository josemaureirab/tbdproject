package cl.rollers.tbdproject.SQL.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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

    @NonNull
    @Column(name = "voluntary_id")
    private Integer voluntary_id;

    @NonNull
    @Column(name = "task_id")
    private Integer task_id;
}
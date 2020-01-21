package cl.rollers.tbdproject.SQL.SQL2O.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name= "task")
public class Task implements Comparable<Task>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_SEQ")
    @SequenceGenerator(name = "TASK_SEQ", sequenceName = "SEQ_TASK", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "status")
    private Boolean status;

    @NonNull
    @Column(name = "emergency_id")
    private Integer emergency_id;

    @Override
    public int compareTo(Task task) {
        return this.getId().compareTo(task.getId());
    }
}
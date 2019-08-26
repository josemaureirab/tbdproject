package cl.rollers.tbdproject.SQL.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name= "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_SEQ")
    @SequenceGenerator(name = "TASK_SEQ", sequenceName = "SEQ_TASK", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "descrption")
    private String description;

    @NonNull
    @Column(name = "status")
    private Boolean status;
    
}

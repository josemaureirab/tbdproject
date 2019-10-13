package cl.rollers.tbdproject.SQL.secondDataSource.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name= "task")
public class SDSTask {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
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

}
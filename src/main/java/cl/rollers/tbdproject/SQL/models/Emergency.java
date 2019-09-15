package cl.rollers.tbdproject.SQL.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name= "emergencies")
public class Emergency {
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

    @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task> taskList;

    @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VoluntaryEmergency> voluntaryEmergencyList;
}
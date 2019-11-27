package cl.rollers.tbdproject.SQL.SQL2O.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name= "emergency")
public class Emergency implements Comparable<Emergency>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMERGENCY_SEQ")
    @SequenceGenerator(name = "EMERGENCY_SEQ", sequenceName = "SEQ_EMERGENCY", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "description")
    private String description;
    
    @Override
    public int compareTo(Emergency emergency) {
        return this.getId().compareTo(emergency.getId());
    }
}
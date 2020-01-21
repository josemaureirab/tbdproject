package cl.rollers.tbdproject.SQL.SQL2O.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dimension")
public class Dimension implements Comparable<Dimension>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "SEQ_ROLE", allocationSize = 1)
    private Integer id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "score")
    private Integer score;

    @Override
    public int compareTo(Dimension dimension) {
        return this.getId().compareTo(dimension.getId());
    }

}
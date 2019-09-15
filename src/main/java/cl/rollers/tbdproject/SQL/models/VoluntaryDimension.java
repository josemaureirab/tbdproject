package cl.rollers.tbdproject.SQL.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary_dimension")
public class VoluntaryDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARYDIM_SEQ")
    @SequenceGenerator(name = "VOLUNTARYDIM_SEQ", sequenceName = "SEQ_VOLUNTARYDIM", allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Voluntary voluntary;

    @ManyToOne
    @JoinColumn
    private Dimension dimension;
}
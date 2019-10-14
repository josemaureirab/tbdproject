package cl.rollers.tbdproject.SQL.firstDataSource.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary_dimension")
public class FDSVoluntaryDimension {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARYDIM_SEQ")
  @SequenceGenerator(name = "VOLUNTARYDIM_SEQ", sequenceName = "SEQ_VOLUNTARYDIM", allocationSize = 1)
  private Integer id;

  @ManyToOne
  @JoinColumn
  private FDSVoluntary voluntary;

  @ManyToOne
  @JoinColumn
  private FDSDimension dimension;
}

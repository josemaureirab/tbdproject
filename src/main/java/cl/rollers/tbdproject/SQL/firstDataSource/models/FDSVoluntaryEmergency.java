package cl.rollers.tbdproject.SQL.firstDataSource.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary_emergency")
public class FDSVoluntaryEmergency {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARYEMERGENCY_SEQ")
  @SequenceGenerator(name = "VOLUNTARYEMERGENCY_SEQ", sequenceName = "SEQ_VOLUNTARYEMERGENCY", allocationSize = 1)
  private Integer id;

  @ManyToOne
  @JoinColumn
  private FDSVoluntary voluntary;

  @ManyToOne
  @JoinColumn
  private FDSEmergency emergency;
}

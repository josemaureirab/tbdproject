package cl.rollers.tbdproject.SQL.firstDataSource.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "voluntary_task")
public class FDSVoluntaryTask {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOLUNTARYTASK_SEQ")
  @SequenceGenerator(name = "VOLUNTARYTASK_SEQ", sequenceName = "SEQ_VOLUNTARYTASK", allocationSize = 1)
  private Integer id;

  @ManyToOne
  @JoinColumn
  private FDSVoluntary voluntary;

  @ManyToOne
  @JoinColumn
  private FDSTask task;
}

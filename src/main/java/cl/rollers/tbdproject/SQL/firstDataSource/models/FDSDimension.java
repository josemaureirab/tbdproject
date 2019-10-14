package cl.rollers.tbdproject.SQL.firstDataSource.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dimension")
public class FDSDimension {
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

  @OneToMany(mappedBy = "fdsDimension", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<FDSVoluntaryDimension> fdsVoluntaryDimensionList;
}

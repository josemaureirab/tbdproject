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
@Table(name= "emergencies")
public class FDSEmergency {
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

  @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<FDSTask> taskList;

  @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<FDSVoluntaryEmergency> voluntaryEmergencyList;
}

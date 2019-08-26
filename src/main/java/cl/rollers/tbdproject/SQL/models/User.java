package cl.rollers.tbdproject.SQL.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_SEQ")
    @SequenceGenerator(name = "SERVICE_SEQ", sequenceName = "SEQ_SERVICE", allocationSize = 1)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String lastName;

    @NonNull
    private String rut;

    @NonNull
    private Integer age;

    @NonNull
    private String password;
    
}
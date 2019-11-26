package cl.rollers.tbdproject.DB;

import org.springframework.stereotype.Component;
import org.sql2o.Sql2o;

@Component
public class DatabaseConnection {

  public Sql2o sql2o[] = new Sql2o[2];
  
  public DatabaseConnection () {
    sql2o[0] = new Sql2o("jdbc:postgresql://localhost:5432/tbd1","postgres","secret");
    sql2o[1] = new Sql2o("jdbc:postgresql://localhost:5432/tbd2","postgres","secret");
  }
  
}

package cl.rollers.tbdproject.DB.SQL2O;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;

@Component
@Order(3)
public class DatabaseManager implements CommandLineRunner {

  @Autowired
  private DatabaseConnection databaseConnection;
  
  public void databaseAction (String databaseAction) {
    System.out.println("Chosen option: " + databaseAction);
    if (databaseAction.equals("create")) {
      dropAllTables();
      createAllTables();
    }
    else if (databaseAction.equals("update")) {
      System.out.println("Update isn't implemented yet, what's more, maybe you should implement it");
      System.out.println("Meanwhile, I'll run it in create anyway, cya");
      databaseAction("create");
    }
    else if (databaseAction.equals("createAndAddPostgis")) {
      addPostgisAllDbs();
      databaseAction("create");
    }
    else if (databaseAction.equals("updateAndAddPostgis")) {
      System.out.println("Update isn't implemented yet, what's more, maybe you should implement it");
      System.out.println("Meanwhile, I'll run it in create anyway, cya");
      addPostgisAllDbs();
      databaseAction("create");
    }
    else {
      System.out.println("Option not implemented, next time try another option");
      System.out.println("Running default option...");
      databaseAction("create");
    }
  }
  
  public void dropTable (int dbNumber, String tableName) {
    String dropTable = "drop table if exists " + tableName + " cascade";
    try(Connection connection = databaseConnection.sql2o[dbNumber].open()){
      connection.createQuery(dropTable).executeUpdate();
    }
  }
  
  public void dropAllTables () {
    for (int dbNumber = 0; dbNumber < databaseConnection.sql2o.length; dbNumber++) {
      dropTable(dbNumber, "dimension");
      dropTable(dbNumber, "emergency");
      dropTable(dbNumber, "task");
      dropTable(dbNumber, "voluntary");
      dropTable(dbNumber, "voluntary_dimension");
      dropTable(dbNumber, "voluntary_emergency");
      dropTable(dbNumber, "voluntary_task");
      /*dropTable(dbNumber, "user_roles");
      dropTable(dbNumber, "users");*/
    }
  }
  
  public void createTable (int dbNumber, String tableName, String values) {
    String createTable = "create table " + tableName + "( " + values + " )";
    try(Connection connection = databaseConnection.sql2o[dbNumber].open()){
      connection.createQuery(createTable).executeUpdate();
      if (tableName.equals("voluntary")) {
        String addLocation = "ALTER TABLE voluntary ADD COLUMN location geometry(point)";
        connection.createQuery(addLocation).executeUpdate();
      }
    }
  }
  
  public  void createAllTables () {
    for (int dbNumber = 0; dbNumber < databaseConnection.sql2o.length; dbNumber++) {
      createTable(dbNumber, "dimension", "id INT, name VARCHAR(255), score INT, primary key(id)" );
      createTable(dbNumber, "emergency", "id INT, name VARCHAR(255), description TEXT, primary key(id)" );
      createTable(dbNumber, "task", "id INT, name VARCHAR(255), description TEXT,  status BOOLEAN, emergency_id INT, primary key(id)" );
      createTable(dbNumber, "voluntary", "id INT, firstName VARCHAR(255), lastName VARCHAR(255), mail VARCHAR(255), gender VARCHAR(255), rut VARCHAR(255), age INT, primary key(id)" );
      createTable(dbNumber, "voluntary_dimension", "id INT, voluntary_id INT, dimension_id INT, primary key(id)" );
      createTable(dbNumber, "voluntary_emergency", "id INT, voluntary_id INT, emergency_id INT, primary key(id)" );
      createTable(dbNumber, "voluntary_task", "id INT, voluntary_id INT, task_id INT, primary key(id)" );
      /*createTable(dbNumber, "users", "id SERIAL, active BOOLEAN, birthDate DATE, firstName VARCHAR(255), lastName VARCHAR(255), password TEXT, rut VARCHAR(255), age INT, userName VARCHAR(255), primary key(id)" );
      createTable(dbNumber, "user_roles", "id SERIAL, user_id INT, role VARCHAR(255), primary key(id)" );*/
    }
  }
  
  public void seedEmergencies (Connection connection, int dataNumber) {
    connection.createQuery(
        "insert into emergency(id, name, description) values (:id, :name, :description)")
        .addParameter("id", + dataNumber)
        .addParameter("name", "Emergency " + dataNumber)
        .addParameter("description", "Descripción " + dataNumber)
        .executeUpdate();
  }

  public void seedTasks (Connection connection, int dataNumber) {
    connection.createQuery(
        "insert into task(id, name, description, status, emergency_id) values (:id, :name, :description, :status, :emergency_id)")
        .addParameter("id", + dataNumber)
        .addParameter("name", "task " + dataNumber)
        .addParameter("description", "descripción " + dataNumber)
        .addParameter("status", true)
        .addParameter("emergency_id", 1)
        .executeUpdate();
  }
  
  public void seedUsers (Connection connection) {
    Object nullParameter = null;
    /* Add a normal user */
    connection.createQuery(
        "insert into users(active, birthDate, firstName, lastName, password , rut, age , userName) values (:active, :birthDate, :firstName, :lastName, :password, :rut, :age, :userName)")
        .addParameter("active", true)
        .addParameter("birthDate", nullParameter)
        .addParameter("firstName", nullParameter)
        .addParameter("lastName", nullParameter)
        .addParameter("password", "{bcrypt}$2a$10$0SVxeMe2zWUG2iRt6zc/C.fvjz.pI5mZeSSYp/IzpivQmiQlUdZrO")
        .addParameter("rut", nullParameter)
        .addParameter("age", nullParameter)
        .addParameter("userName", "user")
        .executeUpdate();
    
    /* Add an admin user */
    connection.createQuery(
        "insert into users(active, birthDate, firstName, lastName, password , rut, age , userName) values (:active, :birthDate, :firstName, :lastName, :password, :rut, :age, :userName)")
        .addParameter("active", true)
        .addParameter("birthDate", nullParameter)
        .addParameter("firstName", nullParameter)
        .addParameter("lastName", nullParameter)
        .addParameter("password", "{bcrypt}$2a$10$0SVxeMe2zWUG2iRt6zc/C.fvjz.pI5mZeSSYp/IzpivQmiQlUdZrO")
        .addParameter("rut", nullParameter)
        .addParameter("age", nullParameter)
        .addParameter("userName", "admin")
        .executeUpdate();
    
    /* Add respective roles */
    connection.createQuery(
        "insert into user_roles(user_id, role) values (:user_id, :role)")
        .addParameter("user_id", 1)
        .addParameter("role", "normalUser")
        .executeUpdate();
  
    connection.createQuery(
        "insert into user_roles(user_id, role) values (:user_id, :role)")
        .addParameter("user_id", 2)
        .addParameter("role", "normalUser")
        .executeUpdate();
  
    connection.createQuery(
        "insert into user_roles(user_id, role) values (:user_id, :role)")
        .addParameter("user_id", 2)
        .addParameter("role", "adminUser")
        .executeUpdate();
  }
  
  public void addPostgis (int dbNumber) {
    String addPostgis = "create extension postgis";
    try(Connection conn = databaseConnection.sql2o[dbNumber].open()){
      conn.createQuery(addPostgis).executeUpdate();
    }
  }
  
  public void addPostgisAllDbs () {
    for (int dbNumber = 0; dbNumber < databaseConnection.sql2o.length; dbNumber++) {
      addPostgis(dbNumber);
    }
  }
  
  public void seed (int dataQuantity) {
    for (int dataNumber = 1; dataNumber < dataQuantity; dataNumber++) {
      int dbNumber = dataNumber % databaseConnection.sql2o.length;
      try(Connection connection = databaseConnection.sql2o[dbNumber].open()){
        seedEmergencies(connection, dataNumber);
        seedTasks(connection, dataNumber);
      }
    }
    /* Currently in disuse, using JPA builder */
    /* Seed static users */
    /*try(Connection connection = databaseConnection.sql2o[0].open()){
      seedUsers(connection);
    }*/
  }
  
  @Override
  public void run(String... args) throws Exception {
    /* Use databaseAction in create just if you have added postgis before */
    /* If you haven't added postgis use createAndAddPostgis */
    databaseAction("create");
    seed(60);
  }
}
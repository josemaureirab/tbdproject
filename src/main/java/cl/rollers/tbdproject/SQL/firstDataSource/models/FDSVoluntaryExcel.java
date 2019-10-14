package cl.rollers.tbdproject.SQL.firstDataSource.models;

import lombok.Data;

@Data
public class FDSVoluntaryExcel {
  private Integer id;
  private String name;
  private String lastName;
  private String mail;
  private String gender;
  private String dimensionList;
  private String requirementList;
  private Float latitude;
  private Float longitude;
}

package cl.rollers.tbdproject.SQL.SQL2O.models;

import lombok.Data;

@Data
public class VoluntaryExcel {
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
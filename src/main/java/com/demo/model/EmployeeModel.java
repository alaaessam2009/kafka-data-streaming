package com.demo.model;


import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@NoArgsConstructor
@ToString

public class EmployeeModel {

    @CsvBindByName(column = "NATIONAL-ID")
    private String nationalID;

    @CsvBindByName(column = "NAME")
    private String name;

    @CsvBindByName(column = "AMOUNT")
    private double amount ;

    @CsvBindByName(column = "FEES")
    private double fees ;

}

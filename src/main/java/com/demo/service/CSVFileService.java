package com.demo.service;

import com.demo.model.EmployeeModel;
import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

@Service
public class CSVFileService {

    public List<EmployeeModel> parseFile(CSVReader filePath) throws Exception{
      return new CsvToBeanBuilder(filePath).withType(EmployeeModel.class).build().parse();
    }

    public void generateFile(String filePath, EmployeeModel emps) throws Exception{
        File file = new File(filePath);
        MappingStrategy strategy ;
        if (file.exists())
            strategy = new CsvStrategy(EmployeeModel.class,false) ;
        else
            strategy = new CsvStrategy(EmployeeModel.class,true) ;
        Writer writer = new FileWriter(filePath, true);
        StatefulBeanToCsv<EmployeeModel> beanToCsv = new StatefulBeanToCsvBuilder(writer).withMappingStrategy(strategy).build();
        beanToCsv.write(emps);
        writer.flush();
        writer.close();
    }

}

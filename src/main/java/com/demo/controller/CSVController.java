package com.demo.controller;

import com.demo.model.EmployeeModel;
import com.demo.service.CSVFileService;
import com.demo.service.MultipartFileService;
import com.demo.service.KafkaService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CSVController {

    @Autowired
    CSVFileService csvFileService;

    @Autowired
    KafkaService kafkaService;

    @Autowired
    MultipartFileService multipartFileService;

    @PostMapping(value = "/file")
    public ResponseEntity parseFile(@RequestParam("file") MultipartFile file) throws Exception{
        CSVReader csvReader = multipartFileService.getCSVFileReader(file) ;
        List<EmployeeModel> employeeModels = csvFileService.parseFile(csvReader) ;
        kafkaService.streamDataFile(employeeModels);
        ResponseEntity<List<EmployeeModel>> streamedDataResponse = new ResponseEntity(employeeModels, HttpStatus.OK);
        return streamedDataResponse;
    }
}

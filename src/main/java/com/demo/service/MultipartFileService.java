package com.demo.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@Service
public class MultipartFileService {

    public CSVReader getCSVFileReader(MultipartFile multipartFile) throws Exception{
        Reader reader = new InputStreamReader(multipartFile.getInputStream());
        return new CSVReaderBuilder(reader).build();
    }

}

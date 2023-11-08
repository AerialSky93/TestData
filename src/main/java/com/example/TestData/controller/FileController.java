package com.example.TestData.controller;


import com.example.TestData.status.FileNotLocatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {
    Logger logger = LoggerFactory.getLogger(FileController.class);

    @GetMapping("/file")
    public List<String> getFileText() throws IOException {

        List<String> dataLines = new ArrayList<>();
        Resource companyDataResource = new ClassPathResource("readfiles/companies.txt");
        File file  = null;

        try {
            file = companyDataResource.getFile();
        } catch (IOException e) {
            throw new FileNotLocatedException("The file is not located.");
        }

        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                dataLines.add(line);
            }
        } catch (IOException e) {
            logger.error("cannot read file in resource folder");
            throw new RuntimeException(e);
        }

        return dataLines;
    }
}
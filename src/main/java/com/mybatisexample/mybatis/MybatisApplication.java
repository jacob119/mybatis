package com.mybatisexample.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mybatisexample.mybatis.data.Student;
import com.mybatisexample.mybatis.data.StudentMyBatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
public class MybatisApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private HdfsService hdfsService;

    @Autowired
    StudentMyBatisRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {

        //MultipartFile multipartFile
         //       = new MockMultipartFile("ReadMe.txt", new FileInputStream(new File("ReadMe.txt")));

        File file = new File("ReadMe.txt");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),
                file.getName(), "text/plain", IOUtils.toByteArray(input));

        hdfsService.uploadHDFS(multipartFile);

        logger.info("Student id 10001 -> {}", repository.deleteAll());

        logger.info("All users -> {}", repository.findAll());

        logger.info("Student id 10001 -> {}", repository.findById(10001L));

        logger.info("Inserting -> {}", repository.insert(new Student(10010L, "John", "A1234657")));

        logger.info("Update 10003 -> {}", repository.update(new Student(10001L, "Name-Updated", "New-Passport")));

        repository.deleteById(10002L);

        logger.info("All users -> {}", repository.findAll());
    }
}

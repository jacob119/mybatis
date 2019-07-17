package com.mybatisexample.mybatis;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

@Service
public class HdfsService {

    private FileSystem fileSystem;


    private String baseHDFS = "/user/test/";

    @Autowired
    private Configuration configuration;

    public void uploadHDFS(MultipartFile file) {

        try {
           // configuration.addResource("/Users/jacob/dev/hadoop/etc/hadoop/core-site.xml");
//            configuration.addResource("/Users/jacob/dev/hadoop/etc/hadoop/hfds-site.xml");

            FileSystem fileSystem = FileSystem.get(configuration);
            String dirPath = baseHDFS + file.getName();
            Path outputPath = new Path(dirPath);
            if (fileSystem.exists(outputPath)) {
                throw new IOException("Exist fileSystem files");
            }

            OutputStream outputStream = fileSystem.create(outputPath);
            IOUtils.copyBytes(file.getInputStream(), outputStream, configuration);
            fileSystem.close();

        } catch (IOException ex) {
            if(fileSystem != null){

                try{
                    fileSystem.close();
                }catch(Exception e){
                    System.out.println(e.getLocalizedMessage());
                }

            }
        }
    }
}

package com.mybatisexample.mybatis;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;


@Configuration
@EnableHadoop
public  class Config extends SpringHadoopConfigurerAdapter {

    @Override
    public void configure(HadoopConfigConfigurer configurer) throws Exception {
        configurer.fileSystemUri("hdfs://localhost:9000");
    }
}

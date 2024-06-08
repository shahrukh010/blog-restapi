package com.code.main;

import configuration.ElasticsearchMappingService;
import configuration.EsMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("configuration")
public class BlogappApplication implements CommandLineRunner {

    @Autowired
    private ElasticsearchMappingService elasticsearchMappingService;

    public static void main(String[] args) {
        SpringApplication.run(BlogappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Define index name and mapping string
        String indexName = "post";
        String mapping = EsMapping.ElasticsearchMappings.POST;
        // Create index mapping
//        elasticsearchMappingService.createIndexMapping(indexName, mapping);
    }

}

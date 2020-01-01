package com.atuigu.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 默认支持两种技术来和ES交互
 *  1、Jest（默认不生效）需要导入Jest的工具包（io.searchbox.client.JestClient）
 *  2、SpringData ElasticSearch 【ES版本有可能不合适】
 *      版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 *          Spring Data Elasticsearch 	Elasticsearch
 *                  3.2.x                   6.7.2
 *                  3.1.x                   6.2.2
 *                  3.0.x                   5.5.0
 *                  2.1.x                   2.4.0
 *                  2.0.x                   2.2.0
 *                  1.3.x                   1.5.2
 *      1）、Client：需要clusterNodes，clusterName
 *      2）、ElasticsearchTemplate 操作ES
 *      3）、编写一个 ElasticsearchRepository 的子接口来操作ES(类似jpa的编程方式)
 * 两种用法：
 *  1）、编写一个 ElasticsearchRepository
 */
@SpringBootApplication
public class Springboot03ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03ElasticsearchApplication.class, args);
    }

}

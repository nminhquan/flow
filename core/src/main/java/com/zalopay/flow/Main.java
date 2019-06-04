package com.zalopay.flow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

/**
 * Created by thinhda.
 * Date: 2019-05-21
 */

@Slf4j
//
@SpringBootApplication
@PropertySource(ignoreResourceNotFound = true, value = "classpath:git.properties")
public class Main {

    @Value("${git.commit.id:UNKNOWN}")
    private String commitId;
    @Value("${git.build.version:UNKNOWN}")
    private String buildVersion;
    @Value("${git.build.tags:UNKNOWN}")
    private String tags;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void initialize() {
        log.info("========================================================");
        log.info("  APP VERSION: {}", buildVersion);
        log.info("  GIT VERSION: {}", commitId);
        log.info("      GIT TAG: {}", tags);
        log.info("========================================================");
    }

}

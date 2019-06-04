package com.zalopay.flow.core.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Map;

@Getter
@Setter
@Document
public class Task {
    @Id
    private String name;
    private String service;
    private String entryPoint;
    private Map<String, String> parameters;
    private int timeout;
    private int retryCount;
}

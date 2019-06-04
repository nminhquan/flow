package com.zalopay.flow.core.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter @Setter
@Document
public class TaskExecution {
    private String name;
    private String taskReferenceName;
    private Map<String, Object> inputParameters;
    private Map<String, String> decision;
}

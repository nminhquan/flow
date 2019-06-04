package com.zalopay.flow.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@ToString
@Document
public class Flow {
    @Id
    private String name;
    private String type;
    private String description;
    private List<TaskExecution> tasks;
}

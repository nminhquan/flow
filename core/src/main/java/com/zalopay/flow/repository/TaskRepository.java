package com.zalopay.flow.repository;

import com.zalopay.flow.core.task.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}

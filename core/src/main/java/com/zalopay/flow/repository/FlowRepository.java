package com.zalopay.flow.repository;

import com.zalopay.flow.core.model.Flow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowRepository extends MongoRepository<Flow, String> {

}

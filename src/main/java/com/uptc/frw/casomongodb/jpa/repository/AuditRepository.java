package com.uptc.frw.casomongodb.jpa.repository;

import com.uptc.frw.casomongodb.jpa.models.Audit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditRepository extends MongoRepository <Audit, String>{

}

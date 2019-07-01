package com.agathium.common.repository;

import com.agathium.common.configuration.Schema;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 21:33
 */
public interface SchemasRepository extends MongoRepository<Schema, String> {
}

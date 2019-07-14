package com.agathium.common.repository;

import com.agathium.common.document.Mutations;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 21:33
 */
public interface MutationsRepository extends MongoRepository<Mutations, String> {
}

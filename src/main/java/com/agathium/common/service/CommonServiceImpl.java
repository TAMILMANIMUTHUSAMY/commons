package com.agathium.common.service;

import com.agathium.common.configuration.Schema;
import com.agathium.common.exception.EntityNotFoundException;
import com.agathium.common.repository.CommonRepository;
import com.agathium.common.repository.SchemaRepository;
import com.agathium.common.service.api.CommonService;
import com.agathium.common.validator.api.SchemaValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 02-07-2019 21:55
 */
@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private SchemaRepository schemaRepository;

    @Autowired
    private SchemaValidator schemaValidator;

    @Override
    public Object get(String collection, String id) {
        return commonRepository.find(collection, id);
    }

    @Override
    public Object post(String collection, Object object) {
        Schema schema = schemaRepository.findById(collection).orElseThrow(() -> new EntityNotFoundException("Schema", collection));
        schemaValidator.validate(schema, object);
        return commonRepository.save(collection, object).getObjectId("_id").toString();
    }

    @Override
    public Object put(String collection, String id, Object object) {
        return commonRepository.update(collection, id, object).getObjectId("_id").toString();
    }

    @Override
    public Object delete(String collection, String id) {
        return commonRepository.delete(collection, id).getObjectId("_id").toString();
    }
}

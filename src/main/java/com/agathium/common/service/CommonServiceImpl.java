package com.agathium.common.service;

import com.agathium.common.document.Schema;
import com.agathium.common.exception.EntityNotFoundException;
import com.agathium.common.repository.CommonRepository;
import com.agathium.common.repository.SchemaRepository;
import com.agathium.common.service.api.CommonService;
import com.agathium.common.validator.api.SchemaValidator;
import org.bson.Document;
import org.springframework.stereotype.Service;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 02-07-2019 21:55
 */
@Service
public class CommonServiceImpl implements CommonService {

    private SchemaValidator schemaValidator;
    private CommonRepository commonRepository;
    private SchemaRepository schemaRepository;

    public CommonServiceImpl(CommonRepository commonRepository, SchemaRepository schemaRepository, SchemaValidator schemaValidator) {
        this.commonRepository = commonRepository;
        this.schemaRepository = schemaRepository;
        this.schemaValidator = schemaValidator;
    }

    @Override
    public Object get(String collection, String id) {
        Document document = commonRepository.find(collection, id);
        if (document == null)
            throw new EntityNotFoundException(collection, id);
        document.replace("_id", document.getObjectId("_id").toString());
        return document;
    }

    @Override
    public Object post(String collection, Object object) {
        Schema schema = schemaRepository.findById(collection).orElseThrow(() -> new EntityNotFoundException("Schema", collection));
        schemaValidator.validate(schema, object);
        return commonRepository.save(collection, object).getObjectId("_id").toString();
    }

    @Override
    public Object put(String collection, String id, Object object) {
        Schema schema = schemaRepository.findById(collection).orElseThrow(() -> new EntityNotFoundException("Schema", collection));
        schemaValidator.validate(schema, object);
        Document document = commonRepository.update(collection, id, object);
        if (document == null)
            throw new EntityNotFoundException(collection, id);
        return document.getObjectId("_id").toString();
    }

    @Override
    public Object delete(String collection, String id) {
        Document document = commonRepository.delete(collection, id);
        if (document == null)
            throw new EntityNotFoundException(collection, id);
        return document.getObjectId("_id").toString();
    }
}

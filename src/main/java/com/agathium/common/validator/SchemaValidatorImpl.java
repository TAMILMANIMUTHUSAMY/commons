package com.agathium.common.validator;

import com.agathium.common.document.Field;
import com.agathium.common.document.Schema;
import com.agathium.common.exception.Errors;
import com.agathium.common.exception.ValidationException;
import com.agathium.common.validator.api.SchemaValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 02-07-2019 22:27
 */
@Component
public class SchemaValidatorImpl implements SchemaValidator {

    private static final String ROOT_PREFIX = "$.";
    private static final String PATH_NOTATION = ".";

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void validate(Schema schema, Object object) {

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String jSON = json;
        schema.getFields().forEach(field -> {
            String xpath = ROOT_PREFIX + field.getName();
            LOGGER.debug(xpath);
            Object o = null;
            try {
                o = JsonPath.read(jSON, xpath);
            } catch (PathNotFoundException e) {

                validateField(field, null);
            }
            LOGGER.debug(o);
            validateField(field, o);
        });
    }

    private void validateField(Field field, Object o) {
        if (!field.getNullable() && (o == null || o.toString().equals(""))) {
            throw new ValidationException(new Errors(field.getName() + " is mandatory"));
        }
    }
}

package com.agathium.common.validator.api;

import com.agathium.common.document.Schema;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 02-07-2019 22:28
 */
public interface SchemaValidator {

    void validate(Schema schema, Object object);
}

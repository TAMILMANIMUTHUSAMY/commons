package com.agathium.common.exception;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 23:22
 */
public class SchemaNotDefinedException extends RuntimeException {

    public SchemaNotDefinedException(String collection) {
        super(String.format("Schema '%s' not defined.", collection));
    }
}

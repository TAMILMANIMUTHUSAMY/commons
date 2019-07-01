package com.agathium.common.exception;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 23:22
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String name, String id) {
        super(String.format("Could not find %s for given id %s", name, id));
    }
}

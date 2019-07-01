package com.agathium.common.configuration;

import java.util.List;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 21:20
 */

public class Schema {

    private String collectionName;

    private List<Field> fields;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}

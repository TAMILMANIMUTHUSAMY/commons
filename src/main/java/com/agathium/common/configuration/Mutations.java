package com.agathium.common.configuration;

import java.util.List;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 21:25
 */
public class Mutations {

    private String id;
    private String collectionName;
    private List<String> fields;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}

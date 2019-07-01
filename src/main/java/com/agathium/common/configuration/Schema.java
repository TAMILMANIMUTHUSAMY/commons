package com.agathium.common.configuration;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 21:20
 */

@Document
@TypeAlias("schema")
public class Schema {

    @Id
    private String collectionName;

    @org.springframework.data.mongodb.core.mapping.Field("fields")
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

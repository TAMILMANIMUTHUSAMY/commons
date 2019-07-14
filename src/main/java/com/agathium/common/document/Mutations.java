package com.agathium.common.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 21:25
 */
@Document
@TypeAlias("mutation")
public class Mutations {

    @Id
    private String id;

    @Field("collectionname")
    private String collectionName;

    @Field("fields")
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

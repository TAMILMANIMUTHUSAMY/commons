package com.agathium.common.repository;

import com.agathium.common.configuration.MongoDBConnection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 11:52
 */
@Component
public class CommonRepository {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private MongoDBConnection dbConnection;

    public Document save(String collection, Object object) {
        MongoDatabase mongoDatabase = dbConnection.getConnection().getDatabase("agathium_applications_data");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.debug("Error while parsing to json ", e);
        }
        Document document = Document.parse(json);
        mongoCollection.insertOne(document);
        return document;
    }

    public Document update(String collection, String id, Object object) {
        MongoDatabase mongoDatabase = dbConnection.getConnection().getDatabase("agathium_applications_data");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        Bson bson = Updates.set("firstname", "xxxx");
        FindOneAndUpdateOptions findOneAndUpdateOptions = new FindOneAndUpdateOptions();
        findOneAndUpdateOptions.returnDocument(ReturnDocument.AFTER);
        return mongoCollection.findOneAndUpdate(Filters.eq("_id", new ObjectId(id)), bson, findOneAndUpdateOptions);
    }

    public Document find(String collection, String id) {
        MongoDatabase mongoDatabase = dbConnection.getConnection().getDatabase("agathium_applications_data");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        Bson bson = Filters.eq("_id", new ObjectId(id));
        return mongoCollection.find(bson).first();
    }

    public Document delete(String collection, String id) {
        MongoDatabase mongoDatabase = dbConnection.getConnection().getDatabase("agathium_applications_data");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        Bson bson = Filters.eq("_id", new ObjectId(id));
        return mongoCollection.findOneAndDelete(bson);
    }
}

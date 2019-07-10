package com.agathium.common.repository;

import com.agathium.common.configuration.MongoDBConfigurationProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 11:52
 */
@Component
public class CommonRepository {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "_id";
    private MongoClient mongoClient;
    private MongoDBConfigurationProperties configurationProperties;

    public CommonRepository(MongoClient mongoClient, MongoDBConfigurationProperties configurationProperties) {
        this.mongoClient = mongoClient;
        this.configurationProperties = configurationProperties;
    }

    public Document save(String collection, Object object) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(configurationProperties.getDatabase());
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
        MongoDatabase mongoDatabase = mongoClient.getDatabase(configurationProperties.getDatabase());
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.debug("Error while parsing to json ", e);
        }
        Document document = Document.parse(json);
        List<Bson> bsons = new ArrayList<>();
        for (Map.Entry<String, Object>  entry: document.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            bsons.add(Updates.set(key, value));
        }
        Bson finalUpdate = Updates.combine(bsons);
        FindOneAndUpdateOptions findOneAndUpdateOptions = new FindOneAndUpdateOptions();
        findOneAndUpdateOptions.returnDocument(ReturnDocument.AFTER);
        return mongoCollection.findOneAndUpdate(Filters.eq(ID, new ObjectId(id)), finalUpdate, findOneAndUpdateOptions);
    }

    public Document find(String collection, String id) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(configurationProperties.getDatabase());
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        Bson bson = Filters.eq(ID, new ObjectId(id));
        return mongoCollection.find(bson).first();
    }

    public Document delete(String collection, String id) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase(configurationProperties.getDatabase());
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
        Bson bson = Filters.eq(ID, new ObjectId(id));
        return mongoCollection.findOneAndDelete(bson);
    }
}

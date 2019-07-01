package com.agathium.common.configuration;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 18:23
 */
@Component
public class MongoDBConnection {

    @Autowired
    private MongoDBConfiguration dbConfiguration;

    public MongoClient getConnection() {
        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(
                        builder -> builder.hosts(dbConfiguration.getHosts().stream()
                                .map(serverAddress -> new ServerAddress(serverAddress.getHost(), serverAddress.getPort()))
                                .collect(Collectors.toList()))
                ).build());
    }

}

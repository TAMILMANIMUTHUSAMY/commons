package com.agathium.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 17:38
 */
@ConfigurationProperties("mongodb")
@Component
public final class MongoDBConfigurationProperties {

    private List<ServerAddress> hosts = new ArrayList<>();

    private String database;

    public List<ServerAddress> getHosts() {
        return new ArrayList<>(hosts);
    }

    public void setHosts(List<ServerAddress> hosts) {
        this.hosts = hosts;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}

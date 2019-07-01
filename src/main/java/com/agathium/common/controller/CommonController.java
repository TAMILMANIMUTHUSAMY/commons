package com.agathium.common.controller;

import com.agathium.common.configuration.MongoDBConfiguration;
import com.agathium.common.configuration.MongoDBConnection;
import com.agathium.common.repository.CommonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 11:31
 */
@RequestMapping("/")
@RestController
public class CommonController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    MongoDBConfiguration dbConfiguration;

    @Autowired
    MongoDBConnection dbConnection;

    @Autowired
    CommonRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Object get() {
        LOGGER.debug(dbConfiguration.getHosts());
        LOGGER.debug(dbConnection.getConnection());
        LOGGER.debug(dbConnection.getConnection());
        LOGGER.debug(dbConnection.getConnection());
        LOGGER.debug(dbConnection.getConnection());
        LOGGER.debug(dbConnection.getConnection());
        LOGGER.debug(dbConnection.getConnection());
        LOGGER.debug(dbConnection.getConnection());
        LOGGER.debug(dbConnection.getConnection());
        return "Hello!..";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object post(@RequestBody Object object) {
        return repository.save("test_1", object);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object put(@PathVariable String id, @RequestBody Object object) {
        return repository.update("test_1", id, object);
    }

}

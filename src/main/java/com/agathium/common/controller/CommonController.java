package com.agathium.common.controller;

import com.agathium.common.service.CommonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private CommonServiceImpl service;

    @GetMapping(path = "/{collection}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@PathVariable("collection") String collection, @PathVariable String id) {
        return new ResponseEntity<>(service.get(collection, id), HttpStatus.OK);
    }

    @PostMapping(path = "/{collection}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> post(@PathVariable("collection") String collection, @RequestBody Object object) {
        return new ResponseEntity<>(service.post(collection, object), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{collection}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> put(@PathVariable("collection") String collection, @PathVariable String id, @RequestBody Object object) {
        return new ResponseEntity<>(service.put(collection, id, object), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{collection}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable("collection") String collection, @PathVariable String id) {
        return new ResponseEntity<>(service.delete(collection, id), HttpStatus.OK);
    }

}

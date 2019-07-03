package com.agathium.common.controller;

import com.agathium.common.configuration.Schema;
import com.agathium.common.exception.EntityNotFoundException;
import com.agathium.common.repository.SchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Tamilmani
 * @version : 1.0
 * @since : 01-07-2019 23:14
 */
@RequestMapping("/")
@RestController
public class SchemaController {

    @Autowired
    private SchemaRepository repository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schema> post(@RequestBody Schema schema) {
        return new ResponseEntity<>(repository.save(schema), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schema> put(@RequestBody Schema schema) {
        return new ResponseEntity<>(repository.save(schema), HttpStatus.OK);
    }

    @GetMapping(path = "/{collectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schema> get(@PathVariable("collectionName") String collectionName) {
        return new ResponseEntity<>(repository.findById(collectionName)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Schema", collectionName);
                }), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{collectionName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("collectionName") String collectionName) {
        repository.deleteById(collectionName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

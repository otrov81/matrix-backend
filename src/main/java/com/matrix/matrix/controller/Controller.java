package com.matrix.matrix.controller;

import com.matrix.matrix.model.Kab;
import com.matrix.matrix.model.User;
import com.matrix.matrix.service.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/matrix")
@CrossOrigin(origins = "*") // Dodajte ovo ovde
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = service.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addNeuUser(@Validated @RequestBody User user) {
        User user1 = service.addNeuUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> getDeleteUserById(@PathVariable(name = "id") Long id) {
        boolean isDelete = service.chekUser(id);
        if (isDelete) {
            service.getDeleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return ResponseEntity.status(202).body("User with Id " + id + " does not exist.");
        }
    }

    @GetMapping(path = "/find/{id}")
    public ResponseEntity<?> findUserById(@PathVariable(name = "id") Long id) {
        boolean chekU = service.chekUser(id);
        if (chekU) {
            User user = service.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return ResponseEntity.status(202).body("User not found with id " + id);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = service.updateUser(id, user);
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/allKab")
    public ResponseEntity<List<Kab>> getAllKab() {
        List<Kab> kabList = service.getAllKab();
        return new ResponseEntity<>(kabList, HttpStatus.OK);
    }

    @GetMapping(path = "/allKabVer2")
    public ResponseEntity<List<Kab>> getAllKabVersion2() {
        List<Kab> kabList = service.getAllKabVer2();
        return new ResponseEntity<>(kabList, HttpStatus.OK);
    }
}

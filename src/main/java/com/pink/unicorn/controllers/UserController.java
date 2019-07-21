package com.pink.unicorn.controllers;

import com.pink.unicorn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> registration(@RequestBody String user) {
        String response = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        String response = userService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/user/{id}/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody String user, @PathVariable Long id) {
        String response = userService.update(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/user/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
    * Exceptions handling
    */
    @ExceptionHandler
    public ResponseEntity<String> onConflictingUserEmail(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
    }
}

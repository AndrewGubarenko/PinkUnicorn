package com.pink.unicorn.controllers;

import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.services.IUserService;
import com.pink.unicorn.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 *@author Andrii Hubarenko
 * Rest Controller for processing user's requests
 */
@RestController
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getSimpleName());
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> registration(@RequestBody String user) throws IOException, EmptyDataException {
        String response = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        String response = userService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/user/{id}/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody String user, @PathVariable Long id) throws IOException {
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
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass())
                + ": User with such email already registered."
                + "/ "
                + e.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onEmptyData(EmptyDataException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass())
                + ": One or more data`s fields are empty."
                + "/ "
                + e.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUser(EmptyResultDataAccessException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass())
                + ": No such element was found"
                + "/ ");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUserId(NoSuchElementException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass())
                + ": No such user was found"
                + "/ ");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass())
                + ": Check the arguments!"
                + "/ "
                + e.getLocalizedMessage());
    }
}

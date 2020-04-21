package com.pink.unicorn.controllers;

import com.pink.unicorn.domain.PlainObjects.AuthData;
import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.services.interfaces.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
 *@author Andrii Hubarenko
 * <p>Rest Controller for processing user's requests</p>
 */
@RestController
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getSimpleName());
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<PlainUser> registration(@RequestBody User user) {
        PlainUser response = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = "/authentication")
    public ResponseEntity<PlainUser> authenticate(@RequestBody AuthData authData) {
        PlainUser respond = userService.findByEmailAndPassword(authData);
        return ResponseEntity.status(HttpStatus.OK).body(respond);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<PlainUser> getUser(@PathVariable Long id) {
        PlainUser response = userService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/users/{id}")
    public ResponseEntity<PlainUser> updateUser(@RequestBody User user, @PathVariable Long id) {
        PlainUser response = userService.update(user, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/users/{id}")
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
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass()) + ": User with such email already registered.");
    }

    @ExceptionHandler
    public ResponseEntity<String> onEmptyData(EmptyDataException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUser(EmptyResultDataAccessException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass()) + ": No such user was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> onMissingUserId(NoSuchElementException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ClassUtils.getShortName(e.getClass()) + ": No such user was found");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIOException(IOException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass()) + ": Check the arguments!");
    }

    @ExceptionHandler
    public ResponseEntity<String> handleSQLException(SQLException e) {
        LOGGER.error(ClassUtils.getShortName(e.getClass()) + ": " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ClassUtils.getShortName(e.getClass()) + ": SQL statement problem");
    }
}

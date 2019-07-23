package com.pink.unicorn.services;

import com.pink.unicorn.exceptions.EmptyDataException;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @author Andrii Hubarenko
 * This interface explains the main methods, that must be implemented by any class of user services, to get a completely workable application.
 */
public interface IUserService {

    /**
     * @param user
     * @return JSON string of user
     * @throws IOException
     * @throws EmptyDataException
     */
    String create(String user) throws IOException, EmptyDataException;

    /**
     * Method for updating user's data
     * @param user
     * @param id
     * @return updated user in JSON format
     * @throws IOException
     * @throws NoSuchElementException
     */
    String update(String user, Long id) throws IOException;

    /**
     * Method for getting user
     * @param id
     * @return user in JSON format
     * @throws NoSuchElementException
     */
    String get(Long id);

    /**
     * Method uses for searching of user by registration parameters, usually for authentication
     * @param email
     * @param password
     * @return user in JSON format
     * @throws NoSuchElementException
     */
    String findByEmailAndPassword(String email, String password);

    /**
     * Method for deleting user
     * @param id
     * @return String message about success deleting
     * @throws NoSuchElementException
     */
    String delete(Long id);
}

package com.pink.unicorn.services;

import com.pink.unicorn.exceptions.EmptyDataException;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of User services, to get a completely workable application.</p>
 */
public interface IUserService {

    /**
     * <p>Method for creating user.</p>
     * @param user
     * @return JSON string of user
     * @throws EmptyDataException
     */
    String create(String user) throws Exception;

    /**
     * <p>Method for updating user's data</p>
     * @param user
     * @param id
     * @return updated user in JSON format
     * @throws Exception
     */
    String update(String user, Long id) throws Exception;

    /**
     * <p>Method for getting user</p>
     * @param id
     * @return user in JSON format
     * @throws Exception
     */
    String get(Long id) throws Exception;

    /**
     * <p>Method uses for searching of user by registration parameters, usually for authentication</p>
     * @param authData
     * @return user in JSON format
     * @throws Exception
     */
    String findByEmailAndPassword(String authData) throws Exception;

    /**
     * <p>Method for deleting user</p>
     * @param id
     * @return String message about success deleting
     * @throws Exception
     */
    String delete(Long id) throws Exception;
}

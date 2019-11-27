package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.exceptions.EmptyDataException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author Andrii Hubarenko
 * <p>This interface explains the main methods, that must be implemented by any class of User services, to get a completely workable application.</p>
 */
public interface IUserService {

    /**
     * <p>Method for creating user.</p>
     * @param user
     * @return PlainUser
     */
    @Transactional
    PlainUser create(PlainUser user);

    /**
     * <p>Method for updating user's data</p>
     * @param user
     * @param id
     * @return PlainUser
     * @throws EmptyDataException
     */
    @Transactional
    PlainUser update(PlainUser user, Long id) throws EmptyDataException;

    /**
     * <p>Method for getting user</p>
     * @param id
     * @return PlainUser
     * @throws EmptyDataException
     */
    @Transactional
    PlainUser get(Long id) throws EmptyDataException;

    /**
     * <p>Method uses for searching of user by registration parameters, usually for authentication</p>
     * @param authData
     * @return PlainUser
     * @throws IOException
     * @throws EmptyDataException
     */
    @Transactional
    PlainUser findByEmailAndPassword(String authData) throws IOException, EmptyDataException;

    /**
     * <p>Method for deleting user</p>
     * @param id
     * @return String
     * @throws EmptyDataException
     */
    @Transactional
    String delete(Long id) throws EmptyDataException;

    /**
     * <p>Method for adding product to wish list</p>
     * @param plainUser
     * @return Boolean
     * @throws EmptyDataException
     */
    @Transactional
    Boolean addProductToWishList (PlainUser plainUser, Long userId) throws EmptyDataException;
}

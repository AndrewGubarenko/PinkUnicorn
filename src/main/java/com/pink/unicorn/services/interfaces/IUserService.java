package com.pink.unicorn.services.interfaces;

import com.pink.unicorn.domain.PlainObjects.AuthData;
import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.User;
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
    PlainUser create(User user);

    /**
     * <p>Method for updating user's data</p>
     * @param user
     * @param id
     * @return PlainUser
     */
    @Transactional
    PlainUser update(User user, Long id);

    /**
     * <p>Method for getting user</p>
     * @param id
     * @return PlainUser
     */
    @Transactional
    PlainUser get(Long id) throws EmptyDataException;

    /**
     * <p>Method uses for searching of user by registration parameters, usually for authentication</p>
     * @param authData
     * @return PlainUser
     */
    @Transactional
    PlainUser findByEmailAndPassword(AuthData authData);

    /**
     * <p>Method for deleting user</p>
     * @param id
     * @return String
     */
    @Transactional
    String delete(Long id) throws EmptyDataException;

    /**
     * <p>Method for adding product to wish list</p>
     * @param user
     * @return Boolean
     */
    @Transactional
    Boolean addProductToWishList (User user, Long userId);
}

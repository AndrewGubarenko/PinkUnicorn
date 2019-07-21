package com.pink.unicorn.services;

/**
 *
 * @author Andrii Hubarenko
 * This interface explains the main methods, that must be implemented by any class of user services, to get a completely workable application.
 *
 */
public interface IUserService {

    String create(String user);

    String update(String user, Long id);

    String get(Long id);

    String delete(Long id);
}

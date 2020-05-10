package com.pink.unicorn.services;

import com.pink.unicorn.domain.PlainObjects.AuthData;
import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.Role;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.UserRepository;
import com.pink.unicorn.services.interfaces.IUserService;
import com.pink.unicorn.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>This class implements an interfase IUserService</p>
 */
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserConverter userConverter,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public PlainUser create(User user) {

        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new EmptyDataException("Empty EMAIL field");
        } else if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new EmptyDataException("Empty PASSWORD field");
        }

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setPhone(user.getPhone());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoles(roles);
        userRepository.save(newUser);

        return userConverter.UserToPlain(newUser);
    }

    @Override
    @Transactional
    public PlainUser update(User updatedUser, Long id) {

        if (updatedUser.getEmail() == null || updatedUser.getEmail().equals("")) {
            throw new EmptyDataException("Empty EMAIL field");
        } else if (updatedUser.getPassword() == null || updatedUser.getPassword().equals("")) {
            throw new EmptyDataException("Empty PASSWORD field");
        }

        Optional<User> userForUpdateOpt = userRepository.findById(id);
        if (!userForUpdateOpt.isPresent()) {
            throw new EmptyDataException(new StringBuilder("No user with id ").append(id).append(" exists!").toString());
        }
        User userForUpdate = userForUpdateOpt.get();
        userForUpdate.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userForUpdate.setPhone(updatedUser.getPhone());

        userRepository.save(userForUpdate);

        return userConverter.UserToPlain(userForUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public PlainUser get(Long id) {
        Optional<User> foundUserOpt = userRepository.findById(id);
        if (!foundUserOpt.isPresent()) {
            throw new NoSuchElementException(new StringBuilder("No user with id ").append(id).append(" exists!").toString());
        }
        User result = foundUserOpt.get();
        return userConverter.UserToPlain(result);
    }

    @Override
    @Transactional(readOnly = true)
    public PlainUser findByEmailAndPassword(AuthData authData) {

        if (authData.getEmail() == null || authData.getEmail().equals("")) {
            throw new EmptyDataException("Empty EMAIL field");
        } else if (authData.getPassword() == null || authData.getPassword().equals("")) {
            throw new EmptyDataException("Empty PASSWORD field");
        }

        Optional<User> foundUserOpt = userRepository.findByEmailAndPassword(authData.getEmail(), passwordEncoder.encode(authData.getPassword()));
        if (!foundUserOpt.isPresent()) {
            throw new NoSuchElementException("Wrong user email or password" );
        }
        return userConverter.UserToPlain(foundUserOpt.get());
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<User> userForDeleteOpt = userRepository.findById(id);
        if (!userForDeleteOpt.isPresent()) {
            throw new NoSuchElementException(new StringBuilder("No user with id ").append(id).append(" exists!").toString());
        }
        User userForDelete = userForDeleteOpt.get();
        userForDelete.removeAllOrders();
        userForDelete.getWishList().clear();
        userForDelete.getRoles().clear();
        String userEmail = userForDelete.getEmail();
        userRepository.delete(userForDelete);
        return new StringBuilder("User with email ").append(userEmail).append(" was completely removed").toString();
    }

    @Override
    @Transactional
    public Boolean addProductToWishList (User user, Long userId) {
        Optional<User> userForAddWishListOpt = userRepository.findById(userId);
        if (!userForAddWishListOpt.isPresent()) {
            throw new NoSuchElementException(new StringBuilder("No user with id ").append(userId).append(" exists!").toString());
        }
        List<Long> listOfProductIds = user.getWishList().stream().collect(Collectors.toList());
        User userForAddWishList = userForAddWishListOpt.get();
        userForAddWishList.setWishList(listOfProductIds);
        return true;
    }

}

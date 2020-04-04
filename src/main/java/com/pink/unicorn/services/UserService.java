package com.pink.unicorn.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Andrii Hubarenko
 * <p>This class implements an interfase IUserService</p>
 */
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       ObjectMapper objectMapper,
                       UserConverter userConverter,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public PlainUser create(PlainUser plainUser) {
        User newUser = new User();

        newUser.setEmail(plainUser.getEmail());
        newUser.setPassword(passwordEncoder.encode(plainUser.getPassword()));
        newUser.setPhone(plainUser.getPhone());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoles(roles);
        userRepository.save(newUser);

        return userConverter.UserToPlain(newUser);
    }

    @Override
    @Transactional
    public PlainUser update(PlainUser updatedPlaneUser, Long id) throws EmptyDataException {
        Optional<User> userForUpdateOpt = userRepository.findById(id);
        if (!userForUpdateOpt.isPresent()) {
            throw new EmptyDataException("No user with id " + id + " exists!" );
        }
        User userForUpdate = userForUpdateOpt.get();
        userForUpdate.setPassword(passwordEncoder.encode(updatedPlaneUser.getPassword()));
        userForUpdate.setPhone(updatedPlaneUser.getPhone());

        userRepository.save(userForUpdate);

        return userConverter.UserToPlain(userForUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public PlainUser get(Long id) throws EmptyDataException {
        Optional<User> foundUserOpt = userRepository.findById(id);
        if (!foundUserOpt.isPresent()) {
            throw new EmptyDataException("No user with id " + id + " exists!" );
        }
        User result = foundUserOpt.get();
        return userConverter.UserToPlain(result);
    }

    @Override
    @Transactional(readOnly = true)
    public PlainUser findByEmailAndPassword(String authData) throws IOException, EmptyDataException{
        JsonNode rootNode = objectMapper.readTree(authData);
        if (rootNode.path("email").asText().equals("")) {
            throw new EmptyDataException("Empty EMAIL field");
        } else if (rootNode.path("password").asText().equals("")) {
            throw new EmptyDataException("Empty PASSWORD field");
        }
        String email = rootNode.path("email").asText().toLowerCase();
        String password = rootNode.path("password").asText();
        Optional<User> foundUserOpt = userRepository.findByEmailAndPassword(email, passwordEncoder.encode(password));
        if (!foundUserOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        return userConverter.UserToPlain(foundUserOpt.get());
    }

    @Override
    @Transactional
    public String delete(Long id) throws EmptyDataException {
        Optional<User> userForDeleteOpt = userRepository.findById(id);
        if (!userForDeleteOpt.isPresent()) {
            throw new EmptyDataException("No user with id " + id + " exists!" );
        }
        User userForDelete = userForDeleteOpt.get();
        userForDelete.removeAllOrders();
        userForDelete.getWishList().clear();
        userForDelete.getRoles().clear();
        String userEmail = userForDelete.getEmail();
        userRepository.delete(userForDelete);
        return "User with email " + userEmail + " was completely removed";
    }

    @Override
    @Transactional
    public Boolean addProductToWishList (PlainUser plainUser, Long userId) throws EmptyDataException {
        Optional<User> userForAddWishListOpt = userRepository.findById(userId);
        if (!userForAddWishListOpt.isPresent()) {
            throw new EmptyDataException("No user with id " + userId + " exists!" );
        }
        List<Long> listOfProductIds = plainUser.getWishList().stream().map(plainProduct -> plainProduct.getId()).collect(Collectors.toList());
        User userForAddWishList = userForAddWishListOpt.get();
        userForAddWishList.setWishList(listOfProductIds);
        return true;
    }

}

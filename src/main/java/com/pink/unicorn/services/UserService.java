package com.pink.unicorn.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.Role;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.UserRepository;
import com.pink.unicorn.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

/**
 * @author Andrii Hubarenko
 * <p>This class implements an interfase IUserService</p>
 */
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public PlainUser create(PlainUser plainUser) {
        User newUser = new User();

        newUser.setEmail(plainUser.getEmail());
        newUser.setPassword(plainUser.getPassword());
        newUser.setPhone(plainUser.getPhone());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoles(roles);
        userRepository.save(newUser);

        return UserConverter.UserToPlain(newUser);
    }

    @Override
    @Transactional
    public PlainUser update(PlainUser updatedPlaneUser, Long id) {
        Optional<User> userForUpdateOpt = userRepository.findById(id);
        if (!userForUpdateOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        User userForUpdate = userForUpdateOpt.get();
        userForUpdate.setPassword(updatedPlaneUser.getPassword());
        userForUpdate.setPhone(updatedPlaneUser.getPhone());

        userRepository.save(userForUpdate);

        return UserConverter.UserToPlain(userForUpdate);
    }

    @Override
    @Transactional
    public PlainUser get(Long id) {
        Optional<User> foundUserOpt = userRepository.findById(id);
        if (!foundUserOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        User result = foundUserOpt.get();
        return UserConverter.UserToPlain(result);
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
        Optional<User> foundUserOpt = userRepository.findByEmailAndPassword(email, password);
        if (!foundUserOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        return UserConverter.UserToPlain(foundUserOpt.get());
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<User> userForDeleteOpt = userRepository.findById(id);
        if (!userForDeleteOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        String userEmail = userForDeleteOpt.get().getEmail();
        userRepository.delete(userForDeleteOpt.get());
        return "User with email " + userEmail + " was completely removed";
    }

}

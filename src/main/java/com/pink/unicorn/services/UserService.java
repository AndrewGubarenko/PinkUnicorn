package com.pink.unicorn.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.unicorn.domain.Role;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.repositories.UserRepository;
import com.pink.unicorn.utils.Converter;
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
    private final Converter converter;

    @Autowired
    public UserService(UserRepository userRepository,
                       ObjectMapper objectMapper,
                       Converter converter) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.converter = converter;
    }

    @Override
    @Transactional
    public String create(String user) throws IOException, EmptyDataException {
        JsonNode rootNode = objectMapper.readTree(user);
        if (rootNode.path("email").asText().equals("")) {
            throw new EmptyDataException("Empty EMAIL field");
        } else if (rootNode.path("password").asText().equals("")) {
            throw new EmptyDataException("Empty PASSWORD field");
        }
        User newUser = new User();
        newUser.setEmail(rootNode.path("email").asText().toLowerCase());
        newUser.setPassword(rootNode.path("password").asText());
        newUser.setPhone(rootNode.path("phone").asText());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoles(roles);
        userRepository.save(newUser);

        return converter.ObjectToJSON(newUser);
    }

    @Override
    @Transactional
    public String update(String updatedUser, Long id) throws IOException {
        Optional<User> userForUpdateOpt = userRepository.findById(id);
        if (!userForUpdateOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        JsonNode rootNode = objectMapper.readTree(updatedUser);
        User userForUpdate = userForUpdateOpt.get();
        userForUpdate.setPassword(rootNode.path("password").asText());
        userForUpdate.setPhone(rootNode.path("phone").asText());

        userRepository.save(userForUpdate);

        return converter.ObjectToJSON(userForUpdate);
    }

    @Override
    @Transactional
    public String get(Long id) {
        Optional<User> foundUserOpt = userRepository.findById(id);
        if (!foundUserOpt.isPresent()) {
            throw new NoSuchElementException();
        }
        User result = foundUserOpt.get();
        return converter.ObjectToJSON(result);
    }

    @Override
    @Transactional(readOnly = true)
    public String findByEmailAndPassword(String authData) throws IOException, EmptyDataException{
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
        return converter.ObjectToJSON(foundUserOpt.get());
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

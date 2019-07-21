package com.pink.unicorn.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pink.unicorn.domain.Role;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.repositories.UserRepository;
import com.pink.unicorn.utils.Converter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Andrii Hubarenko
 * <p>This class implements an interfase IUserService</p>
 *
 */
@Service
public class UserService implements IUserService{

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getSimpleName());

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
    public String create(String user) {
        JsonNode rootNode;
        try {
            rootNode = objectMapper.readTree(user);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
            return "IOException of JSON";
        }
        User newUser = new User();

        newUser.setEmail(rootNode.path("email").asText());
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
    public String update(String updatedUser, Long id) {
        Optional<User> userForUpdateOpt = userRepository.findById(id);
        if (userForUpdateOpt.isPresent()) {
            JsonNode rootNode;
            try {
                rootNode = objectMapper.readTree(updatedUser);
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
                return "IOException of JSON";
            }
            User userForUpdate = userForUpdateOpt.get();
            userForUpdate.setPassword(rootNode.path("password").asText());
            userForUpdate.setPhone(rootNode.path("phone").asText());

            userRepository.save(userForUpdate);

            return converter.ObjectToJSON(userForUpdate);
        }
        return "Something wrong! Unable to update user's data";
    }

    @Override
    @Transactional
    @JsonIgnoreProperties(ignoreUnknown = true)
    public String get(Long id) {
        Optional<User> foundUserOpt = userRepository.findById(id);
        if(foundUserOpt.isPresent()) {
            User result = foundUserOpt.get();
            return converter.ObjectToJSON(result);
        }
        return "Something wrong! Unable to find such user";
    }

    @Override
    @Transactional
    public String delete(Long id) {
        String message;
        Optional<User> userForDeleteOpt = userRepository.findById(id);
        if(userForDeleteOpt.isPresent()) {
            String userEmail = userForDeleteOpt.get().getEmail();
            userRepository.delete(userForDeleteOpt.get());
            message = "User with email " + userEmail + " was completely removed";
        } else {
            message = "Something wrong. User deleting is unable";
        }
        return message;
    }

}

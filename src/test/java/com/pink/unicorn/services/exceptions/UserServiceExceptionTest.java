package com.pink.unicorn.services.exceptions;

import com.pink.unicorn.domain.Role;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.exceptions.EmptyDataException;
import com.pink.unicorn.services.interfaces.IUserService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test_application_context.xml")
public class UserServiceExceptionTest {

    private static final Logger LOGGER = Logger.getLogger(UserServiceExceptionTest.class.getSimpleName());

    @Autowired
    private IUserService userService;
    private static User user;

    @BeforeAll
    public static void init() {
        LOGGER.info("@BeforeAll: (outer); init()");

        user = new User();
        user.setEmail("qwerty@qwe.qw");
        user.setPassword("qwerty123");
        user.setPhone("0000000000");
        user.setRoles(new HashSet<>(Arrays.asList(Role.USER)));
        user.setWishList(new ArrayList<>());
        user.setOrders(new ArrayList<>());
    }

    @BeforeEach
    public void setUp() {
        LOGGER.info("@BeforeEach: (outer); setUp()");
    }

    @AfterAll
    public static void tearDown() {
        LOGGER.info("@AfterAll: (outer); tearDown()");
    }

    @Test
    public void emptyDataTest() {
        user.setEmail(null);
        assertThrows(EmptyDataException.class, () -> userService.create(user));
        user.setEmail("");
        assertThrows(EmptyDataException.class, () -> userService.create(user));
        user.setEmail("qwerty@qwe.qw");
        user.setPassword("");
        assertThrows(EmptyDataException.class, () -> userService.create(user));
        user.setPassword(null);
        assertThrows(EmptyDataException.class, () -> userService.create(user));
        user.setPassword("qwerty123");
    }

    @Test
    public void DataIntegrityViolationExceptionTest() {
        userService.create(user);
        assertThrows(DataIntegrityViolationException.class, () -> userService.create(user));
    }

    @Test
    public void NoSuchElementExceptionTest() {
        assertThrows(NoSuchElementException.class, () -> userService.get(15l));
    }
}

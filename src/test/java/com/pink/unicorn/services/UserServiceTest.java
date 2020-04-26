package com.pink.unicorn.services;

import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.Role;
import com.pink.unicorn.domain.User;
import com.pink.unicorn.services.interfaces.IUserService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test_application_context.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private static final Logger LOGGER = Logger.getLogger(UserServiceTest.class.getSimpleName());

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
    @Order(1)
    public void createUserTest() {
        LOGGER.info("@Test: (outer); createUserTest()");

        PlainUser actual = userService.create(user);

        assertEquals(1l, actual.getId());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals("", actual.getPassword());
        assertEquals(user.getPhone(), actual.getPhone());
        assertEquals(user.getRoles(), actual.getRoles());
        assertEquals(user.getWishList(), actual.getWishList());
        assertEquals(user.getOrders(), actual.getOrders());

    }

    @Test
    @Order(2)
    public void getUserTest() {
        LOGGER.info("@Test: (outer); getUserTest()");

        PlainUser actual = userService.get(1l);

        assertEquals(1l, actual.getId());
        assertEquals(user.getEmail(), actual.getEmail());
        assertEquals("", actual.getPassword());
        assertEquals(user.getPhone(), actual.getPhone());
        assertEquals(user.getRoles(), actual.getRoles());
        assertEquals(user.getWishList(), actual.getWishList());
        assertEquals(user.getOrders(), actual.getOrders());

    }

    @Test
    @Order(3)
    public void updateUserTest() {
        LOGGER.info("@Test: (outer); updateUserTest()");

        user.setPhone("1111111111");
        user.setPassword("123");
        PlainUser actual = userService.update(user, 1l);

        assertEquals(1l, actual.getId());
        assertEquals("", actual.getPassword());
        assertEquals(user.getPhone(), actual.getPhone());

    }

    @Test
    @Order(4)
    public void deleteUserTest() {
        LOGGER.info("@Test: (outer); updateUserTest()");

        String actual = userService.delete(1l);
        String expected = "User with email qwerty@qwe.qw was completely removed";

        assertEquals(expected, actual);
    }


}

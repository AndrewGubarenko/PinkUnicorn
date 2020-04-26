package com.pink.unicorn.utils;

import com.pink.unicorn.domain.PlainObjects.PlainUser;
import com.pink.unicorn.domain.Role;
import com.pink.unicorn.domain.User;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test_application_context.xml")
public class UserUtilsTest {

    private static final Logger LOGGER = Logger.getLogger(UserUtilsTest.class.getSimpleName());

    @Autowired
    private UserConverter userConverter;
    private User user;

    @BeforeAll
    public static void init() {
        LOGGER.info("@BeforeAll: (outer); init()");
    }

    @AfterAll
    public static void tearDown() {
        LOGGER.info("@AfterAll: (outer); tearDown()");
    }

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1l);
        user.setEmail("qwerty@qwe.qw");
        user.setPassword("qwerty123");
        user.setPhone("0000000000");
        user.setRoles(new HashSet<>(Arrays.asList(Role.USER)));
        user.setWishList(new ArrayList<>());
        user.setOrders(new ArrayList<>());
    }

    @Test
    public void userToPlaneTest() {
        PlainUser actual = userConverter.UserToPlain(user);
        PlainUser expected = new PlainUser();
        expected.setId(1l);
        expected.setEmail("qwerty@qwe.qw");
        expected.setPassword("");
        expected.setPhone("0000000000");
        expected.setRoles(new HashSet<>(Arrays.asList(Role.USER)));
        expected.setWishList(new ArrayList<>());
        expected.setOrders(new ArrayList<>());

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getRoles(), actual.getRoles());
        assertEquals(expected.getWishList(), actual.getWishList());
        assertEquals(expected.getOrders(), actual.getOrders());
    }

}

package com.pink.unicorn.controllers;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test_application_context.xml")
@WebAppConfiguration(value = "/WEB-INF/web.xml")
@RunWith(JUnitPlatform.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    private static final Logger LOGGER = Logger.getLogger(UserControllerTest.class.getSimpleName());

    @Autowired
    private UserController userController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        LOGGER.info("@BeforeEach: (outer); setUp()");
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }

    @Test
    @Order(1)
    public void test() {
        LOGGER.info("@Test: (outer); test()");
        assertNotNull(userController);
        assertNotNull(mockMvc);
    }

    @Test
    @Order(2)
    public void createUserTest() throws Exception{
        LOGGER.info("@Test: (outer); createUserTest()");
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0000000000\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":1," +
                                                            "\"email\":\"123\"," +
                                                            "\"password\":\"\"," +
                                                            "\"phone\":\"0000000000\"," +
                                                            "\"roles\":[\"USER\"]," +
                                                            "\"wishList\":[]," +
                                                            "\"orders\":[]}"));
    }

    @Test
    @Order(3)
    public void getUserTest() throws Exception {
        LOGGER.info("@Test: (outer); getUserTest()");
        mockMvc.perform(get("/users/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1," +
                                                            "\"email\":\"123\"," +
                                                            "\"password\":\"\"," +
                                                            "\"phone\":\"0000000000\"," +
                                                            "\"roles\":[\"USER\"]," +
                                                            "\"wishList\":[]," +
                                                            "\"orders\":[]}"));
    }

    @Test
    @Order(4)
    public void updateUserTest() throws Exception {
        LOGGER.info("@Test: (outer); updateUserTest()");
        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"1111111111\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1," +
                                                            "\"email\":\"123\"," +
                                                            "\"password\":\"\"," +
                                                            "\"phone\":\"1111111111\"," +
                                                            "\"roles\":[\"USER\"]," +
                                                            "\"wishList\":[]," +
                                                            "\"orders\":[]}"));
    }

    @Test
    @Order(5)
    public void deleteUserTest() throws Exception {
        LOGGER.info("@Test: (outer); deleteUserTest()");
        mockMvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("User with email 123 was completely removed"));
    }
}

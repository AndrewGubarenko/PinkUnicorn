package com.pink.unicorn.controllers.exceptions;

import com.pink.unicorn.controllers.UserController;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test_application_context.xml")
@WebAppConfiguration(value = "/WEB-INF/web.xml")
@RunWith(JUnitPlatform.class)
public class UserControllerExceptionTest {

    private static final Logger LOGGER = Logger.getLogger(UserControllerExceptionTest.class.getSimpleName());

    @Autowired
    private UserController userController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        LOGGER.info("@BeforeEach: setUp()");
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }

    @Test
    public void test() {
        LOGGER.info("@Test: test()");
        assertNotNull(userController);
        assertNotNull(mockMvc);
    }

    @Test
    public void onConflictingUserEmailTest() throws Exception{
        LOGGER.info("@Test: onConflictingUserEmailTest()");
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0000000000\"\n" +
                        "}"));
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0000000000\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("DataIntegrityViolationException: User with such email already registered."));
    }

    @Test
    public void onEmptyDataExceptionTest() throws Exception {
        LOGGER.info("@Test: onEmptyDataExceptionTest()");
        mockMvc.perform(post("/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"email\":\"\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0000000000\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Empty EMAIL field"));
    }

    @Test
    public void onMissingUserTest() throws Exception {
        LOGGER.info("@Test: onMissingUserTest()");
        mockMvc.perform(get("/users/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No user with id 3 exists!"));
    }

}

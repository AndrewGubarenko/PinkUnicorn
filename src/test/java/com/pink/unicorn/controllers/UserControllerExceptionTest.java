package com.pink.unicorn.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test_application_context.xml")
@WebAppConfiguration
@Transactional
public class UserControllerExceptionTest {

    @Autowired
    private UserController userController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }

    @Test
    public void test() {
        assertNotNull(userController);
        assertNotNull(mockMvc);
    }

    //TODO: repair this test
    /*@Test
    public void onConflictingUserEmailTest() throws Exception{
        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0504121271\"\n" +
                        "}"))
                .andDo(print());
        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0504121271\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("DataIntegrityViolationException: User with such email already registered."));
    }*/

    @Test
    public void onEmptyDataExceptionTest() throws Exception {
        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "\t\"email\":\"\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0504121271\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("EmptyDataException: One or more data`s fields are empty."));
    }

    @Test
    public void onMissingUserTest() throws Exception {
        mockMvc.perform(get("/user/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("NoSuchElementException: No such user was found"));
    }

}

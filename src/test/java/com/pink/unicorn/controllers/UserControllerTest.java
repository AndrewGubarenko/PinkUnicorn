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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:test_application_context.xml")
@WebAppConfiguration
@Transactional
public class UserControllerTest {

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

    @Test
    public void registrationTest() throws Exception {
        mockMvc.perform(post("/user/registration")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content("{\n" +
                            "\t\"email\":\"123\",\n" +
                            "\t\"password\":\"123\",\n" +
                            "\t\"phone\":\"0504121271\"\n" +
                            "}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"roles\":[\"USER\"]," +
                                                            "\"id\":1," +
                                                            "\"email\":\"123\"," +
                                                            "\"password\":\"123\"," +
                                                            "\"phone\":\"0504121271\"," +
                                                            "\"wishlist\":null}"));
    }

    @Test
    public void getUserTest() throws Exception {
        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0504121271\"\n" +
                        "}"));
        mockMvc.perform(get("/user/3")
        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"roles\":[\"USER\"]," +
                                                            "\"id\":3," +
                                                            "\"email\":\"123\"," +
                                                            "\"password\":\"123\"," +
                                                            "\"phone\":\"0504121271\"," +
                                                            "\"wishlist\":null}"));
    }

    @Test
    public void updateUserTest() throws Exception {
        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0504121271\"\n" +
                        "}"));
        mockMvc.perform(put("/user/2/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0990421962\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"roles\":[\"USER\"]," +
                                                            "\"id\":2," +
                                                            "\"email\":\"123\"," +
                                                            "\"password\":\"123\"," +
                                                            "\"phone\":\"0990421962\"," +
                                                            "\"wishlist\":null}"));
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\n" +
                        "\t\"email\":\"123\",\n" +
                        "\t\"password\":\"123\",\n" +
                        "\t\"phone\":\"0504121271\"\n" +
                        "}"));
        mockMvc.perform(delete("/user/4/delete")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("User with email 123 was completely removed"));
    }

}

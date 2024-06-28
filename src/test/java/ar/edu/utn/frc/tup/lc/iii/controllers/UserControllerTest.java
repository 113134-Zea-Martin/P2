package ar.edu.utn.frc.tup.lc.iii.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUsersTest() throws Exception {
        this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].length()").value(4));
    }

    @Test
    void getUsersByIdTest() throws Exception {
        this.mockMvc.perform(get("/users/100")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.name").value("Hernan"))
                .andExpect(jsonPath("$.email").value("hjm@hjm.com"))
                .andExpect(jsonPath("$.last_name").value("Morais"));
    }

    @Test
    void checkAvailableEmailTest() throws Exception {
        this.mockMvc.perform(get("/users/available-email?email=hjm@hjm.com")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
        this.mockMvc.perform(get("/users/available-email?email=hjm1@hjm.com")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    void signInTest() throws Exception {
        String content = "{\"email\":\"hjm@hjm.com\",\"password\":\"password\"}";
        this.mockMvc.perform(post("/users/sign-in").contentType(APPLICATION_JSON_UTF8).content(content))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.name").value("Hernan"))
                .andExpect(jsonPath("$.email").value("hjm@hjm.com"))
                .andExpect(jsonPath("$.last_name").value("Morais"));
    }

    @Test
    void signUpTes() throws Exception {
        String content = "{\"email\":\"sol@sol.com\",\"password\":\"password\",\"name\":\"Sol\",\"last_name\":\"Morais\"}";
        this.mockMvc.perform(post("/users/sign-up").contentType(APPLICATION_JSON_UTF8).content(content))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sol"))
                .andExpect(jsonPath("$.email").value("sol@sol.com"))
                .andExpect(jsonPath("$.last_name").value("Morais"));
    }
}
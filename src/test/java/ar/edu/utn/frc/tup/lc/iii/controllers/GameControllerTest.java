package ar.edu.utn.frc.tup.lc.iii.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGamesTest() throws Exception {
        this.mockMvc.perform(get("/games")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(32));

        this.mockMvc.perform(get("/games?fase_game=GROUP_STAGE")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(24));

        this.mockMvc.perform(get("/games?fase_game=QUARTER_FINALS")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));

        this.mockMvc.perform(get("/games?fase_game=SEMI_FINALS")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        this.mockMvc.perform(get("/games?fase_game=FINAL")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        this.mockMvc.perform(get("/games?fase_game=THIRD_PLACE")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getGameTest() throws Exception {
        this.mockMvc.perform(get("/games/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.local.id").value(1))
                .andExpect(jsonPath("$.local.name").value("Argentina"))
                .andExpect(jsonPath("$.visitor.id").value(5))
                .andExpect(jsonPath("$.visitor.name").value("Canada"))
                .andExpect(jsonPath("$.stadium").value("Mercedes Benz Stadium - Atlanta, GA"))
                .andExpect(jsonPath("$.date").value("20-06-2024 20:00:00"))
                .andExpect(jsonPath("$.fase_game").value("GROUP_STAGE"));
    }

    @Test
    void getGameResultTest() throws Exception {
        this.mockMvc.perform(get("/games/1/result")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.game.local.id").value(1))
                .andExpect(jsonPath("$.game.local.name").value("Argentina"))
                .andExpect(jsonPath("$.game.visitor.id").value(5))
                .andExpect(jsonPath("$.game.visitor.name").value("Canada"))
                .andExpect(jsonPath("$.game.stadium").value("Mercedes Benz Stadium - Atlanta, GA"))
                .andExpect(jsonPath("$.game.date").value("20-06-2024 20:00:00"))
                .andExpect(jsonPath("$.game.fase_game").value("GROUP_STAGE"))
                .andExpect(jsonPath("$.result").value("LOCAL_WIN"))
                .andExpect(jsonPath("$.local_goals").value(2))
                .andExpect(jsonPath("$.visitor_goals").value(0));
    }

    @Test
    void putGameResultTest() throws Exception {
        this.mockMvc.perform(put("/games/1/result?local_goals=3&visitor_goals=0"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.game.local.id").value(1))
                .andExpect(jsonPath("$.game.local.name").value("Argentina"))
                .andExpect(jsonPath("$.game.visitor.id").value(5))
                .andExpect(jsonPath("$.game.visitor.name").value("Canada"))
                .andExpect(jsonPath("$.game.stadium").value("Mercedes Benz Stadium - Atlanta, GA"))
                .andExpect(jsonPath("$.game.date").value("20-06-2024 20:00:00"))
                .andExpect(jsonPath("$.game.fase_game").value("GROUP_STAGE"))
                .andExpect(jsonPath("$.result").value("LOCAL_WIN"))
                .andExpect(jsonPath("$.local_goals").value(3))
                .andExpect(jsonPath("$.visitor_goals").value(0));;
    }

    @Test
    void putPredictionTest() throws Exception {
        String content = "{\"local_goals\":3,\"visitor_goals\":0}";
        this.mockMvc.perform(put("/games/1/predictions?user_id=100").contentType(APPLICATION_JSON_UTF8).content(content))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").value("400 The game has already started"))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.status").value(400));

        this.mockMvc.perform(put("/games/15/predictions?user_id=200").contentType(APPLICATION_JSON_UTF8).content(content))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getPrediction() throws Exception {
        this.mockMvc.perform(get("/games/predictions?user_id=100&include_unpredicted=true"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(24));

        this.mockMvc.perform(get("/games/predictions?user_id=100&include_unpredicted=false"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetPrediction() throws Exception {
        this.mockMvc.perform(get("/games/1/predictions?user_id=100"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }
}
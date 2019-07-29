package com.stackroute.SpringBootTask.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.SpringBootTask.domain.Music;
import com.stackroute.SpringBootTask.exceptions.MusicAlreadyExistsException;
import com.stackroute.SpringBootTask.service.MusicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MusicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Music music;
    @MockBean
    private MusicService musicService;
    @InjectMocks
    private MusicController musicController;

    private List<Music> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(musicController).build();
        music = new Music();

        list = new ArrayList();
music.setName("Vasavi");
music.setComments("Good");
music.setRating(10);
music.setId(1);
        list.add(music);
    }


    @Test
    public void saveUser() throws Exception {
        when(musicService.saveMusic(any())).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void saveUserFailure() throws Exception {
        when(musicService.saveMusic(any())).thenThrow(MusicAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }


    private String asJsonString(final Object object)
    {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
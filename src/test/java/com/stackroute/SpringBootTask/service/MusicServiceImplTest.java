package com.stackroute.SpringBootTask.service;

import com.stackroute.SpringBootTask.domain.Music;
import com.stackroute.SpringBootTask.exceptions.MusicAlreadyExistsException;
import com.stackroute.SpringBootTask.repository.MusicRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MusicServiceImplTest
{

    Music music;

    //Create a mock for UserRepository
    @Mock
    MusicRepository musicRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MusicServiceImpl musicService;
    List<Music> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        music = new Music();
        music.setName("Vasavi");
        music.setId(1);
        music.setRating(10);
        music.setComments("Good");
        list = new ArrayList<>();
        list.add(music);

    }

    @Test
    public void saveUserTestSuccess() throws MusicAlreadyExistsException {

        when(musicRepository.save((Music) any())).thenReturn(music);
        Music savedUser = musicService.saveMusic(music);
        Assert.assertEquals(music,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(music);

    }


    @Test
    public void getAllUser(){

        musicRepository.save(music);
        //stubbing the mock to return specific data
        when(musicRepository.findAll()).thenReturn(list);
        List<Music> userlist = musicService.getMusic();
        Assert.assertEquals(list,userlist);
    }



}
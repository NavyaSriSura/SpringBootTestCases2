package com.stackroute.SpringBootTask.repository;

import com.stackroute.SpringBootTask.domain.Music;
import com.stackroute.SpringBootTask.service.MusicService;
import com.stackroute.SpringBootTask.service.MusicServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@WebMvcTest
public class MusicRepositoryTest {
Music music;
    @Mock
    MusicRepository musicRepository;

    @InjectMocks
    MusicServiceImpl musicService;
    List<Music> list= null;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        music.setId(1);
        music.setRating(10);
        music.setComments("Awesome");
        music.setName("Vasavi");
    }

    @Test
    public void findAllActiveUsers()
    {
       Music u = new Music(10,"Vasavi",10,"super");
        Music u1 = new Music(9,"Jenny",7,"average");
        musicRepository.save(u);
       musicRepository.save(u1);

        List<Music> list = musicRepository.findAll();
        Assert.assertEquals("Johny",list.get(0).getName());

    }



}
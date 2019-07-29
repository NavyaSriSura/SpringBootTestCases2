package com.stackroute.SpringBootTask.service;

import com.stackroute.SpringBootTask.domain.Music;
import com.stackroute.SpringBootTask.exceptions.MusicAlreadyExistsException;
import com.stackroute.SpringBootTask.exceptions.TrackNotFoundException;

import java.util.List;

public interface MusicService {
    public Music saveMusic(Music music) throws MusicAlreadyExistsException;

    public List<Music> getMusic();

    public Music getById(int id) throws TrackNotFoundException;

    public void deleteById(int id);

    public boolean updateById(Music music, int id);

    public  List<Music>   getByName(String name);
}

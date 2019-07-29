package com.stackroute.SpringBootTask.service;

import com.stackroute.SpringBootTask.domain.Music;
import com.stackroute.SpringBootTask.exceptions.MusicAlreadyExistsException;
import com.stackroute.SpringBootTask.exceptions.TrackNotFoundException;
import com.stackroute.SpringBootTask.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService , ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {


    @Value("${music.1.name:default}")
    String name1;
    @Value("${music.1.rating:default}")
    int rating1;
    @Value("${music.1.comments:default}")
    String comments1;
    @Value("${music.2.name:default}")
    String name2;
    @Value("${music.2.rating:default}")
    int rating2;
    @Value("${music.2.comments:default}")
    String comments2;
    private MusicRepository musicRepository;

    @Autowired
    public MusicRepository getMusicRepository() {
        return musicRepository;
    }

    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public MusicServiceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public Music saveMusic(Music music) throws MusicAlreadyExistsException {


        Music savedMusic = musicRepository.save(music);

        return savedMusic;
    }

    @Override
    public List<Music> getMusic() {
        return  musicRepository.findAll();
    }

    @Override
    public Music getById(int id) throws TrackNotFoundException {

        Optional<Music> user_id = musicRepository.findById(id);
        return user_id.get();
    }


    @Override
    public void deleteById(int id) {
        musicRepository.deleteById(id);
    }

    @Override
    public boolean updateById(Music music, int id) {
        Optional<Music> userOptional = musicRepository.findById(id);

        if (!userOptional.isPresent())
            return false;


        music.setId(id);

        musicRepository.save(music);
        return true;
    }

    @Override
    public List<Music> getByName(String name) {
        List<Music> user_id = musicRepository.findTitleByName(name);

        return user_id;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        musicRepository.save(new Music(1, name1, rating1, comments1));
        musicRepository.save(new Music(2, name2, rating2, comments2));
    }

    @Override
    public void run(String... args) throws Exception {

    }

}

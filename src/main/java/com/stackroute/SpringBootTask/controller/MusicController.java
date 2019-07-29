package com.stackroute.SpringBootTask.controller;

import com.stackroute.SpringBootTask.domain.Music;
import com.stackroute.SpringBootTask.exceptions.MusicAlreadyExistsException;
import com.stackroute.SpringBootTask.exceptions.TrackNotFoundException;
import com.stackroute.SpringBootTask.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1")
public class MusicController {
    public MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @PostMapping("/music")
    public ResponseEntity<?> saveMusic(@RequestBody Music music) throws MusicAlreadyExistsException {
        ResponseEntity responseEntity;
        Music savedMusic = null;

        try {
            musicService.saveMusic(music);
            responseEntity = new ResponseEntity("Succesfully created", HttpStatus.CREATED);
            savedMusic = musicService.saveMusic(music);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

            System.out.println(e);
        }
        return responseEntity;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        //return new ResponseEntity<List<Music>>(musicService.getMusic(),HttpStatus.OK);
        List<Music> musicList = musicService.getMusic();
        return new ResponseEntity<List<Music>>(musicList, HttpStatus.CREATED);
    }

    @GetMapping("/music/{id}")

    public ResponseEntity<?> getById(@PathVariable int id) throws TrackNotFoundException {

        Music music = null;

        /*try {
            music = musicService.getById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
*/
        return new ResponseEntity<Music>(music, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMusic(@PathVariable int id) {
        musicService.deleteById(id);
        return "Data deleted";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Music> updateMusix(@RequestBody Music music, @PathVariable int id) {

        if (musicService.updateById(music, id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<List<Music>> getByname(@PathVariable String name) {
        List<Music> music = musicService.getByName(name);
        return new ResponseEntity<List<Music>>(music, HttpStatus.OK);
    }


}

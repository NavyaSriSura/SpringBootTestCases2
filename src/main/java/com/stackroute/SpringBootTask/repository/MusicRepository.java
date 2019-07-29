package com.stackroute.SpringBootTask.repository;

import com.stackroute.SpringBootTask.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Integer> {
    @Query(
            value = "SELECT * FROM MUSIC",
            nativeQuery = true)
    Collection<Music> findAllActiveUsers();

    @Query(value = "SELECT * FROM Music  where name = ?1",
            nativeQuery = true )
    List<Music> findTitleByName(String name);
}

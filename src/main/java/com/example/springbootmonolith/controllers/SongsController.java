package com.example.springbootmonolith.controllers;

import com.example.springbootmonolith.models.Song;
import com.example.springbootmonolith.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SongsController {

    @Autowired
    SongRepository songRepository;

    @GetMapping("/songs")
    public Iterable<Song> findAllSongs() {
        return songRepository.findAll();
    }

    @GetMapping("/songs/{songId}")
    public Optional<Song> findById(@PathVariable Long songId){
        return songRepository.findById(songId);
    }

    @PostMapping("/songs")
    public Song createUser(@RequestBody Song song){
        return songRepository.save(song);
    }

    @DeleteMapping("/songs/{songId}")
    public HttpStatus deleteUser(@PathVariable Long songId){
        songRepository.deleteById(songId);
        return HttpStatus.OK;
    }

    @PutMapping("/songs/{songId}")
    public Song updateUser(@PathVariable Long songId, @RequestBody Song songRequest){
        Song songFromDb = songRepository.findById(songId).get();

        songFromDb.setTitle(songRequest.getTitle());
        songFromDb.setLength(songRequest.getLength());
        songFromDb.setArtist(songRequest.getArtist());

        return songRepository.save(songFromDb);
    }
}
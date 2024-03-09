package com.Kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Kodnest.tunehub.entity.Song;
import com.Kodnest.tunehub.repository.SongRepository;
import com.Kodnest.tunehub.service.SongService;
@Service
public class SongServiceImpl implements SongService {
	@Autowired	
	SongRepository songRepository;

	@Override
	public String addSong(Song song) {
		songRepository.save(song);
		return "song added successfully";

	}

	public boolean songExists(String name) {
		Song song=songRepository.findByName(name);
		if(song==null){
			return false;
		}else {
			return true;
		}

	}

	public List<Song> fetchAllSongs() {
		List<Song> songs = songRepository.findAll();
		return songs;
	}

	public void updateSong(Song s) {
		songRepository.save(s);

	}


}


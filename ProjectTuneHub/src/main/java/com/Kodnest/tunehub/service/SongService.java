package com.Kodnest.tunehub.service;

import java.util.List;

import com.Kodnest.tunehub.entity.Song;

public interface SongService {

	public String addSong(Song song);
	
	public boolean songExists(String name);
	
	public List<Song> fetchAllSongs();
	
	public void updateSong(Song s);
}

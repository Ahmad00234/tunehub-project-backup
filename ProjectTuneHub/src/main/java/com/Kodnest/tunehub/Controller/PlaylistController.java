package com.Kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Kodnest.tunehub.entity.Playlist;
import com.Kodnest.tunehub.entity.Song;
import com.Kodnest.tunehub.serviceimpl.PlaylistServiceImpl;
import com.Kodnest.tunehub.serviceimpl.SongServiceImpl;

@Controller
public class PlaylistController {
	@Autowired
	SongServiceImpl songServiceImpl;
	
	@Autowired
	PlaylistServiceImpl playlistServiceImpl;
	
	@GetMapping("/createplaylists")
	public String createplaylists(Model model) {
	  List<Song> songList=songServiceImpl.fetchAllSongs();
	  model.addAttribute("songs", songList);
	  return "createplaylists";
	}
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist) {
		
		//updating playlist table
		 playlistServiceImpl.addplaylist(playlist);
		 
		 //updating the song table
		 List<Song> songList = playlist.getSongs();
		 for (Song s : songList) {
			s.getPlaylist().add(playlist);
			songServiceImpl.updateSong(s);
		}
		 
		 return "adminhome";
		
	}

	@GetMapping("/viewplaylists")
	public String viewPlaylists(Model model) {
	    List<Playlist> playlistList = playlistServiceImpl.fetchAllPlaylist();
	    model.addAttribute("playlists", playlistList); // Changed attribute name to "playlist"
	    return "displayplaylist"; // Changed view name to "displayplaylist"
	}
}

package com.Kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Kodnest.tunehub.entity.Song;
import com.Kodnest.tunehub.serviceimpl.SongServiceImpl;

@Controller
public class SongController {
	@Autowired
	SongServiceImpl songserviceimpl;
	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song) {
		
		boolean songStatus=songserviceimpl.songExists(song.getName());
		if(songStatus==false) {	
			songserviceimpl.addSong(song);
			System.out.println("song added Successfully");
		}else {
			System.out.println("song already exists");
		}
		return "adminhome";
	}
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
	  List<Song> songList=songserviceimpl.fetchAllSongs();
	  model.addAttribute("songs", songList);
	  return "displaysongs";
	}
	@GetMapping("/playsongs")
	public String laysongs(Model model) {
		boolean premium=false;
		if(premium) {
	  List<Song> songList=songserviceimpl.fetchAllSongs();
	  model.addAttribute("songs", songList);
	  return "displaysongs";
	}else {
		return"subscriptionfrom";
		
	}
}
}
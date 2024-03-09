package com.Kodnest.tunehub.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Kodnest.tunehub.entity.Song;
import com.Kodnest.tunehub.entity.User;
import com.Kodnest.tunehub.serviceimpl.SongServiceImpl;
import com.Kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	SongServiceImpl songserviceimpl;
	@PostMapping("/register")
	public String addUsers(@ModelAttribute User user) {
		
		//email taken from registration from
		String email=user.getEmail();
		
		//checking if email as entered in registration form is present in DB or not.
		boolean status=userServiceImpl.emailExists(email);
		if(status==false) {	
			userServiceImpl.addUser(user);
			System.out.println("User added");
		}else {
			System.out.println("User already exists");
		}
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password,HttpSession session,Model model) {
		
		if(userServiceImpl.validateUser(email,password)==true) {
			String role=userServiceImpl.getRole(email);
			
			session.setAttribute("email",email);
			if(role.equals("admin")) {
				return "adminhome";
			}else {
				User user = userServiceImpl.getUser(email);
				boolean userstatus =user.isIspremium();
				List<Song> fetchAllSongs = songserviceimpl.fetchAllSongs();
				model.addAttribute("songs",fetchAllSongs);
				
				model.addAttribute("ispremium",userstatus);
				return "customerhome";
			}
		}else {
			return "login";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "login";
	}
	@GetMapping("/exploresongs")
	public String exploresongs(String email) {
		return email;
	}
}


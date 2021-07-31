package com.mp4.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mp4.music.service.MusicService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/music")
public class MusicController {

	@Autowired
	@Qualifier("msi")
	private MusicService service;
	
	private String MODULE = "music";
	
	@GetMapping("home")
	public String list(Model model) throws Exception {
		
		log.info(service.list());
		
		model.addAttribute("list", service.list());
		
		return MODULE + "/home";
		
	}
	
}

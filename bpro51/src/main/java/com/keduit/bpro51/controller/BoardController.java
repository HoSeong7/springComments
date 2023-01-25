package com.keduit.bpro51.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.keduit.bpro51.dto.PageRequestDTO;
import com.keduit.bpro51.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list......................" + pageRequestDTO);
		model.addAttribute("result", service.getList(pageRequestDTO));
	}
}

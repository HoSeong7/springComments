package com.keduit.bpro51.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.bpro51.dto.BoardDTO;
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
	
	@GetMapping({"/register"}) // 입력받는 register.html로 이동
	public void register() {
		log.info("register get.....");
	}
	
	@PostMapping("/register")  // 입력 완료되면 리스트로 다시 이동시키기 (bno를 가지고)
	public String registerPost(BoardDTO dto, RedirectAttributes redirectAttributes) {	
		log.info("register dto...... " + dto);
		Long bno = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", bno);  // bno번 게시글이 등록되었습니다 알람 뜨게끔 하기 위해 만듬
		redirectAttributes.addFlashAttribute("writer", dto.getWriter());
		
		return "redirect:/board/list";
	}
	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
		
		log.info("post modify ..................");
		log.info("dto : " + dto );
		service.modify(dto);
		
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("bno", dto.getBno());
		
		return "redirect:/board/read";
	}
	
	@GetMapping({"/read","/modify"})
	public void read(long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("bno : " + bno);
		
		BoardDTO dto = service.read(bno);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/remove")
	public String remove(Long bno, RedirectAttributes redirectAttributes) {
		
		log.info("remove bno : "+bno);
		
		service.remove(bno);
		redirectAttributes.addAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
}

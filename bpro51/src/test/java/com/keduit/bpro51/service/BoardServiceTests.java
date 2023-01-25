package com.keduit.bpro51.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro51.dto.BoardDTO;
import com.keduit.bpro51.dto.PageRequestDTO;
import com.keduit.bpro51.dto.PageResultDTO;
import com.keduit.bpro51.entity.Webuser;

@SpringBootTest
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
	@Test
	public void testRegister() {
		BoardDTO boardDTO = BoardDTO.builder().title("제목")
											.content("내용")
											.writer("user")
											.build();
		System.out.println(service.register(boardDTO));
	}
	
	@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<BoardDTO, Webuser> resultDTO = service.getList(pageRequestDTO);
		
		System.out.println(pageRequestDTO);
		
		System.out.println("prev : " + resultDTO.isPrev());
		System.out.println("next : " + resultDTO.isNext());
		System.out.println("total : " + resultDTO.getTotalPage());
		System.out.println("---------------------------------------------------");
		
		for(BoardDTO bDto : resultDTO.getDtoList()) {
			System.out.println(bDto);
		}
		System.out.println("---------------------------------------------------");
		resultDTO.getPageList().forEach(i -> {System.out.println(i);});
	}
}

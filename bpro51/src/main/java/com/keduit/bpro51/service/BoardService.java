package com.keduit.bpro51.service;

import com.keduit.bpro51.dto.BoardDTO;
import com.keduit.bpro51.dto.PageRequestDTO;
import com.keduit.bpro51.dto.PageResultDTO;
import com.keduit.bpro51.entity.Webuser;

public interface BoardService {

	Long register(BoardDTO dto);
	
	PageResultDTO<BoardDTO, Webuser> getList(PageRequestDTO requestDTO);
	
	BoardDTO read(Long bno);
	
	void remove(Long bno);  
	
	void modify(BoardDTO dto);
	
	default Webuser dtoToEntity(BoardDTO dto) {
		Webuser entity = Webuser.builder().bno(dto.getBno())
										.title(dto.getTitle())
										.content(dto.getContent())
										.writer(dto.getWriter())
										.build();
		return entity;
	}
	
	default BoardDTO entityToDto(Webuser entity) {
		
		
		BoardDTO dto = BoardDTO.builder().bno(entity.getBno())
										.title(entity.getTitle())
										.content(entity.getContent())
										.writer(entity.getWriter())
										.regDate(entity.getRegDate())
										.updateDate(entity.getUpdateDate())
										.build();
		return dto;
	}
}

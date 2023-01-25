package com.keduit.bpro51.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.keduit.bpro51.dto.BoardDTO;
import com.keduit.bpro51.dto.PageRequestDTO;
import com.keduit.bpro51.dto.PageResultDTO;
import com.keduit.bpro51.entity.Webuser;
import com.keduit.bpro51.repository.WebuserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final WebuserRepository repository;

	@Override
	public Long register(BoardDTO dto) {
		
		log.info("DTO----------------------------");
		log.info(dto);
		
		Webuser entity = dtoToEntity(dto);
		log.info(entity);
		
		repository.save(entity);
		return entity.getBno();
	}

	@Override
	public PageResultDTO<BoardDTO, Webuser> getList(PageRequestDTO requestDTO) {
		
		Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
		Page<Webuser> result = repository.findAll(pageable);
		
		Function<Webuser, BoardDTO> fn = (entity -> entityToDto(entity));
		return new PageResultDTO<>(result, fn);
	}

}

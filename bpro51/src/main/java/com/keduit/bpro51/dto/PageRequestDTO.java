package com.keduit.bpro51.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PageRequestDTO {

	private int page;
	private int size;
	
	public PageRequestDTO() {
		this.page = 1;    // 디폴트 페이지 값
		this.size = 10;   // 디폴트 페이지 사이즈
	}
	
	public Pageable getPageable(Sort sort) {
		return PageRequest.of(page -1, size, sort);     // pageable을 생성해서 리턴함 페이지는 0부터 시작하지만 누르는건 1부터 시작하기 때문에 -1을 함
	}
}

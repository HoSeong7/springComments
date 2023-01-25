package com.keduit.bpro51.RepositoryTests;



import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;

import com.keduit.bpro51.entity.Webuser;
import com.keduit.bpro51.repository.WebuserRepository;

@SpringBootTest
public class WebuserRepositoryTests {

	@Autowired
	WebuserRepository user;
	
	@Test
	public void testclass() {
		System.out.println(user.getClass().getName());
	}
	
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Webuser tbl_board = Webuser.builder().writer("user" + i).title("제목" + i).content("내용입니다 ~~ " + i + "번입니다 ~~").build();
			user.save(tbl_board);
		});
	}
	
	@Test
	public void testSelect() {
		LongStream.range(1, 100).forEach(i-> {
			Optional<Webuser> result = user.findById(i);
			if(result.isPresent()) {
				Webuser tbl_board = result.get();
				System.out.println(tbl_board);
			}
		});
	}
	@Test
	public void testSelect2() {
		Optional<Webuser> result = user.findById(1L);
		System.out.println(result);
	
	
	}
	@Test
	public void testUpdate() {

		Webuser tbl_board = Webuser.builder().bno(1L).title("1번제목수정").content("1번 내용수정~~").writer("유저수정").build();
		System.out.println(user.save(tbl_board));
	}
	
	@Test
	public void testDelete() {
		user.deleteById(100L);
	}
	@Test
	public void testPaging() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Webuser> result = user.findAll(pageable);
		
		System.out.println("total count : " + result.getTotalElements());
		System.out.println("total page : " + result.getTotalPages());
		System.out.println("page number : " + result.getNumber());
		System.out.println("page size : " + result.getSize());
		
		List<Webuser> board = result.getContent();
		board.forEach(b -> {System.out.println(b);});
	}
}

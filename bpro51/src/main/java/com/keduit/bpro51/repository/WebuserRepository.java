package com.keduit.bpro51.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro51.entity.Webuser;

public interface WebuserRepository extends JpaRepository<Webuser, Long>{

	@Query(value = "select * from tbl_board  order by wno", nativeQuery = true)
	List<Webuser> getList();
}

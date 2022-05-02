package com.like.linx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.like.linx.model.Situacao;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, Long> {
	//public List<Situacao> findAllByStatusContainingIgnoreCase(String status);
	
	@Query(value="select * from tb_situacao where status like %:status%", nativeQuery=true)
	public List<Situacao> getStatus(String status);
}
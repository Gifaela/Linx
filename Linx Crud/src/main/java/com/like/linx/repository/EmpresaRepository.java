package com.like.linx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.like.linx.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

//public List <Empresa> findAllBySegmentoContainingIgnoreCase(String segmento);

	@Query(value="select * from tb_empresa where segmento like %:segmento%", nativeQuery=true)
	public List<Empresa> getSegmento(String segmento);
}

//select * from tb_empresa where segmento like "%j%"




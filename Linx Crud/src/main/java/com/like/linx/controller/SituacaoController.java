package com.like.linx.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.linx.model.Situacao;
import com.like.linx.repository.SituacaoRepository;

@RestController
@RequestMapping("/situacao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SituacaoController {

	@Autowired
	public SituacaoRepository situacaoRepository;

	@GetMapping
	public ResponseEntity<List<Situacao>> getAll() {
		return ResponseEntity.ok(situacaoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Situacao> findByIdCategoria(@PathVariable Long id) {
		return situacaoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

//	@GetMapping("/status/{status}")
//	public ResponseEntity<List<Situacao>> findByStatus(@PathVariable String status) {
//		return ResponseEntity.ok(situacaoRepository.findAllByStatusContainingIgnoreCase(status));
//	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Situacao>> findBySituacao(@PathVariable String status) {
		return ResponseEntity.ok(situacaoRepository.getStatus(status));
	}

	@PostMapping
	public ResponseEntity<Situacao> postSituacao(@Valid @RequestBody Situacao situacao) {
		return ResponseEntity.status(HttpStatus.CREATED).body(situacaoRepository.save(situacao));
	}

	@PutMapping
	public ResponseEntity<Situacao> putSituacao(@Valid @RequestBody Situacao situacao) {
		return situacaoRepository.findById(situacao.getId())
				.map(resposta -> ResponseEntity.ok().body(situacaoRepository.save(situacao)))
				.orElse(ResponseEntity.notFound().build());
	}
	@PatchMapping("/updatelink/{id}/{link}")
	public ResponseEntity<Situacao> patchSituacaoLink(@PathVariable Long id, @PathVariable String link) {
		try {
			Situacao situacao = situacaoRepository.findById(id).get();
			situacao.setLink(link);
			return new ResponseEntity<Situacao>(situacaoRepository.save(situacao), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/update/{id}/{link}")
	public ResponseEntity<Situacao> patchSituacao(@PathVariable Long id, @PathVariable String link) {
		try {
			Situacao situacao = situacaoRepository.findById(id).get();
			situacao.setLink(link);
			return new ResponseEntity<Situacao>(situacaoRepository.save(situacao), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSituacao(@PathVariable Long id) {
		return situacaoRepository.findById(id).map(resposta -> {
			situacaoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}

}

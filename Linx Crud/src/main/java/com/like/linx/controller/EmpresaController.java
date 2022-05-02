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

import com.like.linx.model.Empresa;
import com.like.linx.repository.EmpresaRepository;
import com.like.linx.repository.SituacaoRepository;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*", allowedHeaders = "*") // origin aceita requisição - allowedHeaders enviar título(tolk)
public class EmpresaController {

	@Autowired // ingeção de dependencia - string instancia altimaticamente
	public EmpresaRepository empresaRepository;

	@Autowired
	public SituacaoRepository situacaoRepository;

	
	@GetMapping
	public ResponseEntity<List<Empresa>> getAll() {
		return ResponseEntity.ok(empresaRepository.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> getById(@PathVariable Long id) {
		return empresaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

//	@GetMapping("/segmento/{segmento}")
//	public ResponseEntity<List<Empresa>> getBySegmento(@PathVariable String segmento) {
//		return ResponseEntity.ok(empresaRepository.findAllBySegmentoContainingIgnoreCase(segmento));
//	}
	
	@GetMapping("/segmento/{segmento}")
	public ResponseEntity<List<Empresa>> getBySegmento(@PathVariable String segmento) {
		return ResponseEntity.ok(empresaRepository.getSegmento(segmento));
	}


	@PostMapping
	public ResponseEntity<Empresa> postEmpresa(@Valid @RequestBody Empresa empresa) {
		if (situacaoRepository.existsById(empresa.getSituacao().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(empresaRepository.save(empresa));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}


	@PutMapping
	 public ResponseEntity <Empresa> putEmpresa (@Valid @RequestBody Empresa empresa){
	 if (situacaoRepository.existsById(empresa.getSituacao().getId())) {
		 return empresaRepository.findById(empresa.getId())
				 .map(resposta -> ResponseEntity.ok().body(empresaRepository.save(empresa)))
				 .orElse(ResponseEntity.notFound().build());
				 
				 }
	 return ResponseEntity.badRequest().build();
	}
	
	@PatchMapping("/updatesegmento/{id}/{segmento}")
	public ResponseEntity<Empresa> patchEmpresaSegmento(@PathVariable Long id, @PathVariable String segmento) {
		try {
			Empresa empresa = empresaRepository.findById(id).get();
			empresa.setSegmento(segmento);
			return new ResponseEntity<Empresa>(empresaRepository.save(empresa), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTema(@PathVariable Long id) {
		return empresaRepository.findById(id).map(resposta -> {
			empresaRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());

	}
}

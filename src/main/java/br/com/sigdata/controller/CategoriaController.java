package br.com.sigdata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigdata.exception.EntityAlreadyExistsException;
import br.com.sigdata.exception.EntityNotFoundException;
import br.com.sigdata.model.Categoria;
import br.com.sigdata.repository.CategoriaRepository;
import br.com.sigdata.service.CategoriaService;

@RestController
@RequestMapping("/{tenantid}/categorias")
public class CategoriaController {
	
	private CategoriaRepository categoriaRepository;
	private CategoriaService categoriaService;

	public CategoriaController(CategoriaRepository categoriaRepository, CategoriaService categoriaService) {
		this.categoriaRepository = categoriaRepository;
		this.categoriaService = categoriaService;
	}
			
	@GetMapping
	public List<Categoria> listAll() {
		return categoriaRepository.findAll();
	}	

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Optional<Categoria> produto = categoriaRepository.findById(id);

		if (produto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto.get());
	}
	
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Categoria> findByCodigo(@PathVariable String codigo) {
		Optional<Categoria> produto = categoriaRepository.findByCodigo(codigo);

		if (produto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto.get());
	}		

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Categoria categoria) {
		try {
			categoria = this.categoriaService.create(categoria);

			return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
		} catch (EntityAlreadyExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Categoria categoria) {
		try {
			Categoria categoriaFound = this.categoriaService.update(id, categoria);
			
			if (categoriaFound != null) {
				return ResponseEntity.ok(categoriaFound);
			}

			return ResponseEntity.notFound().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> remove(@PathVariable Long id) {
		try {
			this.categoriaService.delete(id);
			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	

}

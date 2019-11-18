package br.com.sigdata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigdata.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
		
	List<Categoria> findAll();
			
	Optional<Categoria> findByCodigo(String codigo);
	

}

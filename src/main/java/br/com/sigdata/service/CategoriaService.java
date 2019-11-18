package br.com.sigdata.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.sigdata.exception.EntityAlreadyExistsException;
import br.com.sigdata.exception.EntityInUseException;
import br.com.sigdata.exception.EntityNotFoundException;
import br.com.sigdata.model.Categoria;
import br.com.sigdata.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public Categoria create(Categoria categoria) {		
		if (!categoria.getCodigo().isEmpty()) {			
			String codigo = categoria.getCodigo();			
			Optional<Categoria> categoriaFound = this.categoriaRepository.findByCodigo(codigo);

			if (!categoriaFound.isEmpty()) {
				throw new EntityAlreadyExistsException(
						String.format("Categoria já cadastrado com este Codigo %d", codigo));
			}
		}
		return this.categoriaRepository.save(categoria);
	}

	public Categoria update(Long id, Categoria categoria) {
		Categoria categoriaFound = this.categoriaRepository.findById(id).orElse(null);

		if (categoriaFound == null) {
			throw new EntityNotFoundException(String.format("Categoria não encontrado com este ID %d", id));
		}

		BeanUtils.copyProperties(categoria, categoriaFound, "id");
		categoriaFound = this.categoriaRepository.save(categoriaFound);
		return categoriaFound;
	}

	public void delete(Long id) {
		try {
			this.categoriaRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("Categoria não encontrado com este ID %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("Categoria de ID %d não pode ser removido, pois está em uso", id));
		}
	}

}

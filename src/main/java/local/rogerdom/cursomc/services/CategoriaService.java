package local.rogerdom.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.rogerdom.cursomc.domain.Categoria;
import local.rogerdom.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> buscar(Integer id) {
		
		Optional<Categoria> categoria = repo.findById(id);
		return categoria;
		
	}
}

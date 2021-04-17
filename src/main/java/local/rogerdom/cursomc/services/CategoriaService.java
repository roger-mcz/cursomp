package local.rogerdom.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import local.rogerdom.cursomc.domain.Categoria;
import local.rogerdom.cursomc.repositories.CategoriaRepository;
import local.rogerdom.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}
}

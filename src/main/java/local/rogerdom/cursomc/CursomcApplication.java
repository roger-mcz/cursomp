package local.rogerdom.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import local.rogerdom.cursomc.domain.Categoria;
import local.rogerdom.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "outros");
	//	Categoria cat2 = new Categoria(null, "ESCRITÓRIO");
		
		categoriaRepository.saveAll(Arrays.asList(cat1));
		
	}
	
		
		
		
}

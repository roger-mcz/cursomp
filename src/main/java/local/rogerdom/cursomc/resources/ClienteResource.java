package local.rogerdom.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import local.rogerdom.cursomc.domain.Cliente;
import local.rogerdom.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")	//(nome do endPoint Rest)
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente cliente = service.find(id);
		
		return ResponseEntity.ok().body(cliente);
				
		/* metodo antigo 
		 * Categoria cat1 = new Categoria(1, "Informatica"); Categoria cat2 = new
		 * Categoria(2, "Escritorio");
		 * 
		 * List <Categoria> lista = new ArrayList<>(); lista.add(cat1); lista.add(cat2);
		 * return lista;
		 */
	}

}

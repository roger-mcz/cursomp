package local.rogerdom.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import local.rogerdom.cursomc.domain.Pedido;
import local.rogerdom.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")	//(nome do endPoint Rest)
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Pedido pedido = service.buscar(id);
		
		return ResponseEntity.ok().body(pedido);
				
		/* metodo antigo 
		 * Categoria cat1 = new Categoria(1, "Informatica"); Categoria cat2 = new
		 * Categoria(2, "Escritorio");
		 * 
		 * List <Categoria> lista = new ArrayList<>(); lista.add(cat1); lista.add(cat2);
		 * return lista;
		 */
	}

}

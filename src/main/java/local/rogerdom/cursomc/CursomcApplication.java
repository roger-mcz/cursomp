package local.rogerdom.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import local.rogerdom.cursomc.domain.Categoria;
import local.rogerdom.cursomc.domain.Cidade;
import local.rogerdom.cursomc.domain.Cliente;
import local.rogerdom.cursomc.domain.Endereco;
import local.rogerdom.cursomc.domain.Estado;
import local.rogerdom.cursomc.domain.ItemPedido;
import local.rogerdom.cursomc.domain.Pagamento;
import local.rogerdom.cursomc.domain.PagamentoComBoleto;
import local.rogerdom.cursomc.domain.PagamentoComCartao;
import local.rogerdom.cursomc.domain.Pedido;
import local.rogerdom.cursomc.domain.Produto;
import local.rogerdom.cursomc.domain.enums.EstadoPagamento;
import local.rogerdom.cursomc.domain.enums.TipoCliente;
import local.rogerdom.cursomc.repositories.CategoriaRepository;
import local.rogerdom.cursomc.repositories.CidadeRepository;
import local.rogerdom.cursomc.repositories.ClienteRepository;
import local.rogerdom.cursomc.repositories.EnderecoRepository;
import local.rogerdom.cursomc.repositories.EstadoRepository;
import local.rogerdom.cursomc.repositories.ItemPedidoRepository;
import local.rogerdom.cursomc.repositories.PagamentoRepository;
import local.rogerdom.cursomc.repositories.PedidoRepository;
import local.rogerdom.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
		
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "INFORMATICA");
		Categoria cat2 = new Categoria(null, "ESCRITÓRIO");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		//banco gravado, comentado p/ evitar novos ids... 
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco end1 = new Endereco(null,"Rua flores","300","ap 303", "jardins","38220834",cli1, cid1);
		Endereco end2 = new Endereco(null,"Avenida Matos","105","sala 800", "centro","38777012",cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		//retirado o atributo pagamento do construtor p/ evitar falha 
		//de dependência no rel. OneToOne
		//depois que instanciar o pedido, o pagamento será instanciado...
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 5);
		//valor do pagamento sendo definido
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null, sdf.parse("20/10/2017 00:00"));
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
	}
	
		
		
		
}

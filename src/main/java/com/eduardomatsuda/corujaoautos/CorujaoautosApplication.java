package com.eduardomatsuda.corujaoautos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eduardomatsuda.corujaoautos.domain.Categoria;
import com.eduardomatsuda.corujaoautos.domain.Cidade;
import com.eduardomatsuda.corujaoautos.domain.Cliente;
import com.eduardomatsuda.corujaoautos.domain.Endereco;
import com.eduardomatsuda.corujaoautos.domain.Estado;
import com.eduardomatsuda.corujaoautos.domain.ItemPedido;
import com.eduardomatsuda.corujaoautos.domain.Pagamento;
import com.eduardomatsuda.corujaoautos.domain.PagamentoComBoleto;
import com.eduardomatsuda.corujaoautos.domain.PagamentoComCartao;
import com.eduardomatsuda.corujaoautos.domain.Pedido;
import com.eduardomatsuda.corujaoautos.domain.Produto;
import com.eduardomatsuda.corujaoautos.domain.enuns.EstadoPagamento;
import com.eduardomatsuda.corujaoautos.domain.enuns.TipoCliente;
import com.eduardomatsuda.corujaoautos.repositories.CategoriaRepository;
import com.eduardomatsuda.corujaoautos.repositories.CidadeRepository;
import com.eduardomatsuda.corujaoautos.repositories.ClienteRepository;
import com.eduardomatsuda.corujaoautos.repositories.EnderecoRepository;
import com.eduardomatsuda.corujaoautos.repositories.EstadoRepository;
import com.eduardomatsuda.corujaoautos.repositories.ItemPedidoRepository;
import com.eduardomatsuda.corujaoautos.repositories.PagamentoRepository;
import com.eduardomatsuda.corujaoautos.repositories.PedidoRepository;
import com.eduardomatsuda.corujaoautos.repositories.ProdutoRepository;

@SpringBootApplication
public class CorujaoautosApplication implements CommandLineRunner {    
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
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
		SpringApplication.run(CorujaoautosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria (null, "Autos e Carros");
		Categoria  cat2 = new Categoria (null, "Caminhões e Ônibus");
		
		Produto p1 = new Produto(null, "Jogo de Velas VW SpaceFox", 120.00);
		Produto p2 = new Produto(null, "Jogo de Lampadas do Painel", 130.00);
		Produto p3 = new Produto(null, "Jogo de Cabos de Velas VW SpaceFox", 125.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));	
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade (null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PessoaFisica);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "993838393"));
		
		Endereco e1 = new Endereco(null, "RuaFlores ", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2023 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2023 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("10/11/2023 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00 );
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

package br.com.sigdata;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.sigdata.model.Categoria;
import br.com.sigdata.repository.CategoriaRepository;

@Service
@Transactional
public class InitData {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@EventListener
	public void inicializar(ApplicationReadyEvent event) {
		Categoria agua = new Categoria();
		agua.setCodigo("101");
		agua.setNome("AGUA E ESGOTO");
		agua.setTenantId("1");
		
		Categoria energia = new Categoria();
		energia.setCodigo("102");
		energia.setNome("ENERGIA");
		energia.setTenantId("1");
				
		Categoria internet = new Categoria();
		internet.setCodigo("103");
		internet.setNome("INTERNET");
		internet.setTenantId("1");
		
		Categoria compras = new Categoria();
		compras.setCodigo("104");
		compras.setNome("COMPRAS PARA COMERCIALIZAÇÃO");
		compras.setTenantId("1");
		
		Categoria marketing = new Categoria();
		marketing.setCodigo("105");
		marketing.setNome("MARKETING");
		marketing.setTenantId("2");
		
		Categoria despesasVendas = new Categoria();
		despesasVendas.setCodigo("106");
		despesasVendas.setNome("DESPESAS COM VENDAS");
		despesasVendas.setTenantId("2");
		
		Categoria pagamentoComissoes = new Categoria();
		pagamentoComissoes.setCodigo("107");
		pagamentoComissoes.setNome("PAGAMENTO DE COMISSÕES");
		pagamentoComissoes.setTenantId("2");
		
		this.categoriaRepository.saveAll(Arrays.asList(agua, energia, internet, compras, marketing, despesasVendas, pagamentoComissoes));
	}

}

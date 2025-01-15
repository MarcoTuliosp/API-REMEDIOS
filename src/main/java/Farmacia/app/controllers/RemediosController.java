package Farmacia.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import Farmacia.app.remedios.DadosAtualizarRemedio;
import Farmacia.app.remedios.DadosCadastroRemedio;
import Farmacia.app.remedios.DadosDetalhamentoRemedio;
import Farmacia.app.remedios.DadosListagemRemedios;
import Farmacia.app.remedios.Remedios;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import Farmacia.app.remedios.RemedioRepository;
import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemediosController {
	
	//enjetando a dependencia do jpa repository
	@Autowired
	private RemedioRepository remedioRepository;
	
	
	//Entendendo os Response Entity
	
	//Método para cadasrtra um remedio c
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody  @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
		var remedio = new Remedios(dados);
		remedioRepository.save(remedio);
		
		var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();
		
		return ResponseEntity.created(null).body(new DadosDetalhamentoRemedio (remedio));
		
	}
	
	//Métodos que lista todos os remedios cadastrados
	@GetMapping
	public ResponseEntity<List <DadosListagemRemedios>> listar(){
		var lista =  remedioRepository.findByAtivoTrue().stream().map(DadosListagemRemedios::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	
	//Método que usa um id para atualizar um remedio
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = remedioRepository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio (remedio));
	}
	
	//Esse Método deleta um remedio do banco de dados passsando um id no path
	@DeleteMapping("/{id}")
	@Transactional
	public  ResponseEntity<Void> deletar(@PathVariable Long id) {
		remedioRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	//Método que inativa um remedio mas nao exclui do banco
	@DeleteMapping("inativar/{id}")
	@Transactional
	public  ResponseEntity<Void> inativar (@PathVariable Long id) { 
		var remedio = remedioRepository.getReferenceById(id);
		remedio.inativar();
		
		return ResponseEntity.noContent().build();
	} 
	
	@PutMapping("/reativar/{id}")
	@Transactional
	public ResponseEntity <Void>reativar(@PathVariable Long id) {
		var remedio = remedioRepository.getReferenceById(id);
		remedio.reativar();
		
		return ResponseEntity.noContent().build();
	}
	
	//Buscar remédios por Id
	@GetMapping("/{id}")
	public  ResponseEntity<DadosDetalhamentoRemedio> BuscarPorId(@PathVariable Long id) {
		var remedio = remedioRepository.getReferenceById(id);

		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	}
	
	
	
		
}

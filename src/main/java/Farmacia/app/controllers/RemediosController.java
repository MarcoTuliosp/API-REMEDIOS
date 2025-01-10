package Farmacia.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Farmacia.app.remedios.DadosAtualizarRemedio;
import Farmacia.app.remedios.DadosCadastroRemedio;
import Farmacia.app.remedios.DadosListagemRemedios;
import Farmacia.app.remedios.Remedios;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import Farmacia.app.remedios.RemedioRepository;
import java.util.List;

@RestController
@RequestMapping("/remedios")
public class RemediosController {
	
	@Autowired
	private RemedioRepository remedioRepository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroRemedio dados) {
		remedioRepository.save(new Remedios(dados));
	}
	
	@GetMapping
	public List<DadosListagemRemedios> listar(){
		return remedioRepository.findAll().stream().map(DadosListagemRemedios::new).toList();
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = remedioRepository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public void deletar(@PathVariable Long id) {
		remedioRepository.deleteById(id);
	}
	
		
}

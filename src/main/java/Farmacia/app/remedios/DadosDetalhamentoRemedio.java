package Farmacia.app.remedios;

import java.time.LocalDate;

public record DadosDetalhamentoRemedio(
		Long id ,
		String nome,
		Via via,
		String lote, 
		int quantidade, 
		LocalDate validade, 
		Laboratorio laboratorio,
		Boolean ativo) {
	
	public  DadosDetalhamentoRemedio(Remedios remedio){
		this(   remedio.getId(),
				remedio.getNome(),
				remedio.getVia(),
				remedio.getLote(),
				remedio.getQuantidade(),
				remedio.getValidade(),
				remedio.getLaboratorio(),
				remedio.isAtivo());
	}
}

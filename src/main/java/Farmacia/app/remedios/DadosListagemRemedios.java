package Farmacia.app.remedios;

import java.time.LocalDate;

public record DadosListagemRemedios(String nome, Via via, Laboratorio laboratorio, LocalDate validade) {
	
	public DadosListagemRemedios(Remedios remedio) {
		this(remedio.getNome(),remedio.getVia(),remedio.getLaboratorio(), remedio.getValidade());
	}
}

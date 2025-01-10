package Farmacia.app.remedios;

import java.time.LocalDate;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
public record DadosCadastroRemedio(
		@NotNull
		@NotBlank
		String nome,
		@Enumerated
		Via via,
		@NotBlank
		String lote,
		@NotNull
		int quantidade,
		@Future
		LocalDate validade,
		@Enumerated
		Laboratorio laboratorio) {

}

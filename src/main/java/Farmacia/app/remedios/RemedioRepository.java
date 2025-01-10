package Farmacia.app.remedios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//intrerface importada pelo jpa para realizar os CRUD's

public interface RemedioRepository extends JpaRepository<Remedios, Long>{

	List<Remedios> findByAtivoTrue();

}

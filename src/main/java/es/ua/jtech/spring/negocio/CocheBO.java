package es.ua.jtech.spring.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ua.jtech.spring.modelo.Coche;
import es.ua.jtech.spring.repository.CocheRepository;


/**
 * La capa de negocio no hace gran cosa, se limita a "pasarle la pelota" al DAO
 * no es m‡s que una excusa para poder tener tres capas y poder hacer pruebas de integraci—n
 */
@Service
public class CocheBO{

	@Autowired
	private CocheRepository cocheRepository;
	
	public List<Coche> listar() {
		return cocheRepository.findAll();
	}

	public void crear(Coche coche) {
		cocheRepository.save(coche);
	}

	public Coche obtener(String matricula) {
		return cocheRepository.findOne(matricula);
	}

}

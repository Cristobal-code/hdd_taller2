package cl.sarayar.gestorTareasRest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sarayar.gestorTareasRest.entities.Tarea;
import cl.sarayar.gestorTareasRest.repositories.TareasRepository;
@Service
public class TareasServiceImpl implements TareasService {


	private TareasRepository tareasRepository;
	@Autowired
	public TareasServiceImpl(TareasRepository tareasRepository){
		this.tareasRepository = tareasRepository;
	}
	
	@Override
	public List<Tarea> findAll() {
		return tareasRepository.findAll();
	}

	@Override
	public Tarea save(Tarea t) {
		return tareasRepository.save(t);
	}


	@Override
	public boolean remove(String id) {
		try {
			tareasRepository.deleteById(id);
			return true;
		}catch(IllegalArgumentException ex) {
			return false;
		}
	}

	@Override
	public Tarea findById(String id) {

		return this.tareasRepository.findById(id).orElse(null);
	}
	
}

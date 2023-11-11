package cl.sarayar.gestorTareasRest.controllers;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.sarayar.gestorTareasRest.entities.Tarea;
import cl.sarayar.gestorTareasRest.services.TareasService;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tareas")
public class TareasController {
	private TareasService tareasService;
	@Autowired
	public TareasController(TareasService tareasService){
		this.tareasService = tareasService;
	}

	@GetMapping("/get")
	public List<Tarea> getAll(){
		return tareasService.findAll();
	}
	
	@PostMapping("/post")
	public ResponseEntity<Tarea> save(@Valid @RequestBody Tarea tarea) {
		tarea.setFechaCreacion(LocalDateTime.now());
		return ResponseEntity.ok(this.tareasService.save(tarea));
	}
	
	@PostMapping("/update")
	public ResponseEntity<Tarea> update(@Valid @RequestBody Tarea tarea) {
		Tarea tareaOriginal = this.tareasService.findById(tarea.getId());
		if(tareaOriginal != null) {
			tareaOriginal.setDescripcion(tarea.getDescripcion());
			tareaOriginal.setVigente(tarea.isVigente());
			return ResponseEntity.ok(this.tareasService.save(tareaOriginal));
		}else {
			return ResponseEntity.status(500).body(null);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable String id) {
		try {
			this.tareasService.remove(id);
			return ResponseEntity.ok(true);
		}catch(Exception ex) {
			return ResponseEntity.status(500).body(false);
		}
	}
	
	
}

package cl.sarayar.gestorTareasRest.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import cl.sarayar.gestorTareasRest.entities.Tarea;
import cl.sarayar.gestorTareasRest.services.GeneradorSecuenciaService;

@Component
public class TareasModelListener extends AbstractMongoEventListener<Tarea>{


	private GeneradorSecuenciaService generador;
	@Autowired
	public TareasModelListener( GeneradorSecuenciaService generador){
		this.generador = generador;
	}
	
	@Override
	public void onBeforeConvert(BeforeConvertEvent<Tarea> event) {
		if(event.getSource().getIdentificador()<1) {
			event.getSource().setIdentificador(generador.generadorSecuencia(Tarea.NOMBRE_SECUENCIA));
		}
	}
}

package cl.sarayar.gestorTareasRest.listeners;

import cl.sarayar.gestorTareasRest.controllers.TareasController;
import cl.sarayar.gestorTareasRest.entities.Tarea;
import cl.sarayar.gestorTareasRest.services.GeneradorSecuenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TareasModelListenerTest {

    @Mock
    private GeneradorSecuenciaService generador;

    private TareasModelListener tareasModelListener;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.tareasModelListener = new TareasModelListener(generador);

    };

    @Test
    void onBeforeConvert() {
        Tarea tarea = new Tarea();
        // El valor 0 , para que entre a la condicion del IF
        tarea.setIdentificador(0);
        //Ya que el nombre de secuencia es estatica,simplemente la uso.
        when(generador.generadorSecuencia(Tarea.NOMBRE_SECUENCIA)).thenReturn(33L); //33L porque me gusta el 33 jeje

        //Supuestamente se hizo convert a la tarea(deberia tener otro identificador distinto al 0)
        tareasModelListener.onBeforeConvert(new BeforeConvertEvent<>(tarea, null));


        assertEquals(33L, tarea.getIdentificador());

    }


}
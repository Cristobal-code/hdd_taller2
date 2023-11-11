package cl.sarayar.gestorTareasRest.controllers;

import cl.sarayar.gestorTareasRest.entities.Tarea;
import cl.sarayar.gestorTareasRest.services.TareasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TareasControllerTest {
    @Mock
    private TareasService tareasService;
    private  TareasController tareasController;
    private Tarea tarea;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.tareasController = new TareasController(tareasService);
        tarea = new Tarea();
        tarea.setId("1");
        tarea.setDescripcion("Descripción actualizada");
        tarea.setVigente(true);
    };

    @Test
    void getAllOk()  {
        List<Tarea> tareasSimuladas = new ArrayList<>();
        Tarea tarea2 = new Tarea();
        tarea2.setId("2");
        tarea2.setIdentificador(2L);
        tarea2.setDescripcion("Tarea 2");
        tarea2.setFechaCreacion(LocalDateTime.now());
        tarea2.setVigente(false);

        tareasSimuladas.add(tarea);
        tareasSimuladas.add(tarea2);

        when(tareasService.findAll()).thenReturn(tareasSimuladas);

        List<Tarea> resultado = tareasController.getAll();

        assertEquals(tareasSimuladas.size(), resultado.size());
        assertEquals(tareasSimuladas, resultado);
    }
    @Test
    void saveOk()  {
        when(tareasService.save(any(Tarea.class))).thenReturn(tarea);
        ResponseEntity<Tarea> result = tareasController.save(tarea);

        assertEquals(tarea, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    void updateOk()  {
        Tarea tareaOriginal = new Tarea();
        tareaOriginal.setId("1");
        tareaOriginal.setDescripcion("Descripción original");
        tareaOriginal.setVigente(true);
        tareaOriginal.setFechaCreacion(LocalDateTime.now());

        when(tareasService.findById(tarea.getId())).thenReturn(tareaOriginal);
        when(tareasService.save(any(Tarea.class))).thenReturn(tarea);

        ResponseEntity<Tarea> result = tareasController.update(tarea);

        assertEquals(tarea, result.getBody());
    }

    @Test
    void updateOkTestException(){

        String taskId = "2";
        when(tareasService.findById(taskId)).thenReturn(null);

        ResponseEntity<Tarea> result = tareasController.update(new Tarea());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertNull(result.getBody());
    }
    @Test
    void deleteOk()  {
        String id = "1";
        when(tareasService.remove(id)).thenReturn(true);
        ResponseEntity<Boolean> result = tareasController.delete(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void deleteOkTestException(){

        String idException = "33";
        when(tareasService.remove(idException)).thenThrow(new RuntimeException());

        ResponseEntity<Boolean> result = tareasController.delete(idException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertFalse(result.getBody());
    }


}
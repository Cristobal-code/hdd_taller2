package cl.sarayar.gestorTareasRest.services;

import cl.sarayar.gestorTareasRest.entities.Tarea;
import cl.sarayar.gestorTareasRest.repositories.TareasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TareasServiceImplTest {

    @Mock
    private TareasRepository tareasRepository;

    private TareasService tareasService;

    private Tarea tareaFake;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.tareasService = new TareasServiceImpl(tareasRepository);
        tareaFake = new Tarea();
        tareaFake.setId("1");
        tareaFake.setDescripcion("Tarea fake 1");
        tareaFake.setVigente(true);

    };

    @Test
    void findAllOk() {
        List<Tarea> tareasFakes = new ArrayList<>();

        tareasFakes.add(tareaFake);
        when(tareasRepository.findAll()).thenReturn(tareasFakes);

        List<Tarea> result = tareasService.findAll();

        assertEquals(tareasFakes, result);

    }
    @Test
    void saveOk() {
        when(tareasRepository.save(any(Tarea.class))).thenReturn(tareaFake);
        Tarea result = tareasService.save(tareaFake);

        //Se comparan todos los campos de la tarea fake con el resultado
        assertEquals(tareaFake, result);
        //En teoria se prueba si se llamo al repo fake durante el metodo save
        verify(tareasRepository, times(1)).save(any(Tarea.class));
    }
    @Test
    void removeOk() {
        //Caso NO se cae
        boolean result1 = tareasService.remove(tareaFake.getId());
        assertTrue(result1);

        //Caso SI se cae
        String tareaQueNoExiste = "3333";
        doThrow(new IllegalArgumentException()).when(tareasRepository).deleteById(tareaQueNoExiste);

        boolean result2 = tareasService.remove(tareaQueNoExiste);
        assertFalse(result2);
    }

    @Test
    void findById() {
        when(tareasRepository.findById(any(String.class))).thenReturn(Optional.of(tareaFake));
        // "1" es la id de la tarea fake definida
        Tarea result = tareasService.findById("1");

        assertEquals(tareaFake, result);
        //Este es por las dudas, verifcando que sí llamo al repo solo 1 vez.
        verify(tareasRepository, times(1)).findById(eq("1"));
    }


}
package cl.sarayar.gestorTareasRest;

import cl.sarayar.gestorTareasRest.controllers.UsuariosController;
import cl.sarayar.gestorTareasRest.entities.Usuario;
import cl.sarayar.gestorTareasRest.repositories.UsuariosRepository;
import cl.sarayar.gestorTareasRest.services.UsuariosService;
import cl.sarayar.gestorTareasRest.services.UsuariosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GestorTareasRestApplicationTest {

    @Mock
    private UsuariosService usService;

    @Mock
    private UsuariosRepository usRepo;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.usService = new UsuariosServiceImpl(usRepo);

    };

    @Test
    void runWithNoExistingUsers() throws Exception {
        // Simulamos que no hay usuarios existentes
        when(usService.getAll()).thenReturn(Collections.emptyList());
        // Creamos la instancia de la aplicación
        GestorTareasRestApplication application = new GestorTareasRestApplication(usService);
        application.run();
    }

    @Test
    void runWithExistingUsers()throws Exception {
        // Simulamos que hay usuarios existentes
        List<Usuario> existingUsers = Collections.singletonList(new Usuario());
        when(usService.getAll()).thenReturn(existingUsers);

        // Creamos la instancia de la aplicación
        GestorTareasRestApplication application = new GestorTareasRestApplication(usService);

        // Ejecutamos el método run
        application.run();

    }

    @Test
    void runWithException() throws Exception {
        // Simulamos que no hay usuarios existentes
        when(usService.getAll()).thenReturn(Collections.emptyList());

        // Simulamos una excepción al guardar el usuario
        when(usService.save(any(Usuario.class))).thenThrow(new RuntimeException("Simulated exception"));
        GestorTareasRestApplication application = new GestorTareasRestApplication(usService);
        // No debería lanzar una excepción en el bloque catch
        application.run();
    }



}
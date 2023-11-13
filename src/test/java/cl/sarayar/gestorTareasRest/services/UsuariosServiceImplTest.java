package cl.sarayar.gestorTareasRest.services;

import cl.sarayar.gestorTareasRest.config.auth.UserDetailsImpl;
import cl.sarayar.gestorTareasRest.entities.Tarea;
import cl.sarayar.gestorTareasRest.entities.Usuario;
import cl.sarayar.gestorTareasRest.repositories.UsuariosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UsuariosServiceImplTest {

    @Mock
    private UsuariosRepository usRepo;

    private UsuariosService usuariosService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.usuariosService = new UsuariosServiceImpl(usRepo);
    };

    @Test
    void saveOk() {
        Usuario usuario = new Usuario("1", "Usuario1", "usuario1@usm.cl", "12345", 1);

        when(usRepo.save(usuario)).thenReturn(usuario);
        Usuario result = usuariosService.save(usuario);

        assertEquals(usuario, result);
    }
    @Test
    void getAllOk() {
        List<Usuario> usuariosFake = new ArrayList<>();
        usuariosFake.add(new Usuario("1", "Usuario1", "usuario1@usm.cl", "12345", 1));
        usuariosFake.add(new Usuario("2", "Usuario2", "usuario2@usm.cl", "12345", 1));

        when(usRepo.findAll()).thenReturn(usuariosFake);
        List<Usuario> result = usuariosService.getAll();

        assertEquals(usuariosFake, result);
    }

    @Test
    void findByCorreoOk() {
        String correo = "usuario1@usm.cl";
        Usuario usuario = new Usuario("1", "Usuario1", correo, "12345", 1);

        when(usRepo.findByCorreo(correo)).thenReturn(Optional.of(usuario));
        Usuario result = usuariosService.findByCorreo(correo);

        assertEquals(usuario, result);
    }
    @Test
    void findByIdOk() {
        String id = "1";
        Usuario usuario = new Usuario(id, "Usuario1", "usuario1@usm.cl", "12345", 1);

        when(usRepo.findById(id)).thenReturn(Optional.of(usuario));
        Usuario result = usuariosService.findById(id);

        assertEquals(usuario, result);
    }

    @Test
    void loadUserByUsernameOk() {
        String correo = "usuario1@usm.cl";
        Usuario usuario = new Usuario("1", "Usuario1", correo, "clave1", 1);

        when(usRepo.findByCorreo(correo)).thenReturn(Optional.of(usuario));

        //Supuestamente se devuelve un "UserDetailsImpl" si toodo sale OK
        UserDetailsImpl result = (UserDetailsImpl) usuariosService.loadUserByUsername(correo);


        assertNotNull(result);
        assertEquals(usuario, result.getUsuario());
    }

    @Test
    void loadUserByUsernameUsuarioNoEncontradoOk() {
        String correo = "usuario1@usm.cl";

        when(usRepo.findByCorreo(correo)).thenReturn(Optional.empty());

        UserDetails result = usuariosService.loadUserByUsername(correo);

        assertNull(result);
    }
    @Test
    void existsByCorreoOk() {
        String correo = "usuario1@usm.cl";
        when(usRepo.existsByCorreo(correo)).thenReturn(true);
        boolean result = usuariosService.existsByCorreo(correo);

        assertTrue(result);
    }

}
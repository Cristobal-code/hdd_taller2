package cl.sarayar.gestorTareasRest.controllers;


import cl.sarayar.gestorTareasRest.config.auth.dto.MessageResponse;
import cl.sarayar.gestorTareasRest.entities.Usuario;
import cl.sarayar.gestorTareasRest.services.UsuariosService;
import cl.sarayar.gestorTareasRest.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UsuariosControllerTest {
    @Mock
    private UsuariosService usService;
    @Mock
    private JwtUtils jwtUtils;

    private  UsuariosController usuariosController;
    private Usuario usuario;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.usuariosController = new UsuariosController(usService, jwtUtils);

        usuario = new Usuario();
        usuario.setId("1");
        usuario.setNombre("Criss");
        usuario.setCorreo("Criss@usm.cl");
        usuario.setClave("123456");
    };

    @Test
    void loginOk(){


        ResponseEntity<?> result = usuariosController.authenticateUser(usuario);
        assertEquals(200, result.getStatusCodeValue());
        assertEquals(usuario, result.getBody());
    }

    @Test
    void registerUserOk() {

        when(usService.existsByCorreo("Criss@usm.cl")).thenReturn(false);
        when(usService.save(any())).thenReturn(usuario);
        ResponseEntity<?> result = usuariosController.registerUser(usuario);

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void registerUserOkErrorPorCorreoExistente() {

        when(usService.existsByCorreo("Criss@usm.cl")).thenReturn(true);
        when(usService.save(any())).thenReturn(usuario);
        ResponseEntity<?> result = usuariosController.registerUser(usuario);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Error: Usuario ya existe!", ((MessageResponse) result.getBody()).getMensaje());

    }

    @Test
    void actualizarOk() {
        Usuario usuarioOriginal = new Usuario();
        usuarioOriginal.setId("1");
        usuarioOriginal.setNombre("CrissOld");
        usuarioOriginal.setCorreo("CrissOld@usm.cl");
        usuarioOriginal.setClave("98765");

        when(usService.findById(usuario.getId())).thenReturn(usuarioOriginal);
        //El correo nuevo a actualizar, no existirá previamente( Criss@usm.cl ).
        when(usService.findByCorreo(usuario.getCorreo())).thenReturn(null);
        when(usService.save(any())).thenReturn(usuario);

        ResponseEntity<?> result = usuariosController.actualizarUsuario(usuario);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void actualizarUsuarioErrorCorreoExistente() {
        Usuario usuarioOriginal = new Usuario();
        usuarioOriginal.setId("1");
        usuarioOriginal.setNombre("CrissOld");
        usuarioOriginal.setCorreo("CrissOld@usm.cl");
        usuarioOriginal.setClave("98765");

        Usuario usuarioConCorreo = new Usuario();
        usuarioConCorreo.setId("3");

        when(usService.findById(usuario.getId())).thenReturn(usuarioOriginal);
        when(usService.findByCorreo(usuario.getCorreo())).thenReturn(usuarioConCorreo);

        ResponseEntity<?> result = usuariosController.actualizarUsuario(usuario);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Error: Correo se encuentra utilizado!", ((MessageResponse) result.getBody()).getMensaje());
    }

    @Test
    void getAllUsuarios() {
        List<Usuario> usuariosSimulados = new ArrayList<>();
        usuariosSimulados.add(new Usuario("10", "Usuario1", "usuario1@usm.com", "123", 1));
        usuariosSimulados.add(new Usuario("20", "Usuario2", "usuario2@usm.com", "456", 1));
        usuariosSimulados.add(new Usuario("39", "Usuario3", "usuario3@usm.com", "789", 1));


        when(usService.getAll()).thenReturn(usuariosSimulados);
        List<Usuario> resultado = usuariosController.getAll();

        assertEquals(usuariosSimulados.size(), resultado.size());
        assertEquals(usuariosSimulados, resultado);
    }
}
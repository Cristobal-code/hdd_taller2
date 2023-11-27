package cl.sarayar.gestorTareasRest.config.auth;

import cl.sarayar.gestorTareasRest.entities.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsImplTest {
    @Test
    void testUserDetailsImpl() {
        // Arrange
        Usuario usuario = new Usuario();
        usuario.setId("1");
        usuario.setCorreo("test@example.com");
        usuario.setClave("password");

        UserDetailsImpl userDetails = new UserDetailsImpl(usuario);

        // Ya que son funciones simples, probaré todo aqui
        assertNotNull(userDetails);
        assertEquals(usuario.getCorreo(), userDetails.getUsername());
        assertEquals(usuario.getClave(), userDetails.getPassword());
        assertEquals("1", userDetails.getUsuario().getId());
        assertTrue(userDetails.getAuthorities().isEmpty());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    void testEquals() {
        Usuario usuario1 = new Usuario();
        usuario1.setId("1");

        Usuario usuario2 = new Usuario();
        usuario2.setId("1");

        UserDetailsImpl userDetails1 = new UserDetailsImpl(usuario1);
        UserDetailsImpl userDetails2 = new UserDetailsImpl(usuario2);

        assertEquals(userDetails1, userDetails2);
    }

    @Test
    void testEqualsSameInstance() {
        UserDetailsImpl userDetails = new UserDetailsImpl(new Usuario());
        assertTrue(userDetails.equals(userDetails));
    }

    @Test
    void testEqualsWithNull() {
        UserDetailsImpl userDetails = new UserDetailsImpl(new Usuario());
        assertFalse(userDetails.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        UserDetailsImpl userDetails = new UserDetailsImpl(new Usuario());
        assertFalse(userDetails.equals(new Object()));
    }
}
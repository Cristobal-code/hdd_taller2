package cl.sarayar.gestorTareasRest.utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

class JwtUtilsTest {

    private JwtUtils jwtUtils;


    @Mock
    private Authentication authentication;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.jwtUtils = new JwtUtils();


    };
    @Test
    void testGetSigningKey() {
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "testSecret");
        String signingKey = jwtUtils.getSigningKey();
        assertEquals("testSecret", signingKey);
    }

    // Imposible de testear el resto, F
    // 77% Coverage, nunca sé como se instancia "Authentication" para hacer pruebas
    //Los metodos de JWTUTILS no se usan, asi que no se como funcionan

}
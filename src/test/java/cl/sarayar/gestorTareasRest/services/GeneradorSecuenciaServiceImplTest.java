package cl.sarayar.gestorTareasRest.services;
import cl.sarayar.gestorTareasRest.entities.Secuencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GeneradorSecuenciaServiceImplTest {

    @Mock
    private MongoOperations mongoOperations;

    private GeneradorSecuenciaService generadorSecuenciaService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        this.generadorSecuenciaService = new GeneradorSecuenciaServiceImpl(mongoOperations);

    };

    @Test
    void generadorDeSecuenciaOK(){

        Secuencia secuenciaTest = new Secuencia();
        secuenciaTest.setId("1");
        secuenciaTest.setSeq(1);

        when(mongoOperations.findAndModify(any(), any(), any()))
                .thenReturn(secuenciaTest);


        long resultado = generadorSecuenciaService.generadorSecuencia("nombre");


        assertEquals(1, resultado);
    }
}
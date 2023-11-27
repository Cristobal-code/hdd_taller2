package cl.sarayar.gestorTareasRest;

import cl.sarayar.gestorTareasRest.entities.Usuario;
import cl.sarayar.gestorTareasRest.services.UsuariosService;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@ComponentScan
public class GestorTareasRestApplication implements CommandLineRunner {

	private UsuariosService usService;
	@Autowired
	GestorTareasRestApplication(UsuariosService usService){
		this.usService = usService;
	}

@Generated
	public static void main(String[] args) {
		SpringApplication.run(GestorTareasRestApplication.class, args);
	}

	private static final Logger logger = LoggerFactory.getLogger(GestorTareasRestApplication.class);

	@Override
	public void run(String... args) throws Exception{
		try {
			List<Usuario> usuariosExistentes = usService.getAll();
			if(usuariosExistentes.isEmpty()) {
				//Creamos el usuario admin por defecto
				Usuario admin = new Usuario();
				admin.setNombre("Admin");
				admin.setCorreo("sarayar@skynux.cl");
				usService.save(admin);
			}
		}catch(Exception ex) {
			logger.error("Error al definir usuario por defecto:" + ex.getMessage());
		}
	}

}

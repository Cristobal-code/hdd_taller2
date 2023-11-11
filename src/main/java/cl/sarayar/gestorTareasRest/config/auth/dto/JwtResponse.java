package cl.sarayar.gestorTareasRest.config.auth.dto;

import cl.sarayar.gestorTareasRest.entities.Usuario;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class JwtResponse {

	private String token;
	private Usuario usuario;
}

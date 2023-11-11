package cl.sarayar.gestorTareasRest.config.auth.dto;

import cl.sarayar.gestorTareasRest.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

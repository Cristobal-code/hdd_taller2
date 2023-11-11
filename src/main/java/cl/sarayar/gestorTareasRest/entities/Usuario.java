package cl.sarayar.gestorTareasRest.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Usuario {
	@Id
	private String id;
	private String nombre;
	@Indexed(unique = true)
	private String correo;
	private String clave;
	private int estado = 1;
	

}

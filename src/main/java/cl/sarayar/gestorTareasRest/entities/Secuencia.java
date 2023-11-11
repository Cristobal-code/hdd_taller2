package cl.sarayar.gestorTareasRest.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "secuencias")
@Getter
@Setter
@NoArgsConstructor
@Generated
public class Secuencia {
	  	@Id
	    private String id;
	    private long seq;
}

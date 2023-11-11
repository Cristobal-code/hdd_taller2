package cl.sarayar.gestorTareasRest.entities;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Document(collection = "tareas")
@Generated
public class Tarea {

	@Transient
	public static final String NOMBRE_SECUENCIA = "tareas_secuencia";
	@Id
	private String id;
	@Indexed(unique=true)
	private long identificador;
	@NotBlank(message = "Debe proporcionar descripción")
	private String descripcion;
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	private LocalDateTime fechaCreacion;
	@NotNull(message = "Debe indicar si la tarea es vigente o no")
	private boolean vigente;

}

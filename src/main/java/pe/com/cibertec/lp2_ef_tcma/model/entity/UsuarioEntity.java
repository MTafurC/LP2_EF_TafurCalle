package pe.com.cibertec.lp2_ef_tcma.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

	@Id
	@Column(name = "correo", nullable = false, length = 60, unique = true)
	private String correo;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "nombres", nullable = false, length = 100)
	private String nombres;

	@Column(name = "apellidos", nullable = false, length = 100)
	private String apellidos;
	
	@Column(name = "fecha_nacimiento", nullable = false)
	//@Temporal(TemporalType.DATE)
	private String fecha_nacimiento;

	@Column(name = "url_imagen")
	private String urlImagen;
}

package pe.com.cibertec.lp2_ef_tcma.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre_producto", nullable = false)
	private String nombre_producto;
	
	@Column(name = "precio", nullable = false)
	private Double precio;
	
	@Column(name = "stock", nullable = false)
	private Integer stock;
	
	@Column(name = "categoria", nullable = false)
	private String categoria;

}

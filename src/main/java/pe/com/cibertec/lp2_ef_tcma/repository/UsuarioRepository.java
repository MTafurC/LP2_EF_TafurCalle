package pe.com.cibertec.lp2_ef_tcma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.cibertec.lp2_ef_tcma.model.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
	UsuarioEntity findByCorreo(String correo);

}

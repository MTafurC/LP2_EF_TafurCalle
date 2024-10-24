package pe.com.cibertec.lp2_ef_tcma.service;

import pe.com.cibertec.lp2_ef_tcma.model.entity.ProductoEntity;

import java.util.List;

public interface ProductoService {
    List<ProductoEntity> buscarTodosProductos();

    ProductoEntity buscarProductoPorId(Integer id);

	void crearProducto(ProductoEntity producto);

	void eliminarProductoPorId(Integer id);
}

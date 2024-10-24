package pe.com.cibertec.lp2_ef_tcma.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.cibertec.lp2_ef_tcma.model.entity.ProductoEntity;
import pe.com.cibertec.lp2_ef_tcma.repository.ProductoRepository;
import pe.com.cibertec.lp2_ef_tcma.service.ProductoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

	@Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoEntity> buscarTodosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoEntity buscarProductoPorId(Integer id) {
        return productoRepository.findById(id).get();
    }

	@Override
	public void crearProducto(ProductoEntity producto) {
		productoRepository.save(producto);		
	}

	@Override
	public void eliminarProductoPorId(Integer id) {
		productoRepository.delete(this.buscarProductoPorId(id));;		
		
	}
}

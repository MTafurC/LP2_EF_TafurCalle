package pe.com.cibertec.lp2_ef_tcma.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pe.com.cibertec.lp2_ef_tcma.model.entity.ProductoEntity;
import pe.com.cibertec.lp2_ef_tcma.model.entity.UsuarioEntity;
import pe.com.cibertec.lp2_ef_tcma.repository.UsuarioRepository;
import pe.com.cibertec.lp2_ef_tcma.service.ProductoService;
import pe.com.cibertec.lp2_ef_tcma.service.UsuarioService;
import pe.com.cibertec.lp2_ef_tcma.service.impl.PdfService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PdfService pdfService;

	@GetMapping("/productos")
	public String mostrarMenu(Model model, HttpSession session) {
		String info = null;
		if (session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		
		if(session.getAttribute("info") != null) {
			info = session.getAttribute("info").toString();
		}
		
		session.setAttribute("info", null);
		
		String correoSesion = session.getAttribute("usuario").toString();
		 
		UsuarioEntity usuario = usuarioService.buscarUsuarioPorCorreo(correoSesion);

		List<ProductoEntity> listaProductos = productoService.buscarTodosProductos();
		model.addAttribute("productos", listaProductos);	
		model.addAttribute("usuario", usuario);
		model.addAttribute("info", info);

		return "/producto/productos";
	}
	
	@GetMapping("/producto")
	public String mostrarProducto(Model model, HttpSession session, @RequestParam(value = "id", required=false) Integer id) {	
		
		if (session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		String correoSesion = session.getAttribute("usuario").toString();				
		UsuarioEntity usuario = usuarioService.buscarUsuarioPorCorreo(correoSesion);		
		
		List<String> categorias = new ArrayList<>();		
		categorias.add("ABARROTES");
		categorias.add("AGUA DE MESA, GASEOSAS Y JUGOS");
		categorias.add("AVICOLAS");
		categorias.add("BOCADILLOS Y FIAMBRES");
		categorias.add("BODEGAS");
		categorias.add("CARNICERIAS");
		categorias.add("CHOCOLATERIA");
		categorias.add("FRUTAS Y VERDURAS");
		categorias.add("LACTEOS");
		categorias.add("LICORERIAS");
		categorias.add("LONCHERAS");
		categorias.add("PANADERIAS");
		categorias.add("PARRILLA"); 
		categorias.add("PASTAS");
		categorias.add("PASTELERIA Y TORTAS");
		categorias.add("SUPERMERCADOS");
		categorias.add("VITAMINAS Y SUPLEMENTOS ALIMENTICIOS");
		
		ProductoEntity producto = new ProductoEntity();
		if(id != null) { 
			producto = productoService.buscarProductoPorId(id);
		}
		
		model.addAttribute("producto",  producto);
		model.addAttribute("categorias", categorias);
		model.addAttribute("usuario", usuario);		
		
		return "/producto/producto";
	}
	
	@GetMapping("/producto_ver")
	public String verProducto(Model model, HttpSession session, @RequestParam(value = "id", required=false) Integer id) {	
		
		if (session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		String correoSesion = session.getAttribute("usuario").toString();				
		UsuarioEntity usuario = usuarioService.buscarUsuarioPorCorreo(correoSesion);		
		
		
		
		ProductoEntity producto = new ProductoEntity();
		if(id != null) { 
			producto = productoService.buscarProductoPorId(id);
		}
		
		model.addAttribute("producto",  producto);
		model.addAttribute("usuario", usuario);
		
		return "/producto/producto_ver";
	}
	
	@GetMapping("/producto_eliminar")
	public String eliminarProducto(Model model, HttpSession session, @RequestParam(value = "id", required=true) Integer id) {	
		
		if (session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		String correoSesion = session.getAttribute("usuario").toString();				
		UsuarioEntity usuario = usuarioService.buscarUsuarioPorCorreo(correoSesion);
		 
		productoService.eliminarProductoPorId(id);
		
		model.addAttribute("usuario", usuario);
		session.setAttribute("info", "El producto se ha eliminado.");
		
		return "redirect:/productos";
	}

	@PostMapping("/registrar_producto")
	public String registrarProducto(@ModelAttribute("producto") ProductoEntity producto, Model model, HttpSession session, @RequestParam(value = "id", required=false) Integer id) {
		
		if (session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		String correoSesion = session.getAttribute("usuario").toString();				
		UsuarioEntity usuario = usuarioService.buscarUsuarioPorCorreo(correoSesion);	
		
		String info = "El producto se ha creado.";
		if(id != null) { 
			producto.setId(id);
			info = "El producto se ha editado.";
		} 
		
		productoService.crearProducto(producto);
		
		model.addAttribute("usuario", usuario);
		session.setAttribute("info", info);
		
		return "redirect:/productos";
	}

}

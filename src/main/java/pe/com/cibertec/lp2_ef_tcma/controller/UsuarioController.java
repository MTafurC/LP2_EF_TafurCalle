package pe.com.cibertec.lp2_ef_tcma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import pe.com.cibertec.lp2_ef_tcma.model.entity.UsuarioEntity;
import pe.com.cibertec.lp2_ef_tcma.service.UsuarioService;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/registrar_usuario")
	public String mostrarRegistrarUsuario(Model model, HttpSession session) {
		model.addAttribute("usuario", new UsuarioEntity());
		session.invalidate();
		return "registrar_usuario";
	}

	@PostMapping("/registrar_usuario")
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioEntity usuario,
		Model model, @RequestParam("foto") MultipartFile foto, HttpSession session) {
		usuarioService.crearUsuario(usuario, foto);
		session.setAttribute("nuevo_usuario", "Se ha creado el nuevo usuario " + usuario.getCorreo() + ".");
		return "redirect:/";
	}

	@GetMapping("/")
	public String mostrarLogin(Model model, HttpSession session) {
		model.addAttribute("usuario", new UsuarioEntity());
		String nuevo_usuario = null;
		if(session.getAttribute("nuevo_usuario") != null) {
			nuevo_usuario = session.getAttribute("nuevo_usuario").toString();
		}
		session.invalidate();
		model.addAttribute("nuevo_usuario", nuevo_usuario);
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("usuario") UsuarioEntity usuarioFormulario,
			Model model, HttpSession session) {
		boolean validarUsuario = usuarioService.validarUsuario(usuarioFormulario);		
		
		if (validarUsuario) {
			session.setAttribute("usuario", usuarioFormulario.getCorreo());
			return "redirect:/productos";
		}
		model.addAttribute("loginInvalido", "No existe el usuario");
		model.addAttribute("usuario", new UsuarioEntity());
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}

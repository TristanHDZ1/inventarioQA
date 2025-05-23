package inventarioQA.mx.InventarioMVC.Controllers;

import inventarioQA.mx.InventarioMVC.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // login.html (thymeleaf o en /static si es solo HTML)
    }

    @PostMapping("/Login")
    public String procesarLogin(@RequestParam String usuario,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        if (usuarioService.autenticar(usuario, password)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Usuario o contraseña inválidos");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String verDashboard() {
        return "dashboard"; // Página después del login
    }
}

package inventarioQA.mx.InventarioMVC.Controllers;

import inventarioQA.mx.InventarioMVC.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/home/")
public class InventarioController {

    @GetMapping("/Principal/")
    public String Principal() {
        return "Principal";
    }

        @Autowired
        private UsuarioService usuarioService;


        @GetMapping("/Login/")
        public String mostrarLogin() {
            return "Login";
        }

        @PostMapping("/Login/")
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

        @GetMapping("/dashboard/")
        public String verDashboard() {
            return "dashboard"; // Página después del login
        }


    }


package inventarioQA.mx.InventarioMVC.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/")
public class InventarioController {

    @GetMapping("/Principal/")
    public String Principal() {
        return "Principal";
    }
    @GetMapping("/Login/")
    public String Login() {
        return "Login";
    }
}

package inventarioQA.mx.InventarioMVC.Controllers;

import inventarioQA.mx.InventarioMVC.Models.Device;
import inventarioQA.mx.InventarioMVC.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home/")
public class InventarioController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/Principal/")
    public String Principal() {
        return "Principal";
    }

    @GetMapping("/Login/")
    public String mostrarLogin() {
        return "Login";
    }

    @GetMapping("/devices")
    public String verDispositivos(Model model) {
        List<Device> dispositivos = deviceRepository.findAll();
        model.addAttribute("dispositivos", dispositivos);
        return "devices";
    }

    @GetMapping("/nuevo-dispositivo")
    public String nuevoDispositivo(Model model) {
        model.addAttribute("device", new Device());
        return "formulario-dispositivo";
    }

    @GetMapping("/editar-dispositivo/{id}")
    public String editarDispositivo(@PathVariable Long id, Model model) {
        Device device = deviceRepository.findById(id).orElse(null);
        model.addAttribute("device", device);
        return "formulario-dispositivo";
    }

    @PostMapping("/guardar-dispositivo")
    public String guardarDispositivo(@ModelAttribute Device device) {
        deviceRepository.save(device);
        return "redirect:/home/devices";
    }
}

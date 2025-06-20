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
public String verDispositivos(@RequestParam(value = "query", required = false) String query, Model model) {
    List<Device> dispositivos;

    if (query != null && !query.trim().isEmpty()) {
        dispositivos = deviceRepository.findAll().stream()
            .filter(d ->
                (d.getProducto() != null && d.getProducto().toLowerCase().contains(query.toLowerCase())) ||
                (d.getMarca() != null && d.getMarca().toLowerCase().contains(query.toLowerCase())) ||
                (d.getModelo() != null && d.getModelo().toLowerCase().contains(query.toLowerCase())) ||
                (d.getUsuarioActual() != null && d.getUsuarioActual().toLowerCase().contains(query.toLowerCase()))
            )
            .toList();
    } else {
        dispositivos = deviceRepository.findAll();
    }

    model.addAttribute("dispositivos", dispositivos);
    model.addAttribute("totalDispositivos", dispositivos.size());

    long asignados = dispositivos.stream()
        .filter(d -> d.getUsuarioActual() != null && !d.getUsuarioActual().isBlank())
        .count();

    long disponibles = dispositivos.stream()
        .filter(d -> d.getUsuarioActual() == null || d.getUsuarioActual().isBlank())
        .count();

    long bajoStock = dispositivos.stream()
        .filter(d -> d.getDescripcion() != null &&
                     d.getDescripcion().toLowerCase().contains("bajo stock"))
        .count();

    model.addAttribute("totalAsignados", asignados);
    model.addAttribute("totalDisponibles", disponibles);
    model.addAttribute("totalBajoStock", bajoStock);

    return "devices";
}

    @GetMapping("/dispositivos")
    public String dipositivos() {
        return "dispositivos";
    }

    @GetMapping("/miembros")
    public String miembros() {
        return "miembros";
    }

    @GetMapping("/nuevo-dispositivo")
    public String nuevoDispositivo(Model model) {
        model.addAttribute("device", new Device());
        return "nuevo-dispositivo";
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

    @GetMapping("/eliminar-dispositivo/{id}")
    public String eliminarDispositivo(@PathVariable Long id) {
        deviceRepository.deleteById(id);
        return "redirect:/home/devices";
    }

    @GetMapping("/dispositivo/{id}")
    public String verDetalleDispositivo(@PathVariable Long id, Model model) {
    Device device = deviceRepository.findById(id).orElse(null);
    model.addAttribute("device", device);
    return "detalle-dispositivo";
}
}
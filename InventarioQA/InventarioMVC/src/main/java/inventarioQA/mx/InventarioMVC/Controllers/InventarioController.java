package inventarioQA.mx.InventarioMVC.Controllers;

import inventarioQA.mx.InventarioMVC.Models.Device;
import inventarioQA.mx.InventarioMVC.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.*;

@Controller
@RequestMapping("/home/")
public class InventarioController {

    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/Principal/")
    public String Principal() {
        return "Principal";
    }

    @GetMapping("/Pruebas/")
    public String Pruebas() {
        return "Pruebas";
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
                    .filter(d -> (d.getProducto() != null
                            && d.getProducto().toLowerCase().contains(query.toLowerCase())) ||
                            (d.getMarca() != null && d.getMarca().toLowerCase().contains(query.toLowerCase())) ||
                            (d.getModelo() != null && d.getModelo().toLowerCase().contains(query.toLowerCase())) ||
                            (d.getUsuarioActual() != null
                                    && d.getUsuarioActual().toLowerCase().contains(query.toLowerCase())))
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

        Map<String, String> imagenes = new LinkedHashMap<>();
        imagenes.put("fotofrente", "Foto frontal del dispositivo");
        imagenes.put("fotoreverso", "Foto reverso del dispositivo");
        imagenes.put("fotoencendido", "Foto del dispositivo encendido");
        imagenes.put("foto1", "Foto 1");
        imagenes.put("foto2", "Foto 2");
        imagenes.put("foto3", "Foto 3");
        imagenes.put("foto4", "Foto 4");
        imagenes.put("foto5", "Foto 5");
        imagenes.put("foto6", "Foto 6");
        imagenes.put("fotoserie", "Foto del número de serie");
        imagenes.put("cartaentrega", "Carta de entrega");

        model.addAttribute("imagenes", imagenes);

        return "detalle-dispositivo";
    }

    @GetMapping("/dispositivos/base")
    public String mostrarDispositivosPorCategoria(@RequestParam(value = "categoria", required = false) String categoria, Model model) {
        List<Device> dispositivos;

        if (categoria != null && !categoria.trim().isEmpty()) {
            String categoriaLower = categoria.toLowerCase();

            if (categoriaLower.equals("android")) {
                dispositivos = deviceRepository.findAll().stream()
                        .filter(d -> d.getMarca() != null && (
                                d.getMarca().equalsIgnoreCase("Motorola") ||
                                        d.getMarca().equalsIgnoreCase("Xiaomi") ||
                                        d.getMarca().equalsIgnoreCase("Huawei") ||
                                        d.getMarca().equalsIgnoreCase("Samsung") ||
                                        d.getMarca().equalsIgnoreCase("LG") ||
                                        d.getMarca().equalsIgnoreCase("ZTE") ||
                                        d.getMarca().equalsIgnoreCase("Nokia") ||
                                        d.getMarca().equalsIgnoreCase("OnePlus") ||
                                        d.getMarca().equalsIgnoreCase("Nothing") ||
                                        d.getMarca().equalsIgnoreCase("Lanix")
                        ) && d.getProducto() != null && !d.getProducto().equalsIgnoreCase("Monitor"))
                        .toList();

            } else if (categoriaLower.equals("ios") || categoriaLower.equals("iphone")) {
                dispositivos = deviceRepository.findAll().stream()
                        .filter(d -> d.getMarca() != null &&
                                d.getMarca().equalsIgnoreCase("Apple") &&
                                d.getProducto() != null &&
                                !d.getProducto().equalsIgnoreCase("iPad") &&
                                !d.getProducto().equalsIgnoreCase("MacBook") &&
                                !d.getProducto().equalsIgnoreCase("Macbook"))
                        .toList();

            } else {
                dispositivos = deviceRepository.findAll().stream()
                        .filter(d -> d.getProducto() != null &&
                                d.getProducto().equalsIgnoreCase(categoria))
                        .toList();
            }
        } else {
            dispositivos = deviceRepository.findAll();
        }

        model.addAttribute("categoriaSeleccionada", categoria);
        model.addAttribute("dispositivos", dispositivos);
        model.addAttribute("totalDispositivos", dispositivos.size());

        return "base";
    }

    @GetMapping("/dispositivos/usuario")
    public String mostrarDispositivosPorUsuario(@RequestParam("usuario") String usuario, Model model) {
        String[] palabrasClave = normalizar(usuario).split("\\s+");

        List<Device> dispositivos = deviceRepository.findAll().stream()
                .filter(d -> {
                    String nombreUsuario = normalizar(d.getUsuarioActual());
                    return Arrays.stream(palabrasClave).allMatch(nombreUsuario::contains);
                })
                .toList();

        model.addAttribute("categoriaSeleccionada", usuario);
        model.addAttribute("dispositivos", dispositivos);
        model.addAttribute("totalDispositivos", dispositivos.size());

        return "base";
    }

    private String normalizar(String texto) {
        if (texto == null) return "";
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase()
                .trim();
    }

    @GetMapping("/device/image/{id}/{campo}")
    public ResponseEntity<byte[]> getDeviceImage(@PathVariable Long id, @PathVariable String campo) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] imagen = switch (campo.toLowerCase()) {
            case "fotofrente" -> device.getFotoFrente();
            case "fotoreverso" -> device.getFotoReverso();
            case "fotoencendido" -> device.getFotoEncendido();
            case "foto1" -> device.getFoto1();
            case "foto2" -> device.getFoto2();
            case "foto3" -> device.getFoto3();
            case "foto4" -> device.getFoto4();
            case "foto5" -> device.getFoto5();
            case "foto6" -> device.getFoto6();
            case "fotoserie" -> device.getFotoSerie();
            case "cartaentrega" -> device.getCartaEntrega();
            default -> null;
        };

        if (imagen == null || imagen.length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}

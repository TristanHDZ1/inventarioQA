package inventarioQA.mx.InventarioMVC.Service;

import inventarioQA.mx.InventarioMVC.Models.Device;
import inventarioQA.mx.InventarioMVC.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ImageDeviceLoader {

    @Autowired
    private DeviceRepository deviceRepository;

    public void loadImagesFromDirectory(String directoryPath) throws IOException {
        Path imageDir = Paths.get(directoryPath);

        if (!Files.exists(imageDir) || !Files.isDirectory(imageDir)) {
            System.err.println("Directorio de imágenes no válido o no existe: " + imageDir.toAbsolutePath());
            return;
        }

        try {
            Files.walk(imageDir)
                .filter(Files::isRegularFile)
                .filter(path -> !path.getFileName().toString().equals(".DS_Store")) // Ignora archivos basura
                .forEach(imagePath -> {
                    try {
                        String filename = imagePath.getFileName().toString();

                        String[] partes = filename.split("_", 3);
                        if (partes.length < 3) {
                            System.out.println("Nombre de archivo inválido (esperado: CAMPO_USUARIO_PRODUCTO.ext): " + filename);
                            return;
                        }

                        String campo = partes[0].toLowerCase();
                        String usuarioYProductoRaw = partes[1] + "_" + partes[2];
                        usuarioYProductoRaw = usuarioYProductoRaw.replaceAll("\\.(jpg|jpeg|png)$", "");

                        String claveArchivo = normalizar(usuarioYProductoRaw);

                        List<Device> dispositivos = deviceRepository.findAll();
                        boolean asignado = false;

                        for (Device device : dispositivos) {
                            String claveDevice = normalizar(device.getUsuarioActual() + " " + device.getProducto());

                            String[] palabrasClave = claveArchivo.split(" ");
                            boolean match = true;
                            for (String palabra : palabrasClave) {
                                if (!claveDevice.contains(palabra)) {
                                    match = false;
                                    break;
                                }
                            }

                            if (match) {
                                byte[] imageBytes = Files.readAllBytes(imagePath);
                                asignarImagen(device, campo, imageBytes);
                                deviceRepository.save(device);
                                System.out.println("Imagen '" + filename + "' asignada a: " + device.getUsuarioActual() + " " + device.getProducto() + " (Campo: " + campo + ")");
                                asignado = true;
                                break; // salimos del loop porque ya encontró un buen match
                            }
                        }

                        if (!asignado) {
                            System.out.println("No se encontró dispositivo para: '" + usuarioYProductoRaw + "' (Archivo: " + filename + ")");
                        }

                    } catch (IOException e) {
                        System.err.println("Error al procesar imagen: " + imagePath + " → " + e.getMessage());
                    }
                });
        } catch (IOException e) {
            System.err.println("Error al recorrer imágenes en subcarpetas: " + e.getMessage());
        }
    }

    private void asignarImagen(Device device, String campo, byte[] bytes) {
        switch (campo) {
            case "fotofrente" -> device.setFotoFrente(bytes);
            case "fotoreverso" -> device.setFotoReverso(bytes);
            case "fotoencendido" -> device.setFotoEncendido(bytes);
            case "foto1" -> device.setFoto1(bytes);
            case "foto2" -> device.setFoto2(bytes);
            case "foto3" -> device.setFoto3(bytes);
            case "foto4" -> device.setFoto4(bytes);
            case "foto5" -> device.setFoto5(bytes);
            case "foto6" -> device.setFoto6(bytes);
            case "cartaentrega" -> device.setCartaEntrega(bytes);
            case "fotoserie" -> device.setFotoSerie(bytes);
            default -> System.out.println("Campo de imagen desconocido: " + campo);
        }
    }

    private String normalizar(String texto) {
        if (texto == null) return "";
        return texto
                .toLowerCase()
                .replace("_", " ")
                .replaceAll("\\s+", " ")
                .replaceAll("[^\\p{L}\\p{N} ]", "")
                .trim();
    }
}

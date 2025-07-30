package inventarioQA.mx.InventarioMVC.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import inventarioQA.mx.InventarioMVC.Models.Device;
import inventarioQA.mx.InventarioMVC.Models.DeviceCSV;
import inventarioQA.mx.InventarioMVC.Repository.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CsvDeviceLoader {

    @Autowired
    private ImageDeviceLoader imageDeviceLoader;

    @Bean
    CommandLineRunner loadDevices(DeviceRepository deviceRepository) {
        return args -> {
            boolean dbVacia = deviceRepository.count() == 0;

            if (dbVacia) {
                System.out.println("Base de datos vacía. Iniciando carga de dispositivos desde CSV...");

                try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("dispositivos.csv")) {
                    if (inputStream == null) {
                        throw new IllegalStateException("Archivo dispositivos.csv no encontrado en el classpath. Asegúrate de que esté en src/main/resources.");
                    }

                    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                    List<DeviceCSV> dispositivosCSV = new CsvToBeanBuilder<DeviceCSV>(reader)
                            .withType(DeviceCSV.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build()
                            .parse();

                    List<Device> dispositivos = new ArrayList<>();

                    for (DeviceCSV csv : dispositivosCSV) {
                        Device d = new Device();
                        try {
                            d.setNumero(csv.getNumero() != null && !csv.getNumero().isBlank()
                                    ? Integer.parseInt(csv.getNumero().split("\\.")[0].trim())
                                    : null);
                        } catch (NumberFormatException e) {
                            d.setNumero(null);
                            System.err.println("Error al parsear el número de dispositivo '" + csv.getNumero() + "'. Se establecerá en null.");
                        }

                        d.setFactura(csv.getFactura());
                        d.setCecoOperativo(csv.getCecoOperativo());
                        d.setSoc(csv.getSoc());
                        d.setDm(csv.getDm());
                        d.setPlacaActivoFijo("SI".equalsIgnoreCase(csv.getPlacaActivoFijo()));
                        d.setAsignadoNombre(csv.getAsignadoNombre());
                        d.setNumeroPlacaActivoFijo(csv.getNumeroPlacaActivoFijo());
                        d.setAsignadoEnActivoFijo("SI".equalsIgnoreCase(csv.getAsignadoEnActivoFijo()));
                        d.setProducto(csv.getProducto());
                        d.setMarca(csv.getMarca());
                        d.setModelo(csv.getModelo());
                        d.setSerie(csv.getSerie());
                        d.setImei(csv.getImei());
                        d.setCamaraFrontalMP(csv.getCamaraFrontalMP());
                        d.setCamaraTraseraMP(csv.getCamaraTraseraMP());
                        d.setRam(csv.getRam());
                        d.setAlmacenamiento(csv.getAlmacenamiento());
                        d.setSistemaOperativo(csv.getSistemaOperativo());
                        d.setNumeroTelefono(csv.getNumeroTelefono());
                        d.setDescripcion(csv.getDescripcion());
                        d.setAccesorios(csv.getAccesorios());
                        d.setFolioCuboCargador(csv.getFolioCuboCargador());

                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                            d.setFechaPosesion(LocalDate.parse(csv.getFechaPosesion(), formatter));
                        } catch (Exception e) {
                            d.setFechaPosesion(null);
                            System.err.println("Error al parsear la fecha de posesión '" + csv.getFechaPosesion() + "'. Se establecerá en null.");
                        }

                        d.setEntregadoPor(csv.getEntregadoPor());
                        d.setDepartamentoResponsable(csv.getDepartamentoResponsable());
                        d.setUsuarioActual(csv.getUsuarioActual());
                        d.setAsignacionActual(csv.getAsignacionActual());

                        dispositivos.add(d);
                    }

                    deviceRepository.saveAll(dispositivos);
                    System.out.println("CSV cargado correctamente. Se guardaron " + dispositivos.size() + " dispositivos.");

                    System.out.println("Claves esperadas para los nombres de imagen:");
                    dispositivos.forEach(d -> {
                        System.out.println(d.getUsuarioActual() + " " + d.getProducto());
                    });

                } catch (Exception e) {
                    System.err.println("Error al cargar el CSV o convertir datos: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("La base de datos ya contiene dispositivos. No se recargará el CSV.");
            }

            try {
                System.out.println("Intentando cargar imágenes desde 'Fotos_Inventario'...");
                imageDeviceLoader.loadImagesFromDirectory("Fotos_Inventario");
                System.out.println("Proceso de carga de imágenes finalizado.");
            } catch (IOException e) {
                System.err.println("Error al cargar imágenes desde el directorio: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}

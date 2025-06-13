package inventarioQA.mx.InventarioMVC.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import inventarioQA.mx.InventarioMVC.Models.Device;
import inventarioQA.mx.InventarioMVC.Models.DeviceCSV;
import inventarioQA.mx.InventarioMVC.Repository.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class CsvDeviceLoader {

    @Bean
    CommandLineRunner loadDevices(DeviceRepository deviceRepository) {
        return args -> {
            if (deviceRepository.count() == 0) {
                try {
                    // ✅ Ruta corregida: busca en src/main/resources/
                    InputStream inputStream = getClass().getResourceAsStream("/dispositivos.csv");

                    if (inputStream == null) {
                        throw new IllegalStateException("❌ Archivo dispositivos.csv no encontrado en /resources/");
                    }

                    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

                    List<DeviceCSV> dispositivosCSV = new CsvToBeanBuilder<DeviceCSV>(reader)
                            .withType(DeviceCSV.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build()
                            .parse();

                    List<Device> dispositivos = dispositivosCSV.stream().map(csv -> {
                        Device d = new Device();

                        // ✅ Conversión segura de String decimal a Integer
                        try {
                            d.setNumero(csv.getNumero() != null && !csv.getNumero().isBlank()
                                    ? (int) Double.parseDouble(csv.getNumero())
                                    : null);
                        } catch (NumberFormatException e) {
                            d.setNumero(null);
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
                        }

                        d.setEntregadoPor(csv.getEntregadoPor());
                        d.setDepartamentoResponsable(csv.getDepartamentoResponsable());
                        d.setUsuarioActual(csv.getUsuarioActual());
                        d.setAsignacionActual(csv.getAsignacionActual());
                        return d;
                    }).collect(Collectors.toList());

                    deviceRepository.saveAll(dispositivos);
                    System.out.println("✔ CSV cargado correctamente desde /resources.");
                } catch (Exception e) {
                    System.out.println("❌ Error al cargar el CSV: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                System.out.println("🟡 Dispositivos ya existentes en la base de datos.");
            }
        };
    }
}

package inventarioQA.mx.InventarioMVC.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import inventarioQA.mx.InventarioMVC.Models.Device;
import inventarioQA.mx.InventarioMVC.Repository.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class CsvDeviceLoader {

    @Bean
    CommandLineRunner loadDevices(DeviceRepository deviceRepository) {
        return args -> {
            if (deviceRepository.count() == 0) {
                try (InputStreamReader reader = new InputStreamReader(
                        getClass().getResourceAsStream("/dispositivos.csv"),
                        StandardCharsets.UTF_8)) {

                    List<Device> dispositivos = new CsvToBeanBuilder<Device>(reader)
                            .withType(Device.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build()
                            .parse();

                    deviceRepository.saveAll(dispositivos);
                    System.out.println("✔ CSV cargado correctamente.");

                } catch (Exception e) {
                    System.out.println("❌ Error al cargar el CSV: " + e.getMessage());
                }
            } else {
                System.out.println("🟡 Dispositivos ya existentes en la base de datos.");
            }
        };
    }
}

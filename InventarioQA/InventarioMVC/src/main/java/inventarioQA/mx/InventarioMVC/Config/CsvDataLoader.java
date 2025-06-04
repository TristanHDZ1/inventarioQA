package inventarioQA.mx.InventarioMVC.Config;



import com.opencsv.bean.CsvToBeanBuilder;
import inventarioQA.mx.InventarioMVC.Models.Device;
import inventarioQA.mx.InventarioMVC.Repository.DeviceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;
import java.util.List;

@Configuration
public class CsvDataLoader {

    @Bean
    CommandLineRunner loadCsv(DeviceRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                InputStreamReader reader = new InputStreamReader(
                        getClass().getResourceAsStream("/static/dispositivos.csv")
                );

                List<Device> devices = new CsvToBeanBuilder<Device>(reader)
                        .withType(Device.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build()
                        .parse();

                repository.saveAll(devices);
                System.out.println("✔ Datos cargados desde CSV.");
            } else {
                System.out.println("-----Dispositivos ya existentes en la base de datos.");
            }
        };
    }
}

package inventarioQA.mx.InventarioMVC.Repository;





import inventarioQA.mx.InventarioMVC.Models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    
}


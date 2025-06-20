package inventarioQA.mx.InventarioMVC.Repository;

import inventarioQA.mx.InventarioMVC.Models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT d FROM Device d WHERE " +
            "LOWER(CAST(d.numeroPlacaActivoFijo AS string)) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.producto) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.marca) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.modelo) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(d.usuarioActual) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Device> buscarPorCualquierCampo(@Param("query") String query);
}
package inventarioQA.mx.InventarioMVC.Models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo; // iPhone, Android, Laptop, etc.
    private String modelo;
    private String numeroSerie;
    private String detalles;
    @ManyToOne
    @JoinColumn(name = "team_member_id")
    private TeamMember asignadoA; // La persona que tiene el dispositivo
}

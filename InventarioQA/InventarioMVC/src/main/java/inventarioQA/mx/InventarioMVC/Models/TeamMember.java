package inventarioQA.mx.InventarioMVC.Models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "team_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String puesto;
    // Relación con Device
    @OneToMany(mappedBy = "asignadoA")
    private List<Device> dispositivos;
}
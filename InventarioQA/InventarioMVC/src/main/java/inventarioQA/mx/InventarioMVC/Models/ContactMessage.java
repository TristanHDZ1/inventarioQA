package inventarioQA.mx.InventarioMVC.Models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correo;
    private String mensaje;
}

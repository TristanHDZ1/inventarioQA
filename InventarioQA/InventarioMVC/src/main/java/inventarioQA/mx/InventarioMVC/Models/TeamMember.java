package inventarioQA.mx.InventarioMVC.Models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public List<Device> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Device> dispositivos) {
        this.dispositivos = dispositivos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(correo, that.correo) && Objects.equals(puesto, that.puesto) && Objects.equals(dispositivos, that.dispositivos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, correo, puesto, dispositivos);
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", puesto='" + puesto + '\'' +
                ", dispositivos=" + dispositivos +
                '}';
    }
}
package inventarioQA.mx.InventarioMVC.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "No.")
    private Integer numero;

    @Column(name = "Factura")
    private String factura;

    @Column(name = "CeCo Operativo")
    private String cecoOperativo;

    @Column(name = "Soc")
    private String soc;

    @Column(name = "DM")
    private String dm;

    @Column(name = "Placa de Activo Fijo?")
    private Boolean placaActivoFijo;

    @Column(name = "Asignados (nombre)")
    private String asignadoNombre;

    @Column(name = "# Placa de Activo Fijo")
    private String numeroPlacaActivoFijo;

    @Column(name = "Asignado en Activo Fijo?")
    private Boolean asignadoEnActivoFijo;

    @Column(name = "Producto")
    private String producto;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Modelo")
    private String modelo;

    @Column(name = "Serie")
    private String serie;

    @Column(name = "IMEI")
    private String imei;

    @Column(name = "Megapíxeles camara frontal ")
    private String camaraFrontalMP;

    @Column(name = "Megapíxeles camara trasera")
    private String camaraTraseraMP;

    @Column(name = "RAM")
    private String ram;

    @Column(name = "Almacenamiento")
    private String almacenamiento;

    @Column(name = "Sistema operativo")
    private String sistemaOperativo;

    @Column(name = "Núm de teléfono")
    private String numeroTelefono;

    @Column(name = "Descripción")
    private String descripcion;

    @Column(name = "Accesorios")
    private String accesorios;

    @Column(name = "Folio del cubo cargador")
    private String folioCuboCargador;

    @Column(name = "Fecha de posesión")
    private LocalDate fechaPosesion;

    @Column(name = "Nombre de quien lo entrego")
    private String entregadoPor;

    @Column(name = "Departamento Responsable")
    private String departamentoResponsable;

    @Column(name = "Usuario Actual")
    private String usuarioActual;

    @Lob
    @Column(name = "Carta de entrega", columnDefinition = "LONGBLOB")
    private byte[] cartaEntrega;

    @Column(name = "Foto IMEI")
    private String fotoImei;

    @Lob
    @Column(name = "Foto Serie", columnDefinition = "LONGBLOB")
    private byte[] fotoSerie;

    @Lob
    @Column(name = "Foto encendido", columnDefinition = "LONGBLOB")
    private byte[] fotoEncendido;

    @Lob
    @Column(name = "Foto Frente", columnDefinition = "LONGBLOB")
    private byte[] fotoFrente;

    @Lob
    @Column(name = "Foto Reverso ", columnDefinition = "LONGBLOB")
    private byte[] fotoReverso;

    @Column(name = "Foto Reverso con protector")
    private String fotoReversoConProtector;

    @Lob
    @Column(name = "Foto", columnDefinition = "LONGBLOB")
    private byte[] foto1;

    @Lob
    @Column(name = "Foto.1", columnDefinition = "LONGBLOB")
    private byte[] foto2;

    @Lob
    @Column(name = "Foto.2", columnDefinition = "LONGBLOB")
    private byte[] foto3;

    @Lob
    @Column(name = "Foto.3", columnDefinition = "LONGBLOB")
    private byte[] foto4;

    @Lob
    @Column(name = "Foto.4", columnDefinition = "LONGBLOB")
    private byte[] foto5;

    @Lob
    @Column(name = "Foto.5", columnDefinition = "LONGBLOB")
    private byte[] foto6;

    @Lob
    @Column(name = "Foto.6", columnDefinition = "LONGBLOB")
    private byte[] foto7;

    @Column(name = "Asignación Actual.")
    private String asignacionActual;

    @ManyToOne
    @JoinColumn(name = "team_member_id")
    private TeamMember asignadoA;
}

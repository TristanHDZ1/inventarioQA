package inventarioQA.mx.InventarioMVC.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroFactura;
    private String factura;
    private String cecoOperativo;
    private String soc;
    private String dm;
    private Boolean tienePlacaActivoFijo;
    private String asignadoNombre;
    private String numeroPlacaActivoFijo;
    private Boolean asignadoEnActivoFijo;

    //Informacion general
    private String producto;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String imei;
    private String camaraFrontalMP;
    private String camaraTraseraMP;
    private String ram;
    private String almacenamiento;
    private String sistemaOperativo;
    private String numeroTelefono;
    private String detalles;
    private String accesorios;
    private String folioCuboCargador;

    //Dates
    private LocalDate fechaPosesion;
    private String entregadoPor;
    private String departamentoResponsable;
    private String usuarioActual;
    private String cartaEntrega;

    //Fotos
    private String fotoImei;
    private String fotoSerie;
    private String fotoEncendido;
    private String fotoFrente;
    private String fotoReverso;
    private String asignacionActual;

    @ManyToOne
    @JoinColumn(name = "team_member_id")
    private TeamMember asignadoA;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getCecoOperativo() {
        return cecoOperativo;
    }

    public void setCecoOperativo(String cecoOperativo) {
        this.cecoOperativo = cecoOperativo;
    }

    public String getSoc() {
        return soc;
    }

    public void setSoc(String soc) {
        this.soc = soc;
    }

    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    public Boolean getTienePlacaActivoFijo() {
        return tienePlacaActivoFijo;
    }

    public void setTienePlacaActivoFijo(Boolean tienePlacaActivoFijo) {
        this.tienePlacaActivoFijo = tienePlacaActivoFijo;
    }

    public String getAsignadoNombre() {
        return asignadoNombre;
    }

    public void setAsignadoNombre(String asignadoNombre) {
        this.asignadoNombre = asignadoNombre;
    }

    public String getNumeroPlacaActivoFijo() {
        return numeroPlacaActivoFijo;
    }

    public void setNumeroPlacaActivoFijo(String numeroPlacaActivoFijo) {
        this.numeroPlacaActivoFijo = numeroPlacaActivoFijo;
    }

    public Boolean getAsignadoEnActivoFijo() {
        return asignadoEnActivoFijo;
    }

    public void setAsignadoEnActivoFijo(Boolean asignadoEnActivoFijo) {
        this.asignadoEnActivoFijo = asignadoEnActivoFijo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCamaraFrontalMP() {
        return camaraFrontalMP;
    }

    public void setCamaraFrontalMP(String camaraFrontalMP) {
        this.camaraFrontalMP = camaraFrontalMP;
    }

    public String getCamaraTraseraMP() {
        return camaraTraseraMP;
    }

    public void setCamaraTraseraMP(String camaraTraseraMP) {
        this.camaraTraseraMP = camaraTraseraMP;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getFolioCuboCargador() {
        return folioCuboCargador;
    }

    public void setFolioCuboCargador(String folioCuboCargador) {
        this.folioCuboCargador = folioCuboCargador;
    }

    public LocalDate getFechaPosesion() {
        return fechaPosesion;
    }

    public void setFechaPosesion(LocalDate fechaPosesion) {
        this.fechaPosesion = fechaPosesion;
    }

    public String getEntregadoPor() {
        return entregadoPor;
    }

    public void setEntregadoPor(String entregadoPor) {
        this.entregadoPor = entregadoPor;
    }

    public String getDepartamentoResponsable() {
        return departamentoResponsable;
    }

    public void setDepartamentoResponsable(String departamentoResponsable) {
        this.departamentoResponsable = departamentoResponsable;
    }

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public void setUsuarioActual(String usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public String getCartaEntrega() {
        return cartaEntrega;
    }

    public void setCartaEntrega(String cartaEntrega) {
        this.cartaEntrega = cartaEntrega;
    }

    public String getFotoImei() {
        return fotoImei;
    }

    public void setFotoImei(String fotoImei) {
        this.fotoImei = fotoImei;
    }

    public String getFotoSerie() {
        return fotoSerie;
    }

    public void setFotoSerie(String fotoSerie) {
        this.fotoSerie = fotoSerie;
    }

    public String getFotoEncendido() {
        return fotoEncendido;
    }

    public void setFotoEncendido(String fotoEncendido) {
        this.fotoEncendido = fotoEncendido;
    }

    public String getFotoFrente() {
        return fotoFrente;
    }

    public void setFotoFrente(String fotoFrente) {
        this.fotoFrente = fotoFrente;
    }

    public String getFotoReverso() {
        return fotoReverso;
    }

    public void setFotoReverso(String fotoReverso) {
        this.fotoReverso = fotoReverso;
    }

    public String getAsignacionActual() {
        return asignacionActual;
    }

    public void setAsignacionActual(String asignacionActual) {
        this.asignacionActual = asignacionActual;
    }

    public TeamMember getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(TeamMember asignadoA) {
        this.asignadoA = asignadoA;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id) && Objects.equals(numeroFactura, device.numeroFactura) && Objects.equals(factura, device.factura) && Objects.equals(cecoOperativo, device.cecoOperativo) && Objects.equals(soc, device.soc) && Objects.equals(dm, device.dm) && Objects.equals(tienePlacaActivoFijo, device.tienePlacaActivoFijo) && Objects.equals(asignadoNombre, device.asignadoNombre) && Objects.equals(numeroPlacaActivoFijo, device.numeroPlacaActivoFijo) && Objects.equals(asignadoEnActivoFijo, device.asignadoEnActivoFijo) && Objects.equals(producto, device.producto) && Objects.equals(marca, device.marca) && Objects.equals(modelo, device.modelo) && Objects.equals(numeroSerie, device.numeroSerie) && Objects.equals(imei, device.imei) && Objects.equals(camaraFrontalMP, device.camaraFrontalMP) && Objects.equals(camaraTraseraMP, device.camaraTraseraMP) && Objects.equals(ram, device.ram) && Objects.equals(almacenamiento, device.almacenamiento) && Objects.equals(sistemaOperativo, device.sistemaOperativo) && Objects.equals(numeroTelefono, device.numeroTelefono) && Objects.equals(detalles, device.detalles) && Objects.equals(accesorios, device.accesorios) && Objects.equals(folioCuboCargador, device.folioCuboCargador) && Objects.equals(fechaPosesion, device.fechaPosesion) && Objects.equals(entregadoPor, device.entregadoPor) && Objects.equals(departamentoResponsable, device.departamentoResponsable) && Objects.equals(usuarioActual, device.usuarioActual) && Objects.equals(cartaEntrega, device.cartaEntrega) && Objects.equals(fotoImei, device.fotoImei) && Objects.equals(fotoSerie, device.fotoSerie) && Objects.equals(fotoEncendido, device.fotoEncendido) && Objects.equals(fotoFrente, device.fotoFrente) && Objects.equals(fotoReverso, device.fotoReverso) && Objects.equals(asignacionActual, device.asignacionActual) && Objects.equals(asignadoA, device.asignadoA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroFactura, factura, cecoOperativo, soc, dm, tienePlacaActivoFijo, asignadoNombre, numeroPlacaActivoFijo, asignadoEnActivoFijo, producto, marca, modelo, numeroSerie, imei, camaraFrontalMP, camaraTraseraMP, ram, almacenamiento, sistemaOperativo, numeroTelefono, detalles, accesorios, folioCuboCargador, fechaPosesion, entregadoPor, departamentoResponsable, usuarioActual, cartaEntrega, fotoImei, fotoSerie, fotoEncendido, fotoFrente, fotoReverso, asignacionActual, asignadoA);
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", numeroFactura='" + numeroFactura + '\'' +
                ", factura='" + factura + '\'' +
                ", cecoOperativo='" + cecoOperativo + '\'' +
                ", soc='" + soc + '\'' +
                ", dm='" + dm + '\'' +
                ", tienePlacaActivoFijo=" + tienePlacaActivoFijo +
                ", asignadoNombre='" + asignadoNombre + '\'' +
                ", numeroPlacaActivoFijo='" + numeroPlacaActivoFijo + '\'' +
                ", asignadoEnActivoFijo=" + asignadoEnActivoFijo +
                ", producto='" + producto + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", imei='" + imei + '\'' +
                ", camaraFrontalMP='" + camaraFrontalMP + '\'' +
                ", camaraTraseraMP='" + camaraTraseraMP + '\'' +
                ", ram='" + ram + '\'' +
                ", almacenamiento='" + almacenamiento + '\'' +
                ", sistemaOperativo='" + sistemaOperativo + '\'' +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", detalles='" + detalles + '\'' +
                ", accesorios='" + accesorios + '\'' +
                ", folioCuboCargador='" + folioCuboCargador + '\'' +
                ", fechaPosesion=" + fechaPosesion +
                ", entregadoPor='" + entregadoPor + '\'' +
                ", departamentoResponsable='" + departamentoResponsable + '\'' +
                ", usuarioActual='" + usuarioActual + '\'' +
                ", cartaEntrega='" + cartaEntrega + '\'' +
                ", fotoImei='" + fotoImei + '\'' +
                ", fotoSerie='" + fotoSerie + '\'' +
                ", fotoEncendido='" + fotoEncendido + '\'' +
                ", fotoFrente='" + fotoFrente + '\'' +
                ", fotoReverso='" + fotoReverso + '\'' +
                ", asignacionActual='" + asignacionActual + '\'' +
                ", asignadoA=" + asignadoA +
                '}';
    }
}

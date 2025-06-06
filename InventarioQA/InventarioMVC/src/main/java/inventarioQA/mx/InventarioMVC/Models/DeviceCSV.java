package inventarioQA.mx.InventarioMVC.Models;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class DeviceCSV {

    @CsvBindByName(column = "No.")
    private Integer numero;

    @CsvBindByName(column = "Factura")
    private String factura;

    @CsvBindByName(column = "CeCo Operativo")
    private String cecoOperativo;

    @CsvBindByName(column = "Soc")
    private String soc;

    @CsvBindByName(column = "DM")
    private String dm;

    @CsvBindByName(column = "Placa de Activo Fijo?")
    private String placaActivoFijo;

    @CsvBindByName(column = "Asignados (nombre)")
    private String asignadoNombre;

    @CsvBindByName(column = "# Placa de Activo Fijo")
    private String numeroPlacaActivoFijo;

    @CsvBindByName(column = "Asignado en Activo Fijo?")
    private String asignadoEnActivoFijo;

    @CsvBindByName(column = "Producto")
    private String producto;

    @CsvBindByName(column = "Marca")
    private String marca;

    @CsvBindByName(column = "Modelo")
    private String modelo;

    @CsvBindByName(column = "Serie")
    private String serie;

    @CsvBindByName(column = "IMEI")
    private String imei;

    @CsvBindByName(column = "Megapíxeles camara frontal ")
    private String camaraFrontalMP;

    @CsvBindByName(column = "Megapíxeles camara trasera")
    private String camaraTraseraMP;

    @CsvBindByName(column = "RAM")
    private String ram;

    @CsvBindByName(column = "Almacenamiento")
    private String almacenamiento;

    @CsvBindByName(column = "Sistema operativo")
    private String sistemaOperativo;

    @CsvBindByName(column = "Núm de teléfono")
    private String numeroTelefono;

    @CsvBindByName(column = "Descripción")
    private String descripcion;

    @CsvBindByName(column = "Accesorios")
    private String accesorios;

    @CsvBindByName(column = "Folio del cubo cargador")
    private String folioCuboCargador;

    @CsvBindByName(column = "Fecha de posesión")
    private String fechaPosesion;

    @CsvBindByName(column = "Nombre de quien lo entrego")
    private String entregadoPor;

    @CsvBindByName(column = "Departamento Responsable")
    private String departamentoResponsable;

    @CsvBindByName(column = "Usuario Actual")
    private String usuarioActual;

    @CsvBindByName(column = "Carta de entrega")
    private String cartaEntrega;

    @CsvBindByName(column = "Asignación Actual.")
    private String asignacionActual;
}
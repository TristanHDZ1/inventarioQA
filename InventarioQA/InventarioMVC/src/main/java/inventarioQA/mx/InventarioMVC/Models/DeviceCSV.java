package inventarioQA.mx.InventarioMVC.Models;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class DeviceCSV {

    @CsvBindByName(column = "No")
    private String numero;

    @CsvBindByName(column = "Factura")
    private String factura;

    @CsvBindByName(column = "CeCo_Operativo")
    private String cecoOperativo;

    @CsvBindByName(column = "Soc")
    private String soc;

    @CsvBindByName(column = "DM")
    private String dm;

    @CsvBindByName(column = "Placa_de_Activo_Fijo?")
    private String placaActivoFijo;

    @CsvBindByName(column = "Asignados_(nombre)")
    private String asignadoNombre;

    @CsvBindByName(column = "#_Placa_de_Activo_Fijo")
    private String numeroPlacaActivoFijo;

    @CsvBindByName(column = "Asignado_en_Activo_Fijo?")
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

    @CsvBindByName(column = "Megapíxeles_camara_frontal")
    private String camaraFrontalMP;

    @CsvBindByName(column = "Megapíxeles_camara_trasera")
    private String camaraTraseraMP;

    @CsvBindByName(column = "RAM")
    private String ram;

    @CsvBindByName(column = "Almacenamiento")
    private String almacenamiento;

    @CsvBindByName(column = "Sistema_operativo")
    private String sistemaOperativo;

    @CsvBindByName(column = "Núm_de_teléfono")
    private String numeroTelefono;

    @CsvBindByName(column = "Descripción")
    private String descripcion;

    @CsvBindByName(column = "Accesorios")
    private String accesorios;

    @CsvBindByName(column = "Folio_del_cubo_cargador")
    private String folioCuboCargador;

    @CsvBindByName(column = "Fecha_de_posesión")
    private String fechaPosesion;

    @CsvBindByName(column = "Nombre_de_quien_lo_entrego")
    private String entregadoPor;

    @CsvBindByName(column = "Departamento_Responsable")
    private String departamentoResponsable;

    @CsvBindByName(column = "Usuario_Actual")
    private String usuarioActual;

    @CsvBindByName(column = "Asignación_Actual")
    private String asignacionActual;
}

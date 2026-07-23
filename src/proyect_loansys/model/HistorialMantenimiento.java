package proyect_loansys.model;

/**
 *
 * @author Daniel
 */
public class HistorialMantenimiento {
    private int idMantenimiento;
    private String codigoMantenimiento;
    private int idElemento;           // FK real hacia elemento.id_elemento
    private int codigoElemento;       // solo lectura (viene del JOIN con elemento)
    private String nombreElemento;    // solo lectura (viene del JOIN con elemento)
    private String categoria;
    private String tipoMantenimiento;
    private String estadoElemento;
    private String fechaRealizada;
    private int idUsuario;
    private String descripcion;

    public HistorialMantenimiento() {
    }

    public HistorialMantenimiento(int idMantenimiento, String codigoMantenimiento, int idElemento,
            String categoria, String tipoMantenimiento, String estadoElemento,
            String fechaRealizada, int idUsuario, String descripcion) {
        this.idMantenimiento = idMantenimiento;
        this.codigoMantenimiento = codigoMantenimiento;
        this.idElemento = idElemento;
        this.categoria = categoria;
        this.tipoMantenimiento = tipoMantenimiento;
        this.estadoElemento = estadoElemento;
        this.fechaRealizada = fechaRealizada;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }
    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getCodigoMantenimiento() {
        return codigoMantenimiento;
    }
    public void setCodigoMantenimiento(String codigoMantenimiento) {
        this.codigoMantenimiento = codigoMantenimiento;
    }

    public int getIdElemento() {
        return idElemento;
    }
    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public int getCodigoElemento() {
        return codigoElemento;
    }
    public void setCodigoElemento(int codigoElemento) {
        this.codigoElemento = codigoElemento;
    }

    public String getNombreElemento() {
        return nombreElemento;
    }
    public void setNombreElemento(String nombreElemento) {
        this.nombreElemento = nombreElemento;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }
    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public String getEstadoElemento() {
        return estadoElemento;
    }
    public void setEstadoElemento(String estadoElemento) {
        this.estadoElemento = estadoElemento;
    }

    public String getFechaRealizada() {
        return fechaRealizada;
    }
    public void setFechaRealizada(String fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "HistorialMantenimiento{" + "idMantenimiento=" + idMantenimiento
                + ", codigoMantenimiento=" + codigoMantenimiento
                + ", idElemento=" + idElemento
                + ", codigoElemento=" + codigoElemento
                + ", nombreElemento=" + nombreElemento
                + ", categoria=" + categoria
                + ", tipoMantenimiento=" + tipoMantenimiento
                + ", estadoElemento=" + estadoElemento
                + ", fechaRealizada=" + fechaRealizada
                + ", idUsuario=" + idUsuario
                + ", descripcion=" + descripcion + '}';
    }
}
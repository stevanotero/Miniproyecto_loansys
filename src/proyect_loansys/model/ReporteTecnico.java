/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Santiago
 */
public class ReporteTecnico {
    private int idReporteTecnico;
    private String codigoReporte;
    private int idMantenimiento;
    private String fechaRealizado;

    public ReporteTecnico() {
    }

    public ReporteTecnico(int idReporteTecnico, String codigoReporte, int idMantenimiento, String fechaRealizado) {
        this.idReporteTecnico = idReporteTecnico;
        this.codigoReporte = codigoReporte;
        this.idMantenimiento = idMantenimiento;
        this.fechaRealizado = fechaRealizado;
    }

    public int getIdReporteTecnico() {
        return idReporteTecnico;
    }

    public void setIdReporteTecnico(int idReporteTecnico) {
        this.idReporteTecnico = idReporteTecnico;
    }

    public String getCodigoReporte() {
        return codigoReporte;
    }

    public void setCodigoReporte(String codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getFechaRealizado() {
        return fechaRealizado;
    }

    public void setFechaRealizado(String fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

    @Override
    public String toString() {
        return "ReporteTecnico{" + "idReporteTecnico=" + idReporteTecnico
                + ", codigoReporte=" + codigoReporte
                + ", idMantenimiento=" + idMantenimiento
                + ", fechaRealizado=" + fechaRealizado + '}';
    }
}
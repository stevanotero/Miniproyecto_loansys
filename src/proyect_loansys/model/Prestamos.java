/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Timestamp;

/**
 *
 * @author Alexis
 */
public class Prestamos {

    private int idPrestamo;
    private int idUsuario;
    private int idElemento;
    private Timestamp fechaInicioPrestamo;
    private Timestamp fechaFinDevolucion;
    private int idEstadoElemento;
    private int codigoElemento;
    private String nombreElemento;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String documentoUsuario;
    private String nombreEstadoElemento;

    public Prestamos() {
    }

    public Prestamos(int idPrestamo, int codigoElemento, String nombreElemento,
            String nombreUsuario, String apellidoUsuario, String documentoUsuario,
            Timestamp fechaInicioPrestamo, Timestamp fechaFinDevolucion, String nombreEstadoElemento) {
        this.idPrestamo = idPrestamo;
        this.codigoElemento = codigoElemento;
        this.nombreElemento = nombreElemento;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.fechaInicioPrestamo = fechaInicioPrestamo;
        this.fechaFinDevolucion = fechaFinDevolucion;
        this.nombreEstadoElemento = nombreEstadoElemento;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public Timestamp getFechaInicioPrestamo() {
        return fechaInicioPrestamo;
    }

    public void setFechaInicioPrestamo(Timestamp fechaInicioPrestamo) {
        this.fechaInicioPrestamo = fechaInicioPrestamo;
    }

    public Timestamp getFechaFinDevolucion() {
        return fechaFinDevolucion;
    }

    public void setFechaFinDevolucion(Timestamp fechaFinDevolucion) {
        this.fechaFinDevolucion = fechaFinDevolucion;
    }

    public int getIdEstadoElemento() {
        return idEstadoElemento;
    }

    public void setIdEstadoElemento(int idEstadoElemento) {
        this.idEstadoElemento = idEstadoElemento;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombreEstadoElemento() {
        return nombreEstadoElemento;
    }

    public void setNombreEstadoElemento(String nombreEstadoElemento) {
        this.nombreEstadoElemento = nombreEstadoElemento;
    }
}

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
public class UsuarioLogin {

    private int idLogin;
    private int idUsuario;
    private String nombreUsuario;
    private String correo;
    private Timestamp fechaRegistro;
    private int idEstado;
    private String nombreEstado; 
    private int idEstadoMora;
    private String descripcionMora;

    public UsuarioLogin() {
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public int getIdEstadoMora() {
        return idEstadoMora;
    }

    public void setIdEstadoMora(int idEstadoMora) {
        this.idEstadoMora = idEstadoMora;
    }

    public String getDescripcionMora() {
        return descripcionMora;
    }

    public void setDescripcionMora(String descripcionMora) {
        this.descripcionMora = descripcionMora;
    }
}

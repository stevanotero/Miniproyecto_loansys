/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author juans
 */
public class Administrador_Usuario {

    private int idUsuario;
    private int documento;
    private String nombre;
    private String apellido;
    private int idRol;
    private int idEstado;
    private int idTipoDocumento;
    private String nombreRol;
    private String nombreEstado;
    private String correo;
    private String contraseña;
    public Administrador_Usuario() {
    }

    public Administrador_Usuario(int idUsuario, int documento, String nombre, String apellido, int idRol, int idEstado, int idTipoDocumento, String nombreRol, String nombreEstado, String correo, String contraseña) {
        this.idUsuario = idUsuario;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idRol = idRol;
        this.idEstado = idEstado;
        this.idTipoDocumento = idTipoDocumento;
        this.nombreRol = nombreRol;
        this.nombreEstado = nombreEstado;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", documento=" + documento + ", nombre=" + nombre + ", apellido=" + apellido + ", idRol=" + idRol + ", idEstado=" + idEstado + ", idTipoDocumento=" + idTipoDocumento + ", nombreRol=" + nombreRol + ", nombreEstado=" + nombreEstado + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
    }

  
}

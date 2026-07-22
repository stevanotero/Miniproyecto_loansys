/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class Usuario_Solicitud {
    
    private int id_solicitud;
    private int id_usuario;
    private String nombre;
    private String apellido;
    private int documento;
    private int id_elemento;
    private Timestamp fecha_envio;

    public Usuario_Solicitud() {
        
        
    }

    public int getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(int id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getId_elemento() {
        return id_elemento;
    }

    public void setId_elemento(int id_elemento) {
        this.id_elemento = id_elemento;
    }

    public Timestamp getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Timestamp fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "id_solicitud=" + id_solicitud + ", id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", documento=" + documento + ", id_elemento=" + id_elemento + ", fecha_envio=" + fecha_envio + '}';
    }
    
    
}


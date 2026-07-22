/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 * @author Alexis
 */
public class Notificaciones {
    private int idNotificacion;
    private int idTipoNotificacion;
    private String mensaje;
    private int idLogin; 
    private String nombreTipoNotificacion; 

    public Notificaciones() {
    }
    
    public Notificaciones(int idTipoNotificacion, String mensaje, int idLogin) {
        this.idTipoNotificacion = idTipoNotificacion;
        this.mensaje = mensaje;
        this.idLogin = idLogin;
    }

    public int getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public int getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(int idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public String getNombreTipoNotificacion() {
        return nombreTipoNotificacion;
    }

    public void setNombreTipoNotificacion(String nombreTipoNotificacion) {
        this.nombreTipoNotificacion = nombreTipoNotificacion;
    }
}
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
    private int idRemitente;         
    private String nombreRemitente;  
    private String rolRemitente;    
    private int idLogin;             
    private String nombreTipoNotificacion;
    private int idEstadoLectura = 2; 
    private String nombreEstadoLectura; 

    public Notificaciones() {
    }

  
    public Notificaciones(int idTipoNotificacion, String mensaje, int idRemitente, int idLogin) {
        this.idTipoNotificacion = idTipoNotificacion;
        this.mensaje = mensaje;
        this.idRemitente = idRemitente;
        this.idLogin = idLogin;
        this.idEstadoLectura = 2; 
    }

  
    public Notificaciones(int idTipoNotificacion, String mensaje, int idLogin) {
        this.idTipoNotificacion = idTipoNotificacion;
        this.mensaje = mensaje;
        this.idLogin = idLogin;
        this.idEstadoLectura = 2;
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

    public int getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(int idRemitente) {
        this.idRemitente = idRemitente;
    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public String getRolRemitente() {
        return rolRemitente;
    }

    public void setRolRemitente(String rolRemitente) {
        this.rolRemitente = rolRemitente;
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

    public int getIdEstadoLectura() {
        return idEstadoLectura;
    }

    public void setIdEstadoLectura(int idEstadoLectura) {
        this.idEstadoLectura = idEstadoLectura;
    }

    public String getNombreEstadoLectura() {
        return nombreEstadoLectura;
    }

    public void setNombreEstadoLectura(String nombreEstadoLectura) {
        this.nombreEstadoLectura = nombreEstadoLectura;
    }
}
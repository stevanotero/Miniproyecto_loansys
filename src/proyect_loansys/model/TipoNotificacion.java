/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Alexis
 */
public class TipoNotificacion {

    private int idTipoNotificacion;
    private String nombreTipoNotificacion;

    public TipoNotificacion() {
    }

    public TipoNotificacion(int idTipoNotificacion, String nombreTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
        this.nombreTipoNotificacion = nombreTipoNotificacion;
    }

    public int getIdTipoNotificacion() {
        return idTipoNotificacion;
    }

    public void setIdTipoNotificacion(int idTipoNotificacion) {
        this.idTipoNotificacion = idTipoNotificacion;
    }

    public String getNombreTipoNotificacion() {
        return nombreTipoNotificacion;
    }

    public void setNombreTipoNotificacion(String nombreTipoNotificacion) {
        this.nombreTipoNotificacion = nombreTipoNotificacion;
    }

    @Override
    public String toString() {
        return nombreTipoNotificacion; // Texto que mostrará el JComboBox
    }
}

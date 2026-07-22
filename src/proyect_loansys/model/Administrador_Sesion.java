/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author juans
 */


public class Administrador_Sesion {

    private static int idUsuario;
    private static String nombreUsuario;
    private static String rolUsuario;

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        Administrador_Sesion.nombreUsuario = nombreUsuario;
    }

    public static String getRolUsuario() {
        return rolUsuario;
    }

    public static void setRolUsuario(String rolUsuario) {
        Administrador_Sesion.rolUsuario = rolUsuario;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        Administrador_Sesion.idUsuario = idUsuario;
    }

}
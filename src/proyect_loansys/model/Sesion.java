/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Alexis
 */
public class Sesion {

    private static int idLogin;
    private static int idRol;

    public static int getIdLogin() {
        return idLogin;
    }

    public static void setIdLogin(int idLogin) {
        Sesion.idLogin = idLogin;
    }

    public static int getIdRol() {
        return idRol;
    }

    public static void setIdRol(int idRol) {
        Sesion.idRol = idRol;
    }
}

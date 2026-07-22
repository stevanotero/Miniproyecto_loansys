/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys;

import javax.swing.JFrame;
import proyect_loansys.controller.Controlador_Login;
import proyect_loansys.model.Conexion_Registro;
import proyect_loansys.view.Vista_Login;

public class Index {

    public static void main(String[] args) {
        Conexion_Registro con = new Conexion_Registro();
        con.getConection();
        Vista_Login view = new Vista_Login();
        Controlador_Login controller = new Controlador_Login(view);
        view.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

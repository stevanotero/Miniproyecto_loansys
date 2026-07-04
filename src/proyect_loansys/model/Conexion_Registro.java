/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Hewlett-Packard
 */
public class Conexion_Registro {
     Connection con;
    
    String url = "jdbc:mysql://localhost:3306/sistema_prestamos_sena";
    String user = "root";
    String pass = "";
    
    public Connection getConection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
            System.out.println("exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.toString(),"Base de Datos Apagada"+e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}

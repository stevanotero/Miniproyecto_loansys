/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import proyect_loansys.model.Crud_nuevaContraseña;
import proyect_loansys.model.Conexion_Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexis
 */

 public class PersonaDao_nuevaContraseña implements Crud_nuevaContraseña<Persona_nuevaContraseña> {
     
    private Conexion_Registro conectar = new Conexion_Registro();
    private Connection con;
    private PreparedStatement ps;

    @Override
    public int setActualizar(Persona_nuevaContraseña p) {
        //Esto es lo que se actualiza en sql la contraseña donde se establezca el correo
        String sql = "UPDATE login_de_usuarios SET contraseña = ? WHERE correo = ?";
        
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);    
            //Se extrae la contraseña que el usuario ingreso
            ps.setString(1, p.getContraseña());        
            // Se extrae el correo que se establecio en el where
            ps.setString(2, p.getCorreo());
            int filasModificadas = ps.executeUpdate();
            if (filasModificadas > 0) {
                return 1;//Se actualizó la contraseña correctamente
            } else {
                return 0; 
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización: " + e.getMessage(),
                    "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos en el DAO: " + e.getMessage());
            }
        }
    }
}
   

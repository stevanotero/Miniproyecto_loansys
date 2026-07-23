/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import proyect_loansys.model.Conexion_Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import proyect_loansys.model.Crud;

/**
 *
 * @author Alexis
 */
public class PersonaDao_nuevaContraseña implements Crud<Persona_nuevaContraseña> {

    private Conexion_Registro conectar = new Conexion_Registro();
    private Connection con;
    private PreparedStatement ps;

    @Override
    public boolean setActualizar(Persona_nuevaContraseña p) {
        String sql = "UPDATE login_de_usuarios SET contraseña = ? WHERE correo = ?";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getContraseña());
            ps.setString(2, p.getCorreo());

            int filasModificadas = ps.executeUpdate();
            return filasModificadas > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización: " + e.getMessage(),
                    "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos en el DAO: " + e.getMessage());
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author Alexis
 */
import proyect_loansys.model.Conexion_Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonaDao_Restablecer {

    private Connection con;
    private Conexion_Registro conectar = new Conexion_Registro();
    private PreparedStatement ps;
    private ResultSet rs;

    // Validar que el documento existe
    public boolean verificarCorreo(String correo) {
        String sql = "SELECT 1 FROM login_de_usuarios WHERE correo = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("Error en verificarCorreo: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}

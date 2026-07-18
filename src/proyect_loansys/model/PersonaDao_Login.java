/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import proyect_loansys.model.Conexion_Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonaDao_Login {

    private Connection con;
    private Conexion_Login conectar = new Conexion_Login();
    private PreparedStatement ps;
    private ResultSet rs;
    
    private int idUsuarioLogueado = -1;
    
    public int getIdUsuarioLogueado(){
        return idUsuarioLogueado;
    }
    
    // Validar que el documento existe
    public boolean existeDocumento(int documento) {
        String sql = "SELECT 1 FROM usuarios_sena WHERE documento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            return rs.next(); // Retorna true si el documento ya existe
        } catch (Exception e) {
            System.out.println("Error en existeDocumento: " + e.getMessage());
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
            }
        }
    }

    public int validarLogin(int documento, String contraseña) {
        // valida que dependiendo de su rol lo lleve a una vista en especifico
            String sql = "SELECT u.id_rol, u.id_usuario FROM login_de_usuarios l "
            + "INNER JOIN usuarios_sena u ON l.id_usuario = u.id_usuario "
            + "WHERE u.documento = ? AND l.contraseña = ?";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, documento);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                idUsuarioLogueado = rs.getInt("id_usuario");
                return rs.getInt("id_rol"); // Si la contraseña es correcta, devuelve el número del rol que es (1, 2, 3, 4, 5)
            }
            return -1; // Si la contraseña está mal, devuelve -1

        } catch (Exception e) {
            System.out.println("Error en validarLogin: " + e.getMessage());
            return -1;
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
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;
import proyect_loansys.model.Crud_Registro;
import proyect_loansys.model.Conexion_Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class PersonaDao_Registro implements Crud_Registro<Persona_Registro>{

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //pedimos lo que va a agregar el usuario
    @Override
    public boolean setAgregar(Persona_Registro p) {
    String sqlBuscarSena = "SELECT id_usuario FROM usuarios_sena WHERE documento = ?";
    int idUsuarioEncontrado = -1;

    try {
        con = conectar.getConection();
        
        //Buscamos el id en la tabla usuarios_sena
        ps = con.prepareStatement(sqlBuscarSena);
        ps.setInt(1, p.getDocumento());
        rs = ps.executeQuery();

        if (rs.next()) {
            idUsuarioEncontrado = rs.getInt("id_usuario");
        } else {
            JOptionPane.showMessageDialog(null, "El documento no existe en la base de datos SENA");
            return false;
        }

        // Validamos si existe el usuario en la tabla del login
        String sqlVerificarLogin = "SELECT id_usuario FROM login_de_usuarios WHERE id_usuario = ?";
        ps = con.prepareStatement(sqlVerificarLogin);
        ps.setInt(1, idUsuarioEncontrado);
        rs = ps.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Este usuario ya tiene una cuenta registrada.");
            return false; // Salimos antes de intentar insertar
        }

        //Si no existe en login ahora si se inserta
        String sqlInsertar = "INSERT INTO login_de_usuarios(id_usuario, correo, contraseña) VALUES (?, ?, ?)";
        ps = con.prepareStatement(sqlInsertar);
        ps.setInt(1, idUsuarioEncontrado);
        ps.setString(2, p.getCorreo());
        ps.setString(3, p.getContraseña());

        int resultado = ps.executeUpdate();
        return resultado > 0;

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error en el proceso de registro: " + e.getMessage());
        return false;
        } finally {
            try { if (con != null) con.close(); if (ps != null) ps.close(); if (rs != null) rs.close(); } catch (Exception e) {}
        }
    }
}
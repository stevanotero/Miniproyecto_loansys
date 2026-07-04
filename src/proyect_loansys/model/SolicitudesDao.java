/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import proyect_loansys.model.Solicitudes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexis
 */
public class SolicitudesDao {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Solicitudes> listarSolicitudes() {
        List<Solicitudes> lista = new ArrayList<>();

        String sql = "SELECT "
                + "  s.id_solicitud, "
                + "  s.id_elemento, "
                + "  e.nombre_elemento, "
                + "  u.nombre, "
                + "  u.apellido, "
                + "  u.documento, "
                + "  r.nombre_rol, "
                + "  s.fecha_envio "
                + "FROM solicitudes_usuario s "
                + "INNER JOIN usuarios_sena u ON s.id_usuario = u.id_usuario "
                + "INNER JOIN roles r ON u.id_rol = r.id_rol "
                + "INNER JOIN elemento e ON s.id_elemento = e.id_elemento "
                + "ORDER BY s.fecha_envio DESC";

        try (Connection con = conectar.getConection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Solicitudes sol = new Solicitudes(
                        rs.getInt("id_solicitud"),
                        rs.getInt("id_elemento"),
                        rs.getString("nombre_elemento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        String.valueOf(rs.getInt("documento")),
                        rs.getString("nombre_rol"),
                        rs.getTimestamp("fecha_envio")
                );
                lista.add(sol);
            }

        } catch (Exception e) {
            System.out.println("Error al listar las solicitudes pendientes: " + e.getMessage());
        }

        return lista;
    }
}

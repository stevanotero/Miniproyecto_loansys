/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexis
 */
public class SolicitudesDao {

    Conexion_Registro conectar = new Conexion_Registro();

    // listar solicitudes
    public List<Solicitudes> listarSolicitudes() {
        List<Solicitudes> lista = new ArrayList<>();

        String sql = "SELECT "
                + "  s.id_solicitud, "
                + "  s.id_elemento, "
                + "  e.codigo_elemento, " 
                + "  s.id_usuario, "
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

        try (Connection con = conectar.getConection(); 
             PreparedStatement ps = con.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Solicitudes sol = new Solicitudes(
                        rs.getInt("id_solicitud"),
                        rs.getInt("codigo_elemento"), 
                        rs.getString("nombre_elemento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        String.valueOf(rs.getInt("documento")),
                        rs.getString("nombre_rol"),
                        rs.getTimestamp("fecha_envio")
                );

                sol.setIdUsuario(rs.getInt("id_usuario"));
                sol.setIdElemento(rs.getInt("id_elemento"));

                lista.add(sol);
            }

        } catch (Exception e) {
            System.out.println("Error al listar las solicitudes pendientes: " + e.getMessage());
        }

        return lista;
    }

    // metodo de recchazar
    public boolean rechazarSolicitudConNotificacion(int idSolicitud, int idUsuario, String motivoRefusal, String nombreElemento) {
        // insert de la notificacion tipo 6
        String sqlNotificacion = "INSERT INTO notificaciones_usuario (id_tipo_notificacion, mensaje, id_login) VALUES (?, ?, ?)";
        // se borra la que se selecciono 
        String sqlBorrarSolicitud = "DELETE FROM solicitudes_usuario WHERE id_solicitud = ?";

        Connection con = null;
        PreparedStatement psNotif = null;
        PreparedStatement psBorrar = null;

        try {
            con = conectar.getConection();
            con.setAutoCommit(false); 

            // forma en la que va a salir el mensaje de la notificacion en el rechazo
            psNotif = con.prepareStatement(sqlNotificacion);
            psNotif.setInt(1, 6); // Tipo 6 solicitud Rechazada
            psNotif.setString(2, "Tu solicitud del elemento " + nombreElemento + " ha sido rechazada. Motivo: " + motivoRefusal);
            psNotif.setInt(3, idUsuario); // id_login del usuario afectado
            psNotif.executeUpdate();

            // Eliminar la solicitud que estaba
            psBorrar = con.prepareStatement(sqlBorrarSolicitud);
            psBorrar.setInt(1, idSolicitud);
            psBorrar.executeUpdate();

            con.commit(); // Si ambas cosas son exitosas se guarda
            return true;

        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Error al rechazar solicitud: " + e.getMessage());
            return false;
        } finally {
            try {
                if (psNotif != null) {
                    psNotif.close();
                }
                if (psBorrar != null) {
                    psBorrar.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexis
 */
public class PrestamosDao {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;

    
    //Verifica si el usuario ya cuenta con al menos un préstamo activo en la tabla prestamo
    public boolean usuarioTienePrestamoActivo(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM prestamo WHERE id_usuario = ?";
        try (Connection cn = conectar.getConection(); PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, idUsuario);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Retorna true si ya tiene 1 o más préstamos activos
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar préstamos activos del usuario: " + e.getMessage());
        }
        return false;
    }

    // Recibe el objeto Solicitudes completo, la fecha límite digitada y el id de la categoría del elemento
    public boolean registrarPrestamoAprobado(Solicitudes solicitud, String fechaLimite, int idCategoria) {

        // Doble validación de seguridad a nivel de DAO
        if (usuarioTienePrestamoActivo(solicitud.getIdUsuario())) {
            JOptionPane.showMessageDialog(null,
                    "El usuario ya posee un préstamo activo. No es posible aprobar otra solicitud.",
                    "Acción Denegada",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Consultas SQL
        String sqlPrestamo = "INSERT INTO prestamo (id_usuario, id_elemento, fecha_inicio_prestamo, fecha_fin_devolucion, id_estado_elemento) "
                + "VALUES (?, ?, NOW(), ?, 2)";

        String sqlUpdateMora = "UPDATE login_de_usuarios SET id_estado_mora = 2 WHERE id_usuario = ?";

        String sqlHistorial = "INSERT INTO historial_prestamo (id_usuario, id_elemento, nombre_elemento, fecha_prestamo, fecha_limite, id_estado_entrega, id_categoria, id_login) "
                + "VALUES (?, ?, ?, NOW(), ?, 2, ?, (SELECT id_login FROM login_de_usuarios WHERE id_usuario = ?))";

        String sqlUpdateElemento = "UPDATE elemento SET id_estado_elemento = 2 "
                + "WHERE id_elemento = (SELECT id_elemento FROM solicitudes_usuario WHERE id_solicitud = ?) "
                + "OR (id_elemento = ? AND ? > 0)";

        String sqlEliminarSolicitud = "DELETE FROM solicitudes_usuario WHERE id_solicitud = ?";

        try {
            con = conectar.getConection();
            con.setAutoCommit(false);

            // Insertar en prestamos
            ps = con.prepareStatement(sqlPrestamo);
            ps.setInt(1, solicitud.getIdUsuario());
            ps.setInt(2, solicitud.getIdElemento());
            ps.setString(3, fechaLimite);
            ps.executeUpdate();
            ps.close();

            // Actualizar estado de mora
            ps = con.prepareStatement(sqlUpdateMora);
            ps.setInt(1, solicitud.getIdUsuario());
            ps.executeUpdate();
            ps.close();

            // Insertar en historial
            ps = con.prepareStatement(sqlHistorial);
            ps.setInt(1, solicitud.getIdUsuario());
            ps.setInt(2, solicitud.getIdElemento());
            ps.setString(3, solicitud.getNombreElemento());
            ps.setString(4, fechaLimite);
            ps.setInt(5, idCategoria);
            ps.setInt(6, solicitud.getIdUsuario());
            ps.executeUpdate();
            ps.close();

            // Actualizar el estado del elemento a Prestado 
            ps = con.prepareStatement(sqlUpdateElemento);
            ps.setInt(1, solicitud.getIdSolicitud());
            ps.setInt(2, solicitud.getIdElemento());
            ps.setInt(3, solicitud.getIdElemento());
            ps.executeUpdate();
            ps.close();

            // Eliminar la solicitud pendiente
            ps = con.prepareStatement(sqlEliminarSolicitud);
            ps.setInt(1, solicitud.getIdSolicitud());
            ps.executeUpdate();
            ps.close();

            con.commit();
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error en Rollback: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(null, "Error al procesar la aprobación: " + e.getMessage());
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
                System.out.println("Error cerrando conexiones: " + e.getMessage());
            }
        }
    }

    public boolean rechazarSolicitudConNotificacion(int idSolicitud, int idUsuario, String motivoRefusal, String nombreElemento) {
        String sqlNotificacion = "INSERT INTO notificaciones_usuario (id_tipo_notificacion, mensaje, id_login) "
                + "SELECT ?, ?, id_login FROM login_de_usuarios WHERE id_usuario = ?";

        String sqlBorrarSolicitud = "DELETE FROM solicitudes_usuario WHERE id_solicitud = ?";

        Connection con = null;
        PreparedStatement psNotif = null;
        PreparedStatement psBorrar = null;

        try {
            con = conectar.getConection();
            con.setAutoCommit(false);
            psNotif = con.prepareStatement(sqlNotificacion);
            psNotif.setInt(1, 6); // Tipo 6 de solicictud rechazada
            psNotif.setString(2, "Tu solicitud del elemento " + nombreElemento + " ha sido rechazada. Motivo: " + motivoRefusal);
            psNotif.setInt(3, idUsuario);
            psNotif.executeUpdate();
            psBorrar = con.prepareStatement(sqlBorrarSolicitud);
            psBorrar.setInt(1, idSolicitud);
            psBorrar.executeUpdate();

            con.commit();
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexis
 */
public class PrestamosDao {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;

    // Recibe el objeto Solicitudes completo, la fecha límite digitada y el id de la categoría del elemento
    public boolean registrarPrestamoAprobado(Solicitudes solicitud, String fechaLimite, int idCategoria) {

        //Insertar en la tabla de prestamos
        String sqlPrestamo = "INSERT INTO prestamo (id_usuario, id_elemento, fecha_inicio_prestamo, fecha_fin_devolucion, id_estado_elemento) "
                + "VALUES (?, ?, NOW(), ?, 2)";

        //Actualizar el id del usuario
        String sqlUpdateMora = "UPDATE login_de_usuarios SET id_estado_mora = 2 WHERE id_usuario = ?";

        // Guardar en el historial del usuario
        String sqlHistorial = "INSERT INTO historial_prestamo (nombre_elemento, fecha_prestamo, fecha_limite, id_estado_entrega, id_categoria, id_login) "
                + "VALUES (?, NOW(), ?, 2, ?, (SELECT id_login FROM login_de_usuarios WHERE id_usuario = ?))";

        // Eliminar la solicitud que esta pediente
        String sqlEliminarSolicitud = "DELETE FROM solicitudes_usuario WHERE id_solicitud = ?";

        try {
            con = conectar.getConection();
            con.setAutoCommit(false);
            // Insertar en la tabla de prestamos
            ps = con.prepareStatement(sqlPrestamo);
            ps.setInt(1, solicitud.getIdUsuario());
            ps.setInt(2, solicitud.getIdElemento());
            ps.setString(3, fechaLimite);
            ps.executeUpdate();
            ps.close();

            // Actualizar el estado de la mora
            ps = con.prepareStatement(sqlUpdateMora);
            ps.setInt(1, solicitud.getIdUsuario());
            ps.executeUpdate();
            ps.close();

            //Insertar en el historial de prestamo
            ps = con.prepareStatement(sqlHistorial);
            ps.setString(1, solicitud.getNombreElemento());
            ps.setString(2, fechaLimite);
            ps.setInt(3, idCategoria);
            ps.setInt(4, solicitud.getIdUsuario()); 
            ps.executeUpdate();
            ps.close();

            // Eliminar la solicitud pendiente
            ps = con.prepareStatement(sqlEliminarSolicitud);
            ps.setInt(1, solicitud.getIdSolicitud());
            ps.executeUpdate();
            con.commit(); // Confirmar las 4 operaciones en la BD
            return true;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Si falla alguna de las 4 operaciones, deshace todo
                } catch (SQLException ex) {
                    System.out.println("Error en Rollback: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(null, "Error crítico al procesar la aprobación: " + e.getMessage());
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
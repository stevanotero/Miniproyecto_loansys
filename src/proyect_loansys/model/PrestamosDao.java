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
 * @author Alexis
 */
public class PrestamosDao {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;

    // Recibe el objeto Solicitudes completo, la fecha límite digitada y el id de la categoría del elemento
    public boolean registrarPrestamoAprobado(Solicitudes solicitud, String fechaLimite, int idCategoria) {

        // 1. SQL para registrar el préstamo en la tabla principal
        String sqlPrestamo = "INSERT INTO prestamo (id_usuario, id_elemento, fecha_inicio_prestamo, fecha_fin_devolucion, id_estado_elemento) "
                + "VALUES (?, ?, NOW(), ?, 2)";

        // 2. SQL para guardar la auditoría en el historial (id_estado_entrega = 2 significa 'Pendiente')
        String sqlHistorial = "INSERT INTO historial_prestamo (nombre_elemento, fecha_prestamo, fecha_limite, id_estado_entrega, id_categoria) "
                + "VALUES (?, NOW(), ?, 2, ?)";

        // 3. SQL para eliminar la solicitud de la tabla temporal de pendientes porque ya se procesó
        String sqlEliminarSolicitud = "DELETE FROM solicitudes_usuario WHERE id_solicitud = ?";

        try {
            con = conectar.getConection();
            // Desactivamos el commit automático para proteger la operación en bloque (Transacción)
            con.setAutoCommit(false);

            // --- PASO 1: INSERTAR EN LA TABLA PRESTAMO ---
            ps = con.prepareStatement(sqlPrestamo);
            ps.setInt(1, solicitud.getIdUsuario());

            // 🌟 CORREGIDO: Ahora toma correctamente el idElemento (la FK numérica) y no el código visual
            ps.setInt(2, solicitud.getIdElemento());

            ps.setString(3, fechaLimite);
            ps.executeUpdate();
            ps.close();

            // --- PASO 2: INSERTAR EN EL HISTORIAL ---
            ps = con.prepareStatement(sqlHistorial);
            ps.setString(1, solicitud.getNombreElemento());
            ps.setString(2, fechaLimite);
            ps.setInt(3, idCategoria);
            ps.executeUpdate();
            ps.close();

            // --- PASO 3: ELIMINAR LA SOLICITUD YA PROCESADA ---
            ps = con.prepareStatement(sqlEliminarSolicitud);
            ps.setInt(1, solicitud.getIdSolicitud());
            ps.executeUpdate();

            // Si los 3 pasos se ejecutaron sin errores, confirmamos la transacción en la BD
            con.commit();
            return true;

        } catch (SQLException e) {
            // Si algo falla en cualquiera de los 3 pasos, deshacemos todo para no dejar datos corruptos
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error en Rollback: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(null, "Error crítico al procesar la aprobación: " + e.getMessage());
            return false;
        } finally {
            // Aseguramos el cierre de los recursos de conexión
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
        // 🌟 CORREGIDO: Buscamos automáticamente el id_login que le corresponde a ese id_usuario
        String sqlNotificacion = "INSERT INTO notificaciones_usuario (id_tipo_notificacion, mensaje, id_login) "
                + "SELECT ?, ?, id_login FROM login_de_usuarios WHERE id_usuario = ?";

        // 2. Borramos la solicitud de la lista de pendientes
        String sqlBorrarSolicitud = "DELETE FROM solicitudes_usuario WHERE id_solicitud = ?";

        Connection con = null;
        PreparedStatement psNotif = null;
        PreparedStatement psBorrar = null;

        try {
            con = conectar.getConection();
            con.setAutoCommit(false); // Transacción segura

            // Configurar Notificación
            psNotif = con.prepareStatement(sqlNotificacion);
            psNotif.setInt(1, 6); // Tipo 6: Solicitud Rechazada
            psNotif.setString(2, "Tu solicitud del elemento " + nombreElemento + " ha sido rechazada. Motivo: " + motivoRefusal);
            psNotif.setInt(3, idUsuario); // Sigue recibiendo el id_usuario, la base de datos hace el cruce sola
            psNotif.executeUpdate();

            // Eliminar de pendientes
            psBorrar = con.prepareStatement(sqlBorrarSolicitud);
            psBorrar.setInt(1, idSolicitud);
            psBorrar.executeUpdate();

            con.commit(); // Si ambos queries son exitosos, guardamos
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

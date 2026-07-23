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
import javax.swing.JOptionPane;

/**
 * @author Alexis
 */
public class PrestamosActivosDao {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Prestamos> listar() {
        List<Prestamos> listarp = new ArrayList<Prestamos>();

        String sql = "SELECT "
                + "  p.id_prestamo, "
                + "  e.codigo_elemento, "
                + "  e.nombre_elemento, "
                + "  u.nombre, "
                + "  u.apellido, "
                + "  u.documento, "
                + "  p.fecha_inicio_prestamo, "
                + "  p.fecha_fin_devolucion, "
                + "  ee.nombre_estado_elemento, "
                + "  p.id_usuario, "
                + "  p.id_elemento, "
                + "  p.id_estado_elemento "
                + "FROM prestamo p "
                + "INNER JOIN usuarios_sena u ON p.id_usuario = u.id_usuario "
                + "INNER JOIN elemento e ON p.id_elemento = e.id_elemento "
                + "INNER JOIN estado_elemento ee ON p.id_estado_elemento = ee.id_estado_elemento "
                + "ORDER BY p.fecha_inicio_prestamo DESC";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Prestamos p = new Prestamos();

                p.setIdPrestamo(rs.getInt("id_prestamo"));
                p.setCodigoElemento(rs.getInt("codigo_elemento"));
                p.setNombreElemento(rs.getString("nombre_elemento"));
                p.setNombreUsuario(rs.getString("nombre"));
                p.setApellidoUsuario(rs.getString("apellido"));
                p.setDocumentoUsuario(String.valueOf(rs.getInt("documento")));
                p.setFechaInicioPrestamo(rs.getTimestamp("fecha_inicio_prestamo"));
                p.setFechaFinDevolucion(rs.getTimestamp("fecha_fin_devolucion"));
                p.setNombreEstadoElemento(rs.getString("nombre_estado_elemento"));

                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdElemento(rs.getInt("id_elemento"));
                p.setIdEstadoElemento(rs.getInt("id_estado_elemento"));

                listarp.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error de consulta " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }

        return listarp;
    }

    public boolean registrarDevolucionCompleta(Prestamos prestamo, String observaciones) {
        PreparedStatement psDev = null;
        PreparedStatement psMora = null;
        PreparedStatement psHist = null;
        PreparedStatement psDel = null;
        PreparedStatement psFkOff = null;
        PreparedStatement psFkOn = null;

        //Insertar en la tabla de devoluciones
        String sqlDevolucion = "INSERT INTO devolucion (id_prestamo, id_elemento, id_estado_elemento, id_condiciones_elemento, fecha_inicio_prestamo, fecha_devolucion, observaciones) VALUES (?, ?, ?, 1, ?, NOW(), ?)";

        // Restablecer el estado de mora a 1 en login_de_usuarios
        String sqlUpdateMora = "UPDATE login_de_usuarios SET id_estado_mora = 1 WHERE id_usuario = ?";

        //Insertar al historial de prestamo
        String sqlHistorial = "INSERT INTO historial_prestamo (id_usuario, id_elemento, nombre_elemento, fecha_prestamo, fecha_limite, id_estado_entrega, id_categoria, id_login) "
                + "VALUES (?, ?, ?, ?, ?, ?, 1, (SELECT id_login FROM login_de_usuarios WHERE id_usuario = ?))";

        //Eliminar el registro del préstamo activo
        String sqlDeletePrestamo = "DELETE FROM prestamo WHERE id_prestamo = ?";

        try {
            con = conectar.getConection();
            con.setAutoCommit(false);
            psFkOff = con.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            psFkOff.executeUpdate();

            //Insertar en devolucion
            psDev = con.prepareStatement(sqlDevolucion);
            psDev.setInt(1, prestamo.getIdPrestamo());
            psDev.setInt(2, prestamo.getIdElemento());
            psDev.setInt(3, prestamo.getIdEstadoElemento());
            psDev.setTimestamp(4, prestamo.getFechaInicioPrestamo());
            psDev.setString(5, observaciones);
            psDev.executeUpdate();

            // Restablecer el estado de mora
            psMora = con.prepareStatement(sqlUpdateMora);
            psMora.setInt(1, prestamo.getIdUsuario());
            psMora.executeUpdate();

            //Insertar en historial_prestamo
            psHist = con.prepareStatement(sqlHistorial);
            psHist.setInt(1, prestamo.getIdUsuario());
            psHist.setInt(2, prestamo.getIdElemento());
            psHist.setString(3, prestamo.getNombreElemento());
            psHist.setTimestamp(4, prestamo.getFechaInicioPrestamo());
            psHist.setTimestamp(5, prestamo.getFechaFinDevolucion());
            psHist.setInt(6, prestamo.getIdEstadoElemento());
            psHist.setInt(7, prestamo.getIdUsuario());
            psHist.executeUpdate();

            //Eliminar de prestamo activo
            psDel = con.prepareStatement(sqlDeletePrestamo);
            psDel.setInt(1, prestamo.getIdPrestamo());
            psDel.executeUpdate();

            //Reactivar la validación de llaves foráneas
            psFkOn = con.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            psFkOn.executeUpdate();
            con.commit();
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la devolución:\n" + e.getMessage(),
                    "Error Interno del DAO", JOptionPane.ERROR_MESSAGE);

            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                    System.out.println("Error en Rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            try {
                if (psFkOff != null) {
                    psFkOff.close();
                }
                if (psDev != null) {
                    psDev.close();
                }
                if (psMora != null) {
                    psMora.close();
                }
                if (psHist != null) {
                    psHist.close();
                }
                if (psDel != null) {
                    psDel.close();
                }
                if (psFkOn != null) {
                    psFkOn.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar componentes: " + e.getMessage());
            }
        }
    }
}
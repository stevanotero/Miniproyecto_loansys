package proyect_loansys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import proyect_loansys.model.Notificaciones;
import proyect_loansys.model.TipoNotificacion;

/**
 *
 * @author Alexis
 */
public class NotificacionesDAO {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // MÉTODO NECESARIO: Obtiene los tipos de notificación según el rol del usuario en sesión
    public List<TipoNotificacion> listarTiposPorRol(int idRol) {
        List<TipoNotificacion> lista = new ArrayList<>();
        String sql = "";

        // Filtro de tipos de notificación por Rol (1: Aprendiz, 2: Instructor, 3: Técnico, 4: Asesor, 5: Admin)
        switch (idRol) {
            case 1: // Aprendiz
            case 2: // Instructor
                sql = "SELECT id_tipo_notificacion, nombre_tipo_notificacion FROM tipo_notificacion WHERE id_tipo_notificacion IN (5, 10, 11)";
                break;

            case 3: // Técnico
                sql = "SELECT id_tipo_notificacion, nombre_tipo_notificacion FROM tipo_notificacion WHERE id_tipo_notificacion IN (5, 7, 8, 9, 10)";
                break;

            case 4: // Asesor
                sql = "SELECT id_tipo_notificacion, nombre_tipo_notificacion FROM tipo_notificacion WHERE id_tipo_notificacion IN (1, 2, 3, 4, 5, 6)";
                break;
                
            case 5: // Administrador
                sql = "SELECT id_tipo_notificacion, nombre_tipo_notificacion FROM tipo_notificacion WHERE id_tipo_notificacion IN (11, 12)";
                break;

            default: 
                sql = "SELECT id_tipo_notificacion, nombre_tipo_notificacion FROM tipo_notificacion";
                break;
        }

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoNotificacion t = new TipoNotificacion();
                t.setIdTipoNotificacion(rs.getInt("id_tipo_notificacion"));
                t.setNombreTipoNotificacion(rs.getString("nombre_tipo_notificacion"));
                lista.add(t);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de notificación: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarConexiones();
        }
        return lista;
    }

    public List<Notificaciones> listarPorUsuario(int idLogin) {
        List<Notificaciones> listarp = new ArrayList<>();
        String sql = "SELECT n.id_notificacion, n.id_tipo_notificacion, "
                + "t.nombre_tipo_notificacion, n.mensaje, n.id_login "
                + "FROM notificaciones n "
                + "INNER JOIN tipo_notificacion t ON n.id_tipo_notificacion = t.id_tipo_notificacion "
                + "WHERE n.id_login = ? ORDER BY n.id_notificacion DESC";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLogin);
            rs = ps.executeQuery();

            while (rs.next()) {
                Notificaciones n = new Notificaciones();
                n.setIdNotificacion(rs.getInt(1));
                n.setIdTipoNotificacion(rs.getInt(2));
                n.setNombreTipoNotificacion(rs.getString(3));
                n.setMensaje(rs.getString(4));
                n.setIdLogin(rs.getInt(5));

                listarp.add(n);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de consulta: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarConexiones();
        }
        return listarp;
    }

    public int setAgregar(Notificaciones n) {
        int r;
        String sql = "INSERT INTO notificaciones (id_tipo_notificacion, mensaje, id_login) VALUES(?,?,?)";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, n.getIdTipoNotificacion());
            ps.setString(2, n.getMensaje());
            ps.setInt(3, n.getIdLogin());

            r = ps.executeUpdate();
            return r;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Inserción: " + e.getMessage(),
                    "Error SQL", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            cerrarConexiones();
        }
    }

    private void cerrarConexiones() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.err.println("Error al cerrar conexión: " + e.getMessage());
        }
    }
}
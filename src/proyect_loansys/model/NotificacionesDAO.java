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
import proyect_loansys.model.Notificaciones;

/**
 *
 * @author Alexis
 */
public class NotificacionesDAO {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Listar texto del JTable
    public List<Notificaciones> listarPorUsuario(int idLogin) {
        List<Notificaciones> listarp = new ArrayList<Notificaciones>();
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
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error de consulta: " + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    if (ps != null) {
                        ps.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return listarp;
    }

    // Insertar el texto enviado al usuario seleccionado
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
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error de Inserción: " + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                    if (ps != null) {
                        ps.close();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
    }

        //dependiendo del rol se le muestra un tipo de notificacion
    public List<TipoNotificacion> listarTiposPorRol(int idRol) {
        List<TipoNotificacion> lista = new ArrayList<TipoNotificacion>();
        String sql = "";

        switch (idRol) {
            case 1: // Aprendiz
            case 2: // Instructor
                sql = "SELECT * FROM tipo_notificacion WHERE id_tipo_notificacion IN (5, 10, 11)";
                break;

            case 3: // Tecnico
                sql = "SELECT * FROM tipo_notificacion WHERE id_tipo_notificacion IN (5, 7, 8, 9)";
                break;

            case 4: // Asesor
                sql = "SELECT * FROM tipo_notificacion WHERE id_tipo_notificacion IN (1, 2, 3, 4, 5, 6)";
                break;

            case 5: // Administrador
                sql = "SELECT * FROM tipo_notificacion WHERE id_tipo_notificacion IN (12)";
                break;

            default:
                sql = "SELECT * FROM tipo_notificacion WHERE id_tipo_notificacion IN (11)";
                break;
        }

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoNotificacion tipo = new TipoNotificacion();
                tipo.setIdTipoNotificacion(rs.getInt("id_tipo_notificacion"));
                tipo.setNombreTipoNotificacion(rs.getString("nombre_tipo_notificacion"));
                lista.add(tipo);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error al cargar tipos de notificación: " + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            if (con != null) {
                try {
                    con.close();
                    if (ps != null) {
                        ps.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return lista;
    }

}

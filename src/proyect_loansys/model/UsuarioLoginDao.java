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
 *
 * @author Alexis
 */
public class UsuarioLoginDao {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<UsuarioLogin> listarUsuarios() {
        List<UsuarioLogin> lista = new ArrayList<>();
        String sql = "SELECT "
                + "  l.id_login, "
                + "  l.id_usuario, "
                + "  u.nombre, "
                + "  u.apellido, "
                + "  l.correo, "
                + "  l.fecha_registro, "
                + "  l.id_estado, "
                + "  l.id_estado_mora, "
                + "  em.descripcion AS descripcion_mora "
                + "FROM login_de_usuarios l "
                + "LEFT JOIN usuarios_sena u ON l.id_usuario = u.id_usuario "
                + "LEFT JOIN estado_mora em ON l.id_estado_mora = em.id_estado_mora "
                + "ORDER BY l.id_login ASC";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                UsuarioLogin user = new UsuarioLogin();
                user.setIdLogin(rs.getInt("id_login"));
                user.setIdUsuario(rs.getInt("id_usuario"));

                String nom = rs.getString("nombre") != null ? rs.getString("nombre") : "";
                String ape = rs.getString("apellido") != null ? rs.getString("apellido") : "";
                String completo = (nom + " " + ape).trim();
                user.setNombreUsuario(completo.isEmpty() ? "Sin datos persona" : completo);

                user.setCorreo(rs.getString("correo"));
                user.setFechaRegistro(rs.getTimestamp("fecha_registro"));

                int est = rs.getInt("id_estado");
                user.setIdEstado(est);
                user.setNombreEstado(est == 1 ? "Activo" : "Inactivo");

                user.setIdEstadoMora(rs.getInt("id_estado_mora"));
                user.setDescripcionMora(rs.getString("descripcion_mora") != null ? rs.getString("descripcion_mora") : "Sin mora");

                lista.add(user);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar usuarios del sistema: " + e.getMessage());
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
        return lista;
    }

    public boolean cambiarEstadoMoraUsuario(int idLogin, int nuevoEstadoMora) {
        String sql = "UPDATE login_de_usuarios SET id_estado_mora = ? WHERE id_login = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, nuevoEstadoMora);
            ps.setInt(2, idLogin);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado de mora: " + e.getMessage());
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
            }
        }
    }
}

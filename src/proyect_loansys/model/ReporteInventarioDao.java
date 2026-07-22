/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

/**
 *
 * @author juans
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
 
public class ReporteInventarioDao {
 
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
 
    // 1) Estado general del inventario: cada elemento con su categoria y su estado actual
    public List<Object[]> estadoGeneralInventario() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT e.codigo_elemento, e.nombre_elemento, c.nombre_categoria, ee.nombre_estado_elemento "
                + "FROM elemento e "
                + "INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria "
                + "INNER JOIN estado_elemento ee ON e.id_estado_elemento = ee.id_estado_elemento "
                + "ORDER BY e.nombre_elemento";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("codigo_elemento"),
                    rs.getString("nombre_elemento"),
                    rs.getString("nombre_categoria"),
                    rs.getString("nombre_estado_elemento")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error en la consulta", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrar();
        }
        return lista;
    }
 
    // 2) Frecuencia de uso: cuantas veces se ha prestado cada elemento
    public List<Object[]> frecuenciaUso() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT e.codigo_elemento, f.nombre_elemento, SUM(f.veces_prestado) AS total_prestamos "
                + "FROM frecuencia_uso_elementos f "
                + "INNER JOIN elemento e ON f.id_elemento = e.id_elemento "
                + "GROUP BY f.id_elemento, e.codigo_elemento, f.nombre_elemento "
                + "ORDER BY total_prestamos DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("codigo_elemento"),
                    rs.getString("nombre_elemento"),
                    rs.getInt("total_prestamos")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error en la consulta", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrar();
        }
        return lista;
    }
 
    // 3) Alertas de mantenimiento: elementos que estan actualmente en mantenimiento
    public List<Object[]> alertasMantenimiento() {
        List<Object[]> lista = new ArrayList<>();
        String sql = "SELECT me.codigo, c.nombre_categoria, tm.nombre_tipo_mantenimiento, ee.nombre_estado_elemento "
                + "FROM mantenimiento_elemento me "
                + "INNER JOIN categoria_elemento c ON me.id_categoria = c.id_categoria "
                + "INNER JOIN tipo_mantenimiento tm ON me.id_tipo_mantenimiento = tm.id_tipo_mantenimiento "
                + "INNER JOIN estado_elemento ee ON me.id_estado_elemento = ee.id_estado_elemento "
                + "ORDER BY me.codigo";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getInt("codigo"),
                    rs.getString("nombre_categoria"),
                    rs.getString("nombre_tipo_mantenimiento"),
                    rs.getString("nombre_estado_elemento")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error en la consulta", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrar();
        }
        return lista;
    }
 
    private void cerrar() {
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
}
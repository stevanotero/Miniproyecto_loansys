/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import proyect_loansys.model.Elemento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import proyect_loansys.model.Crud;

/**
 *
 * @author Alexis
 */

public class PersonaDao_Inventario implements Crud<Elemento> {

    Conexion_Registro conectar = new Conexion_Registro();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Elemento> listarElementos() {
        List<Elemento> lista = new ArrayList<>();
        String sql = "SELECT e.codigo_elemento, e.nombre_elemento, c.nombre_categoria, est.nombre_estado_elemento, e.descripcion "
                + "FROM elemento e "
                + "INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria "
                + "INNER JOIN estado_elemento est ON e.id_estado_elemento = est.id_estado_elemento";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Elemento elem = new Elemento();
                elem.setCodigoElemento(rs.getInt("codigo_elemento"));
                elem.setNombreElemento(rs.getString("nombre_elemento"));
                elem.setCategoria(rs.getString("nombre_categoria"));
                elem.setEstado(rs.getString("nombre_estado_elemento"));
                elem.setDescripcion(rs.getString("descripcion"));
                lista.add(elem);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    public List<Elemento> buscarElementos(String texto) {
        List<Elemento> lista = new ArrayList<>();
        String sql = "SELECT e.codigo_elemento, e.nombre_elemento, e.descripcion, c.nombre_categoria, est.nombre_estado_elemento "
                + "FROM elemento e "
                + "INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria "
                + "INNER JOIN estado_elemento est ON e.id_estado_elemento = est.id_estado_elemento "
                + "WHERE e.nombre_elemento LIKE ? OR e.codigo_elemento LIKE ?";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                Elemento elem = new Elemento();
                elem.setCodigoElemento(rs.getInt("codigo_elemento"));
                elem.setNombreElemento(rs.getString("nombre_elemento"));
                elem.setCategoria(rs.getString("nombre_categoria"));
                elem.setEstado(rs.getString("nombre_estado_elemento"));
                elem.setDescripcion(rs.getString("descripcion"));
                lista.add(elem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de búsqueda: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    public List<Elemento> listarElementosConFiltro(String categoria, String estado) {
        List<Elemento> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT e.codigo_elemento, e.nombre_elemento, e.descripcion, c.nombre_categoria, est.nombre_estado_elemento "
                + "FROM elemento e "
                + "INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria "
                + "INNER JOIN estado_elemento est ON e.id_estado_elemento = est.id_estado_elemento WHERE 1=1"
        );

        if (categoria != null && !categoria.equals("Todas")) {
            sql.append(" AND c.nombre_categoria = ?");
        }
        if (estado != null && !estado.equals("Todos")) {
            sql.append(" AND est.nombre_estado_elemento = ?");
        }

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (categoria != null && !categoria.equals("Todas")) {
                ps.setString(paramIndex++, categoria);
            }
            if (estado != null && !estado.equals("Todos")) {
                ps.setString(paramIndex++, estado);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Elemento elem = new Elemento();
                elem.setCodigoElemento(rs.getInt("codigo_elemento"));
                elem.setNombreElemento(rs.getString("nombre_elemento"));
                elem.setCategoria(rs.getString("nombre_categoria"));
                elem.setEstado(rs.getString("nombre_estado_elemento"));
                elem.setDescripcion(rs.getString("descripcion"));
                lista.add(elem);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de consulta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    @Override
    public boolean setActualizar(Elemento elem) {
        String sql = "UPDATE elemento SET nombre_elemento = ?, descripcion = ?, id_estado_elemento = ? WHERE codigo_elemento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, elem.getNombreElemento());
            ps.setString(2, elem.getDescripcion());
            ps.setInt(3, elem.getIdEstadoElemento());
            ps.setInt(4, elem.getCodigoElemento());

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {}
    }
}
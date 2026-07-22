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
import java.sql.Timestamp;

public class Usuario_Dao implements Usuario_Crud_Buscar<Usuario_Elemento>,
        Usuario_Insert_soli<Usuario_Solicitud> {

    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Elemento_Dao.java - Modificar el método buscar()
    @Override
    public List<Usuario_Elemento> listar() {

        List<Usuario_Elemento> lista = new ArrayList<>();
        String sql = """
                SELECT e.id_elemento,
                       e.codigo_elemento,
                       e.nombre_elemento,
                       c.id_categoria,
                       c.nombre_categoria,
                       e.descripcion,
                       e.imagenes,
                       e.id_estado_elemento,
                       ee.nombre_estado_elemento
                FROM elemento e
                INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria
                INNER JOIN estado_elemento ee ON e.id_estado_elemento = ee.id_estado_elemento
                WHERE e.id_estado_elemento = 1
                ORDER BY e.codigo_elemento
                """;
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuario_Elemento e = new Usuario_Elemento();
                e.setId_elemento(rs.getInt("id_elemento"));
                e.setCodigo_elemento(rs.getInt("codigo_elemento"));
                e.setNombre_elemento(rs.getString("nombre_elemento"));
                e.setId_categoria(rs.getInt("id_categoria"));
                e.setCategoria_nombre(rs.getString("nombre_categoria"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setImagen(rs.getString("imagenes"));
                e.setId_estado_elemento(rs.getInt("id_estado_elemento"));
                e.setEstado_nombre(rs.getString("nombre_estado_elemento"));
                lista.add(e);
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Error en consulta: " + a.toString(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    @Override
    public int setAgregar(Usuario_Solicitud sr) {
        int r;
        String sql = "INSERT INTO solicitudes_usuario "
                + "(id_usuario, nombre, apellido, documento, id_elemento, fecha_envio) "
                + "VALUES (?,?,?,?,?,?)";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sr.getId_usuario());
            ps.setString(2, sr.getNombre());
            ps.setString(3, sr.getApellido());
            ps.setInt(4, sr.getDocumento());
            ps.setInt(5, sr.getId_elemento());
            ps.setTimestamp(6, sr.getFecha_envio()); // quita por completo el Time.valueOf
            r = ps.executeUpdate();
            return r;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error de Inserción", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public Usuario_Model consultarPorId(int idUsuario) {
        Usuario_Model u = null;
        String sql = "SELECT * FROM usuarios_sena WHERE id_usuario = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario_Model();
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setDocumento(rs.getInt("documento"));
                // agrega los demás campos que tenga tu Usuario_Model
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de consulta", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return u;
    }

    public boolean existeSolicitud(int idUsuario, int idElemento) {
        String sql = "SELECT 1 FROM solicitudes_usuario WHERE id_usuario = ? AND id_elemento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idElemento);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error de consulta", JOptionPane.ERROR_MESSAGE);
            return true; // si falla la consulta, bloqueamos por seguridad
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

    public Usuario_Elemento mostrarFila(int id_elemento) {
        Usuario_Elemento e = null;
        String sql = """
                 SELECT e.id_elemento,
                        e.codigo_elemento,
                        e.nombre_elemento,
                        c.id_categoria,
                        c.nombre_categoria,
                        e.descripcion,
                        e.imagenes,
                        e.id_estado_elemento,
                        ee.nombre_estado_elemento
                 FROM elemento e
                 INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria
                 INNER JOIN estado_elemento ee ON e.id_estado_elemento = ee.id_estado_elemento
                 WHERE e.id_elemento = ?
                 ORDER BY e.id_elemento ASC
                 """;
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_elemento);
            rs = ps.executeQuery();

            if (rs.next()) {
                e = new Usuario_Elemento();
                e.setId_elemento(rs.getInt("id_elemento"));
                e.setCodigo_elemento(rs.getInt("codigo_elemento"));
                e.setNombre_elemento(rs.getString("nombre_elemento"));
                e.setId_categoria(rs.getInt("id_categoria"));
                e.setCategoria_nombre(rs.getString("nombre_categoria"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setImagen(rs.getString("imagenes"));
                e.setId_estado_elemento(rs.getInt("id_estado_elemento"));
                e.setEstado_nombre(rs.getString("nombre_estado_elemento"));
            }
        } catch (Exception a) {
            a.printStackTrace();
        }
        return e; // ← retorna el objeto
    }

    public Usuario_Model validarLogin2(int documento, String contraseña) {
        String sql = "SELECT u.id_usuario, u.nombre, u.id_rol, r.nombre_rol "
                + "FROM login_de_usuarios l "
                + "INNER JOIN usuarios_sena u ON l.id_usuario = u.id_usuario "
                + "INNER JOIN roles r ON u.id_rol = r.id_rol "
                + "WHERE u.documento = ? AND l.contraseña = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, documento);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                Usuario_Model u = new Usuario_Model();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setId_rol(rs.getInt("id_rol"));
                u.setNombre_rol(rs.getString("nombre_rol"));
                return u; // login correcto, con todos los datos
            }
            return null; // contraseña incorrecta o no existe

        } catch (Exception e) {
            System.out.println("Error en validarLogin: " + e.getMessage());
            return null;
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
    }

    public List<Usuario_Elemento> buscarConFiltros(String estadoNombre, String codigoTexto, String categoriaNombre) {
        List<Usuario_Elemento> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
            SELECT e.id_elemento,
                   e.codigo_elemento,
                   e.nombre_elemento,
                   c.id_categoria,
                   c.nombre_categoria,
                   e.descripcion,
                   e.imagenes,
                   e.id_estado_elemento,
                   ee.nombre_estado_elemento
            FROM elemento e
            INNER JOIN categoria_elemento c ON e.id_categoria = c.id_categoria
            INNER JOIN estado_elemento ee ON e.id_estado_elemento = ee.id_estado_elemento
            WHERE 1 = 1
            """);

        if (estadoNombre != null && !estadoNombre.isBlank()) {
            sql.append(" AND ee.nombre_estado_elemento = ? ");
        }
        if (codigoTexto != null && !codigoTexto.isBlank()) {
            sql.append(" AND e.codigo_elemento LIKE ? ");
        }
        if (categoriaNombre != null && !categoriaNombre.isBlank()) {
            sql.append(" AND c.nombre_categoria = ? ");
        }
        sql.append(" ORDER BY e.codigo_elemento");

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql.toString());

            int indice = 1;
            if (estadoNombre != null && !estadoNombre.isBlank()) {
                ps.setString(indice++, estadoNombre);
            }
            if (codigoTexto != null && !codigoTexto.isBlank()) {
                ps.setString(indice++, "%" + codigoTexto + "%");
            }
            if (categoriaNombre != null && !categoriaNombre.isBlank()) {
                ps.setString(indice++, categoriaNombre);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario_Elemento e = new Usuario_Elemento();
                e.setId_elemento(rs.getInt("id_elemento"));
                e.setCodigo_elemento(rs.getInt("codigo_elemento"));
                e.setNombre_elemento(rs.getString("nombre_elemento"));
                e.setId_categoria(rs.getInt("id_categoria"));
                e.setCategoria_nombre(rs.getString("nombre_categoria"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setImagen(rs.getString("imagenes"));
                e.setId_estado_elemento(rs.getInt("id_estado_elemento"));
                e.setEstado_nombre(rs.getString("nombre_estado_elemento"));
                lista.add(e);
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "Error en consulta: " + a.toString(),
                    "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public List<Usuario_Historial> listarHistorialPorUsuario(int idUsuario) {
    List<Usuario_Historial> lista = new ArrayList<>();
    String sql = "SELECT "
            + "  h.id_historial_prestamo, "
            + "  h.id_usuario, "
            + "  h.id_elemento, "
            + "  e.codigo_elemento, "        // ← AGREGAR CÓDIGO DEL ELEMENTO
            + "  e.nombre_elemento, "
            + "  h.fecha_prestamo, "
            + "  h.fecha_limite, "
            + "  h.id_estado_entrega, "
            + "  ee.nombre_estado_entrega, "
            + "  h.id_categoria, "
            + "  c.nombre_categoria "
            + "FROM historial_prestamo h "
            + "INNER JOIN elemento e ON h.id_elemento = e.id_elemento "
            + "INNER JOIN estado_entrega ee ON h.id_estado_entrega = ee.id_estado_entrega "
            + "INNER JOIN categoria_elemento c ON h.id_categoria = c.id_categoria "
            + "WHERE h.id_usuario = ? "
            + "ORDER BY h.fecha_prestamo DESC";

    try (Connection con = conectar.getConection(); PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idUsuario);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario_Historial h = new Usuario_Historial();
                h.setId_historial_prestamo(rs.getInt("id_historial_prestamo"));
                h.setId_usuario(rs.getInt("id_usuario"));
                h.setId_elemento(rs.getInt("id_elemento"));
                h.setCodigo_elemento(rs.getString("codigo_elemento"));  // ← ASIGNAR CÓDIGO
                h.setNombre_elemento(rs.getString("nombre_elemento"));
                h.setFecha_prestamo(rs.getTimestamp("fecha_prestamo"));
                h.setFecha_limite(rs.getTimestamp("fecha_limite"));
                h.setId_estado_entrega(rs.getInt("id_estado_entrega"));
                h.setNombre_estado_entrega(rs.getString("nombre_estado_entrega"));
                h.setId_categoria(rs.getInt("id_categoria"));
                h.setNombre_categoria(rs.getString("nombre_categoria"));
                lista.add(h);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al listar historial: " + e.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();  // ← Agregar para ver el error completo
    }
    return lista;
}

}

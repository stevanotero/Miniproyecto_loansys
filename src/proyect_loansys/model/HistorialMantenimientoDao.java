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
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class HistorialMantenimientoDao implements Crud<HistorialMantenimiento> {
    Conexion conectar = new Conexion();
    Connection con;

    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List<HistorialMantenimiento> listar() {
        List<HistorialMantenimiento> lista = new ArrayList<HistorialMantenimiento>();
        String sql = "SELECT * FROM historial_mantenimiento";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                HistorialMantenimiento h = new HistorialMantenimiento();
                h.setIdMantenimiento(rs.getInt("id_mantenimiento"));
                h.setCodigoMantenimiento(rs.getString("codigo_mantenimiento"));
                h.setNombreElemento(rs.getString("nombre_elemento"));
                h.setCodigoElemento(rs.getInt("codigo_elemento"));
                h.setCategoria(rs.getString("categoria"));
                h.setTipoMantenimiento(rs.getString("tipo_mantenimiento"));
                h.setEstadoElemento(rs.getString("estado_elemento"));
                h.setFechaRealizada(rs.getString("fecha_realizada"));
                h.setIdUsuario(rs.getInt("id_usuario"));
                h.setDescripcion(rs.getString("descripcion"));

                lista.add(h);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la consulta" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return lista;
    }

    @Override
    public int setAgregar(HistorialMantenimiento h) {
        int r;
        String sql = "INSERT INTO historial_mantenimiento "
                + "(codigo_mantenimiento, nombre_elemento, codigo_elemento, categoria, "
                + "tipo_mantenimiento, estado_elemento, id_usuario, descripcion) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);

            ps.setString(1, h.getCodigoMantenimiento());
            ps.setString(2, h.getNombreElemento());
            ps.setInt(3, h.getCodigoElemento());
            ps.setString(4, h.getCategoria());
            ps.setString(5, h.getTipoMantenimiento());
            ps.setString(6, h.getEstadoElemento());
            ps.setInt(7, h.getIdUsuario());
            ps.setString(8, h.getDescripcion());

            r = ps.executeUpdate();
            return r;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la insercion" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
    }

    @Override
    public int setActualizar(HistorialMantenimiento h) {
        String sql = "UPDATE historial_mantenimiento SET codigo_mantenimiento=?, nombre_elemento=?, "
                + "codigo_elemento=?, categoria=?, tipo_mantenimiento=?, estado_elemento=?, "
                + "id_usuario=?, descripcion=? WHERE id_mantenimiento=?";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);

            ps.setString(1, h.getCodigoMantenimiento());
            ps.setString(2, h.getNombreElemento());
            ps.setInt(3, h.getCodigoElemento());
            ps.setString(4, h.getCategoria());
            ps.setString(5, h.getTipoMantenimiento());
            ps.setString(6, h.getEstadoElemento());
            ps.setInt(7, h.getIdUsuario());
            ps.setString(8, h.getDescripcion());
            ps.setInt(9, h.getIdMantenimiento());
            ps.executeUpdate();

            return 1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la Actualizacion" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
    }

    @Override
    public int setEliminar(int id) {
        String sql = "DELETE FROM historial_mantenimiento WHERE id_mantenimiento=?";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la eliminacion    " + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
    }

    public String generarCodigoMantenimiento() {
        String codigo;
        Random random = new Random();
        do {
            int numero = 10000 + random.nextInt(90000);
            codigo = String.valueOf(numero);
        } while (existeCodigoMantenimiento(codigo));
        return codigo;
    }

    private boolean existeCodigoMantenimiento(String codigo) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM historial_mantenimiento WHERE codigo_mantenimiento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error validando el codigo" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return existe;
    }

//metodos de santiago (mantenimientotecnico)
    public List<Object[]> listarPanel() {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = "SELECT hm.codigo_mantenimiento, hm.nombre_elemento, "
                + "CONCAT(u.nombre, ' ', u.apellido) AS tecnico, hm.fecha_realizada "
                + "FROM historial_mantenimiento hm "
                + "INNER JOIN usuarios_sena u ON hm.id_usuario = u.id_usuario "
                + "ORDER BY hm.id_mantenimiento DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("codigo_mantenimiento");
                fila[1] = rs.getString("nombre_elemento");
                fila[2] = rs.getString("tecnico");
                fila[3] = rs.getString("fecha_realizada");
                lista.add(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la consulta" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return lista;
    }

    
    
    public List<Object[]> buscarPanel(String texto) {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = "SELECT hm.codigo_mantenimiento, hm.nombre_elemento, "
                + "CONCAT(u.nombre, ' ', u.apellido) AS tecnico, hm.fecha_realizada "
                + "FROM historial_mantenimiento hm "
                + "INNER JOIN usuarios_sena u ON hm.id_usuario = u.id_usuario "
                + "WHERE hm.codigo_mantenimiento LIKE ? OR hm.nombre_elemento LIKE ? "
                + "OR u.nombre LIKE ? OR u.apellido LIKE ? "
                + "ORDER BY hm.id_mantenimiento DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            String like = "%" + texto + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("codigo_mantenimiento");
                fila[1] = rs.getString("nombre_elemento");
                fila[2] = rs.getString("tecnico");
                fila[3] = rs.getString("fecha_realizada");
                lista.add(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la busqueda" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return lista;
    }
    public Object[] buscarTecnicoPorDocumento(String documento) {
        Object[] resultado = null;
        String sql = "SELECT id_usuario, nombre, apellido FROM usuarios_sena WHERE documento = ? AND id_rol = 3";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, documento);
            rs = ps.executeQuery();

            if (rs.next()) {
                resultado = new Object[2];
                resultado[0] = rs.getInt("id_usuario");
                resultado[1] = rs.getString("nombre") + " " + rs.getString("apellido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la busqueda" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return resultado;
    }

//metodos de daniel (historialtecnicoo)
    public List<Object[]> listarHistorial() {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = "SELECT codigo_mantenimiento, nombre_elemento, categoria, tipo_mantenimiento, estado_elemento "
                + "FROM historial_mantenimiento ORDER BY id_mantenimiento DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("codigo_mantenimiento");
                fila[1] = rs.getString("nombre_elemento");
                fila[2] = rs.getString("categoria");
                fila[3] = rs.getString("tipo_mantenimiento");
                fila[4] = rs.getString("estado_elemento");
                lista.add(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la consulta" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return lista;
    }


    
    public List<Object[]> listarHistorialPorEstado(String estado) {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = "SELECT codigo_mantenimiento, nombre_elemento, categoria, tipo_mantenimiento, estado_elemento "
                + "FROM historial_mantenimiento WHERE estado_elemento = ? ORDER BY id_mantenimiento DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("codigo_mantenimiento");
                fila[1] = rs.getString("nombre_elemento");
                fila[2] = rs.getString("categoria");
                fila[3] = rs.getString("tipo_mantenimiento");
                fila[4] = rs.getString("estado_elemento");
                lista.add(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la consulta" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return lista;
    }


    
    public List<Object[]> buscarHistorial(String texto) {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = "SELECT codigo_mantenimiento, nombre_elemento, categoria, tipo_mantenimiento, estado_elemento "
                + "FROM historial_mantenimiento "
                + "WHERE codigo_mantenimiento LIKE ? OR nombre_elemento LIKE ? OR categoria LIKE ? "
                + "OR tipo_mantenimiento LIKE ? OR estado_elemento LIKE ? "
                + "ORDER BY id_mantenimiento DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            String like = "%" + texto + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            ps.setString(5, like);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("codigo_mantenimiento");
                fila[1] = rs.getString("nombre_elemento");
                fila[2] = rs.getString("categoria");
                fila[3] = rs.getString("tipo_mantenimiento");
                fila[4] = rs.getString("estado_elemento");
                lista.add(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en la busqueda" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return lista;
    }

    public int contarPorEstado(String estado) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM historial_mantenimiento WHERE estado_elemento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error en el conteo" + e.getMessage(),
                    JOptionPane.ERROR_MESSAGE
            );
        } finally {
            if (con != null) {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.toString());
                }
            }
        }
        return total;
    }
}
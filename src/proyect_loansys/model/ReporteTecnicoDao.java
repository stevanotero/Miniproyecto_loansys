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
 * @author Sants
 */
public class ReporteTecnicoDao implements Crud_Tecnico<ReporteTecnico> {
    Conexion conectar = new Conexion();
    Connection con;

    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<ReporteTecnico> listar() {
        List<ReporteTecnico> lista = new ArrayList<ReporteTecnico>();
        String sql = "SELECT * FROM reportes_tecnico";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ReporteTecnico r = new ReporteTecnico();
                r.setIdReporteTecnico(rs.getInt("id_reporte_tecnico"));
                r.setCodigoReporte(rs.getString("codigo_reporte"));
                r.setIdMantenimiento(rs.getInt("id_mantenimiento"));
                r.setFechaRealizado(rs.getString("fecha_realizado"));

                lista.add(r);
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
    public int setAgregar(ReporteTecnico r) {
        int res;
        String sql = "INSERT INTO reportes_tecnico (codigo_reporte, id_mantenimiento) VALUES (?,?)";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);

            ps.setString(1, r.getCodigoReporte());
            ps.setInt(2, r.getIdMantenimiento());

            res = ps.executeUpdate();
            return res;
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
    public int setActualizar(ReporteTecnico r) {
        String sql = "UPDATE reportes_tecnico SET codigo_reporte=?, id_mantenimiento=? WHERE id_reporte_tecnico=?";

        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);

            ps.setString(1, r.getCodigoReporte());
            ps.setInt(2, r.getIdMantenimiento());
            ps.setInt(3, r.getIdReporteTecnico());
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
        String sql = "DELETE FROM reportes_tecnico WHERE id_reporte_tecnico=?";

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

    public String generarCodigoReporte() {
        String codigo;
        Random random = new Random();
        do {
            int numero = 10000 + random.nextInt(90000);
            codigo = String.valueOf(numero);
        } while (existeCodigoReporte(codigo));
        return codigo;
    }

    private boolean existeCodigoReporte(String codigo) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM reportes_tecnico WHERE codigo_reporte = ?";
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

    public boolean existeReporteParaMantenimiento(String codigoMantenimiento) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM historial_mantenimiento hm "
                + "INNER JOIN reportes_tecnico rt ON hm.id_mantenimiento = rt.id_mantenimiento "
                + "WHERE hm.codigo_mantenimiento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoMantenimiento);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error validando el reporte" + e.getMessage(),
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

    public boolean existeMantenimientoPorCodigo(String codigoMantenimiento) {
        boolean existe = false;
        String sql = "SELECT COUNT(*) FROM historial_mantenimiento WHERE codigo_mantenimiento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoMantenimiento);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.toString(),
                    "Error validando el código de mantenimiento" + e.getMessage(),
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

    // CORREGIDO: agrega JOIN con elemento para traer nombre_elemento
    public Object[] buscarDatosPorCodigoMantenimiento(String codigoMantenimiento) {
        Object[] resultado = null;
        String sql = "SELECT hm.id_mantenimiento, e.nombre_elemento, "
                + "CONCAT(u.nombre, ' ', u.apellido) AS tecnico, u.documento, "
                + "hm.tipo_mantenimiento, hm.estado_elemento, hm.descripcion "
                + "FROM historial_mantenimiento hm "
                + "INNER JOIN elemento e ON hm.id_elemento = e.id_elemento "
                + "INNER JOIN usuarios_sena u ON hm.id_usuario = u.id_usuario "
                + "WHERE hm.codigo_mantenimiento = ? "
                + "AND hm.id_mantenimiento NOT IN (SELECT id_mantenimiento FROM reportes_tecnico)";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoMantenimiento);
            rs = ps.executeQuery();

            if (rs.next()) {
                resultado = new Object[7];
                resultado[0] = rs.getInt("id_mantenimiento");
                resultado[1] = rs.getString("nombre_elemento");
                resultado[2] = rs.getString("tecnico");
                resultado[3] = rs.getString("documento");
                resultado[4] = rs.getString("tipo_mantenimiento");
                resultado[5] = rs.getString("estado_elemento");
                resultado[6] = rs.getString("descripcion");
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

    public Object[] buscarDatosParaPDF(String codigoReporte) {
        Object[] datos = null;
        String sql = "SELECT hm.codigo_mantenimiento, e.nombre_elemento, e.codigo_elemento, "
                + "hm.categoria, hm.tipo_mantenimiento, hm.estado_elemento, hm.fecha_realizada, "
                + "u.nombre, u.apellido, u.documento, hm.descripcion, "
                + "rt.fecha_realizado, rt.codigo_reporte "
                + "FROM reportes_tecnico rt "
                + "INNER JOIN historial_mantenimiento hm ON rt.id_mantenimiento = hm.id_mantenimiento "
                + "INNER JOIN elemento e ON hm.id_elemento = e.id_elemento "
                + "INNER JOIN usuarios_sena u ON hm.id_usuario = u.id_usuario "
                + "WHERE rt.codigo_reporte = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, codigoReporte);
            rs = ps.executeQuery();

            if (rs.next()) {
                datos = new Object[13];
                datos[0] = rs.getString("codigo_mantenimiento");
                datos[1] = rs.getString("nombre_elemento");
                datos[2] = rs.getInt("codigo_elemento");
                datos[3] = rs.getString("categoria");
                datos[4] = rs.getString("tipo_mantenimiento");
                datos[5] = rs.getString("estado_elemento");
                datos[6] = rs.getString("fecha_realizada");
                datos[7] = rs.getString("nombre");
                datos[8] = rs.getString("apellido");
                datos[9] = rs.getString("documento");
                datos[10] = rs.getString("descripcion");
                datos[11] = rs.getString("fecha_realizado");
                datos[12] = rs.getString("codigo_reporte");
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
        return datos;
    }

    public int contarTotalReportes() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM reportes_tecnico";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
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

    public int contarReportesPorEstado(String estado) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM reportes_tecnico rt "
                + "INNER JOIN historial_mantenimiento hm ON rt.id_mantenimiento = hm.id_mantenimiento "
                + "WHERE hm.estado_elemento = ?";
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

    public List<Object[]> listarTabla() {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = "SELECT rt.codigo_reporte, e.nombre_elemento, rt.fecha_realizado, hm.estado_elemento "
                + "FROM reportes_tecnico rt "
                + "INNER JOIN historial_mantenimiento hm ON rt.id_mantenimiento = hm.id_mantenimiento "
                + "INNER JOIN elemento e ON hm.id_elemento = e.id_elemento "
                + "ORDER BY rt.id_reporte_tecnico DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("codigo_reporte");
                fila[1] = rs.getString("nombre_elemento");
                fila[2] = rs.getString("fecha_realizado");
                fila[3] = rs.getString("estado_elemento");
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

    public List<Object[]> buscarTabla(String texto) {
        List<Object[]> lista = new ArrayList<Object[]>();
        String sql = "SELECT rt.codigo_reporte, e.nombre_elemento, rt.fecha_realizado, hm.estado_elemento "
                + "FROM reportes_tecnico rt "
                + "INNER JOIN historial_mantenimiento hm ON rt.id_mantenimiento = hm.id_mantenimiento "
                + "INNER JOIN elemento e ON hm.id_elemento = e.id_elemento "
                + "WHERE e.nombre_elemento LIKE ? OR rt.codigo_reporte LIKE ? "
                + "ORDER BY rt.id_reporte_tecnico DESC";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            String like = "%" + texto + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("codigo_reporte");
                fila[1] = rs.getString("nombre_elemento");
                fila[2] = rs.getString("fecha_realizado");
                fila[3] = rs.getString("estado_elemento");
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
}
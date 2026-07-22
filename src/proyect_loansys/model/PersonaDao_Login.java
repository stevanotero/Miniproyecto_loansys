/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import proyect_loansys.model.Conexion_Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexis
 */
public class PersonaDao_Login {

    private Connection con;
    private Conexion_Registro conectar = new Conexion_Registro();
    private PreparedStatement ps;
    private ResultSet rs;

    // Validar que el documento existe
    public boolean existeDocumento(int documento) {
        String sql = "SELECT 1 FROM usuarios_sena WHERE documento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            return rs.next(); // Retorna true si el documento ya existe
        } catch (Exception e) {
            System.out.println("Error en existeDocumento: " + e.getMessage());
            return false;
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

    public int obtenerIdLogin(int documento) {
        String sql = "SELECT l.id_login FROM login_de_usuarios l "
                + "INNER JOIN usuarios_sena u ON l.id_usuario = u.id_usuario "
                + "WHERE u.documento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_login");
            }
        } catch (Exception e) {
            System.out.println("Error SQL en obtenerIdLogin: " + e.getMessage());
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
        return -1;
    }

    public int validarLogin(int documento, String contraseña) {
        // valida que dependiendo de su rol lo lleve a una vista en especifico
        String sql = "SELECT u.id_rol FROM login_de_usuarios l "
                + "INNER JOIN usuarios_sena u ON l.id_usuario = u.id_usuario "
                + "WHERE u.documento = ? AND l.contraseña = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, documento);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_rol"); // Si la contraseña es correcta, devuelve el número del rol que es 
            }
            return -1; // Si la contraseña está mal, devuelve -1

        } catch (Exception e) {
            System.out.println("Error en validarLogin: " + e.getMessage());
            return -1;
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

    // metodo boleano para validar que existe el correo
    public boolean existeCorreo(String correo) {
        String sql = "SELECT id_login FROM login_de_usuarios WHERE correo = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error al consultar correo: " + e.getMessage(),
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
        return false;
    }

    // Validar si la cuenta esta activa
    public boolean estaCuentaActiva(int documento) {
        String sql = "SELECT l.id_estado "
                + "FROM login_de_usuarios l "
                + "INNER JOIN usuarios_sena u ON l.id_usuario = u.id_usuario "
                + "WHERE u.documento = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, documento);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_estado") == 1; // 1 = Activo, 2 = Inactivo
            }
        } catch (Exception e) {
            System.out.println("Error al consultar estado de cuenta: " + e.getMessage());
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
        return false;
    }

    //Validar para conseguir el id del login por el correo
    public int obtenerIdLoginPorCorreo(String correo) {
        int idLogin = -1;
        String sql = "SELECT id_login FROM login_de_usuarios WHERE correo = ?";
        try {
            con = conectar.getConection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            if (rs.next()) {
                idLogin = rs.getInt("id_login");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),
                    "Error al obtener ID de login: " + e.getMessage(),
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
        return idLogin;
    }
}

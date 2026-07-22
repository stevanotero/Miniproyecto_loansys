/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import proyect_loansys.view.Vista_Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;
import proyect_loansys.view.Vista_RestablecerContraseña;
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.model.PersonaDao_Login;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.model.Usuario_Model;
import proyect_loansys.view.Administrador_Inicio_Loansys_Administrador;
import proyect_loansys.view.Inicio_Tecnico;
import proyect_loansys.view.Usuario_Inicio;
import proyect_loansys.view.Vista_Registro;

public class Controlador_Login implements ActionListener {

    private int intentosFallidos = 0;
    private long tiempoBloqueoHasta = 0;
    private final long TIEMPO_ESPERA = 60000;

    public PersonaDao_Login pdao = new PersonaDao_Login();
    public Vista_Login vista;
    public Usuario_Model usuarioLogueado = new Usuario_Model();
    public Usuario_Dao pdaos = new Usuario_Dao();

    public Controlador_Login(Vista_Login vista) {
        this.vista = vista;

        this.vista.botonRegistrar.addActionListener(this);
        this.vista.botonOlvidar.addActionListener(this);
        this.vista.botonLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.botonLogin) {
            if (System.currentTimeMillis() < tiempoBloqueoHasta) {
                long segundosRestantes = (tiempoBloqueoHasta - System.currentTimeMillis()) / 1000;
                JOptionPane.showMessageDialog(vista,
                        "Acceso temporalmente bloqueado.\nIntente de nuevo en " + segundosRestantes + " segundos.",
                        "Bloqueo Activo", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String passwordConvertido = String.valueOf(vista.textoDeLaContraseña.getPassword()).trim();
            String documentoTexto = vista.textoDelDocumento.getText().trim();
            if (!documentoTexto.isBlank() && !passwordConvertido.isBlank()) {
                if (procesarLogin(documentoTexto, passwordConvertido)) {
                    intentosFallidos = 0;
                } else {
                    // Los intentos solo suman si el documento era real
                    if (intentosFallidos >= 5) {
                        tiempoBloqueoHasta = System.currentTimeMillis() + TIEMPO_ESPERA;
                        JOptionPane.showMessageDialog(vista,
                                "Has superado los 5 intentos permitidos con contraseñas erróneas.\nBloqueado por 1 minuto.",
                                "Límite Superado", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else {
                JOptionPane.showMessageDialog(vista, "Faltan datos por llenar", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (e.getSource() == vista.botonRegistrar) {
            vista.dispose();
            Vista_Registro vistaRegistro = new Vista_Registro();
            vistaRegistro.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            vistaRegistro.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            Controlador_Registro controladorReg = new Controlador_Registro(vistaRegistro);
            vistaRegistro.setVisible(true);
        }

        if (e.getSource() == vista.botonOlvidar) {
            vista.dispose();
            Vista_RestablecerContraseña vistaRestablecer = new Vista_RestablecerContraseña();
            vistaRestablecer.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            vistaRestablecer.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            Controlador_Restablecer controladorRestablecer = new Controlador_Restablecer(vistaRestablecer);
            vistaRestablecer.setVisible(true);
        }

    }

    public boolean procesarLogin(String documentoTexto, String contraseña) {
        try {
            int documento = Integer.parseInt(documentoTexto);

            if (!pdao.existeDocumento(documento)) {
                JOptionPane.showMessageDialog(vista, "El número de documento ingresado no se encuentra registrado", "Usuario no encontrado", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Trae el objeto completo: nombre, rol, id_usuario
            Usuario_Model usuarioLogueado = pdaos.validarLogin2(documento, contraseña);

            if (usuarioLogueado != null) {
                String nombreUsuario = usuarioLogueado.getNombre();
                String rolUsuario = usuarioLogueado.getNombre_rol();
                int idRol = usuarioLogueado.getId_rol();
                int idUsuario = usuarioLogueado.getId_usuario();

                // Auditoría de inicio de sesión
                Administrador_Sesion.setIdUsuario(idUsuario);
                Administrador_Auditoria auditoria = new Administrador_Auditoria();
                auditoria.setIdUsuario(idUsuario);
                auditoria.setAccion("Inicio de sesion");
                new Administrador_AuditoriaDao().registrarAccion(auditoria);

                JOptionPane.showMessageDialog(vista, "Bienvenido al Sistema de Préstamos del SENA", "Acceso Concedido", JOptionPane.INFORMATION_MESSAGE);
                borrador();
                vista.dispose();

                // Se evalua el rol y lo lleva a una vista en especifico
                switch (idRol) {
                    case 1: // aprendiz
                        Usuario_Inicio aprendiz = new Usuario_Inicio( rolUsuario, rolUsuario, nombreUsuario);
                        Usuario_ControladorNavedagor aprendizC = new Usuario_ControladorNavedagor(aprendiz, nombreUsuario, rolUsuario);
                        aprendiz.setVisible(true);
                        break;

                    case 2: // instructor
                        Usuario_Inicio instructor = new Usuario_Inicio( rolUsuario, rolUsuario, nombreUsuario);
                        Usuario_ControladorNavedagor instructorC = new Usuario_ControladorNavedagor(instructor, nombreUsuario, rolUsuario);
                        instructor.setVisible(true);
                        break;

                    case 3: // tecnico
                        Inicio_Tecnico vistaTecnico = new Inicio_Tecnico();
                        Inicio_Tecnico_Controller tecnico = new Inicio_Tecnico_Controller(vistaTecnico);
                        vistaTecnico.setVisible(true);
                        break;

                    case 4: // asesor
                        Vista_Inicio vistaAsesor = new Vista_Inicio();
                        Controlador_inicio controlAsesor = new Controlador_inicio(vistaAsesor);
                        vistaAsesor.setVisible(true);
                        break;

                    case 5: // administrador
                        Administrador_Inicio_Loansys_Administrador usaActi = new Administrador_Inicio_Loansys_Administrador();
                        Administrador_ControladorBotones controller = new Administrador_ControladorBotones(usaActi);
                        Administrador_ControladorInicioAdministrador controladorAuditoria = new Administrador_ControladorInicioAdministrador(usaActi);
                        usaActi.setVisible(true);
                        break;

                    default:
                        JOptionPane.showMessageDialog(vista, "El rol asignado no cuenta con una interfaz configurada.", "Error de Rol", JOptionPane.ERROR_MESSAGE);
                        break;
                }

                return true;
            } else {
                intentosFallidos++;
                JOptionPane.showMessageDialog(vista, "La contraseña ingresada es incorrecta", "Contraseña Incorrecta", JOptionPane.ERROR_MESSAGE);
                vista.textoDeLaContraseña.setText("");
                return false;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El documento debe contener solo números enteros", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void borrador() {
        vista.textoDelDocumento.setText("");
        vista.textoDeLaContraseña.setText("");
    }
}

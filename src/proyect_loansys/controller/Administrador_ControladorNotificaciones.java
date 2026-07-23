/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Notificaciones;
import proyect_loansys.model.NotificacionesDAO;
import proyect_loansys.model.PersonaDao_Login;
import proyect_loansys.model.Sesion;
import proyect_loansys.model.TipoNotificacion;
import proyect_loansys.view.Administrador_Cambio_de_rol;
import proyect_loansys.view.Administrador_Inicio_Loansys_Administrador;
import proyect_loansys.view.Administrador_ModificarUsuario;
import proyect_loansys.view.Vista_NotificacionesAdmin;
import proyect_loansys.view.Administrador_Registro_de_usuario;
import proyect_loansys.view.Administrador_Usuarios_activos;
import proyect_loansys.view.Vista_Login;

public class Administrador_ControladorNotificaciones implements ActionListener {

    private Vista_NotificacionesAdmin vista;
    private NotificacionesDAO modelo;
    private PersonaDao_Login loginDao;
    private DefaultTableModel modeloTabla;
    private List<Notificaciones> listaNotificaciones;
    private List<TipoNotificacion> listaTiposCombo;

    private Administrador_Inicio_Loansys_Administrador inicio = new Administrador_Inicio_Loansys_Administrador();
    private Administrador_Usuarios_activos usActivo = new Administrador_Usuarios_activos();
    private Administrador_ModificarUsuario modificarUsuario = new Administrador_ModificarUsuario();
    private Administrador_Cambio_de_rol cambio_de_rol = new Administrador_Cambio_de_rol();
    private Administrador_Registro_de_usuario registro_de_usuario = new Administrador_Registro_de_usuario();

    public Administrador_ControladorNotificaciones(Vista_NotificacionesAdmin vista) {
        this.vista = vista;
        this.modelo = new NotificacionesDAO();
        this.loginDao = new PersonaDao_Login();

        this.vista.btnEnviarNotificacion.addActionListener(this);
        this.vista.binicio.addActionListener(this);
        this.vista.activacion_usuario.addActionListener(this);
        this.vista.modificar.addActionListener(this);
        this.vista.gestion_roles.addActionListener(this);
        this.vista.registrar_usuario.addActionListener(this);
        this.vista.cerrar_sesion.addActionListener(this);

        listarNotificacionesTabla();
        cargarComboTipos();
    }

    public void cargarComboTipos() {
        vista.comboTipoNotificacion.removeAllItems();

        int idRolActual = Sesion.getIdRol();
        listaTiposCombo = modelo.listarTiposPorRol(idRolActual);

        if (listaTiposCombo != null) {
            for (TipoNotificacion t : listaTiposCombo) {
                vista.comboTipoNotificacion.addItem(t.getNombreTipoNotificacion());
            }
        }
    }

    public void listarNotificacionesTabla() {
        modeloTabla = (DefaultTableModel) vista.tablaNotificaciones.getModel();
        modeloTabla.setRowCount(0);

        int idUsuarioActual = Sesion.getIdLogin();
        listaNotificaciones = modelo.listarPorUsuario(idUsuarioActual);

        if (listaNotificaciones != null) {
            Object[] fila = new Object[2];
            for (Notificaciones notif : listaNotificaciones) {
                fila[0] = notif.getNombreTipoNotificacion();
                fila[1] = notif.getMensaje();
                modeloTabla.addRow(fila);
            }
        }
    }

    private void registrarNuevaNotificacion() {
        String correoDestinatario = vista.txtDocumentoDestinatario.getText().trim();
        String mensaje = vista.txtAreaMensaje.getText().trim();
        String placeholder = "Escriba el mensaje que desea enviar...";

        if (correoDestinatario.isEmpty() || mensaje.isEmpty() || mensaje.equals(placeholder)) {
            JOptionPane.showMessageDialog(vista,
                    "Todos los campos son obligatorios (Correo del destinatario y Mensaje).",
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (mensaje.length() < 10) {
            JOptionPane.showMessageDialog(vista,
                    "El mensaje debe tener como mínimo 10 caracteres. \n(Llevas: " + mensaje.length() + ")",
                    "Mínimo De Caracteres", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (mensaje.length() > 60) {
            JOptionPane.showMessageDialog(vista,
                    "El mensaje excede el límite permitido de 60 caracteres. \n(Llevas: " + mensaje.length() + ")",
                    "Límite Excedido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (!loginDao.existeCorreo(correoDestinatario)) {
                JOptionPane.showMessageDialog(vista,
                        "El correo electrónico ingresado no se encuentra registrado en el sistema.",
                        "Correo No Existe", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idLoginDestino = loginDao.obtenerIdLoginPorCorreo(correoDestinatario);
            if (idLoginDestino == -1) {
                JOptionPane.showMessageDialog(vista, "Error al procesar la cuenta de destino.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int indexSeleccionado = vista.comboTipoNotificacion.getSelectedIndex();

            if (indexSeleccionado < 0 || listaTiposCombo == null || listaTiposCombo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Seleccione un tipo de notificación válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idTipoNotificacion = listaTiposCombo.get(indexSeleccionado).getIdTipoNotificacion();

            Notificaciones nuevaNotif = new Notificaciones(idTipoNotificacion, mensaje, idLoginDestino);
            int resultado = modelo.setAgregar(nuevaNotif);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, "Notificación enviada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                vista.txtDocumentoDestinatario.setText("");
                vista.txtAreaMensaje.setText(placeholder);
                vista.txtAreaMensaje.setForeground(new Color(110, 110, 110));

                listarNotificacionesTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al guardar en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error al procesar la solicitud: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnEnviarNotificacion) {
            registrarNuevaNotificacion();
        }

        if (e.getSource() == vista.binicio) {
            CargarInicio(inicio);
            vista.setVisible(false);
        }

        if (e.getSource() == vista.activacion_usuario) {
            CargarActivacionUsuario(usActivo);
            vista.setVisible(false);
        }

        if (e.getSource() == vista.modificar) {
            CargarModificarUsuario(modificarUsuario);
            vista.setVisible(false);
        }

        if (e.getSource() == vista.gestion_roles) {
            CargarCambioDeRol(cambio_de_rol);
            vista.setVisible(false);
        }

        if (e.getSource() == vista.registrar_usuario) {
            CargarRigistroDeUsuario(registro_de_usuario);
            vista.setVisible(false);
        }

        if (e.getSource() == vista.cerrar_sesion) {
            vista.setVisible(false);
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }
    }

    public void CargarInicio(Administrador_Inicio_Loansys_Administrador inicio) {
        inicio.setVisible(true);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarActivacionUsuario(Administrador_Usuarios_activos usActivo) {
        usActivo.setVisible(true);
        usActivo.binicio.setBackground(Color.white);
        usActivo.setExtendedState(JFrame.MAXIMIZED_BOTH);
        usActivo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarModificarUsuario(Administrador_ModificarUsuario modificarUsuario) {
        modificarUsuario.setVisible(true);
        modificarUsuario.activacion_usuario.setBackground(Color.white);
        modificarUsuario.binicio.setBackground(Color.white);
        modificarUsuario.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarCambioDeRol(Administrador_Cambio_de_rol cambio_de_rol) {
        cambio_de_rol.setVisible(true);
        cambio_de_rol.activacion_usuario.setBackground(Color.white);
        cambio_de_rol.binicio.setBackground(Color.white);
        cambio_de_rol.modificar.setBackground(Color.white);
        cambio_de_rol.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cambio_de_rol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarRigistroDeUsuario(Administrador_Registro_de_usuario registro_de_usuario) {
        registro_de_usuario.setVisible(true);
        registro_de_usuario.activacion_usuario.setBackground(Color.white);
        registro_de_usuario.binicio.setBackground(Color.white);
        registro_de_usuario.modificar.setBackground(Color.white);
        registro_de_usuario.gestion_roles.setBackground(Color.white);
        registro_de_usuario.setExtendedState(JFrame.MAXIMIZED_BOTH);
        registro_de_usuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;
import proyect_loansys.model.Notificaciones;
import proyect_loansys.model.NotificacionesDAO;
import proyect_loansys.model.PersonaDao_Login;
import proyect_loansys.model.Sesion;
import proyect_loansys.model.TipoNotificacion;
import proyect_loansys.view.Historial_Tecnico;
import proyect_loansys.view.Inicio_Tecnico;
import proyect_loansys.view.Mantenimiento;
import proyect_loansys.view.Reportes_Tecnico;
import proyect_loansys.view.Ventana_DetalleNotificacion;
import proyect_loansys.view.Vista_NotificacionesTecnico;

/**
 *
 * @author Alexis
 */
public class Controlador_NotificacionesTecnico implements ActionListener {

    private Vista_NotificacionesTecnico vista;
    private NotificacionesDAO modelo;
    private PersonaDao_Login loginDao;
    private DefaultTableModel modeloTabla;
    private List<Notificaciones> listaNotificaciones;
    private List<TipoNotificacion> listaTiposCombo;

    public Controlador_NotificacionesTecnico(Vista_NotificacionesTecnico vista) {
        this.vista = vista;
        this.modelo = new NotificacionesDAO();
        this.loginDao = new PersonaDao_Login();
        this.vista.btnEnviarNotificacion.addActionListener(this);
        
        vista.inicio.addActionListener(e -> {
            Inicio_Tecnico ini = new Inicio_Tecnico();
            new Inicio_Tecnico_Controller(ini);
            vista.dispose();
        });

        vista.historial.addActionListener(e -> {
            Historial_Tecnico vistaHistorial = new Historial_Tecnico();
            new Historial_Tecnico_Controller(vistaHistorial);
            vista.dispose();
        });

        vista.Reportes.addActionListener(e -> {
            Reportes_Tecnico vistaReportes = new Reportes_Tecnico();
            new Reportes_Tecnico_Controller(vistaReportes);
            vista.dispose();
        });

        vista.Mantenimiento.addActionListener(e -> {
            Mantenimiento vistaMantenimiento = new Mantenimiento();
            new ControllerMantenimiento(vistaMantenimiento);
            vista.dispose();
        });
        
        vista.Notificaciones.addActionListener(e -> {
            Vista_NotificacionesTecnico vistaNo = new Vista_NotificacionesTecnico();
            new Controlador_NotificacionesTecnico(vistaNo);
            vista.setVisible(false);
            vistaNo.setVisible(true);
        });


        // Escuchador para detectar el doble clic en la tabla
        this.vista.tablaNotificaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    abrirModalDetalleNotificacion();
                }
            }
        });

        // Inicialización de la vista
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

        // Notificaciones destinadas al usuario en sesión
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

    private void abrirModalDetalleNotificacion() {
        int filaSeleccionada = vista.tablaNotificaciones.getSelectedRow();

        if (filaSeleccionada != -1 && listaNotificaciones != null && !listaNotificaciones.isEmpty()) {
            Notificaciones notifSeleccionada = listaNotificaciones.get(filaSeleccionada);

            // Obtener el Frame contenedor padre para centrar la ventana modal
            Frame marcoPadre = (Frame) SwingUtilities.getWindowAncestor(vista);

            Ventana_DetalleNotificacion vistaDetalle = new Ventana_DetalleNotificacion(marcoPadre);
            Controlador_DetalleNotificacion controlDetalle = new Controlador_DetalleNotificacion(vistaDetalle, notifSeleccionada, this);

            vistaDetalle.setVisible(true);
        }
    }

    private void registrarNuevaNotificacion() {
        String correoDestinatario = vista.txtDocumentoDestinatario.getText().trim();
        String mensaje = vista.txtAreaMensaje.getText().trim();
        String placeholder = "Escriba el mensaje que desea enviar...";

        // Validaciones de formulario
        if (correoDestinatario.isEmpty() || mensaje.isEmpty() || mensaje.equals(placeholder)) {
            JOptionPane.showMessageDialog(vista,
                    "Todos los campos son obligatorios (Correo del destinatario y Mensaje).",
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validaciones de minimo de caracteres
        if (mensaje.length() < 20) {
            JOptionPane.showMessageDialog(vista,
                    "El mensaje debe tener como mínimo 20 caracteres. \n(Llevas: " + mensaje.length() + ")",
                    "Mínimo De Caracteres", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //Validacion de maximo de caracteres
        if (mensaje.length() > 100) {
            JOptionPane.showMessageDialog(vista,
                    "El mensaje excede el límite permitido de 100 caracteres. \n(Llevas: " + mensaje.length() + ")",
                    "Límite Excedido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Validar correo existente
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

            // Evitar enviarse notificaciones a sí mismo
            if (idLoginDestino == Sesion.getIdLogin()) {
                JOptionPane.showMessageDialog(vista,
                        "No puedes enviarte una notificación a ti mismo.",
                        "Destinatario Inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener el id_tipo_notificacion real según la posición seleccionada en el ComboBox
            int indexSeleccionado = vista.comboTipoNotificacion.getSelectedIndex();

            if (indexSeleccionado < 0 || listaTiposCombo == null || listaTiposCombo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Seleccione un tipo de notificación válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idTipoNotificacion = listaTiposCombo.get(indexSeleccionado).getIdTipoNotificacion();
            int idRemitente = Sesion.getIdLogin();
            Notificaciones nuevaNotif = new Notificaciones(idTipoNotificacion, mensaje, idRemitente, idLoginDestino);

            int resultado = modelo.setAgregar(nuevaNotif);

            if (resultado > 0) {
                try {
                    Administrador_Auditoria auditoria = new Administrador_Auditoria();
                    auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
                    auditoria.setAccion("Envío de notificación a: " + correoDestinatario);
                    new Administrador_AuditoriaDao().registrarAccion(auditoria);
                } catch (Exception eAuditoria) {
                    System.err.println("Error al registrar auditoría: " + eAuditoria.getMessage());
                }

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
    }
}
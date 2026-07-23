/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Notificaciones;
import proyect_loansys.model.NotificacionesDAO;
import proyect_loansys.model.PersonaDao_Login;
import proyect_loansys.model.Sesion;
import proyect_loansys.model.TipoNotificacion;
import proyect_loansys.view.Vista_NotificacionesUsuario;

/**
 *
 * @author Alexis
 */
public class Controlador_NotificacionesUsuario implements ActionListener {

    private Vista_NotificacionesUsuario vista;
    private NotificacionesDAO modelo;
    private PersonaDao_Login loginDao;
    private DefaultTableModel modeloTabla;
    private List<Notificaciones> listaNotificaciones;
    private List<TipoNotificacion> listaTiposCombo;

    public Controlador_NotificacionesUsuario(Vista_NotificacionesUsuario vista) {
        this.vista = vista;
        this.modelo = new NotificacionesDAO();
        this.loginDao = new PersonaDao_Login();
        // Escuchadores de eventos
        this.vista.btnEnviarNotificacion.addActionListener(this);
        // Inicialización de la vista
        listarNotificacionesTabla();
        cargarComboTipos();
    }

    public void cargarComboTipos() {
        vista.comboTipoNotificacion.removeAllItems();

        int idRolActual = Sesion.getIdRol();
        listaTiposCombo = modelo.listarTiposPorRol(idRolActual);

        for (TipoNotificacion t : listaTiposCombo) {
            vista.comboTipoNotificacion.addItem(t.getNombreTipoNotificacion());
        }
    }

    public void listarNotificacionesTabla() {
        modeloTabla = (DefaultTableModel) vista.tablaNotificaciones.getModel();
        modeloTabla.setRowCount(0);

        // Notificaciones destinadas al usuario en sesión
        int idUsuarioActual = Sesion.getIdLogin();
        listaNotificaciones = modelo.listarPorUsuario(idUsuarioActual);
        Object[] fila = new Object[2];

        for (Notificaciones notif : listaNotificaciones) {
            fila[0] = notif.getNombreTipoNotificacion();
            fila[1] = notif.getMensaje();
            modeloTabla.addRow(fila);
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

            // Obtener el id_tipo_notificacion real según la posición seleccionada en el ComboBox
            int indexSeleccionado = vista.comboTipoNotificacion.getSelectedIndex();

            if (indexSeleccionado < 0 || listaTiposCombo == null || listaTiposCombo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Seleccione un tipo de notificación válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idTipoNotificacion = listaTiposCombo.get(indexSeleccionado).getIdTipoNotificacion();

            // Guardar notificación
            Notificaciones nuevaNotif = new Notificaciones(idTipoNotificacion, mensaje, idLoginDestino);
            int resultado = modelo.setAgregar(nuevaNotif);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, "Notificación enviada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                vista.txtDocumentoDestinatario.setText("");
                vista.txtAreaMensaje.setText(placeholder);
                vista.txtAreaMensaje.setForeground(new java.awt.Color(110, 110, 110));

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

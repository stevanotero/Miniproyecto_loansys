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
import proyect_loansys.view.Vista_Devoluciones;
import proyect_loansys.view.Vista_GestionUsuarios;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.view.Vista_Reportes_Asesor;
import proyect_loansys.view.Vista_Solicitudes;
import proyect_loansys.view.Vista_Notificaciones;
import proyect_loansys.view.Vista_Prestamo;

/**
 *
 * @author Alexis
 */
public class Controlador_Notificaciones implements ActionListener {

    private Vista_Notificaciones vista;
    private NotificacionesDAO modelo;
    private PersonaDao_Login loginDao;
    private DefaultTableModel modeloTabla;
    private List<Notificaciones> listaNotificaciones; 
    private List<TipoNotificacion> listaTiposCombo;    

    public Controlador_Notificaciones(Vista_Notificaciones vista) {
        this.vista = vista;
        this.modelo = new NotificacionesDAO();
        this.loginDao = new PersonaDao_Login();
        // Activar los botones
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonInventario.addActionListener(this);
        this.vista.botonPrestamos.addActionListener(this);
        this.vista.botonDevoluciones.addActionListener(this);
        this.vista.btnEnviarNotificacion.addActionListener(this);
        this.vista.botonReportes.addActionListener(this);
        this.vista.botonNotificaciones.addActionListener(this);
        this.vista.botonUsuarios.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);
        this.vista.botonCerrarSesion.addActionListener(this);
        // Cargar datos al iniciar
        listarNotificacionesTabla();
        cargarComboTipos(); 
    }
    

    public void cargarComboTipos() {
        vista.comboTipoNotificacion.removeAllItems();
        int idRolActual = Sesion.getIdRol(); //Obtiene el rol activo
        
        // Guarda los objetos en la nueva lista
        listaTiposCombo = modelo.listarTiposPorRol(idRolActual);
        // Agregamos solo el nombre (String) al ComboBox
        for (TipoNotificacion t : listaTiposCombo) {
            vista.comboTipoNotificacion.addItem(t.getNombreTipoNotificacion());
        }
    }

    public void listarNotificacionesTabla() {
        modeloTabla = (DefaultTableModel) vista.tablaNotificaciones.getModel();
        modeloTabla.setRowCount(0);

        // Trae las notificaciones del usuario en sesión
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

        // Validación de campos vacíos
        if (correoDestinatario.isEmpty() || mensaje.isEmpty() || mensaje.equals(placeholder)) {
            JOptionPane.showMessageDialog(vista,
                    "Todos los campos son obligatorios (Correo del destinatario y Mensaje).",
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Validación de mínimo de caracteres
        if (mensaje.length() < 10) {
            JOptionPane.showMessageDialog(vista,
                    "El mensaje debe tener como mínimo 10 caracteres. \n(Llevas: " + mensaje.length() + ")",
                    "Mínimo De Caracteres", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación de máximo de caracteres
        if (mensaje.length() > 60) {
            JOptionPane.showMessageDialog(vista,
                    "El mensaje excede el límite permitido de 60 caracteres. \n(Llevas: " + mensaje.length() + ")",
                    "Límite Excedido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Verificar si el correo existe
            if (!loginDao.existeCorreo(correoDestinatario)) {
                JOptionPane.showMessageDialog(vista,
                        "El correo electrónico ingresado no se encuentra registrado en el sistema.",
                        "Correo No Existe", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Obtener el id_login del destinatario
            int idLoginDestino = loginDao.obtenerIdLoginPorCorreo(correoDestinatario);

            if (idLoginDestino == -1) {
                JOptionPane.showMessageDialog(vista, "Error al procesar la cuenta de destino.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // obtener el id según la opción seleccionada
            int indexSeleccionado = vista.comboTipoNotificacion.getSelectedIndex();

            if (indexSeleccionado < 0 || listaTiposCombo == null || listaTiposCombo.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Seleccione un tipo de notificación válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idTipoNotificacion = listaTiposCombo.get(indexSeleccionado).getIdTipoNotificacion();

            // Registrar notificación
            Notificaciones nuevaNotif = new Notificaciones(idTipoNotificacion, mensaje, idLoginDestino);
            int resultado = modelo.setAgregar(nuevaNotif);

            if (resultado > 0) {
                JOptionPane.showMessageDialog(vista, "Notificación enviada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Limpieza de campos
                vista.txtDocumentoDestinatario.setText("");
                vista.txtAreaMensaje.setText(placeholder);
                vista.txtAreaMensaje.setForeground(new java.awt.Color(110, 110, 110));

                // Refrescar tabla
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
        
        //Modulo inicio
        if (e.getSource() == vista.botonInicio) {
            vista.dispose();
            Vista_Inicio vistaIni = new Vista_Inicio();
            Controlador_inicio controlador = new Controlador_inicio(vistaIni);
            vistaIni.setVisible(true);
        }
        //Modulo del login
        if (e.getSource() == vista.botonCerrarSesion) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }
        
        //Modulo de inventario
        if (e.getSource() == vista.botonInventario) {
            vista.dispose();
            Vista_Inventario vistaInventario = new Vista_Inventario();
            Controlador_inventario controladorIn = new Controlador_inventario(vistaInventario);
            vistaInventario.setVisible(true);
        }
        
        //Modulo de gestion de solicitudes
        if (e.getSource() == vista.botonSolicitudes) {
            vista.dispose();
            Vista_Solicitudes vistaSolicitud = new Vista_Solicitudes();
            Controlador_Solicitudes controladorSol = new Controlador_Solicitudes(vistaSolicitud);
            vistaSolicitud.setVisible(true);
        }
        
        //Modulo de prestamo
        if (e.getSource() == vista.botonPrestamos) {
            vista.dispose();
            Vista_Prestamo vistap = new Vista_Prestamo();
            Controlador_Prestamos controlPrestamo = new Controlador_Prestamos(vistap);
            vistap.setVisible(true);
        }
        
        //Modulo de devoluciones
        if (e.getSource() == vista.botonDevoluciones) {
            vista.dispose();
            Vista_Devoluciones vistaDev = new Vista_Devoluciones();
            Controlador_Devoluciones controlDev = new Controlador_Devoluciones(vistaDev);
            vistaDev.setVisible(true);
        }
        
        //Modulo de gestión de usuarios
        if (e.getSource() == vista.botonUsuarios) {
            vista.dispose();
            Vista_GestionUsuarios vistaUsers = new Vista_GestionUsuarios();
            Controlador_GestionUsuarios controlUsers = new Controlador_GestionUsuarios(vistaUsers);
            vistaUsers.setVisible(true);
        }
        //Modulo de reportes
        if (e.getSource()== vista.botonReportes){
        vista.dispose();
        Vista_Reportes_Asesor vistaRep = new Vista_Reportes_Asesor();
        Controlador_Reportes_Asesor controlRep = new Controlador_Reportes_Asesor(vistaRep);
        vistaRep.setVisible(true);
        }
    }
}
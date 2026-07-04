/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.model.Solicitudes;
import proyect_loansys.model.SolicitudesDao;
import proyect_loansys.view.Vista_Solicitudes;
import proyect_loansys.view.VentanaGestionarSolicitud;

/**
 * @author Alexis
 */
public class Controlador_Solicitudes implements ActionListener {

    private Vista_Solicitudes vista;
    private SolicitudesDao modelo;
    private DefaultTableModel modeloTabla;

    public Controlador_Solicitudes(Vista_Solicitudes vista) {
        this.vista = vista;
        this.modelo = new SolicitudesDao();
        this.vista.botonCerrarSesion.addActionListener(this);

        // Cargar las solicitudes en la JTable al iniciar el panel
        listarSolicitudesTabla();

        // Al hacer doble clic que se abra la modal
        this.vista.tablaDeSolicitudes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    abrirFormularioGestionar();
                }
            }
        });
    }

    public void listarSolicitudesTabla() {
        modeloTabla = (DefaultTableModel) vista.tablaDeSolicitudes.getModel();
        modeloTabla.setRowCount(0); // Limpiar filas existentes

        List<Solicitudes> lista = modelo.listarSolicitudes();
        Object[] fila = new Object[7]; //columnas para acoplarse perfecto a tu JTable visual

        for (Solicitudes sol : lista) {
            fila[0] = sol.getIdSolicitud();
            fila[1] = sol.getCodigoElemento();
            fila[2] = sol.getNombreElemento();
            fila[3] = sol.getNombreUsuario() + " " + sol.getApellidoUsuario(); //Juntamos Nombre y Apellido aquí
            fila[4] = sol.getDocumentoUsuario();
            fila[5] = sol.getNombreRol();
            fila[6] = sol.getFechaEnvio();
            modeloTabla.addRow(fila);
        }
    }

    private void abrirFormularioGestionar() {
        int filaSeleccionada = vista.tablaDeSolicitudes.getSelectedRow();
        if (filaSeleccionada != -1) {
            
            //Extraemos los datos basándonos estrictamente en las 7 columnas de la vista (0 al 6)
            String idSolicitud = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 0).toString();
            String codigoElemento = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 1).toString();
            String nombreElemento = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 2).toString();
            String solicitante = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 3).toString();
            String documentoUsuario = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 4).toString();
            String nombreRol = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 5).toString();
            String fechaEnvio = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 6).toString();

            // Abrimos la vista modal pasándole null como Frame padre
            VentanaGestionarSolicitud ventanaGestion = new VentanaGestionarSolicitud(null);
            
            // Rellenamos los campos usando tus variables EXACTAS en español de la vista
            ventanaGestion.textoIdSolicitud.setText(idSolicitud);
            ventanaGestion.textoCodigoElemento.setText(codigoElemento); // Corregida la variable
            ventanaGestion.textoNombreElemento.setText(nombreElemento);
            ventanaGestion.textoUsuario.setText(solicitante);
            ventanaGestion.textoDocumento.setText(documentoUsuario);
            ventanaGestion.textoRol.setText(nombreRol);
            ventanaGestion.textoFecha.setText(fechaEnvio);
            
            ventanaGestion.setLocationRelativeTo(vista);
            ventanaGestion.setVisible(true);
            
            // Al cerrar la modal, refrescamos la tabla automáticamente
            listarSolicitudesTabla();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.botonCerrarSesion) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }
        
        if (e.getSource() == vista.botonInventario) {
            vista.dispose();
            Vista_Inventario vistaInventario = new Vista_Inventario();
            Controlador_inventario controladorIn = new Controlador_inventario(vistaInventario);
            vistaInventario.setVisible(true);
        }
    }
}
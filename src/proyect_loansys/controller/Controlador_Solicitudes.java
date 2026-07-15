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
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.view.Vista_Prestamo;

/**
 * @author Alexis
 */
public class Controlador_Solicitudes implements ActionListener {

    private Vista_Solicitudes vista;
    private SolicitudesDao modelo;
    private DefaultTableModel modeloTabla;
    private List<Solicitudes> listaSolicitudes; 

    public Controlador_Solicitudes(Vista_Solicitudes vista) {
        this.vista = vista;
        this.modelo = new SolicitudesDao();
        this.vista.botonCerrarSesion.addActionListener(this);
        this.vista.botonInventario.addActionListener(this); 
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);

        listarSolicitudesTabla();

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
        modeloTabla.setRowCount(0); 

        listaSolicitudes = modelo.listarSolicitudes();
        Object[] fila = new Object[7]; 

        for (Solicitudes sol : listaSolicitudes) {
            fila[0] = sol.getIdSolicitud();
            fila[1] = sol.getCodigoElemento();
            fila[2] = sol.getNombreElemento();
            fila[3] = sol.getNombreUsuario() + " " + sol.getApellidoUsuario(); 
            fila[4] = sol.getDocumentoUsuario();
            fila[5] = sol.getNombreRol();
            fila[6] = sol.getFechaEnvio();
            modeloTabla.addRow(fila);
        }
    }

    private void abrirFormularioGestionar() {
        int filaSeleccionada = vista.tablaDeSolicitudes.getSelectedRow();
        if (filaSeleccionada != -1) {

            // 🌟 CORREGIDO: Declaramos la variable de la solicitud seleccionada con sus IDs completos
            Solicitudes solicitudSeleccionada = listaSolicitudes.get(filaSeleccionada);

            String idSolicitud = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 0).toString();
            String codigoElemento = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 1).toString();
            String nombreElemento = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 2).toString();
            String solicitante = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 3).toString();
            String documentoUsuario = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 4).toString();
            String nombreRol = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 5).toString();
            String fechaEnvio = vista.tablaDeSolicitudes.getValueAt(filaSeleccionada, 6).toString();

            VentanaGestionarSolicitud ventanaGestion = new VentanaGestionarSolicitud(null);
            
            // Pasamos los objetos limpios al controlador modal
            ControladorModal_solicitudes gestionModal = new ControladorModal_solicitudes(ventanaGestion, solicitudSeleccionada, this); 

            ventanaGestion.textoIdSolicitud.setText(idSolicitud);
            ventanaGestion.textoCodigoElemento.setText(codigoElemento); 
            ventanaGestion.textoNombreElemento.setText(nombreElemento);
            ventanaGestion.textoUsuario.setText(solicitante);
            ventanaGestion.textoDocumento.setText(documentoUsuario);
            ventanaGestion.textoRol.setText(nombreRol);
            ventanaGestion.textoFecha.setText(fechaEnvio);

            ventanaGestion.setLocationRelativeTo(vista);
            ventanaGestion.setVisible(true);

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
        
        if (e.getSource() == vista.botonInicio) {
            vista.dispose();
            Vista_Inicio vistaIni = new Vista_Inicio();
            Controlador_inicio controlin = new Controlador_inicio(vistaIni);
            vistaIni.setVisible(true);
        }
        if(e.getSource() == vista.botonPrestamos){
            vista.dispose();
            Vista_Prestamo vistap = new Vista_Prestamo();
            Controlador_Prestamos controlp = new Controlador_Prestamos(vistap);
            vistap.setEnabled(true);
        }
    }
}
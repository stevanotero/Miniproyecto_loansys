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
import proyect_loansys.model.Prestamos;
import proyect_loansys.model.PrestamosActivosDao;
import proyect_loansys.model.PrestamosDao;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Inicio;
import proyect_loansys.view.Vista_Prestamo;
import proyect_loansys.view.Vista_Solicitudes;

// Nota: Importa aquí tus vistas modales cuando las crees
// import proyect_loansys.view.VentanaGestionarPrestamo;

/**
 * @author Alexis
 */
public class Controlador_Prestamos implements ActionListener {

    private Vista_Prestamo vista;
    private PrestamosActivosDao modelo;
    private DefaultTableModel modeloTabla;
    private List<Prestamos> listaPrestamos; 

    public Controlador_Prestamos(Vista_Prestamo vista) {
        this.vista = vista;
        this.modelo = new PrestamosActivosDao();
        
        // Escuchadores del menú principal heredados de Vista_Principal
        this.vista.botonCerrarSesion.addActionListener(this);
        this.vista.botonInventario.addActionListener(this); 
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonSolicitudes.addActionListener(this);
        // Si tienes el botón de préstamos en el menú, lo escuchas también:
        // this.vista.botonPrestamos.addActionListener(this);

        listarPrestamosTabla();

        // Evento de doble clic para gestionar el préstamo
        this.vista.tablaDePrestamos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    abrirFormularioGestionar();
                }
            }
        });
    }

    public void listarPrestamosTabla() {
        modeloTabla = (DefaultTableModel) vista.tablaDePrestamos.getModel();
        modeloTabla.setRowCount(0); 

        listaPrestamos = modelo.listar();
        Object[] fila = new Object[8]; // 8 columnas según tu Vista_Prestamos

        for (Prestamos pres : listaPrestamos) {
            fila[0] = pres.getIdPrestamo();
            fila[1] = pres.getCodigoElemento();
            fila[2] = pres.getNombreElemento();
            fila[3] = pres.getNombreUsuario() + " " + pres.getApellidoUsuario(); 
            fila[4] = pres.getDocumentoUsuario();
            fila[5] = pres.getFechaInicioPrestamo();
            fila[6] = pres.getFechaFinDevolucion();
            fila[7] = pres.getNombreEstadoElemento();
            modeloTabla.addRow(fila);
        }
    }

    private void abrirFormularioGestionar() {
        int filaSeleccionada = vista.tablaDePrestamos.getSelectedRow();
        if (filaSeleccionada != -1) {

            // Objeto completo seleccionado con sus IDs ocultos de Base de Datos
            Prestamos prestamoSeleccionado = listaPrestamos.get(filaSeleccionada);

            // Extracción de datos de las celdas de la tabla
            String idPrestamo = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 0).toString();
            String codigoElemento = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 1).toString();
            String nombreElemento = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 2).toString();
            String usuario = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 3).toString();
            String documentoUsuario = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 4).toString();
            String fechaInicio = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 5).toString();
            String fechaDevolucion = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 6).toString();
            String estado = vista.tablaDePrestamos.getValueAt(filaSeleccionada, 7).toString();

            /* 
               Cuando crees la vista de la modal, solo descomentas estas líneas:
               
            VentanaGestionarPrestamo ventanaGestion = new VentanaGestionarPrestamo(null);
            
            // Pasamos los objetos limpios al controlador de la modal
            ControladorModal_prestamos gestionModal = new ControladorModal_prestamos(ventanaGestion, prestamoSeleccionado, this); 

            ventanaGestion.textoIdPrestamo.setText(idPrestamo);
            ventanaGestion.textoCodigoElemento.setText(codigoElemento); 
            ventanaGestion.textoNombreElemento.setText(nombreElemento);
            ventanaGestion.textoUsuario.setText(usuario);
            ventanaGestion.textoDocumento.setText(documentoUsuario);
            ventanaGestion.textoFechaInicio.setText(fechaInicio);
            ventanaGestion.textoFechaDevolucion.setText(fechaDevolucion);
            ventanaGestion.textoEstado.setText(estado);

            ventanaGestion.setLocationRelativeTo(vista);
            ventanaGestion.setVisible(true);

            // Refrescar la tabla al cerrar la modal
            listarPrestamosTabla();
            */
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
        
        if (e.getSource() == vista.botonSolicitudes) {
            vista.dispose();
            Vista_Solicitudes vistaSol = new Vista_Solicitudes();
            Controlador_Solicitudes controlSol = new Controlador_Solicitudes(vistaSol);
            vistaSol.setVisible(true);
        }
    }
}
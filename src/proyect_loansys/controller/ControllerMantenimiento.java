/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

/**
 *
 * @author Sants
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.HistorialMantenimientoDao;
import proyect_loansys.view.Formulario;
import proyect_loansys.view.Historial_Tecnico;
import proyect_loansys.view.Inicio_Tecnico;
import proyect_loansys.view.Mantenimiento;
import proyect_loansys.view.Reportes_Tecnico;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_NotificacionesTecnico;

public class ControllerMantenimiento implements ActionListener {

    private Mantenimiento vista;
    private HistorialMantenimientoDao dao = new HistorialMantenimientoDao();

    public ControllerMantenimiento(Mantenimiento vista) {
        this.vista = vista;
        this.vista.getBtnRegistrarMantenimiento().addActionListener(this);
        
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
        
           vista.inicio.addActionListener(e -> {
            Inicio_Tecnico ini = new Inicio_Tecnico();
            new Inicio_Tecnico_Controller(ini);
            vista.dispose();
        });
           
            vista.Notificaciones.addActionListener(e -> {
            Vista_NotificacionesTecnico vistaNo = new Vista_NotificacionesTecnico();
            new Controlador_NotificacionesTecnico(vistaNo);
            vista.setVisible(false);
            vistaNo.setVisible(true);
        });
            vista.cerrar_sesion.addActionListener(e -> {
            Vista_Login vistalog = new Vista_Login();
            new Controlador_Login(vistalog);
            vistalog.setVisible(true);
            vista.dispose();
        });


        //filtra la tabla mientras uno escribe pues
        this.vista.getTxtBuscar().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                buscar();
            }
        });

        cargarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrarMantenimiento()) {
            abrirFormulario();
        }
    }

    private void abrirFormulario() {
        Formulario formulario = new Formulario();
        new ControllerFormulario(formulario); // conecta el formulario con la base de datos

        JDialog dialogo = new JDialog(vista, "Registrar mantenimiento", true);
        dialogo.getContentPane().add(formulario);
        dialogo.setSize(770, 550);
        dialogo.setResizable(false); //bloquea la ventana esa para q no se mueva de nuevo
        dialogo.setLocationRelativeTo(vista);

        //cuando se cierre el formulario se pone de nuevo en cero total
        dialogo.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent we) {
                cargarTabla();
            }
        });

        dialogo.setVisible(true);
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.getTabla().getModel();
        modelo.setRowCount(0); //lo deja vacio antes de refrescar

        List<Object[]> datos = dao.listarPanel();
        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
    }

    private void buscar() {
        String texto = vista.getTxtBuscar().getText().trim();

        DefaultTableModel modelo = (DefaultTableModel) vista.getTabla().getModel();
        modelo.setRowCount(0);

        List<Object[]> datos;
        if (texto.isEmpty() || texto.equals("Buscar...")) {
            datos = dao.listarPanel();
        } else {
            datos = dao.buscarPanel(texto);
        }

        for (Object[] fila : datos) {
            modelo.addRow(fila);
        }
    }
}
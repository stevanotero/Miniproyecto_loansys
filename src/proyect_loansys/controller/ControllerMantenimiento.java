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
import modelTecnico.MantenimientoDao;
import viewTecnico.Formulario;
import viewTecnico.Mantenimiento;

public class ControllerMantenimiento implements ActionListener {

    private Mantenimiento vista;
    private MantenimientoDao dao = new MantenimientoDao();

    public ControllerMantenimiento(Mantenimiento vista) {
        this.vista = vista;
        this.vista.getBtnRegistrarMantenimiento().addActionListener(this);

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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.view.Administrador_Inicio_Loansys_Administrador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import proyect_loansys.model.PDFExporter;

/**
 *
 * @author juans
 */
public class Administrador_ControladorInicioAdministrador implements ActionListener {

    private Administrador_Inicio_Loansys_Administrador inicio;

    Administrador_Auditoria auditoria = new Administrador_Auditoria();
    Administrador_AuditoriaDao auditoriaDao = new Administrador_AuditoriaDao();

    DefaultTableModel modelo = new DefaultTableModel();
    private int idAuditoria;
    private int idUsuario;

    public Administrador_ControladorInicioAdministrador(Administrador_Inicio_Loansys_Administrador inicio) {

        this.inicio = inicio;
        this.inicio.exportar.addActionListener(this);

        limpiarTabla();
        getListar(inicio.tabla);

    }
public void getListar(JTable tabla) {

        modelo = (DefaultTableModel) tabla.getModel();

        List<Administrador_Auditoria> lista = auditoriaDao.listar();

        Object[] datos = new Object[4];

        for (int i = 0; i < lista.size(); i++) {

            datos[0] = lista.get(i).getDocumento();
            datos[1] = lista.get(i).getNombreCompleto();
            datos[2] = lista.get(i).getAccion();
            datos[3] = lista.get(i).getFechaHora();

            modelo.addRow(datos);

        }

        inicio.tabla.setModel(modelo);

    }


    public void limpiarTabla() {

        modelo = (DefaultTableModel) inicio.tabla.getModel();

        while (modelo.getRowCount() > 0) {

            modelo.removeRow(0);

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==inicio.exportar){
                     boolean exito = PDFExporter.generarPDFConSelector(
        inicio.tabla,
"Reporte de Movimientos de Usuario",
        "reporte_movimientos_usuario",
        inicio
    );

    if (exito) {
        JOptionPane.showMessageDialog(null, "Reporte PDF guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
       }
    }

}
    





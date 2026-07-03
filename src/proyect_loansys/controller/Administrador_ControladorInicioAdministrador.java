/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.view.Administrador_Inicio_Loansys_Administrador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juans
 */
public class Administrador_ControladorInicioAdministrador {

    private Administrador_Inicio_Loansys_Administrador inicio;

    Administrador_Auditoria auditoria = new Administrador_Auditoria();
    Administrador_AuditoriaDao auditoriaDao = new Administrador_AuditoriaDao();

    DefaultTableModel modelo = new DefaultTableModel();
    private int idAuditoria;
    private int idUsuario;

    public Administrador_ControladorInicioAdministrador(Administrador_Inicio_Loansys_Administrador inicio) {

        this.inicio = inicio;

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

    //================ LIMPIAR TABLA =================//
    public void limpiarTabla() {

        modelo = (DefaultTableModel) inicio.tabla.getModel();

        while (modelo.getRowCount() > 0) {

            modelo.removeRow(0);

        }

    }

}
    





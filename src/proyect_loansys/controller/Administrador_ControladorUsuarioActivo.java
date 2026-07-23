/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Administrador_Usuario;
import proyect_loansys.model.Administrador_UsuarioDao;
import proyect_loansys.view.Administrador_Inicio_Loansys_Administrador;
import proyect_loansys.view.Administrador_Modal_Usuarios_Activos;
import proyect_loansys.view.Administrador_Plantilla_Administrador;
import proyect_loansys.view.Administrador_Usuarios_activos;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;

/**
 *
 * @author juans
 */
public class Administrador_ControladorUsuarioActivo implements ActionListener {

    private Administrador_Usuarios_activos usActivo;

    private Administrador_Modal_Usuarios_Activos modal;
    Administrador_Usuario usuario = new Administrador_Usuario();
    Administrador_UsuarioDao usuarioDao = new Administrador_UsuarioDao();
    private boolean veri = false;

    DefaultTableModel modelo = new DefaultTableModel();
    Administrador_Auditoria auditoria = new Administrador_Auditoria();
    Administrador_AuditoriaDao auditoriaDao = new Administrador_AuditoriaDao();

    public Administrador_ControladorUsuarioActivo(Administrador_Usuarios_activos usActivo, Administrador_Modal_Usuarios_Activos modal) {
        this.modal = modal;
        this.usActivo = usActivo;
        this.usActivo.botonA.addActionListener(this);
        this.modal.cancelar.addActionListener(this);
        this.modal.guardar.addActionListener(this);
        this.usActivo.botonFiltroAct.addActionListener(this);
        limpiarTabla();
        getListar(usActivo.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == usActivo.botonA) {

            int fila = usActivo.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(usActivo, "Debes de selecionar un usuario para cambiar su informacion ", "", JOptionPane.WARNING_MESSAGE);
            } else {
                cargarDatosModal();
                CargarModal(modal);

            }
        }
        if (e.getSource() == modal.cancelar) {

            modal.dispose();
            JOptionPane.showMessageDialog(null, "No se registro ningun cambio", "", JOptionPane.WARNING_MESSAGE);
        }
        if (e.getSource() == modal.guardar) {
            setActualizarEstado();

        }
        if (e.getSource() == usActivo.botonFiltroAct) {
            String docuemnto = usActivo.filtroAct.getText().trim();
            if (usActivo.tabla.isEditing()) {
                usActivo.tabla.getCellEditor().cancelCellEditing();
            }

            modelo = (DefaultTableModel) usActivo.tabla.getModel();
            modelo.setRowCount(0);
            if (docuemnto.isEmpty() || docuemnto.matches("[0-9]+")) {
                getListarFiltro(usActivo.tabla, docuemnto);
            } else {
                JOptionPane.showMessageDialog(usActivo, "Solo se permiten números enteros, sin puntos ni guiones.");
            }

        }
    }

    public void CargarModal(Administrador_Modal_Usuarios_Activos modal) {
        modal.setSize(450, 350);
        modal.setLocationRelativeTo(null);
        modal.setModal(true);
        modal.setVisible(true);
    }

    public void getListar(JTable tabla) {

        modelo = (DefaultTableModel) tabla.getModel();

        List<Administrador_Usuario> lista = usuarioDao.listar();

        Object[] object = new Object[6];

        for (int indice = 0; indice < lista.size(); indice++) {

            object[0] = lista.get(indice).getDocumento();
            object[1] = lista.get(indice).getNombre();
            object[2] = lista.get(indice).getApellido();
            object[3] = lista.get(indice).getCorreo();

            object[5] = lista.get(indice).getNombreEstado();

            object[4] = lista.get(indice).getNombreRol();

            modelo.addRow(object);
        }

        usActivo.tabla.setModel(modelo);
    }

    public void limpiarTabla() {

        for (int i = 0; i < usActivo.tabla.getRowCount(); i++) {

            modelo.removeRow(i);

            i = i - 1;
        }

    }

    public void cargarDatosModal() {

        int fila = usActivo.tabla.getSelectedRow();

        modal.txtDocumento.setText(usActivo.tabla.getValueAt(fila, 0).toString());
        modal.txtNombre.setText(usActivo.tabla.getValueAt(fila, 1).toString());
        modal.txtApellido.setText(usActivo.tabla.getValueAt(fila, 2).toString());
        modal.txtCorreo.setText(usActivo.tabla.getValueAt(fila, 3).toString());

        modal.estado.setSelectedItem(usActivo.tabla.getValueAt(fila, 5).toString());

    }

    public void setActualizarEstado() {

        int resultado;

        usuario.setDocumento(Integer.parseInt(modal.txtDocumento.getText()));

        if (modal.estado.getSelectedItem().toString().equals("ACTIVO")) {

            usuario.setIdEstado(1);

        } else if (modal.estado.getSelectedItem().toString().equals("DESACTIVADO")) {

            usuario.setIdEstado(2);

        }

        resultado = usuarioDao.setActualizarEstado(usuario);

        if (resultado > 0) {
            auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());

            if (usuario.getIdEstado() == 1) {
                auditoria.setAccion("Activó un usuario");
            } else {
                auditoria.setAccion("Desactivó un usuario");
            }
            auditoriaDao.registrarAccion(auditoria);
            JOptionPane.showMessageDialog(modal,
                    "Estado actualizado correctamente");
            modal.dispose();
            borrarModal();
            limpiarTabla();
            getListar(usActivo.tabla);

        } else {

            JOptionPane.showMessageDialog(modal,
                    "No se pudo actualizar el estado");
        }
    }

    public void getListarFiltro(JTable tabla, String documento) {
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // limpia la tabla antes de volver a llenarla
        List<Administrador_Usuario> lista = usuarioDao.listar();
        Object[] object = new Object[6];

        for (int indice = 0; indice < lista.size(); indice++) {
            String docActual = String.valueOf(lista.get(indice).getDocumento());

            if (documento.isEmpty() || docActual.contains(documento)) {
                object[0] = lista.get(indice).getDocumento();
                object[1] = lista.get(indice).getNombre();
                object[2] = lista.get(indice).getApellido();
                object[3] = lista.get(indice).getCorreo();
                object[5] = lista.get(indice).getNombreEstado();
                object[4] = lista.get(indice).getNombreRol();
                modelo.addRow(object);
            }
        }
        usActivo.tabla.setModel(modelo);
    }

    public void borrarModal() {
        modal.txtDocumento.setText("");
        modal.txtNombre.setText("");
        modal.txtCorreo.setText("");
        modal.txtApellido.setText("");
        modal.estado.setSelectedIndex(0);

    }

}

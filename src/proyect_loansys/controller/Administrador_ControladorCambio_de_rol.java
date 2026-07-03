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
import proyect_loansys.view.Administrador_Cambio_de_rol;
import proyect_loansys.view.Administrador_Modal_cambio_de_rol;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;

/**
 *
 * @author juans
 */
public class Administrador_ControladorCambio_de_rol implements ActionListener {

    public Administrador_Cambio_de_rol cambio_de_rol;
    public Administrador_Modal_cambio_de_rol modal;
    DefaultTableModel modelo = new DefaultTableModel();
    Administrador_Usuario usuario = new Administrador_Usuario();
    Administrador_UsuarioDao usuarioDao = new Administrador_UsuarioDao();
    Administrador_Auditoria auditoria = new Administrador_Auditoria();
    Administrador_AuditoriaDao auditoriaDao = new Administrador_AuditoriaDao();

    public Administrador_ControladorCambio_de_rol(Administrador_Cambio_de_rol cambio_de_rol, Administrador_Modal_cambio_de_rol modal) {
        this.cambio_de_rol = cambio_de_rol;
        this.modal = modal;
        this.cambio_de_rol.botonC.addActionListener(this);
        this.modal.cancelar.addActionListener(this);
        this.modal.guardar.addActionListener(this);
        limpiarTabla();
        getListar(cambio_de_rol.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cambio_de_rol.botonC) {

            int fila = cambio_de_rol.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(cambio_de_rol, "Debes de selecionar un usuario para cambiar su informacion ", "", JOptionPane.WARNING_MESSAGE);
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
            setActualizarRol();
        }
    }

    public void CargarModal(Administrador_Modal_cambio_de_rol modal) {
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

        cambio_de_rol.tabla.setModel(modelo);
    }

    public void cargarDatosModal() {

        int fila = cambio_de_rol.tabla.getSelectedRow();

        modal.txtDocumento.setText(cambio_de_rol.tabla.getValueAt(fila, 0).toString());
        modal.txtNombre.setText(cambio_de_rol.tabla.getValueAt(fila, 1).toString());
        modal.txtApellido.setText(cambio_de_rol.tabla.getValueAt(fila, 2).toString());
        modal.txtCorreo.setText(cambio_de_rol.tabla.getValueAt(fila, 3).toString());

        modal.rol.setSelectedItem(cambio_de_rol.tabla.getValueAt(fila, 4).toString());

    }

    public void limpiarTabla() {

        for (int i = 0; i < cambio_de_rol.tabla.getRowCount(); i++) {

            modelo.removeRow(i);
            i--;

        }

    }

    public void borrarModal() {

        modal.txtDocumento.setText("");
        modal.txtNombre.setText("");
        modal.txtApellido.setText("");
        modal.txtCorreo.setText("");

        modal.rol.setSelectedIndex(0);

    }

    public void setActualizarRol() {

        int resultado;

        usuario.setDocumento(
                Integer.parseInt(modal.txtDocumento.getText()));

        switch (modal.rol.getSelectedItem().toString()) {

            case "APRENDIZ":
                usuario.setIdRol(1);
                break;

            case "INSTRUCTOR":
                usuario.setIdRol(2);
                break;

            case "TECNICO":
                usuario.setIdRol(3);
                break;

            case "ASESOR":
                usuario.setIdRol(4);
                break;

            case "ADMINISTRADOR":
                usuario.setIdRol(5);
                break;
        }

        resultado = usuarioDao.setActualizarRol(usuario);

        if (resultado > 0) {
            auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
            auditoria.setAccion("Cambió el rol de un usuario");
            auditoriaDao.registrarAccion(auditoria);
            JOptionPane.showMessageDialog(modal,
                    "Rol actualizado correctamente");

            modal.dispose();

            borrarModal();

            limpiarTabla();

            getListar(cambio_de_rol.tabla);

        } else {

            JOptionPane.showMessageDialog(modal,
                    "No se pudo actualizar el rol");

        }

    }

}

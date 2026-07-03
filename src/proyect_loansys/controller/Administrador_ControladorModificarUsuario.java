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
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Usuario;
import proyect_loansys.model.Administrador_UsuarioDao;
import proyect_loansys.view.Administrador_Modal_Modificar_Usuario;
import proyect_loansys.view.Administrador_Modal_Usuarios_Activos;
import proyect_loansys.view.Administrador_ModificarUsuario;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;

/**
 *
 * @author juans
 */
public class Administrador_ControladorModificarUsuario implements ActionListener {

    public Administrador_ModificarUsuario modi;
    public Administrador_Modal_Modificar_Usuario modal;
    Administrador_Usuario usuario = new Administrador_Usuario();
    Administrador_UsuarioDao usuarioDao = new Administrador_UsuarioDao();
    private boolean veri = false;
    DefaultTableModel modelo = new DefaultTableModel();
    Administrador_Auditoria auditoria = new Administrador_Auditoria();
    Administrador_AuditoriaDao auditoriaDao = new Administrador_AuditoriaDao();

    public Administrador_ControladorModificarUsuario(Administrador_Modal_Modificar_Usuario modal, Administrador_ModificarUsuario modi) {
        this.modi = modi;
        this.modal = modal;
        this.modi.botonC.addActionListener(this);
        this.modal.cancelar.addActionListener(this);
        this.modal.guardar.addActionListener(this);
        limpiarTabla();
        getListar(modi.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modi.botonC) {

            int fila = modi.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(modi, "Debes de selecionar un usuario para cambiar su informacion ", "", JOptionPane.WARNING_MESSAGE);
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
            String correo = modal.txtCorreo.getText().trim();

            if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                JOptionPane.showMessageDialog(
                        modal,
                        "Ingrese un correo válido. Ejemplo: usuario@gmail.com",
                        "Correo inválido",
                        JOptionPane.WARNING_MESSAGE);
                return;
            } else {

                setActualizarCorreo();
            }
        }
    }

    public void CargarModal(Administrador_Modal_Modificar_Usuario modal) {
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

        modi.tabla.setModel(modelo);
    }

    public void cargarDatosModal() {

        int fila = modi.tabla.getSelectedRow();

        modal.txtDocumento.setText(
                modi.tabla.getValueAt(fila, 0).toString());

        modal.txtNombre.setText(
                modi.tabla.getValueAt(fila, 1).toString());

        modal.txtApellido.setText(
                modi.tabla.getValueAt(fila, 2).toString());

        modal.txtCorreo.setText(
                modi.tabla.getValueAt(fila, 3).toString());

    }

    public void limpiarTabla() {

        for (int i = 0; i < modi.tabla.getRowCount(); i++) {

            modelo.removeRow(i);

            i--;

        }

    }

    public void borrarModal() {

        modal.txtDocumento.setText("");
        modal.txtNombre.setText("");
        modal.txtApellido.setText("");
        modal.txtCorreo.setText("");

    }

    public void setActualizarCorreo() {

        int resultado;

        usuario.setDocumento(
                Integer.parseInt(modal.txtDocumento.getText()));

        usuario.setCorreo(
                modal.txtCorreo.getText());

        resultado = usuarioDao.setActualizarCorreo(usuario);

        if (resultado > 0) {
            auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
            auditoria.setAccion("Modificó un usuario");
            auditoriaDao.registrarAccion(auditoria);
            JOptionPane.showMessageDialog(
                    modal,
                    "Correo actualizado correctamente");

            modal.dispose();

            borrarModal();

            limpiarTabla();

            getListar(modi.tabla);

        } else {

            JOptionPane.showMessageDialog(
                    modal,
                    "No se pudo actualizar el correo");

        }

    }
}

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
import proyect_loansys.view.Administrador_Modal_registrar_usuario;
import proyect_loansys.view.Administrador_Registro_de_usuario;
import proyect_loansys.model.Administrador_Auditoria;
import proyect_loansys.model.Administrador_AuditoriaDao;
import proyect_loansys.model.Administrador_Sesion;

/**
 *
 * @author juans
 */
public class Administrador_Controlador_registro_usuario implements ActionListener {

    private Administrador_Registro_de_usuario registro_usuario;
    private Administrador_Modal_registrar_usuario modal;
    Administrador_Usuario usuario = new Administrador_Usuario();
    Administrador_UsuarioDao usuarioDao = new Administrador_UsuarioDao();
    DefaultTableModel modelo = new DefaultTableModel();
    Administrador_Auditoria auditoria = new Administrador_Auditoria();
    Administrador_AuditoriaDao auditoriaDao = new Administrador_AuditoriaDao();

    public Administrador_Controlador_registro_usuario(Administrador_Registro_de_usuario registro_usuario, Administrador_Modal_registrar_usuario modal) {
        this.modal = modal;
        this.registro_usuario = registro_usuario;
        this.registro_usuario.botonRegistro.addActionListener(this);
        this.modal.cancelar.addActionListener(this);
        this.modal.guardar.addActionListener(this);
        limpiarTabla();
        getListar(registro_usuario.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registro_usuario.botonRegistro) {
            CargarModal(modal);

        }

        if (e.getSource() == modal.cancelar) {
            modal.dispose();
            JOptionPane.showMessageDialog(null, "No se registro ningun usuario", "", JOptionPane.WARNING_MESSAGE);
        }
        if (e.getSource() == modal.guardar) {

            String nombre = modal.txtNombre.getText().trim();
            String apellido = modal.txtApellido.getText().trim();
            String documento = modal.txtDocumento.getText().trim();
            String correo = modal.txtCorreo.getText().trim();
            String contraseña = modal.txtContraseña.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty() || documento.isEmpty()
                    || correo.isEmpty() || contraseña.isEmpty()) {

                JOptionPane.showMessageDialog(modal,
                        "Debe completar todos los campos.",
                        "Campos vacíos", JOptionPane.WARNING_MESSAGE);

                // ---- NOMBRE desglosado ----
            } else if (nombre.length() < 3) {

                JOptionPane.showMessageDialog(modal,
                        "El nombre debe tener al menos 3 caracteres.",
                        "Nombre inválido", JOptionPane.WARNING_MESSAGE);

            } else if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {

                JOptionPane.showMessageDialog(modal,
                        "El nombre solo puede contener letras, sin números ni símbolos.",
                        "Nombre inválido", JOptionPane.WARNING_MESSAGE);

                // ---- APELLIDO desglosado ----
            } else if (apellido.length() < 3) {

                JOptionPane.showMessageDialog(modal,
                        "El apellido debe tener al menos 3 caracteres.",
                        "Apellido inválido", JOptionPane.WARNING_MESSAGE);

            } else if (!apellido.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")) {

                JOptionPane.showMessageDialog(modal,
                        "El apellido solo puede contener letras, sin números ni símbolos.",
                        "Apellido inválido", JOptionPane.WARNING_MESSAGE);

                // ---- DOCUMENTO desglosado ----
            } else if (!documento.matches("\\d+")) {

                JOptionPane.showMessageDialog(modal,
                        "El documento solo puede contener números, sin puntos, espacios ni letras.",
                        "Documento inválido", JOptionPane.WARNING_MESSAGE);

            } else if (documento.length() < 6 || documento.length() > 10) {

                JOptionPane.showMessageDialog(modal,
                        "El documento debe tener entre 6 y 10 dígitos.",
                        "Documento inválido", JOptionPane.WARNING_MESSAGE);

            } else if (documento.matches("(\\d)\\1+")) {

                JOptionPane.showMessageDialog(modal,
                        "El documento no puede tener todos los dígitos iguales (ej: 0000000).",
                        "Documento inválido", JOptionPane.WARNING_MESSAGE);

                // ---- CORREO ----
            } else if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

                JOptionPane.showMessageDialog(modal,
                        "Ingrese un correo válido. Ejemplo: usuario@gmail.com",
                        "Correo inválido", JOptionPane.WARNING_MESSAGE);

                // ---- CONTRASEÑA desglosada ----
            } else if (contraseña.length() < 8) {

                JOptionPane.showMessageDialog(modal,
                        "La contraseña debe tener mínimo 8 caracteres.",
                        "Contraseña inválida", JOptionPane.WARNING_MESSAGE);

            } else if (!contraseña.matches(".*[a-z].*")) {

                JOptionPane.showMessageDialog(modal,
                        "La contraseña debe contener al menos una letra minúscula.",
                        "Contraseña inválida", JOptionPane.WARNING_MESSAGE);

            } else if (!contraseña.matches(".*[A-Z].*")) {

                JOptionPane.showMessageDialog(modal,
                        "La contraseña debe contener al menos una letra mayúscula.",
                        "Contraseña inválida", JOptionPane.WARNING_MESSAGE);

            } else if (!contraseña.matches(".*\\d.*")) {

                JOptionPane.showMessageDialog(modal,
                        "La contraseña debe contener al menos un número.",
                        "Contraseña inválida", JOptionPane.WARNING_MESSAGE);

            } else if (!contraseña.matches(".*[@#$%^&+=!.*_?-].*")) {

                JOptionPane.showMessageDialog(modal,
                        "La contraseña debe contener al menos un carácter especial (@#$%^&+=!.*_?-).",
                        "Contraseña inválida", JOptionPane.WARNING_MESSAGE);

                // ---- ROL / ESTADO ----
            } else if (modal.rol.getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(modal,
                        "Seleccione un rol.",
                        "Rol obligatorio", JOptionPane.WARNING_MESSAGE);

            } else if (modal.estado.getSelectedIndex() == 0) {

                JOptionPane.showMessageDialog(modal,
                        "Seleccione un estado.",
                        "Estado obligatorio", JOptionPane.WARNING_MESSAGE);

            } else {

                registrarUsuario();

            }
        }
    }
//   

    public void CargarModal(Administrador_Modal_registrar_usuario modal) {
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

        registro_usuario.tabla.setModel(modelo);
    }

    public void borrarModal() {

        modal.txtDocumento.setText("");
        modal.txtNombre.setText("");
        modal.txtApellido.setText("");
        modal.txtCorreo.setText("");

        modal.estado.setSelectedIndex(0);
        modal.rol.setSelectedIndex(0);

    }

    public void limpiarTabla() {

        for (int i = 0; i < registro_usuario.tabla.getRowCount(); i++) {

            modelo.removeRow(i);

            i = i - 1;
        }

    }

    public void registrarUsuario() {

        Administrador_Usuario usuario = new Administrador_Usuario();

        usuario.setDocumento(Integer.parseInt(modal.txtDocumento.getText()));
        usuario.setNombre(modal.txtNombre.getText());
        usuario.setApellido(modal.txtApellido.getText());
        usuario.setCorreo(modal.txtCorreo.getText());
        usuario.setContraseña(modal.txtContraseña.getText());
        // Estado
        if (modal.estado.getSelectedItem().toString().equals("ACTIVO")) {

            usuario.setIdEstado(1);

        } else {

            usuario.setIdEstado(2);

        }

        // Rol
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

        usuario.setIdTipoDocumento(1);

        int resultado = usuarioDao.setAgragar(usuario);

        if (resultado > 0) {
            auditoria.setIdUsuario(Administrador_Sesion.getIdUsuario());
            auditoria.setAccion("Registró un usuario");
            auditoriaDao.registrarAccion(auditoria);
            JOptionPane.showMessageDialog(modal,
                    "Usuario registrado correctamente");
            modal.dispose();
            borrarModal();
            limpiarTabla();
            getListar(registro_usuario.tabla);

        } else {

            JOptionPane.showMessageDialog(modal,
                    "No se pudo registrar el usuario");

        }

    }

}

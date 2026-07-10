/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import proyect_loansys.view.Administrador_Cambio_de_rol;
import proyect_loansys.view.Administrador_Inicio_Loansys_Administrador;
import proyect_loansys.view.Administrador_Modal_Modificar_Usuario;
import proyect_loansys.view.Administrador_Modal_Usuarios_Activos;
import proyect_loansys.view.Administrador_Modal_cambio_de_rol;
import proyect_loansys.view.Administrador_Modal_registrar_usuario;
import proyect_loansys.view.Administrador_ModificarUsuario;
import proyect_loansys.view.Administrador_Plantilla_Administrador;
import proyect_loansys.view.Administrador_Registro_de_usuario;
import proyect_loansys.view.Administrador_Usuarios_activos;
import proyect_loansys.view.Vista_Login;

/**
 *
 * @author juans
 */
public class Administrador_ControladorBotones implements ActionListener {

    private boolean vali = false;
    private boolean visible = false;

    Administrador_Inicio_Loansys_Administrador inicio = new Administrador_Inicio_Loansys_Administrador();

    // usuarios activos o inactivos
    Administrador_Usuarios_activos usActivo = new Administrador_Usuarios_activos();
    Administrador_Modal_Usuarios_Activos modalUsu = new Administrador_Modal_Usuarios_Activos();

    // modificar usuarios
    Administrador_ModificarUsuario modificarUsuario = new Administrador_ModificarUsuario();
    Administrador_Modal_Modificar_Usuario modal_modifi = new Administrador_Modal_Modificar_Usuario();

    //gestion de roles
    Administrador_Cambio_de_rol cambio_de_rol = new Administrador_Cambio_de_rol();
    Administrador_Modal_cambio_de_rol modalRol = new Administrador_Modal_cambio_de_rol();

    //registrar usuario
    Administrador_Registro_de_usuario registro_de_usuario = new Administrador_Registro_de_usuario();
    Administrador_Modal_registrar_usuario modalRigistrar = new Administrador_Modal_registrar_usuario();

    // para cerrar sesion 
    Vista_Login login = new Vista_Login();

    //controladores                 
    Administrador_ControladorUsuarioActivo controladorUsuarioActivo;
    Administrador_ControladorModificarUsuario controladorModificarUsuario;
    Administrador_ControladorCambio_de_rol controladorCambio_de_rol;
    Administrador_Controlador_registro_usuario controlador_registro_usuario;

    public Administrador_ControladorBotones(Administrador_Inicio_Loansys_Administrador inicio) {
        this.inicio = inicio;

        //------Activar boton inicio----------//
        this.inicio.activacion_usuario.addActionListener(this);
        this.inicio.modificar.addActionListener(this);
        this.inicio.gestion_roles.addActionListener(this);
        this.inicio.registrar_usuario.addActionListener(this);
        this.inicio.cerrar_sesion.addActionListener(this);

        //----fin inicio_---------------//
        //-------activar boton usuarioActivos ---------//
        this.usActivo.binicio.addActionListener(this);
        this.usActivo.modificar.addActionListener(this);
        this.usActivo.gestion_roles.addActionListener(this);
        this.usActivo.registrar_usuario.addActionListener(this);
        this.usActivo.cerrar_sesion.addActionListener(this);

        //------fin usaActivo
        //------modificra usuario-----------//
        this.modificarUsuario.binicio.addActionListener(this);
        this.modificarUsuario.activacion_usuario.addActionListener(this);
        this.modificarUsuario.gestion_roles.addActionListener(this);
        this.modificarUsuario.registrar_usuario.addActionListener(this);
        this.modificarUsuario.cerrar_sesion.addActionListener(this);

        //--------cambio de rol ---------------//
        this.cambio_de_rol.binicio.addActionListener(this);
        this.cambio_de_rol.activacion_usuario.addActionListener(this);
        this.cambio_de_rol.registrar_usuario.addActionListener(this);
        this.cambio_de_rol.modificar.addActionListener(this);
        this.cambio_de_rol.cerrar_sesion.addActionListener(this);

        //------registrar usuario------------//
        this.registro_de_usuario.binicio.addActionListener(this);
        this.registro_de_usuario.activacion_usuario.addActionListener(this);
        this.registro_de_usuario.gestion_roles.addActionListener(this);
        this.registro_de_usuario.modificar.addActionListener(this);
        this.registro_de_usuario.cerrar_sesion.addActionListener(this);

        //-------fin mdificar usuario--------//
        controladorUsuarioActivo = new Administrador_ControladorUsuarioActivo(usActivo, modalUsu);
        controladorModificarUsuario = new Administrador_ControladorModificarUsuario(modal_modifi, modificarUsuario);
        controladorCambio_de_rol = new Administrador_ControladorCambio_de_rol(cambio_de_rol, modalRol);
        controlador_registro_usuario = new Administrador_Controlador_registro_usuario(registro_de_usuario, modalRigistrar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ----------------menu_Inicio -----------------------------------//
        if (e.getSource() == inicio.activacion_usuario) {
            controladorUsuarioActivo.limpiarTabla();
            controladorUsuarioActivo.getListar(usActivo.tabla);

            CargarActivacionUsuario(usActivo);
            inicio.setVisible(false);
        }

        if (e.getSource() == inicio.modificar) {
            controladorModificarUsuario.limpiarTabla();
            controladorModificarUsuario.getListar(modificarUsuario.tabla);

            CargarModificarUsuario(modificarUsuario);
            inicio.setVisible(false);
        }

        if (e.getSource() == inicio.gestion_roles) {
            controladorCambio_de_rol.limpiarTabla();
            controladorCambio_de_rol.getListar(cambio_de_rol.tabla);

            CargarCambioDeRol(cambio_de_rol);
            inicio.setVisible(false);
        }

        if (e.getSource() == inicio.registrar_usuario) {
            controlador_registro_usuario.limpiarTabla();
            controlador_registro_usuario.getListar(registro_de_usuario.tabla);

            CargarRigistroDeUsuario(registro_de_usuario);
            inicio.setVisible(false);
        }
        //----------- menu_fin------------///

        //--------menu_activo--------------//
        if (e.getSource() == usActivo.binicio) {
            CargarInicio(inicio);
            usActivo.setVisible(false);
        }

        if (e.getSource() == usActivo.modificar) {
            controladorModificarUsuario.limpiarTabla();
            controladorModificarUsuario.getListar(modificarUsuario.tabla);

            CargarModificarUsuario(modificarUsuario);
            usActivo.setVisible(false);
        }

        if (e.getSource() == usActivo.gestion_roles) {
            controladorCambio_de_rol.limpiarTabla();
            controladorCambio_de_rol.getListar(cambio_de_rol.tabla);

            CargarCambioDeRol(cambio_de_rol);
            usActivo.setVisible(false);
        }

        if (e.getSource() == usActivo.registrar_usuario) {
            controlador_registro_usuario.limpiarTabla();
            controlador_registro_usuario.getListar(registro_de_usuario.tabla);

            CargarRigistroDeUsuario(registro_de_usuario);
            usActivo.setVisible(false);
        }
        //---------menu_fin---------------//

        //---------modificar_usuario------------//
        if (e.getSource() == modificarUsuario.binicio) {
            CargarInicio(inicio);
            modificarUsuario.setVisible(false);
        }

        if (e.getSource() == modificarUsuario.activacion_usuario) {
            controladorUsuarioActivo.limpiarTabla();
            controladorUsuarioActivo.getListar(usActivo.tabla);

            CargarActivacionUsuario(usActivo);
            modificarUsuario.setVisible(false);
        }

        if (e.getSource() == modificarUsuario.gestion_roles) {
            controladorCambio_de_rol.limpiarTabla();
            controladorCambio_de_rol.getListar(cambio_de_rol.tabla);

            CargarCambioDeRol(cambio_de_rol);
            modificarUsuario.setVisible(false);
        }

        if (e.getSource() == modificarUsuario.registrar_usuario) {
            controlador_registro_usuario.limpiarTabla();
            controlador_registro_usuario.getListar(registro_de_usuario.tabla);

            CargarRigistroDeUsuario(registro_de_usuario);
            modificarUsuario.setVisible(false);
        }

        //-----------cambio de rol---------------//
        if (e.getSource() == cambio_de_rol.binicio) {
            CargarInicio(inicio);
            cambio_de_rol.setVisible(false);
        }

        if (e.getSource() == cambio_de_rol.activacion_usuario) {
            controladorUsuarioActivo.limpiarTabla();
            controladorUsuarioActivo.getListar(usActivo.tabla);

            CargarActivacionUsuario(usActivo);
            cambio_de_rol.setVisible(false);
        }

        if (e.getSource() == cambio_de_rol.modificar) {
            controladorModificarUsuario.limpiarTabla();
            controladorModificarUsuario.getListar(modificarUsuario.tabla);

            CargarModificarUsuario(modificarUsuario);
            cambio_de_rol.setVisible(false);
        }

        if (e.getSource() == cambio_de_rol.registrar_usuario) {
            controlador_registro_usuario.limpiarTabla();
            controlador_registro_usuario.getListar(registro_de_usuario.tabla);

            CargarRigistroDeUsuario(registro_de_usuario);
            cambio_de_rol.setVisible(false);
        }

        //---------registro de usuario------------///
        if (e.getSource() == registro_de_usuario.binicio) {
            CargarInicio(inicio);
            registro_de_usuario.setVisible(false);
        }

        if (e.getSource() == registro_de_usuario.activacion_usuario) {
            controladorUsuarioActivo.limpiarTabla();
            controladorUsuarioActivo.getListar(usActivo.tabla);

            CargarActivacionUsuario(usActivo);
            registro_de_usuario.setVisible(false);
        }

        if (e.getSource() == registro_de_usuario.modificar) {
            controladorModificarUsuario.limpiarTabla();
            controladorModificarUsuario.getListar(modificarUsuario.tabla);

            CargarModificarUsuario(modificarUsuario);
            registro_de_usuario.setVisible(false);
        }

        if (e.getSource() == registro_de_usuario.gestion_roles) {
            controladorCambio_de_rol.limpiarTabla();
            controladorCambio_de_rol.getListar(cambio_de_rol.tabla);

            CargarCambioDeRol(cambio_de_rol);
            registro_de_usuario.setVisible(false);
        }
        // cerrar sesion 

        if (e.getSource() == inicio.cerrar_sesion) {
            inicio.setVisible(false);
            login.setVisible(true);
        }
        if (e.getSource() == usActivo.cerrar_sesion) {
            usActivo.setVisible(false);
            login.setVisible(true);
        }
        if (e.getSource() == modificarUsuario.cerrar_sesion) {
            modificarUsuario.setVisible(false);
            login.setVisible(true);
        }
        if (e.getSource() == cambio_de_rol.cerrar_sesion) {
            cambio_de_rol.setVisible(false);
            login.setVisible(true);
        }
        if (e.getSource() == registro_de_usuario.cerrar_sesion) {
            registro_de_usuario.setVisible(false);
            login.setVisible(true);
        }
    }

    //------Metodos para cargar las view----------//
    public void CargarInicio(Administrador_Inicio_Loansys_Administrador inicio) {
        inicio.setVisible(true);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void CargarActivacionUsuario(Administrador_Usuarios_activos usActivo) {
        usActivo.setVisible(true);
        usActivo.binicio.setBackground(Color.white);
        usActivo.setExtendedState(JFrame.MAXIMIZED_BOTH);
        usActivo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarModificarUsuario(Administrador_ModificarUsuario modificarUsuario) {
        modificarUsuario.setVisible(true);
        modificarUsuario.activacion_usuario.setBackground(Color.white);
        modificarUsuario.binicio.setBackground(Color.white);
        modificarUsuario.setExtendedState(JFrame.MAXIMIZED_BOTH);
        modificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarCambioDeRol(Administrador_Cambio_de_rol cambio_de_rol) {
        cambio_de_rol.setVisible(true);
        cambio_de_rol.activacion_usuario.setBackground(Color.white);
        cambio_de_rol.binicio.setBackground(Color.white);
        cambio_de_rol.modificar.setBackground(Color.white);
        cambio_de_rol.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cambio_de_rol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarRigistroDeUsuario(Administrador_Registro_de_usuario registro_de_usuario) {
        registro_de_usuario.setVisible(true);
        registro_de_usuario.activacion_usuario.setBackground(Color.white);
        registro_de_usuario.binicio.setBackground(Color.white);
        registro_de_usuario.modificar.setBackground(Color.white);
        registro_de_usuario.gestion_roles.setBackground(Color.white);
        registro_de_usuario.setExtendedState(JFrame.MAXIMIZED_BOTH);
        registro_de_usuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

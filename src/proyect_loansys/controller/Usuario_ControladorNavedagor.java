package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.view.Usuario_HistorialPrestamo;
import proyect_loansys.view.Usuario_Inicio;
import proyect_loansys.view.Usuario_Inventario;
import proyect_loansys.view.Usuario_Notificacion;
import proyect_loansys.view.Usuario_SolicitarPrestamo;
import proyect_loansys.view.Vista_Login;

public class Usuario_ControladorNavedagor implements ActionListener {

    private boolean vali = false;
    Usuario_Dao prus = new Usuario_Dao();
    Usuario_Inicio inicio = new Usuario_Inicio("Aprendiz", "Aprendiz", "Miguel");
    Usuario_Inventario inven = new Usuario_Inventario("Aprendiz", "Aprendiz", "Miguel");
    Usuario_HistorialPrestamo pres = new Usuario_HistorialPrestamo("Aprendiz", "Aprendiz", "Miguel");
    Usuario_Notificacion noti = new Usuario_Notificacion("Aprendiz", "Aprendiz", "Miguel");
    Usuario_SolicitarPrestamo soli = new Usuario_SolicitarPrestamo("Aprendiz", "Aprendiz", "Miguel");
    Vista_Login sesion = new Vista_Login();

    //comtroladores
    Usuario_ControladorDatos controladorDatos;

    public Usuario_ControladorNavedagor(Usuario_Inicio inicio) {
        this.inicio = inicio;
        this.inicio.inventario.addActionListener(this);
        this.inicio.prestamo.addActionListener(this);
        this.inicio.notificacion.addActionListener(this);
        this.inicio.cerrarS.addActionListener(this);

        ///////////////////////////////////////////////////
        this.inven.iniciod.addActionListener(this);
        this.inven.prestamo.addActionListener(this);
        this.inven.notificacion.addActionListener(this);
        this.inven.prueba.addActionListener(this);
        this.inven.prueba1.addActionListener(this);
        this.inven.prueba2.addActionListener(this);
        this.inven.prueba3.addActionListener(this);
        this.inven.prueba4.addActionListener(this);
        this.inven.prueba5.addActionListener(this);
        this.inven.prueba6.addActionListener(this);
        this.inven.prueba7.addActionListener(this);
        this.inven.prueba8.addActionListener(this);
        this.inven.prueba9.addActionListener(this);
        this.inven.prueba10.addActionListener(this);
        this.inven.prueba11.addActionListener(this);
        this.inven.prueba12.addActionListener(this);
        this.inven.prueba13.addActionListener(this);
        this.inven.cerrarS.addActionListener(this);

        /////////////////////////////////////////////////////
        this.pres.iniciod.addActionListener(this);
        this.pres.inventario.addActionListener(this);
        this.pres.notificacion.addActionListener(this);
        this.pres.cerrarS.addActionListener(this);
        /////////////////////////////////////////////////////
        this.noti.iniciod.addActionListener(this);
        this.noti.prestamo.addActionListener(this);
        this.noti.inventario.addActionListener(this);
        this.noti.cerrarS.addActionListener(this);

        ////////////////////////////////////////////////////
        this.soli.volver.addActionListener(this);
        this.soli.solicitar.addActionListener(this);
        this.soli.iniciod.addActionListener(this);
        this.soli.inventario.addActionListener(this);
        this.soli.notificacion.addActionListener(this);
        this.soli.prestamo.addActionListener(this);
        this.soli.cerrarS.addActionListener(this);

        //aver
        controladorDatos = new Usuario_ControladorDatos(inven, soli);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inicio.inventario) {
            CargarInventario(inven);
            inicio.dispose();
        }

        if (e.getSource() == inicio.prestamo) {
            CargarHistorial(pres);
            inicio.dispose();
        }
        if (e.getSource() == inicio.notificacion) {
            CargarNotificacion(noti);
            inicio.dispose();
        }
        if (e.getSource() == inicio.cerrarS) {
            CargarInicioS(sesion);
            inicio.dispose();
        }

        ////////////////////////////////////////////
        if (e.getSource() == inven.iniciod) {
            CargarInicio(inicio);
            inven.dispose();
        }
        if (e.getSource() == inven.prestamo) {
            CargarHistorial(pres);
            inven.dispose();
        }
        if (e.getSource() == inven.notificacion) {
            CargarNotificacion(noti);
            inven.dispose();
        }
        if (e.getSource() == inven.cerrarS) {
            CargarInicioS(sesion);
            inven.dispose();
        }
        //Para que muestre los datos y la tabla
        if (e.getSource() == inven.prueba) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba1) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar1(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba2) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar2(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba3) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar3(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba4) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar4(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba5) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar5(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba6) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar6(soli.tabla);

            inven.dispose();
        }
        if (e.getSource() == inven.prueba7) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar7(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba8) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar8(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba9) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar9(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba10) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar10(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba11) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar11(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba12) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar12(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba13) {
            CargarSolicitud(soli);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar13(soli.tabla);
            inven.dispose();
        }

        ////////////////////////////////////////////
        if (e.getSource() == pres.iniciod) {
            CargarInicio(inicio);
            pres.dispose();
        }
        if (e.getSource() == pres.inventario) {
            CargarInventario(inven);
            pres.dispose();
        }
        if (e.getSource() == pres.notificacion) {
            CargarNotificacion(noti);
            pres.dispose();
        }
        if (e.getSource() == pres.cerrarS) {
            CargarInicioS(sesion);
            pres.dispose();
        }

        ///////////////////////////////////////////
        if (e.getSource() == noti.iniciod) {
            CargarInicio(inicio);
            noti.dispose();
        }
        if (e.getSource() == noti.inventario) {
            CargarInventario(inven);
            noti.dispose();
        }
        if (e.getSource() == noti.prestamo) {
            CargarHistorial(pres);
            noti.dispose();
        }
        if (e.getSource() == noti.cerrarS) {
            CargarInicioS(sesion);
            noti.dispose();
        }

        //////////////////////////////////////
        
        if (e.getSource() == soli.iniciod) {
            CargarInicio(inicio);
            soli.dispose();
        }
        if (e.getSource() == soli.prestamo) {
            CargarHistorial(pres);
            soli.dispose();
        }
        if (e.getSource() == soli.inventario) {
            CargarInventario(inven);
            soli.dispose();
        }
        if (e.getSource() == soli.notificacion) {
            CargarNotificacion(noti);
            soli.dispose();
        }

        if (e.getSource() == soli.volver) {
            CargarInventario(inven);
            soli.dispose();
        }

        if (e.getSource() == soli.solicitar) {
            JOptionPane.showMessageDialog(null, "Solicitud con exito");
        }
        if (e.getSource() == soli.cerrarS) {
            CargarInicioS(sesion);
            soli.dispose();
        }

    }

    public void CargarInicio(Usuario_Inicio inicio) {
        inicio.setVisible(true);
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//

    public void CargarInventario(Usuario_Inventario inven) {
        inven.setVisible(true);
        inven.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inven.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarHistorial(Usuario_HistorialPrestamo inven) {
        pres.setVisible(true);
        pres.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pres.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarNotificacion(Usuario_Notificacion noti) {
        noti.setVisible(true);
        noti.setExtendedState(JFrame.MAXIMIZED_BOTH);
        noti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarSolicitud(Usuario_SolicitarPrestamo soli) {
        soli.setVisible(true);
        soli.setExtendedState(JFrame.MAXIMIZED_BOTH);
        soli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void CargarInicioS(Vista_Login sesion) {
        sesion.setVisible(true);
        sesion.setExtendedState(JFrame.MAXIMIZED_BOTH);
        sesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

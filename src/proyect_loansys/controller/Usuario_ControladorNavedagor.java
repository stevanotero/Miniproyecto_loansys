package proyect_loansys.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import proyect_loansys.model.Administrador_Sesion;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.model.Usuario_Elemento;
import proyect_loansys.model.Usuario_Model;
import proyect_loansys.model.Usuario_Solicitud;
import proyect_loansys.view.Usuario_HistorialPrestamo;
import proyect_loansys.view.Usuario_Inicio;
import proyect_loansys.view.Usuario_Inventario;
import proyect_loansys.view.Usuario_Notificacion;
import proyect_loansys.view.Usuario_SolicitarPrestamo;
import proyect_loansys.view.Vista_Login;
import java.sql.Timestamp;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.PersonaDao_Login;

public class Usuario_ControladorNavedagor implements ActionListener {

    Usuario_Solicitud sr = new Usuario_Solicitud();
    public Usuario_Dao elementoDao = new Usuario_Dao();
    Usuario_Elemento ele = new Usuario_Elemento();
    Usuario_Solicitud solicitud = new Usuario_Solicitud();
    private boolean vali = false;
    Usuario_Dao prus = new Usuario_Dao();
    PersonaDao_Login vistaLo = new PersonaDao_Login();

    Usuario_Inicio inicio;
    Usuario_Inventario inven;
    Usuario_HistorialPrestamo pres;
    Usuario_Notificacion noti;
    Usuario_SolicitarPrestamo soli;
    Vista_Login sesion = new Vista_Login();
    private String nombreUsuario;
    private String rolUsuario;
    public Usuario_Model usuario;
    private int idElementoSeleccionado;
    private Usuario_Dao apellidoUsuarioActual;
    private Usuario_Dao documentoUsuarioActual;

    Usuario_ControladorDatos controladorDatos;

    public Usuario_ControladorNavedagor(Usuario_Inicio inicio, String nombreUsuario, String rolUsuario) {
        // 1º: asigno los campos
        this.inicio = inicio;
        this.nombreUsuario = nombreUsuario;
        this.rolUsuario = rolUsuario;
        

        // 2º: AHORA sí creo las demás vistas, ya con los valores reales
        this.inven = new Usuario_Inventario(rolUsuario, "", "");
        this.pres = new Usuario_HistorialPrestamo(rolUsuario, "", "");
        this.noti = new Usuario_Notificacion(rolUsuario, "", "");
        this.soli = new Usuario_SolicitarPrestamo(rolUsuario, "", "");

        //this.inicio = inicio;
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
        this.inven.buscador.addActionListener(this);

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
            controladorDatos.mostrarEstado(inven.estado);
            controladorDatos.mostrarEstado1(inven.estado1);
            controladorDatos.mostrarEstado2(inven.estado2);
            controladorDatos.mostrarEstado3(inven.estado3);
            controladorDatos.mostrarEstado4(inven.estado4);
            controladorDatos.mostrarEstado5(inven.estado5);
            controladorDatos.mostrarEstado6(inven.estado6);
            controladorDatos.mostrarEstado7(inven.estado7);
            controladorDatos.mostrarEstado8(inven.estado8);
            controladorDatos.mostrarEstado9(inven.estado9);
            controladorDatos.mostrarEstado10(inven.estado10);
            controladorDatos.mostrarEstado11(inven.estado11);
            controladorDatos.mostrarEstado12(inven.estado12);
            controladorDatos.mostrarEstado13(inven.estado13);

            inicio.dispose();
        }

        if (e.getSource() == inicio.prestamo) {
            CargarHistorial(pres);
            controladorDatos.mostrarH(pres.tabla);
            inicio.dispose();
            /*
            if(pres.textoDeBienvenida == null){
            }
             */
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
            controladorDatos.mostrarH(pres.tabla);
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
            idElementoSeleccionado = 1;
            controladorDatos.mostrarImagen("imgTaladrosena.png");
            controladorDatos.mostrarEstado(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba1) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 2;
            controladorDatos.mostrarImagen("imgMultimetro.png");
            controladorDatos.mostrarEstado1(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar1(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba2) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 3;
            controladorDatos.mostrarImagen("imgLaptop.png");
            controladorDatos.mostrarEstado2(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar2(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba3) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 4;
            controladorDatos.mostrarImagen("imgOsciloscopio.png");
            controladorDatos.mostrarEstado3(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar3(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba4) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 5;
            controladorDatos.mostrarImagen("imgCautin.png");
            controladorDatos.mostrarEstado4(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar4(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba5) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 6;
            controladorDatos.mostrarImagen("imgTorque.png");
            controladorDatos.mostrarEstado5(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar5(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba6) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 7;
            controladorDatos.mostrarImagen("imgProtoboard.png");
            controladorDatos.mostrarEstado6(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar6(soli.tabla);

            inven.dispose();
        }
        if (e.getSource() == inven.prueba7) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 8;
            controladorDatos.mostrarImagen("imgArduino.png");
            controladorDatos.mostrarEstado7(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar7(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba8) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 9;
            controladorDatos.mostrarImagen("imgPinza.png");
            controladorDatos.mostrarEstado8(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar8(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba9) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 10;
            controladorDatos.mostrarImagen("imgRouter.png");
            controladorDatos.mostrarEstado9(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar9(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba10) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 11;
            controladorDatos.mostrarImagen("imgProyector.png");
            controladorDatos.mostrarEstado10(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar10(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba11) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 12;
            controladorDatos.mostrarImagen("imgMouse.png");
            controladorDatos.mostrarEstado11(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar11(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba12) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 13;
            controladorDatos.mostrarImagen("imgLaptop.png");
            controladorDatos.mostrarEstado12(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar12(soli.tabla);
            inven.dispose();
        }
        if (e.getSource() == inven.prueba13) {
            CargarSolicitud(soli);
            idElementoSeleccionado = 14;
            controladorDatos.mostrarImagen("imgLaptop.png");
            controladorDatos.mostrarEstado13(soli.texto1);
            controladorDatos.limpiarTabla();
            controladorDatos.pruebaMostrar13(soli.tabla);
            inven.dispose();
        }

        ////////////////////////////////////////////
        if (e.getSource() == pres.iniciod) {
            limpiarHistorial(pres.tabla);
            CargarInicio(inicio);
            pres.dispose();
        }
        if (e.getSource() == pres.inventario) {
            limpiarHistorial(pres.tabla);
            CargarInventario(inven);
            controladorDatos.mostrarEstado(inven.estado);
            controladorDatos.mostrarEstado1(inven.estado1);
            controladorDatos.mostrarEstado2(inven.estado2);
            controladorDatos.mostrarEstado3(inven.estado3);
            controladorDatos.mostrarEstado4(inven.estado4);
            controladorDatos.mostrarEstado5(inven.estado5);
            controladorDatos.mostrarEstado6(inven.estado6);
            controladorDatos.mostrarEstado7(inven.estado7);
            controladorDatos.mostrarEstado8(inven.estado8);
            controladorDatos.mostrarEstado9(inven.estado9);
            controladorDatos.mostrarEstado10(inven.estado10);
            controladorDatos.mostrarEstado11(inven.estado11);
            controladorDatos.mostrarEstado12(inven.estado12);
            controladorDatos.mostrarEstado13(inven.estado13);
            pres.dispose();
        }
        if (e.getSource() == pres.notificacion) {
            limpiarHistorial(pres.tabla);
            CargarNotificacion(noti);
            pres.dispose();
        }
        if (e.getSource() == pres.cerrarS) {
            limpiarHistorial(pres.tabla);
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
            controladorDatos.mostrarEstado(inven.estado);
            controladorDatos.mostrarEstado1(inven.estado1);
            controladorDatos.mostrarEstado2(inven.estado2);
            controladorDatos.mostrarEstado3(inven.estado3);
            controladorDatos.mostrarEstado4(inven.estado4);
            controladorDatos.mostrarEstado5(inven.estado5);
            controladorDatos.mostrarEstado6(inven.estado6);
            controladorDatos.mostrarEstado7(inven.estado7);
            controladorDatos.mostrarEstado8(inven.estado8);
            controladorDatos.mostrarEstado9(inven.estado9);
            controladorDatos.mostrarEstado10(inven.estado10);
            controladorDatos.mostrarEstado11(inven.estado11);
            controladorDatos.mostrarEstado12(inven.estado12);
            controladorDatos.mostrarEstado13(inven.estado13);
            noti.dispose();
        }
        if (e.getSource() == noti.prestamo) {
            CargarHistorial(pres);
            controladorDatos.mostrarH(pres.tabla);
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
            controladorDatos.mostrarH(pres.tabla);
            soli.dispose();
        }
        if (e.getSource() == soli.inventario) {
            CargarInventario(inven);
            controladorDatos.mostrarEstado(inven.estado);
            controladorDatos.mostrarEstado1(inven.estado1);
            controladorDatos.mostrarEstado2(inven.estado2);
            controladorDatos.mostrarEstado3(inven.estado3);
            controladorDatos.mostrarEstado4(inven.estado4);
            controladorDatos.mostrarEstado5(inven.estado5);
            controladorDatos.mostrarEstado6(inven.estado6);
            controladorDatos.mostrarEstado7(inven.estado7);
            controladorDatos.mostrarEstado8(inven.estado8);
            controladorDatos.mostrarEstado9(inven.estado9);
            controladorDatos.mostrarEstado10(inven.estado10);
            controladorDatos.mostrarEstado11(inven.estado11);
            controladorDatos.mostrarEstado12(inven.estado12);
            controladorDatos.mostrarEstado13(inven.estado13);
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
            setAdd(); // toda la validación ya vive adentro (estado + duplicado + carga real de datos)

        }

        if (e.getSource() == soli.cerrarS) {
            CargarInicioS(sesion);
            soli.dispose();
        }
        if (e.getSource()== inven.buscador){
            filtroEstado();
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

    public void setAdd() {
        int idUsuario = Administrador_Sesion.getIdUsuario();

        // Traer datos reales del usuario logueado desde la BD
        Usuario_Model usuarioActual = elementoDao.consultarPorId(idUsuario);
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(soli, "No se pudo cargar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar estado del elemento — cortar la ejecución si no está disponible
        String estado = soli.texto1.getText();
        switch (estado) {
            case "Disponible":
                break; // sigue con el flujo normal
            case "Prestado":
                JOptionPane.showMessageDialog(soli, "Elemento no disponible por préstamo", "Prestado", JOptionPane.INFORMATION_MESSAGE);
                return;
            case "En Mantenimiento":
                JOptionPane.showMessageDialog(soli, "Elemento no disponible por mantenimiento", "Mantenimiento", JOptionPane.INFORMATION_MESSAGE);
                return;
            case "Dañado":
                JOptionPane.showMessageDialog(soli, "Elemento no disponible por estado dañado", "Daño", JOptionPane.INFORMATION_MESSAGE);
                return;
            case "Dado de Baja":
                JOptionPane.showMessageDialog(soli, "Elemento no se encuentra disponible en el almacén", "Robo", JOptionPane.INFORMATION_MESSAGE);
                return;
            default:
                JOptionPane.showMessageDialog(soli, "Estado del elemento desconocido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        // Validar que no exista ya una solicitud de este usuario para este elemento
        if (elementoDao.existeSolicitud(idUsuario, idElementoSeleccionado)) {
            JOptionPane.showMessageDialog(soli, "Ya tienes una solicitud registrada para este elemento",
                    "Solicitud duplicada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        sr.setId_usuario(idUsuario);
        sr.setNombre(usuarioActual.getNombre());
        sr.setApellido(usuarioActual.getApellido());
        sr.setDocumento(usuarioActual.getDocumento());
        sr.setId_elemento(idElementoSeleccionado);
        sr.setFecha_envio(new Timestamp(System.currentTimeMillis()));

        int resultado = elementoDao.setAgregar(sr);
        if (resultado > 0) {
            JOptionPane.showMessageDialog(soli, "Solicitud enviada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(soli, "No se pudo registrar la solicitud", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarHistorial(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        if (modelo.getRowCount() > 0) {
            modelo.setRowCount(0);
        }
    }

    /*
        public void filtroEstado(){
            String estodofiltro = invecon.listaEstado.
        }
     */
    public void filtroEstado() {
        int idUsuario = Administrador_Sesion.getIdUsuario();

        Usuario_Model usuarioActual = elementoDao.consultarPorId(idUsuario);
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(soli, "No se pudo cargar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Consulta el estado real desde la BD, no desde el label de la pantalla
        String estado = elementoDao.consultarEstadoElemento(idElementoSeleccionado);
        if (estado == null) {
            JOptionPane.showMessageDialog(soli, "No se pudo verificar el estado del elemento", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (estado) {
            case "Disponible":
                break;
            case "Prestado":
                JOptionPane.showMessageDialog(soli, "Elemento no disponible por préstamo", "Prestado", JOptionPane.INFORMATION_MESSAGE);
                return;
            case "En Mantenimiento":
                JOptionPane.showMessageDialog(soli, "Elemento no disponible por mantenimiento", "Mantenimiento", JOptionPane.INFORMATION_MESSAGE);
                return;
            case "Dañado":
                JOptionPane.showMessageDialog(soli, "Elemento no disponible por estado dañado", "Daño", JOptionPane.INFORMATION_MESSAGE);
                return;
            case "Dado de Baja":
                JOptionPane.showMessageDialog(soli, "Elemento no se encuentra disponible en el almacén", "Robo", JOptionPane.INFORMATION_MESSAGE);
                return;
            default:
                JOptionPane.showMessageDialog(soli, "Estado del elemento desconocido", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        if (elementoDao.existeSolicitud(idUsuario, idElementoSeleccionado)) {
            JOptionPane.showMessageDialog(soli, "Ya tienes una solicitud registrada para este elemento",
                    "Solicitud duplicada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        sr.setId_usuario(idUsuario);
        sr.setNombre(usuarioActual.getNombre());
        sr.setApellido(usuarioActual.getApellido());
        sr.setDocumento(usuarioActual.getDocumento());
        sr.setId_elemento(idElementoSeleccionado);
        sr.setFecha_envio(new Timestamp(System.currentTimeMillis()));

        int resultado = elementoDao.setAgregar(sr);
        if (resultado > 0) {
            JOptionPane.showMessageDialog(soli, "Solicitud enviada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(soli, "No se pudo registrar la solicitud", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

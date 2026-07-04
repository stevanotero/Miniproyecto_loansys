/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import proyect_loansys.controller.Controlador_EditarElemento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Elemento;
import proyect_loansys.model.PersonaDao_Inventario;
import proyect_loansys.view.VentanaEditarElemento;
import proyect_loansys.view.Vista_Inventario;
import proyect_loansys.view.Vista_Login;
import proyect_loansys.view.Vista_Solicitudes;

/**
 * @author Alexis
 */
public class Controlador_inventario implements ActionListener {

    private Vista_Inventario vista;
    private PersonaDao_Inventario modelo;
    private DefaultTableModel modeloTabla;

    public Controlador_inventario(Vista_Inventario vista) {
        this.vista = vista;
        this.modelo = new PersonaDao_Inventario();

        // Asignación de listeners para navegación y botones de acción
        this.vista.botonInicio.addActionListener(this);
        this.vista.botonInventario.addActionListener(this);
        this.vista.botonCerrarSesion.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        // Cargar los componentes en la JTable al iniciar
        listarComponentesTabla();
        // Escuchador del doble clic en la tabla
        this.vista.tablaDeElementos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    abrirFormularioEditar();
                }
            }
        });

        // Escuchadores para los filtros en tiempo real
        this.vista.cbxCategoria.addActionListener(e -> filtrarComponentesTabla());
        this.vista.cbxEstado.addActionListener(e -> filtrarComponentesTabla());
    }

    // Metodo para limpiar la
    public void limpiarBusquedaYFiltros() {
        vista.textoParaBuscar.setText("");
        vista.cbxCategoria.setSelectedIndex(0);
        vista.cbxEstado.setSelectedIndex(0);
        listarComponentesTabla();
    }

    public void ejecutarBusqueda() {
        String textoBusqueda = vista.textoParaBuscar.getText().trim();
        if (textoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No ha puesto nada en el campo de búsqueda.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            listarComponentesTabla();
            return;
        }

        List<Elemento> resultados = modelo.buscarElementos(textoBusqueda);
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No se encuentra el elemento solicitado.", "Sin Resultados", JOptionPane.ERROR_MESSAGE);
            listarComponentesTabla();
            return;
        }

        JOptionPane.showMessageDialog(vista, "Búsqueda exitosa, el elemento ya se encontró.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Pintar los resultados en la tabla
        modeloTabla = (DefaultTableModel) vista.tablaDeElementos.getModel();
        modeloTabla.setRowCount(0);
        Object[] fila = new Object[5];

        for (Elemento elem : resultados) {
            fila[0] = elem.getCodigoElemento();
            fila[1] = elem.getNombreElemento();
            fila[2] = elem.getCategoria();
            fila[3] = elem.getEstado();
            fila[4] = elem.getDescripcion();
            modeloTabla.addRow(fila);
        }
    }

    public void listarComponentesTabla() {
        modeloTabla = (DefaultTableModel) vista.tablaDeElementos.getModel();
        modeloTabla.setRowCount(0);

        List<Elemento> lista = modelo.listarElementos();
        Object[] fila = new Object[5];

        for (Elemento elem : lista) {
            fila[0] = elem.getCodigoElemento();
            fila[1] = elem.getNombreElemento();
            fila[2] = elem.getCategoria();
            fila[3] = elem.getEstado();
            fila[4] = elem.getDescripcion();
            modeloTabla.addRow(fila);
        }
    }

    public void filtrarComponentesTabla() {
        String catSeleccionada = vista.cbxCategoria.getSelectedItem().toString();
        String estSeleccionado = vista.cbxEstado.getSelectedItem().toString();

        modeloTabla = (DefaultTableModel) vista.tablaDeElementos.getModel();
        modeloTabla.setRowCount(0);

        List<Elemento> lista = modelo.listarElementosConFiltro(catSeleccionada, estSeleccionado);
        Object[] fila = new Object[5];

        for (Elemento elem : lista) {
            fila[0] = elem.getCodigoElemento();
            fila[1] = elem.getNombreElemento();
            fila[2] = elem.getCategoria();
            fila[3] = elem.getEstado();
            fila[4] = elem.getDescripcion();
            modeloTabla.addRow(fila);
        }
    }

    private void abrirFormularioEditar() {
        int filaSeleccionada = vista.tablaDeElementos.getSelectedRow();

        if (filaSeleccionada != -1) {
            Elemento elemSeleccionado = new Elemento();
            elemSeleccionado.setCodigoElemento(Integer.parseInt(vista.tablaDeElementos.getValueAt(filaSeleccionada, 0).toString()));
            elemSeleccionado.setNombreElemento(vista.tablaDeElementos.getValueAt(filaSeleccionada, 1).toString());
            elemSeleccionado.setCategoria(vista.tablaDeElementos.getValueAt(filaSeleccionada, 2).toString());
            elemSeleccionado.setEstado(vista.tablaDeElementos.getValueAt(filaSeleccionada, 3).toString());
            elemSeleccionado.setDescripcion(vista.tablaDeElementos.getValueAt(filaSeleccionada, 4).toString());

            VentanaEditarElemento ventanaEditar = new VentanaEditarElemento(vista);

            Controlador_EditarElemento contEditar = new Controlador_EditarElemento(ventanaEditar, elemSeleccionado);
            ventanaEditar.setLocationRelativeTo(vista);
            ventanaEditar.setVisible(true);
            filtrarComponentesTabla();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //dar clic a cerrar sesion 
        if (e.getSource() == vista.botonCerrarSesion) {
            vista.dispose();
            Vista_Login vistaLogin = new Vista_Login();
            Controlador_Login controlador = new Controlador_Login(vistaLogin);
            vistaLogin.setVisible(true);
        }

        if (e.getSource() == vista.botonInventario) {
            vista.dispose();
            Vista_Inventario vistaInventario = new Vista_Inventario();
            Controlador_inventario controladorIn = new Controlador_inventario(vistaInventario);
            vistaInventario.setVisible(true);
        }

        if (e.getSource() == vista.botonSolicitudes) {
            vista.dispose();
            Vista_Solicitudes vistaSolicitud = new Vista_Solicitudes();
            Controlador_Solicitudes controladorSol = new Controlador_Solicitudes(vistaSolicitud);
            vistaSolicitud.setVisible(true);
        }

    }

}

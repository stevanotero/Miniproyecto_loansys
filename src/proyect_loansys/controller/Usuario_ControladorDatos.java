/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import proyect_loansys.model.Administrador_Sesion;
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.model.Usuario_Elemento;
import proyect_loansys.model.Usuario_Historial;
import proyect_loansys.model.Usuario_Model;
import proyect_loansys.view.Usuario_HistorialPrestamo;
import proyect_loansys.view.Usuario_Inventario;
import proyect_loansys.view.Usuario_SolicitarPrestamo;

public class Usuario_ControladorDatos implements ActionListener {

    private Usuario_SolicitarPrestamo soliprestamo1;
    private Usuario_Inventario inveta;
    public Usuario_Dao elementoDao = new Usuario_Dao();
    public Usuario_Elemento elementos = new Usuario_Elemento();
    public Usuario_Historial histori = new Usuario_Historial();
    //public Usuario_HistorialPrestamo historipres = new Usuario_HistorialPrestamo();
    DefaultTableModel modelo = new DefaultTableModel();

    public Usuario_ControladorDatos(Usuario_Inventario inveta,
            Usuario_SolicitarPrestamo soliprestamo) {
        this.soliprestamo1 = soliprestamo;

        this.inveta = inveta;

        this.inveta.prueba.addActionListener(this);
        getListar(soliprestamo.tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inveta.prueba) {
            getListar(soliprestamo1.tabla);

        }
    }

    public void getListar(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();

        List<Usuario_Elemento> lista = elementoDao.listar();
        Object[] object = new Object[4];
        for (int indice = 0; indice < lista.size(); indice++) {
            //Object[] object = new Object[4];
            object[0] = lista.get(indice).getCodigo_elemento();
            object[1] = lista.get(indice).getNombre_elemento();
            object[2] = lista.get(indice).getCategoria_nombre();
            object[3] = lista.get(indice).getDescripcion();
            modelo.addRow(object);

        }
        soliprestamo1.tabla.setModel(modelo);
        //limpiarTabla();

    }

    public void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) soliprestamo1.tabla.getModel();
        if (model.getRowCount() > 0) {  // Solo eliminar si hay filas
            for (int i = model.getRowCount() - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }

    public void mostrarImagen(String nombreArchivo) {
        ImageIcon icono = new ImageIcon(getClass().getResource("/proyect_loansys/img/" + nombreArchivo));
        soliprestamo1.texto2.setIcon(icono);
        soliprestamo1.texto2.setText(null);
    }

    // El controlador para hacer la Busqueda de cada tabla
    public void pruebaMostrar(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(1);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar1(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(2);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar2(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(3);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar3(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(4);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar4(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(5);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar5(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(6);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar6(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(7);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar7(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(8);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar8(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(9);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar9(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(10);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar10(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(11);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar11(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(12);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar12(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(13);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void pruebaMostrar13(JTable tabla) {
        Usuario_Elemento e = elementoDao.mostrarFila(14);

        if (e != null) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // limpia la tabla antes de cargar

            modelo.addRow(new Object[]{
                e.getCodigo_elemento(),
                e.getNombre_elemento(),
                e.getCategoria_nombre(),
                e.getDescripcion(),});
        } else {
            System.out.println("No se encontró el elemento con ese ID");
        }
    }

    public void mostrarEstado(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(1);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado1(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(2);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado2(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(3);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado3(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(4);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado4(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(5);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado5(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(6);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado6(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(7);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado7(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(8);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado8(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(9);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado9(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(10);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado10(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(11);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado11(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(12);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado12(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(13);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarEstado13(JLabel texto) {
        Usuario_Elemento e = elementoDao.mostrarFila(14);

        if (e != null) {
            texto.setText(e.getEstado_nombre());
        } else {
            texto.setText("No encontrado");
        }
    }

    public void mostrarH(JTable tabla) {
        // Limpiar la tabla antes de cargar nuevos datos
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        if (modelo.getRowCount() > 0) {
            modelo.setRowCount(0);
        }

        // Obtener el ID del usuario actual
        int idUsuario = Administrador_Sesion.getIdUsuario();

        // Obtener el historial solo del usuario actual
        List<Usuario_Historial> lista = elementoDao.listarHistorialPorUsuario(idUsuario);

        if (lista != null && !lista.isEmpty()) {
            for (Usuario_Historial h : lista) {
                modelo.addRow(new Object[]{
                    h.getCodigo_elemento(),
                    h.getNombre_elemento(),
                    h.getNombre_categoria(),
                    h.getFecha_prestamo(),
                    h.getFecha_limite(),
                    h.getNombre_estado_entrega()
                });
            }
        }

        tabla.setModel(modelo);
    }
}

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
import proyect_loansys.model.Usuario_Dao;
import proyect_loansys.model.Usuario_Elemento;
import proyect_loansys.model.Usuario_Model;
import proyect_loansys.view.Usuario_Inventario;
import proyect_loansys.view.Usuario_SolicitarPrestamo;

public class Usuario_ControladorDatos implements ActionListener {

    private Usuario_SolicitarPrestamo soliprestamo1;
    private Usuario_Inventario inveta;
    public Usuario_Dao elementoDao = new Usuario_Dao();
    public Usuario_Elemento elementos = new Usuario_Elemento();
    DefaultTableModel modelo = new DefaultTableModel();

    public Usuario_ControladorDatos(Usuario_Inventario inveta, Usuario_SolicitarPrestamo soliprestamo) {
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
        for (int i = 0; i < soliprestamo1.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
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
/*
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
    

    Usuario_Dao dao = new Usuario_Dao();
    Usuario_Model resultado = dao.login(correoIngresado, contraseñaIngresada);


if (resultado != null) {
    // Aquí "saco" los datos del objeto y los guardo en variables sueltas
    int id = resultado.getId_usuario();
    String nombre = resultado.getNombre();
    String rol = resultado.getNombre_rol();

    System.out.println("Nombre: " + nombre);
    System.out.println("Rol: " + rol);

} else {
    System.out.println("Login incorrecto");
}
*/
}

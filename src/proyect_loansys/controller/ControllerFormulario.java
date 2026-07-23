package proyect_loansys.controller;

/**
 *
 * @author Sants
 */

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import proyect_loansys.model.HistorialMantenimientoDao;
import proyect_loansys.model.HistorialMantenimiento;
import proyect_loansys.view.Formulario;

public class ControllerFormulario implements ActionListener {

    private Formulario vista;
    private HistorialMantenimientoDao dao = new HistorialMantenimientoDao();
    private int idUsuarioEncontrado = -1;  //id tc x cc
    private int idElementoEncontrado = -1; //id elm x c elm

    public ControllerFormulario(Formulario vista) {
        this.vista = vista;
        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnCancelar().addActionListener(this);

        //pa q se vea 
        this.vista.getTxtTecnico().setEditable(false);
        this.vista.getTxtTecnico().setFocusable(false); //pa q no se pueda escribir ni nd (nombre
        this.vista.getTxtTecnico().setEnabled(true);
        this.vista.getTxtTecnico().setForeground(Color.BLACK); //paq se vea negra la letr a
        this.vista.getTxtTecnico().setBackground(new Color(230, 230, 230)); //el fondo gris y q no se mueva la suc
        this.vista.getTxtTecnico().setDisabledTextColor(Color.BLACK);

        //paq no se pueda escribri
        this.vista.getTxtNombreElemento().setEditable(false);
        this.vista.getTxtNombreElemento().setFocusable(false);
        this.vista.getTxtNombreElemento().setEnabled(true);
        this.vista.getTxtNombreElemento().setForeground(Color.BLACK);
        this.vista.getTxtNombreElemento().setBackground(new Color(230, 230, 230));
        this.vista.getTxtNombreElemento().setDisabledTextColor(Color.BLACK);

        //la categoria y el coso pa q se vea negrala letra
        this.vista.getCCategoria().setEditable(false);
        this.vista.getCCategoria().setFocusable(false);
        this.vista.getCCategoria().setEnabled(true);
        this.vista.getCCategoria().setForeground(Color.BLACK);
        this.vista.getCCategoria().setBackground(new Color(230, 230, 230));
        this.vista.getCCategoria().setDisabledTextColor(Color.BLACK);

        //pone el tecnico
        this.vista.getTxtDocumento().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    buscarTecnico();
                }
            }
        });

        //pone el elemtno
        this.vista.getTxtCodigoElemento().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    buscarElemento();
                }
            }
        });
    }

    //l odel boton
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrar();
        }
        if (e.getSource() == vista.getBtnCancelar()) {
            cerrarFormulario();
        }
    }

    private void buscarTecnico() {
        String documento = vista.getTxtDocumento().getText().trim();
        if (documento.isEmpty()) {
            return;
        }

        Object[] resultado = dao.buscarTecnicoPorDocumento(documento);

        if (resultado != null) {
            idUsuarioEncontrado = (int) resultado[0];
            vista.getTxtTecnico().setText(resultado[1].toString());
        } else {
            idUsuarioEncontrado = -1;
            vista.getTxtTecnico().setText("");
            JOptionPane.showMessageDialog(
                    vista,
                    "No se encontró ningún técnico con ese número de documento.",
                    "Técnico no encontrado",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void buscarElemento() {
        String codigoTxt = vista.getTxtCodigoElemento().getText().trim();
        if (codigoTxt.isEmpty()) {
            return;
        }

        int codigoElemento;
        try {
            codigoElemento = Integer.parseInt(codigoTxt);
        } catch (NumberFormatException ex) {
            idElementoEncontrado = -1;
            vista.getTxtNombreElemento().setText("");
            ponerCategoria();
            JOptionPane.showMessageDialog(
                    vista, "El código de elemento debe ser un número.", "Dato inválido", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Object[] resultado = dao.buscarElementoPorCodigo(codigoElemento);

        if (resultado != null) {
            idElementoEncontrado = (int) resultado[0];
            vista.getTxtNombreElemento().setText(resultado[1].toString());            
            String nombreCatDb = resultado[2].toString();
            String categoriaEnum = "Herramientas";
            
            if (nombreCatDb.contains("Electr") || nombreCatDb.contains("Medici")) {
                categoriaEnum = "Tecnologia";
            } else if (nombreCatDb.contains("C?mputo") || nombreCatDb.contains("Cómputo")) {
                categoriaEnum = "Portatiles";
            } else if (nombreCatDb.contains("Laboratorio")) {
                categoriaEnum = "Maquinas";
            }
            vista.getCCategoria().setText(categoriaEnum);
        } else {
            idElementoEncontrado = -1;
            vista.getTxtNombreElemento().setText("");
            ponerCategoria();            
            JOptionPane.showMessageDialog(
                    vista,
                    "No se encontró ningún elemento con ese código.",
                    "Elemento no encontrado",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
 
    private void ponerCategoria() {
        vista.getCCategoria().setText("Sin categoría");
    }
    
    private void registrar() {
        String categoria = vista.getCCategoria().getText().trim();
        String codigoElementoTxt = vista.getTxtCodigoElemento().getText().trim();
        String tecnico = vista.getTxtTecnico().getText().trim();
        String estado = vista.getCEstado().getSelectedItem().toString();
        String documento = vista.getTxtDocumento().getText().trim();
        String descripcion = vista.getTxtDescripcion().getText().trim();
        String tipoMantenimiento = vista.getCTipoMantenimiento().getSelectedItem().toString();

        if (codigoElementoTxt.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vista, "El código de elemento no puede quedar vacío.", "Campo obligatorio", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vista, "El número de documento no puede quedar vacío.", "Campo obligatorio", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (tecnico.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vista, "Debes ingresar un número de documento válido para que aparezca el técnico.", "Campo obligatorio", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (tipoMantenimiento.equals("Seleccione")) {
            JOptionPane.showMessageDialog(
                    vista, "Debes seleccionar el tipo de mantenimiento.", "Campo obligatorio", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (estado.equals("Seleccione")) {
            JOptionPane.showMessageDialog(
                    vista, "Debes seleccionar el estado del elemento.", "Campo obligatorio", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(
                    vista, "La descripción no puede quedar vacía.", "Campo obligatorio", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        //el tecnico debe de llegar si osi por una consulta del documento
        if (idUsuarioEncontrado == -1) {
            JOptionPane.showMessageDialog(
                    vista, "Debes ingresar un número de documento válido y registrado en el sistema.", "Técnico no válido", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        //el elemento debe de llegar si o si por una consulta del codigo_elemento
        if (idElementoEncontrado == -1) {
            JOptionPane.showMessageDialog(
                    vista, "Debes ingresar un código de elemento válido y registrado en el inventario.", "Elemento no válido", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        //el codigo de 5 digitos aleatorio
        String codigoMantenimiento = dao.generarCodigoMantenimiento();

        HistorialMantenimiento h = new HistorialMantenimiento();
        h.setCodigoMantenimiento(codigoMantenimiento);
        h.setIdElemento(idElementoEncontrado);
        h.setCategoria(categoria);
        h.setTipoMantenimiento(tipoMantenimiento);
        h.setEstadoElemento(estado);
        h.setIdUsuario(idUsuarioEncontrado);
        h.setDescripcion(descripcion);

        int resultado = dao.setAgregar(h);

        if (resultado == 1) {
            JOptionPane.showMessageDialog(
                    vista,
                    "El registro ha sido exitoso.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            cerrarFormulario();
        }
    }

    private void cerrarFormulario() {
        Window ventana = SwingUtilities.getWindowAncestor(vista);
        if (ventana != null) {
            ventana.dispose();
        }
    }
}
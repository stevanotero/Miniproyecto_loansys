package proyect_loansys.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase para generar reportes PDF
 */
public class PDFExporter {

    private static final Font TITULO_FONT = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static final Font SUBTITULO_FONT = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);

    public static boolean generarPDFConSelector(JTable tabla, String tituloReporte, String nombreBase, java.awt.Component parent) {
    try {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Reporte PDF");
        
        String fecha = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
        fileChooser.setSelectedFile(new File(nombreBase + "_" + fecha + ".pdf"));
        
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo PDF (*.pdf)", "pdf"));

        int userSelection = fileChooser.showSaveDialog(parent);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File archivo = fileChooser.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".pdf")) {
            archivo = new File(archivo.getAbsolutePath() + ".pdf");
        }

        // Generar PDF
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(archivo));
        document.open();

        Paragraph titulo = new Paragraph("SISTEMA DE PRÉSTAMOS SENA", TITULO_FONT);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);

        Paragraph subtitulo = new Paragraph(tituloReporte, SUBTITULO_FONT);
        subtitulo.setAlignment(Element.ALIGN_CENTER);
        subtitulo.setSpacingAfter(20);
        document.add(subtitulo);

        Paragraph fechaP = new Paragraph("Fecha: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        fechaP.setAlignment(Element.ALIGN_RIGHT);
        document.add(fechaP);

        document.add(new Paragraph("\n"));

        // Tabla
        int columnas = tabla.getColumnCount();
        PdfPTable pdfTable = new PdfPTable(columnas);
        pdfTable.setWidthPercentage(100);

        for (int i = 0; i < columnas; i++) {
            pdfTable.addCell(new com.itextpdf.text.Phrase(tabla.getColumnName(i), SUBTITULO_FONT));
        }

        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            for (int col = 0; col < columnas; col++) {
                Object valor = tabla.getValueAt(fila, col);
                pdfTable.addCell(valor != null ? valor.toString() : "");
            }
        }

        document.add(pdfTable);
        document.close();

        return true;

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(parent, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyect_loansys.model;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Santiago
 */
public class GeneradorReportePDF {

    private static final Font FONT_TITULO = FontFactory.getFont(FontFactory.HELVETICA, 22, Font.NORMAL);
    private static final Font FONT_SUBTITULO = FontFactory.getFont(FontFactory.HELVETICA, 13, Font.BOLD | Font.UNDERLINE);
    private static final Font FONT_TEXTO = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);

    public boolean generarPDF(String rutaDestino, Object[] datos) {
        if (datos == null) {
            return false;
        }

        if (!rutaDestino.toLowerCase().endsWith(".pdf")) {
            rutaDestino = rutaDestino + ".pdf";
        }

        Document documento = new Document(PageSize.A4, 50, 50, 50, 50);

        try {
            PdfWriter.getInstance(documento, new FileOutputStream(rutaDestino));
            documento.open();

            Paragraph titulo = new Paragraph("LoanSys - Reporte", FONT_TITULO);
            titulo.setSpacingAfter(20);
            documento.add(titulo);


            
            documento.add(seccion("Historial mantenimiento"));

            documento.add(campo("Codigo del mantenimiento", datos[0]));
            documento.add(campo("Nombre del elemento", datos[1]));
            documento.add(campo("Codigo del elemento", datos[2]));
            documento.add(campo("Categoria asignada", datos[3]));
            documento.add(campo("Tipo de mantenimiento", datos[4]));
            documento.add(campo("Estado del elemento", datos[5]));
            documento.add(campo("Fecha realizado", datos[6]));
            documento.add(campo("Nombre del tecnico", datos[7] + " - " + datos[8]));
            documento.add(campo("Numero documento del tecnico", datos[9]));
            documento.add(campo("Descripcion del mantenimiento",
                    datos[10] == null ? "Sin descripcion" : datos[10]));


            
            Paragraph seccionReporte = seccion("Reportes tecnico");
            seccionReporte.setSpacingBefore(25);
            documento.add(seccionReporte);

            documento.add(campo("Fecha realizada", datos[11]));
            documento.add(campo("Codigo Reporte", datos[12]));

            documento.close();
            return true;

        } catch (FileNotFoundException fnf) {
            return false;
        } catch (DocumentException de) {
            return false;
        }
    }

    private Paragraph seccion(String texto) {
        Paragraph p = new Paragraph(texto, FONT_SUBTITULO);
        p.setSpacingAfter(10);
        return p;
    }

    private Paragraph campo(String etiqueta, Object valor) {
        String valorTexto = (valor == null) ? "" : valor.toString();
        Paragraph p = new Paragraph(etiqueta + ": " + valorTexto, FONT_TEXTO);
        p.setSpacingAfter(6);
        p.setAlignment(Element.ALIGN_LEFT);
        return p;
    }
}
package com.samsbeauty.warehouse.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class TableHeader extends PdfPageEventHelper {
	 /** The header text. */
    String header;
    String headerBarcode;
    /** The template with the total number of pages. */
    PdfTemplate total;
    
    private String dateDisp;
    private Font font;

    public TableHeader(String dateDisp, Font font) {
    	this.dateDisp = dateDisp;
    	this.font = font;
    }
    
    /**
     * Allows us to change the content of the header.
     * @param header The new header String
     */
    public void setHeader(String header) {
        this.header = header;
    }

    public void setHeaderBarcode(String headerBarcode) {
        this.headerBarcode = headerBarcode;
    }

    /**
     * Creates the PdfTemplate that will hold the total number of pages.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }

    /**
     * Adds a header to every page
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(2);
        try {
            table.setWidths(new int[]{24, 24});
            table.setTotalWidth(527);
            table.getDefaultCell().setFixedHeight(45);
            table.getDefaultCell().setBorder(0);
            table.addCell(header);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
            table.addCell(new Phrase(String.format("Page %d", writer.getPageNumber()) + "  " + dateDisp, font));

            table.writeSelectedRows(0, 1, 50, 803, writer.getDirectContent());
        }catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Fills out the total number of pages before the document is closed.
     * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
     *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
     */
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                2, 2, 0);
    }
}

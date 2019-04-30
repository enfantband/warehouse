package com.samsbeauty.warehouse.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingItemExport;
import com.samsbeauty.warehouse.picking.export.model.GroupPickingJobExport;

public class PickingReportExporter {
	Document document;
	PdfWriter writer;
	public void saveGroupPickingList(
			String path,  
			WarehouseEmployee regBy,
			Integer pickingSet,
			GroupPickingJobExport groupPickingJobExport
			) throws DocumentException, IOException {
		document = new Document(PageSize.LETTER);
		
		writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		TableHeader event = new TableHeader(DateUtil.getToday(), new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY));
	    writer.setPageEvent(event);	    
		
		document.open();
		document.addTitle("Group Picking List");
		document.addCreationDate();

		document.add(getPageHeader(regBy.getName()));
		
		document.add(new Chunk(""));
		
		Paragraph p = new Paragraph();
		Font titleFont = new Font(FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
		p.add(new Chunk("Group Picking List - " + groupPickingJobExport.getGroupName()));
		p.setAlignment(Element.ALIGN_CENTER);
		p.setFont(titleFont);
		document.add(p);
		
		p = new Paragraph();
		p.add(new Chunk(groupPickingJobExport.getGroupDescription(), new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.DARK_GRAY)));
		p.setAlignment(Element.ALIGN_CENTER);
		p.setLeading(10);
		document.add(p);
		
		// Create item by group
		drawTop(groupPickingJobExport, pickingSet);
		drawItemList(groupPickingJobExport.getItemList());
		document.close();
	}
	private void drawTop(GroupPickingJobExport pickingJob, Integer pickingSet) throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{ (float)28, (float)42, (float)30 });
		String groupName = pickingJob.getGroupName().toUpperCase() + "-" + pickingSet.toString();
		Phrase groupTitle = new Phrase(groupName, new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.DARK_GRAY));
		Phrase groupDetail = new Phrase(pickingJob.getGroupDescription(), new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY));
		PdfPCell cell = new PdfPCell();
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.addElement(groupTitle);
		cell.addElement(groupDetail);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Missing (       ) Wrong (       )", new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY)));
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setPaddingTop(40);
		cell.setFixedHeight(60);
		cell.setBorder(0);
		table.addCell(cell);
		
		PdfPCell outerCell = new PdfPCell();
		PdfPTable signTable = new PdfPTable(1);
		signTable.setWidthPercentage(100f);
		signTable.setWidths(new float[]{ (float) 100 });
		
		cell = new PdfPCell(new Phrase("Picker", new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY)));
		cell.setBorder(0);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		signTable.addCell(cell);
		cell = new PdfPCell(new Phrase(" "));
		signTable.addCell(cell);

		outerCell.addElement(signTable);
		outerCell.setBorder(0);
		outerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(outerCell);
		document.add(table);
	}
	private void drawItemList(Collection<GroupPickingItemExport> groupPickingItemExports) throws DocumentException {
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{ (float)2.5, (float)8 , (float)23 , (float)8 , (float)15, (float)2.5, (float)8});
		PdfPCell cell;
		
		Font headerFont = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
		Font detailFont = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.BLACK);
		
		cell = new PdfPCell(new Phrase("",headerFont));		
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("LOCATION",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);		
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("DESCRIPTION ( ITEM CODE )",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);		
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("OPTION",headerFont));	
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);	
		table.addCell(cell); 
		cell = new PdfPCell(new Phrase("BARCODE",headerFont));	
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Qty",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);		
		table.addCell(cell); 
		cell = new PdfPCell(new Phrase("COMMENT",headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);		
		table.addCell(cell); 
		int seq = 1;
		for(GroupPickingItemExport gp : groupPickingItemExports) {
			
			cell = new PdfPCell(new Phrase(String.format("%d", seq), detailFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			// location
			StringBuilder sb = new StringBuilder();			
			for(String location : gp.getLocations()) {
				sb.append(location);
				sb.append(System.lineSeparator());
			}
			
			cell = new PdfPCell(new Phrase(sb.toString(), detailFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			// description
			cell = new PdfPCell(new Phrase(gp.getDescription(), detailFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			// option
			cell = new PdfPCell(new Phrase(gp.getOption(), detailFont));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			// barcode
			Barcode128 barcode = new Barcode128();
			barcode.setCode(gp.getGeneratedBarcode());
			
			cell = new PdfPCell(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK), false);
			cell.setPadding(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			// quantity
			cell = new PdfPCell(new Phrase(gp.getQuantity().toString(), detailFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			// Comment
			cell = new PdfPCell(new Phrase(""));
			table.addCell(cell);
			
		}
		document.add(table);
	}
	
	public Paragraph getPageHeader(String name) {
		Paragraph p = new Paragraph();
		p.add(new Chunk("Generated by " + name + " (" + DateUtil.getToday() + ")", new Font(FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.DARK_GRAY)));
		p.setAlignment(Element.ALIGN_RIGHT);
		p.setLeading(10);
		return p;
	}
}

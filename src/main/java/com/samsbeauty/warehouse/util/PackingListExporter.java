package com.samsbeauty.warehouse.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.samsbeauty.old.model.OrderInfoForPicking;
import com.samsbeauty.old.model.OrderItem;
import com.samsbeauty.old.model.OrderItem.InventoryStatus;
import com.samsbeauty.old.model.OrderItem.ProductType;
import com.samsbeauty.warehouse.employee.model.WarehouseEmployee;

public class PackingListExporter {
	Document document;
	PdfWriter writer;
	public static Font headerFont = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, BaseColor.DARK_GRAY);
	public static Font smallFont = new Font(FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.DARK_GRAY);
	public static Font titleFont = new Font(FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.DARK_GRAY);
	public static Font normalFont = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.DARK_GRAY);
	public static Font normalItalicFont = new Font(FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.DARK_GRAY);
	public static Font boldFont = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.DARK_GRAY);
	public static Font barcodeLabelFont = new Font(FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);
	
	public void createPackingList(String path, WarehouseEmployee regBy, List<OrderInfoForPicking> orderInfoForPickings) throws DocumentException, MalformedURLException, IOException {
		document = new Document(PageSize.LETTER);
		
		writer = PdfWriter.getInstance(document, new FileOutputStream(path));
		TableHeader event = new TableHeader(DateUtil.getToday(), headerFont);
	    writer.setPageEvent(event);	    
		
		document.open();
		document.addTitle("Packing List");
		document.addCreationDate();
		
		
		
		document.add(new Chunk(""));
		
		Integer seq = 0;
		for(OrderInfoForPicking oi : orderInfoForPickings) {
			if(seq != 0) {
				document.newPage();
			}
			createToBox(oi);
			createOrderInfoBox(oi);
			createOrderItemBox(oi.getOrderItems());
			seq ++;
		}
		
		document.close();
	}
	
	public void createOrderItemBox(List<OrderItem> orderItems) throws DocumentException {
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{ 1.7f, 18f, 30f, 12f, 3f, 6f, 8.3f});
		PdfPCell cell;
		
		cell = new PdfPCell(new Phrase(""));
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("BARCODE", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("DESCRIPTION & SKU", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("OPTION", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("QTY", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("PRICE", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("LOCATION", headerFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		Set<String> dealChecker = new HashSet<>();
		Integer dealCount = 1;
		Integer seq = 1;
		Boolean hairLineDisp = Boolean.FALSE;
		Boolean gmLineDisp = Boolean.FALSE;
		List<OrderItem> hairList = new ArrayList<>();
		List<OrderItem> gmList = new ArrayList<>();
		for(OrderItem oi : orderItems) {
			if(oi.getProductType().equals(ProductType.HAIR)) {
				hairList.add(oi);
			} else {
				gmList.add(oi);
			}
		}
		List<OrderItem> orgList = new ArrayList<>();
		orgList.addAll(hairList);
		orgList.addAll(gmList);
		for(OrderItem oi : orgList) {
			if(oi.getProductType().equals(ProductType.HAIR) && !hairLineDisp) {
				cell = new PdfPCell(new Phrase("---------- HAIR LIST ----------", normalFont));
				cell.setColspan(7);
				table.addCell(cell);
				hairLineDisp = Boolean.TRUE;
			}
			
			if(!oi.getProductType().equals(ProductType.HAIR) && !gmLineDisp) {
				cell = new PdfPCell(new Phrase("---------- GM LIST ----------", normalFont));
				cell.setColspan(7);
				table.addCell(cell);
				gmLineDisp = Boolean.TRUE;
			}

			// sequence number (column 0)
			cell = new PdfPCell(new Phrase(seq.toString(), normalFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			// barcode (product) (column 1)
			if(StringUtils.isEmpty(oi.getProductBarcode())){
				PdfPTable barcodeTable = getBarcodeTable(oi.getGeneratedBarcode() + "P", 80f, false);
				
				cell = new PdfPCell(new Phrase(oi.getVendorItemCode(), normalFont));
				cell.setBorder(0);
				barcodeTable.addCell(cell);
				
				cell = new PdfPCell();
				cell.addElement(barcodeTable);
				table.addCell(cell);
			} else {
				PdfPTable titleTable = new PdfPTable(1);
				titleTable.setWidthPercentage(100f);
				
				cell = new PdfPCell(new Phrase(oi.getGeneratedBarcode(), titleFont));
				
				cell.setBorder(0);
				cell.setPadding(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				titleTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(oi.getVendorItemCode(), normalFont));
				cell.setBorder(0);
				cell.setPadding(0);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				titleTable.addCell(cell);
				
				cell = new PdfPCell();
				cell.addElement(titleTable);
				table.addCell(cell);
			}
			
			// title (column 2)
			if(oi.getInventoryStatus().equals(InventoryStatus.DISCONTINUE) || oi.getInventoryStatus().equals(InventoryStatus.FINAL_SALE)){
				PdfPTable titleTable = new PdfPTable(1);
				titleTable.setWidthPercentage(100f);
				cell = new PdfPCell(new Phrase(oi.getTitle() + " (" + oi.getProductGroup() + ")", normalFont));
				cell.setBorder(0);
				titleTable.addCell(cell);
				
				PdfPTable titleIn = new PdfPTable(3);
				titleIn.setWidthPercentage(100f);				
				titleIn.setWidths(new float[]{ 3.5f, 0.1f, 0.4f });
				
				cell = new PdfPCell();
				if(!StringUtils.isEmpty(oi.getProductBarcode())){
					PdfPTable barcodeTable = getBarcodeTable(oi.getGeneratedBarcode() + "T", 60f, false);					
					cell.addElement(barcodeTable);
				}
				cell.setBorder(0);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				titleIn.addCell(cell);
				
				cell = new PdfPCell();
				cell.setBorder(0);
				titleIn.addCell(cell);
								
				String statusMark = "F";
				if(oi.getInventoryStatus().equals(InventoryStatus.DISCONTINUE) ) {
					statusMark = "D";
				}
				PdfPTable markTable = new PdfPTable(1);
				cell = new PdfPCell(new Phrase(statusMark, new Font(FontFamily.HELVETICA, 7, Font.NORMAL, BaseColor.WHITE)));
				cell.setBackgroundColor(BaseColor.GRAY);
				cell.setBorder(0);;
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				markTable.addCell(cell);
				
				cell = new PdfPCell();
				cell.setBorder(0);
				cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell.addElement(markTable);
				titleIn.addCell(cell);
				
				cell = new PdfPCell();
				cell.addElement(titleIn);
				cell.setBorder(0);
				titleTable.addCell(cell);
				
				cell = new PdfPCell();
				cell.addElement(titleTable);
				table.addCell(cell);
				
			} else {
				PdfPTable titleTable = new PdfPTable(1);
				titleTable.setWidthPercentage(100f);
				cell = new PdfPCell(new Phrase(oi.getTitle() + " (" + oi.getProductGroup() + ")", normalFont));
				cell.setBorder(0);
				titleTable.addCell(cell);
				
				if(!StringUtils.isEmpty(oi.getProductBarcode())) {
					PdfPTable titleIn = new PdfPTable(1);
					titleIn.setWidthPercentage(100f);		
					
					cell = new PdfPCell();
					PdfPTable barcodeTable = getBarcodeTable(oi.getGeneratedBarcode() + "T", 60f, false);	
					cell.setBorder(0);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);				
					cell.addElement(barcodeTable);
					titleIn.addCell(cell);
					
					cell = new PdfPCell();
					cell.setBorder(0);
					cell.addElement(titleIn);
					titleTable.addCell(cell);
				}
				cell = new PdfPCell();		
				cell.addElement(titleTable);
				table.addCell(cell);
			}
			
			// column3
			String optionStr = "";
			if(!StringUtils.isEmpty(oi.getFirstOption())) {
				optionStr = "Option: " + oi.getFirstOption();
			}
			cell = new PdfPCell(new Phrase(optionStr, normalFont));
			table.addCell(cell);
			
			//column 4
			cell = new PdfPCell(new Phrase(oi.getQuantity().toString(), normalFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			// column 5
			String finalPriceStr = oi.getFinalPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
			String usedPointStr = "";
			String unitPriceStr = "";
			if(oi.getUsedPoint() != null && oi.getUsedPoint() > 0) {
				finalPriceStr = "0.00";
				usedPointStr = "P " + oi.getUsedPoint().toString();
			}

			BigDecimal unitPrice = BigDecimal.valueOf(oi.getFinalPrice().doubleValue() / oi.getQuantity().doubleValue()).setScale(2, BigDecimal.ROUND_UP);
			unitPriceStr = unitPrice.toString();
			
			if(!StringUtils.isEmpty(oi.getDealGroup())) {
				if(dealChecker.contains(oi.getDealGroup())) {
					finalPriceStr = "-";
					unitPriceStr = "-";
				} else {
					dealChecker.add(oi.getDealGroup());
					dealCount ++;
				}
			}
			
			StringBuilder priceSb = new StringBuilder();
			if(oi.getUsedPoint() != null && oi.getUsedPoint() > 0) {
				priceSb.append(usedPointStr);
			} else {
				priceSb.append(unitPriceStr);
				priceSb.append(System.lineSeparator());
				priceSb.append("/");
				priceSb.append(System.lineSeparator());
				priceSb.append(finalPriceStr);
			}
			
			cell = new PdfPCell(new Phrase(priceSb.toString(), normalFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
								
			// column 6
			String locationStr = "";
			if(oi.getLocationNames() != null && oi.getLocationNames().size() > 0) {
				locationStr = oi.getLocationNames().get(0);
			}
			cell = new PdfPCell(new Phrase(locationStr.toString(), smallFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			seq++;
		}
		document.add(table);
	}
	
	public void createToBox(OrderInfoForPicking orderInfoForPicking) throws MalformedURLException, IOException, DocumentException {
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{ 30f, 40f, 30f });
		
		String url = "https://www.samsbeauty.com" + orderInfoForPicking.getLogoImagePath();
		
		PdfPCell cell = new PdfPCell();
		
		Image image = Image.getInstance(url);
		cell.setImage(image);
		
		cell.setBorderWidthBottom(1);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		table.addCell(cell);
		
		cell = new PdfPCell();
		
		cell.setBorder(0);
		cell.addElement(getBarcodeTable(orderInfoForPicking.getOrderNo(), 70f, true));
		cell.setBorderWidthBottom(1);
		cell.setBorderWidthTop(0);		
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(orderInfoForPicking.getRiDate(), normalFont));
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setFixedHeight(20);
		cell.setBorderWidthBottom(1);
		cell.setBorderWidthTop(0);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthLeft(0);
		cell.setPaddingBottom(5);
		table.addCell(cell);
		document.add(table);
	}
	
	public PdfPTable getBarcodeTable(String barcodeTxt, float width, boolean text) {
		PdfPTable barcodeTable = new PdfPTable(1);
		PdfPCell cell = new PdfPCell();
		barcodeTable.setWidthPercentage(width);
		
		cell = new PdfPCell(new Phrase());
		
		Barcode128 barcode = new Barcode128();
		barcode.setCode(barcodeTxt);
		if(!text) {
			barcode.setFont(null);
		}
		Image barcodeImg = barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
		
		cell.setImage(barcodeImg);
		cell.setBorder(0);
		cell.setPadding(0);
		barcodeTable.addCell(cell);
		
		return barcodeTable;
	}
	
	public void createOrderInfoBox(OrderInfoForPicking orderInfoForPicking) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{ 6f, 4f});
		PdfPCell cell = new PdfPCell();	
		PdfPTable shippingInfoTable = getShippingInfoTable(orderInfoForPicking);
		
		cell.addElement(shippingInfoTable);
		cell.setBorder(0);
		table.addCell(cell);
		
		cell = new PdfPCell();
		
		cell.addElement(getConfirmBox());
		cell.setBorder(0);
		table.addCell(cell);
		
		document.add(table);
		
		
	}
	
	public PdfPTable getShippingInfoTable(OrderInfoForPicking orderInfoForPicking) throws DocumentException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{ 4f, 6f });
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("Name: ", normalFont));
		cell.setBorder(0);
		table.addCell(cell);
		
		String firstname = orderInfoForPicking.getFirstname() == null ? "" : orderInfoForPicking.getFirstname();
		String middlename = orderInfoForPicking.getMiddlename() == null ? "" : orderInfoForPicking.getMiddlename();
		String lastname = orderInfoForPicking.getLastname() == null ? "" : orderInfoForPicking.getLastname();
		
		cell = new PdfPCell(new Phrase(firstname + " " + middlename + " " + lastname, normalFont));
		cell.setBorder(0);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("Email: ", normalFont));
		cell.setBorder(0);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(orderInfoForPicking.getEmail(), normalFont));
		cell.setBorder(0);
		table.addCell(cell);		
		
		cell = new PdfPCell(new Phrase("Shipping Address:", normalFont));
		cell.setBorder(0);
		table.addCell(cell);
		
		StringBuilder sinfo = new StringBuilder();
		sinfo.append(orderInfoForPicking.getShippingAddress1());
		sinfo.append(" ");
		sinfo.append(orderInfoForPicking.getShippingAddress2());
		sinfo.append(" ");
		sinfo.append(orderInfoForPicking.getShippingCity());
		sinfo.append(" ");
		sinfo.append(orderInfoForPicking.getShippingState());
		sinfo.append(" ");
		sinfo.append(orderInfoForPicking.getShippingZipcode());
		sinfo.append(" ");
		sinfo.append(orderInfoForPicking.getShippingCountry());
		
		cell = new PdfPCell(new Phrase(sinfo.toString(), normalFont));
		cell.setBorder(0);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Shipping Method:", normalFont));
		cell.setBorder(0);
        table.addCell(cell);
        Font sfont = new Font(FontFamily.HELVETICA, 9, Font.NORMAL);
        
        cell = new PdfPCell(new Phrase(orderInfoForPicking.getShippingMethodName(), sfont));
        if(UrgentShippingChecking.checkUrgentShipping(orderInfoForPicking.getShippingMethodName())){
        	sfont.setColor(BaseColor.WHITE);
        	cell = new PdfPCell(new Phrase(orderInfoForPicking.getShippingMethodName(),sfont));
        	cell.setBackgroundColor(BaseColor.GRAY);
        }else{
        	cell = new PdfPCell(new Phrase(orderInfoForPicking.getShippingMethodName(), normalFont));
        }
		cell.setBorder(0);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Shipping & Handling:", normalFont));
        cell.setBorder(0);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("$ " + orderInfoForPicking.getShippingPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString(), normalFont));
        cell.setBorder(0);
        table.addCell(cell);
        
        if(orderInfoForPicking.getShippingDiscPrice() != null && orderInfoForPicking.getShippingDiscPrice().doubleValue() > 0) {
        	cell = new PdfPCell(new Phrase("Shipping & Handling DISC:", normalFont));
        	cell.setBorder(0);
        	table.addCell(cell);
        	
        	cell = new PdfPCell(new Phrase("$ " + orderInfoForPicking.getShippingDiscPrice().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString(), normalFont));
        	cell.setBorder(0);
        	table.addCell(cell);
        }
        
		return table;
	}
	
	public PdfPTable getConfirmBox() throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		PdfPCell cell;
		table.setWidthPercentage(100f);
		
		table.setWidths(new float[]{ (float) 1.4 , (float)2.2 , (float)1.3});
		cell = new PdfPCell(new Phrase("Hair Pick  ", normalItalicFont));
		cell.setFixedHeight(22f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(" ", normalFont));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Total Basket", normalFont));
		cell.setFixedHeight(22f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("G/M Pick", normalFont));
		cell.setFixedHeight(22f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		table.addCell(new PdfPCell(new Phrase(" ", normalFont)));
		cell = new PdfPCell(new Phrase(" ", normalFont));
		cell.setRowspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Final Check & Input", normalFont));
		cell.setFixedHeight(22f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(" ", normalFont));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Packing", normalFont));
		cell.setFixedHeight(22f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(" ", normalFont));
		table.addCell(cell);
		
		table.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		return table;
	}
}

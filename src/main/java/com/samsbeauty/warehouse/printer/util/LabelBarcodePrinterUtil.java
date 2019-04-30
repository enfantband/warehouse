package com.samsbeauty.warehouse.printer.util;

import java.util.List;

import com.samsbeauty.warehouse.model.WarehouseItemBox;
import com.samsbeauty.warehouse.model.WarehouseLevel;

public class LabelBarcodePrinterUtil {
	public static String getWarehouseBarcode(List<WarehouseLevel> list, String listSeparator, String separator) {
		StringBuilder genBar = new StringBuilder();
		for(int i=0; i<list.size(); i++){
			WarehouseLevel level = list.get(i);
			genBar.append(level.getLocationName(separator));
			genBar.append(separator);
			genBar.append("L_");
			genBar.append(level.getWarehouseCode());
			if(i != list.size() - 1) {
				genBar.append(listSeparator);
			}
		}
		return genBar.toString();
	}
	
	public static String getBoxBarcode(List<WarehouseItemBox> list, String listSeparator) {
		StringBuilder genBar = new StringBuilder();
		for(int i=0; i<list.size(); i++) {
			WarehouseItemBox box = list.get(i);
			genBar.append("BOX_");
			genBar.append(box.getBoxPrefix());
			genBar.append(box.getBoxCode());
			if(i != list.size() - 1) {
				genBar.append(listSeparator);
			}
		}
		return genBar.toString();
	}
}

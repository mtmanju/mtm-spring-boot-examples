package com.mtm.springboot.util;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Row;

public class FuturesAndOptionsUtil {

	private FuturesAndOptionsUtil() {
	}

	public static String getOrderType(Row row, String type) {
		int convertColStringToIndex = CellReference.convertColStringToIndex(type);
		return row.getCell(convertColStringToIndex).getStringCellValue();
	}

	public static Double getOrderQuantity(Row row, String quantity) {
		int convertColStringToIndex = CellReference.convertColStringToIndex(quantity);
		return row.getCell(convertColStringToIndex).getNumericCellValue();
	}

	public static String getAveragePrice(Row row, String avgPrice) {
		int convertColStringToIndex = CellReference.convertColStringToIndex(avgPrice);
		return row.getCell(convertColStringToIndex).getStringCellValue();
	}
}

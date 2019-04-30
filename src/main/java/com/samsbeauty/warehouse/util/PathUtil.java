package com.samsbeauty.warehouse.util;


public class PathUtil {
	public static String getProductImagePath(String productGroup , String imageFileName)
	{
		String retVal = "";
		productGroup = removeSpecialCharacter(productGroup);
		imageFileName = getFileNameWithOutExt(imageFileName)+"."+getFileExt(imageFileName);		

		retVal = "https://www.samsbeauty.com/common/productimages/" + productGroup + "/" + imageFileName;
		
		
		return retVal;
	}
	public static String getProductImagePathWithUrl(String productGroup , String imageFileName)
	{
		String retVal = "";
		productGroup = removeSpecialCharacter(productGroup);
		imageFileName = getFileNameWithOutExt(imageFileName)+"."+getFileExt(imageFileName);		

		retVal = "https://www.samsbeauty.com/common/productimages/" + productGroup + "/" + imageFileName;
		
		
		return retVal;
	}
	public static String getNoImagePath() {
		return "https://www.samsbeauty.com/common/productimages/noimage_2.gif";
	}
	public static String getOptionImagePath( String imageFileName)
	{
		  return "https://www.samsbeauty.com/common/colorimages/" +  imageFileName;
	}
	
	public static String removeSpecialCharacter(String str) {
		return str.replaceAll("[^\\p{L}\\p{Z}0-9]", "");
	}
	public static String getFileNameWithOutExt(String fn) 
	{
		String ext = "";
		try {
			int index = fn.lastIndexOf(".");
			ext = fn.substring(0,index);
		}	
		catch(Exception e) {
		}
		
		return(ext);
	}
	public static String getFileExt(String fn) 
	{
		String ext = "";
		try {
			int index = fn.lastIndexOf(".");
			ext = fn.substring(index+1).toLowerCase();
		}	
		catch(Exception e) {
		}
		
		return(ext);
	}
}
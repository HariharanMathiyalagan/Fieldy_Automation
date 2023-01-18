package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javafaker.Faker;

public class Name extends BaseClass {

	public static void main(String[] args) throws IOException {
//String excelPath = "C:\\Users\\Zaigo PC\\eclipse-workspace\\Fieldy_Zaiportal_New\\Folder\\Test Data.xlsx";
//		File file = new File(excelPath);
//		FileInputStream fis = new FileInputStream(file);
//		XSSFWorkbook workBook = new XSSFWorkbook(fis);
//		
//		XSSFSheet sheet = workBook.getSheet("Onboarding");
//		sheet.getRow(1).createCell(1).setCellValue("Value");
//		FileOutputStream fos = new FileOutputStream(new File(excelPath));
//		workBook.write(fos);
//		workBook.close();
//		
//		
//
//		Properties properties = new Properties();
//		properties.setProperty("Tenant", "Test");
//		FileOutputStream stream = new FileOutputStream(System.getProperty("user.dir") + "\\Folder\\config.properties");
//		properties.store(stream, null);
////		String value = (String) properties.get("Tenant");

		Faker faker = new Faker(new Locale("en-IND"));
		String country = faker.address().country();
		System.out.println(country);

		
	}

}

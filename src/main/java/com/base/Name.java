package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Name extends BaseClass {

	public double calculation(String quantity, String price, String discount, String tax) {

//		double costBeforeTax = Double.parseDouble("1000.256");
//		double taxRate = Double.parseDouble("10");
//		double discountRate = Double.parseDouble("10");
//		String discount = costBeforeTax * (discountRate / 100);
//		double discountValue = costBeforeTax - discount;
//		String tax = discountValue * (taxRate / 100);
//		double taxableValue = costBeforeTax * (taxRate / 100);
//		double taxPlusDiscountValue = discountValue + tax;
//		double taxValue = tax;
//		double discountableValue = discountValue;
//		DecimalFormat format = new DecimalFormat("0.00");
//		String format2 = format.format(taxPlusDiscountValue);
//		System.out.println(format2);

		// priceCalculation
//		String Quantity = quantity;
//		String Price = price;
//		String Discount = discount;
//		String Tax = tax;

		double quantityValue = Double.parseDouble(quantity);
		double priceValue = Double.parseDouble(price);
		double discountValue = Double.parseDouble(discount);
		double taxValue = Double.parseDouble(tax);
		double taxAmount = ((quantityValue * priceValue) - (quantityValue * priceValue * (discountValue / 100)))
				+ (((quantityValue * priceValue) - (quantityValue * priceValue * (discountValue / 100)))
						* (taxValue / 100));
//		double quantityAmount = quantityValue * priceValue;
//		double discountAmount = quantityAmount - (quantityAmount * (discountValue / 100));
//		double taxAmount = discountAmount + (discountAmount * (taxValue / 100));
		return taxAmount;
		

	}
	
	public static void main(String[] args) {
		Name n = new Name();
		double calculation = n.calculation("1", "250.7596", "10", "10");
		double calculation1 = n.calculation("1", "100", "10", "10");
		double totalAmount = calculation+calculation1;
		DecimalFormat f = new DecimalFormat("0.00");
		String format = f.format(totalAmount);
		System.out.println(format);
		
	}

}

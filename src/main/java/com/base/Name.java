package com.base;

import java.awt.Color;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class Name extends BaseClass {
	public static void main(String[] args) {
		// define original array
		int[] intArray = new int[] { 52, 45, 32, 64, 12, 87, 78, 98, 23, 7 };
		int temp = 0;

		// print original array
//		System.out.println("Original array: ");
//		for (int i = 0; i < intArray.length; i++) {
//			System.out.print(intArray[i] + " ");
//		}
//		// Sort the array in ascending order using two for loops
//		for (int i = 0; i < intArray.length; i++) {
//			for (int j = i + 1; j < intArray.length; j++) {
//				if (intArray[i] > intArray[j]) { // swap elements if not in order
//					temp = intArray[i];
//					intArray[i] = intArray[j];
//					intArray[j] = temp;
//				}
//				System.out.println("J" + temp);
//			}
//			System.out.println("I" + temp);
//		}
//		// print sorted array
//		System.out.println("\nArray sorted in ascending order: ");
//		for (int i = 0; i < intArray.length; i++) {
//			System.out.print(intArray[i] + " ");
//		}

//		Arrays.sort(intArray);
//		for (int i : intArray) {
//			System.out.println(i);
//		}

		String value = "hariharan";
		
		char string[] = value.toCharArray(); 

		for (int i = 0; i < value.length(); i++) {
			for (int j = i + 1; j < value.length(); j++) {
				if (string[i] == string[j]) {
					
					System.out.println(string[i]);

				}
			}
		}

	}

}

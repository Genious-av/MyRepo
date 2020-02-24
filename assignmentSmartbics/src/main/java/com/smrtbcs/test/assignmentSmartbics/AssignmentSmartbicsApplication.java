package com.smrtbcs.test.assignmentSmartbics;

import java.io.IOException;

import com.opencsv.exceptions.CsvValidationException;
import com.smrtbcs.test.assignmentSmartbics.service.CsvReduce;

public class AssignmentSmartbicsApplication {

	public static void main(String[] args)  {
		CsvReduce csvReduce = new CsvReduce();
		
		csvReduce.startThreads();
		
		//csvReduce.parseCsvByreader();
		//csvReduce.parseCSVwithOpenCSV();
		//csvReduce.parseCsvBySuperCsv();
		
		//csvReduce.parseCsvWithScanner();
	}

}

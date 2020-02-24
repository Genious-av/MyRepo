package com.smrtbcs.test.assignmentSmartbics.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.smrtbcs.test.assignmentSmartbics.entity.LogError;
import com.smrtbcs.test.assignmentSmartbics.exception.OtherException;

public class Searcher implements Runnable{

	@Override
	public void run() {
long start=System.currentTimeMillis();

		CSVReader readerOpenCsv = null;
		LogError error=null;
		String[] unpursedLine;
		String[] parsedLine;
		LocalDateTime ldt=null;
		String pattern="HH";
		DateTimeFormatter dtfForParse = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd',' "+pattern);
		String fileName=CsvReduce.DIR+CsvReduce.queue.poll();
		
		
	try (FileReader reader = new FileReader(fileName)) {
		
		readerOpenCsv = new CSVReader(reader);
	   
		while ((unpursedLine = readerOpenCsv.readNext()) != null) {
			//parse each line to object
			parsedLine=unpursedLine[0].split(";");
			
			try {
				ldt=LocalDateTime.parse(parsedLine[0]);
			} catch (DateTimeParseException e) {
				System.err.println("Error parisng line : "+e.getLocalizedMessage()+ " in "+ fileName);
			}
			
			
			error = new LogError(ldt, parsedLine[1]);
				   	
		   	//setting minutes and secundes to '00'
			LocalDateTime dateString = error.getTheTime()
					.minus(error.getTheTime().getMinute(),ChronoUnit.MINUTES)
					.minus(error.getTheTime().getSecond(),ChronoUnit.SECONDS);
			
			//put data to hashmap
			if(CsvReduce.resultMap.containsKey(dateString) && error.getTypeOfError().equals("ERROR")) {
				CsvReduce.resultMap.put(dateString, CsvReduce.resultMap.get(dateString)+1);
				//System.out.println(dateString+" "+errorsReducer.get(dateString) );
			}
			else if(!CsvReduce.resultMap.containsKey(dateString) && error.getTypeOfError().equals("ERROR")){
				CsvReduce.resultMap.put(dateString, 1);
			}
		}
		
	} catch (FileNotFoundException e) {
		System.err.println("Following file not found: "+ fileName+" "+e.getLocalizedMessage());
	} catch (IOException e) {
		System.err.println("Error accessing: "+ fileName+" "+e.getLocalizedMessage());
	} catch (CsvValidationException e) {
		System.err.println("Error validating line "+ e.getMessage());
	} catch (Exception e) {
		 new OtherException("Error during reading file to "+fileName+" "+e.getLocalizedMessage());
	}		

	  
System.out.println("Working time of parseCSVwithOpenCSV "+(System.currentTimeMillis()-start));    	
	}
}

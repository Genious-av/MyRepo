package com.smrtbcs.test.assignmentSmartbics.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.smrtbcs.test.assignmentSmartbics.exception.OtherException;

public class Searcher2 implements Runnable{

	private static final String ERROR = "ERROR";

	@Override
	public void run() {
long start=System.currentTimeMillis();

		CSVReader readerOpenCsv = null;
		String[] unpursedLine;
		String[] parsedLine;
		String fileName=ProcessColtroller.DIR+ProcessColtroller.queueFiles.poll();
		
				
	try (FileReader reader = new FileReader(fileName)) {
		
		readerOpenCsv = new CSVReader(reader);
	   
		while ((unpursedLine = readerOpenCsv.readNext()) != null) {
			//parse each line to array
			parsedLine=unpursedLine[0].split(";");
						
			if(parsedLine[1].equals(ERROR)) {
						pushingData(parsedLine[0].substring(0, 13));	
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
	
	//put data to hashmap
	public void pushingData(String dateString) {
		if(ProcessColtroller.resultMap.containsKey(dateString)) {
			ProcessColtroller.resultMap.put(dateString, ProcessColtroller.resultMap.get(dateString)+1);
			//System.out.println(dateString+" "+errorsReducer.get(dateString) );
		}else if(!ProcessColtroller.resultMap.containsKey(dateString) ){
			ProcessColtroller.resultMap.put(dateString, 1);
		}
	}
}

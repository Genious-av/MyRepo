package com.smrtbcs.test.assignmentSmartbics.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;

import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.Equals;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.smrtbcs.test.assignmentSmartbics.entity.LogError;
import com.smrtbcs.test.assignmentSmartbics.exception.OtherException;



public class CsvReduce {
	
	//private static final CsvPreference PIPE_DELIMITED = new CsvPreference.Builder('"', ';', "\r\n").build();
	private static int NUM_OF_PROC=Runtime.getRuntime().availableProcessors();
	protected static final String DIR = "./GeneratedData/";
	//protected static final String DIR = "./GeneratedDataTest/";
	private static final String WRITER_FILE="./Output/Statistics.txt";
	protected static ConcurrentHashMap<LocalDateTime, Integer> resultMap = new ConcurrentHashMap<>();
	protected static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
	
	//getting file list
	static{
		try {
			queue.addAll(Arrays.asList(new File(DIR).listFiles()).stream()
					.map(fl -> fl.getName())
					.filter(fl->fl.substring(fl.length()-4,fl.length()).equals(".txt"))
					.collect(Collectors.toList()));
		} catch (NullPointerException e) {
			System.err.println("Following directory not found: "+ DIR);
		} 
		System.out.println(queue);
	}
	
	
	
	public void startThreads() {
		TreeMap<LocalDateTime, Integer> resultTree=null;
		DateTimeFormatter dtfForWrite=DateTimeFormatter.ofPattern("yyyy-MM-dd, ");
		String dateTimeResult;
		
long start=System.currentTimeMillis();
		//starting serch threads
		ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_PROC);
		for(String file: queue) executor.execute(new Searcher());

		//waiting for thread`s end
		executor.shutdown();
		while (!executor.isTerminated()) {}
		
		//sorting keys by data
		resultTree = new TreeMap<>(resultMap);
		
		
		
		try (PrintWriter writer = new PrintWriter(new File(WRITER_FILE)) ) {
		    for ( Entry<LocalDateTime, Integer> entry : resultTree.entrySet() ) {
		    	dateTimeResult=entry.getKey().format(dtfForWrite)+entry.getKey().getHour()+".00-"+(entry.getKey().getHour()+1)+".00 ";
		        writer.write(dateTimeResult + "Number of errors: " + entry.getValue() + "\n");
		  }
			} catch (FileNotFoundException e) {
					System.err.println("Following file not found: "+ WRITER_FILE);
			} catch (Exception ex) {
				 new OtherException("Error during writing file to "+WRITER_FILE);
			}
		System.out.println("Working time of application "+(System.currentTimeMillis()-start));
	}
}

	
//	
//	
//	public void parseCsvWithScanner() throws FileNotFoundException {
//		File file = new File("./GeneratedData/fileNBig.txt");
//		
//		long start=System.currentTimeMillis();
//		
//		 FileInputStream fis = new FileInputStream(file);
//
//		Scanner scan = new Scanner(fis);
//		 HashMap<String, Integer> errorsReducer = new HashMap<>();
//		 String line;
//		 DateTimeFormatter dtfForParse = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//		  while (scan.hasNextLine()) {
//			 
//		    	String[] lineObject=scan.nextLine().split(";");
//		    	LogError error = new LogError(LocalDateTime.parse(lineObject[0],dtfForParse), lineObject[1]);
//		    	String pattern="HH";
//		       	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd',' "+pattern);
//		       	
//		    	String dateString = dtf.format(error.getTheTime());
//		    	
//		    	if(errorsReducer.containsKey(dateString) && error.getTypeOfError().equals("ERROR")) {
//		    		errorsReducer.put(dateString, errorsReducer.get(dateString)+1);
//		    		//System.out.println(dateString+" "+errorsReducer.get(dateString) );
//		    	}
//		    	else if(!errorsReducer.containsKey(dateString) && error.getTypeOfError().equals("ERROR")){
//		    		errorsReducer.put(dateString, 1);
//		    	}
//		    }
//
////		    for(String key: errorsReducer.keySet()) {
////		    	System.out.println(key+" number of errors"+errorsReducer.get(key));
////		    }
//	     
//	       
//		  System.out.println("Working time of parseCsvWithScanner "+(System.currentTimeMillis()-start));
//		
//		
//		
//		scan.close();
//	}
//	
//	
//	
//	 
//	//CSVReader reader = new CSVReader(new FileReader("./GeneratedData/fileN1579083848071-BIG.csv"));
//	
//	
//	public void parseCsvByreader() throws IOException {
//FileReader readerBuffer = new FileReader("./GeneratedData/fileNBig.txt");
//		
//		long start=System.currentTimeMillis();
//		  HashMap<String, Integer> errorsReducer = new HashMap<>();
//		
//		  BufferedReader reader = new BufferedReader(readerBuffer,50000);
//		  String unpursedLine;
//		  DateTimeFormatter dtfForParse = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//		  while ((unpursedLine = reader.readLine()) != null) {
//		    	String[] lineObject=unpursedLine.split(";");
//		    	LogError error = new LogError(LocalDateTime.parse(lineObject[0],dtfForParse), lineObject[1]);
//		    	String pattern="HH";
//		       	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd',' "+pattern);
//		       	
//		    	String dateString = dtf.format(error.getTheTime());
//		    	
//		    	if(errorsReducer.containsKey(dateString) && error.getTypeOfError().equals("ERROR")) {
//		    		errorsReducer.put(dateString, errorsReducer.get(dateString)+1);
//		    		//System.out.println(dateString+" "+errorsReducer.get(dateString) );
//		    	}
//		    	else if(!errorsReducer.containsKey(dateString) && error.getTypeOfError().equals("ERROR")){
//		    		errorsReducer.put(dateString, 1);
//		    	}
//		    }
//
////		    for(String key: errorsReducer.keySet()) {
////		    	System.out.println(key+" number of errors"+errorsReducer.get(key));
////		    }
//	     
//	       
//		  System.out.println("Working time of parseCsvByreader "+(System.currentTimeMillis()-start));
//		  readerBuffer.close();
//	}
//	
//	public void parseCsvBySuperCsv() throws IOException {
//		long start=System.currentTimeMillis();
//		
//		FileReader readerSuperCSV = new FileReader("./GeneratedData/fileN.txt");
//		
//		
//		try(ICsvMapReader listReader = new CsvMapReader(readerSuperCSV, PIPE_DELIMITED))
//        {
//            //First Column is header names
//            final String[] headers = {"theTime","typeOfError",null};
//            final CellProcessor[] processors = getProcessors();
// 
//            Map<String, Object> fieldsInCurrentRow;
//            while ((fieldsInCurrentRow = listReader.read(headers, processors)) != null) {
//                //System.out.println(fieldsInCurrentRow);
//            }
//        }
//		readerSuperCSV.close();
//		  System.out.println("Working time of parseCsvBySuperCsv "+(System.currentTimeMillis()-start));
//	}
//	
//		 private static CellProcessor[] getProcessors() {
//
//		        final CellProcessor[] processors = new CellProcessor[] {
//		        		new ParseDate("yyyy-MM-dd'T'HH:mm:ss"), //theTime
//		                //new Equals("ERROR"), // typeOfError
//		        		new NotNull(),// typeOfError
// 		                new NotNull(), // error Message
////		                new Optional(new ParseLong()), // PinCode
////		                new StrRegEx(emailRegex) // Email
//		        };
//		        return processors;
//		    }
//	
//	
//	public void parseCSVwithOpenCSV() throws CsvValidationException, IOException {
//		CSVReader readerOpenCsv = new CSVReader(new FileReader("./GeneratedData/fileNBig.txt"));
//		
//		long start=System.currentTimeMillis();
//		
//	    String[] unpursedLine;
//	    HashMap<String, Integer> errorsReducer = new HashMap<>();
//	    //ArrayList<LogError> errors = new ArrayList<LogError>();
//	    DateTimeFormatter dtfForParse = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//	    while ((unpursedLine = readerOpenCsv.readNext()) != null) {
//	    	unpursedLine=unpursedLine[0].split(";");
//	    	LogError error = new LogError(LocalDateTime.parse(unpursedLine[0],dtfForParse), unpursedLine[1]);
//	    	String pattern="HH";
//	       	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd',' "+pattern);
//	       	
//	    	String dateString = dtf.format(error.getTheTime());
//	    	
//	    	if(errorsReducer.containsKey(dateString) && error.getTypeOfError().equals("ERROR")) {
//	    		errorsReducer.put(dateString, errorsReducer.get(dateString)+1);
//	    		//System.out.println(dateString+" "+errorsReducer.get(dateString) );
//	    	}
//	    	else if(!errorsReducer.containsKey(dateString) && error.getTypeOfError().equals("ERROR")){
//	    		errorsReducer.put(dateString, 1);
//	    	}
//	    }
//
////	    for(String key: errorsReducer.keySet()) {
////	    	System.out.println(key+" number of errors"+errorsReducer.get(key));
////	    }
//	   System.out.println("Working time of parseCSVwithOpenCSV "+(System.currentTimeMillis()-start));
//	    	
//	   readerOpenCsv.close(); 
//	   
//	}
////	


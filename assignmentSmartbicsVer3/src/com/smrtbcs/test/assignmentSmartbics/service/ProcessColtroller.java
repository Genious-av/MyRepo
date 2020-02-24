package com.smrtbcs.test.assignmentSmartbics.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


import com.smrtbcs.test.assignmentSmartbics.exception.OtherException;



public class ProcessColtroller {
	private static final String WRTITER_FILE_DIR="./Output/";
	private static final String WRITER_FILE=WRTITER_FILE_DIR+"Statistics.txt";
	protected static int NUM_OF_PROC=Runtime.getRuntime().availableProcessors();
	protected static final String DIR = "./TestData/";
	protected static ConcurrentHashMap<String, Integer> resultMap = new ConcurrentHashMap<>();
	protected static ConcurrentLinkedQueue<String> queueFiles = new ConcurrentLinkedQueue<>();
	
	//getting file list
	static{
		try {
			queueFiles.addAll(Arrays.asList(new File(DIR).listFiles()).stream()
					.map(fl -> fl.getName())
					.filter(fl->fl.substring(fl.length()-4,fl.length()).equals(".txt"))
					.collect(Collectors.toList()));
		} catch (NullPointerException e) {
			 new OtherException(DIR+" not exists");
		}
		System.out.println(queueFiles);
	}
	
	
	
	public void startSearch() {
		TreeMap<String, Integer> resultTree=null;
		 
long start=System.currentTimeMillis();

		//starting search threads
		ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_PROC);
		for(String file: queueFiles) executor.execute(new Finder());

		//waiting for thread`s end
		executor.shutdown();
		while (!executor.isTerminated()) {}
		
		//sorting keys by data
		resultTree = new TreeMap<>(resultMap);
		
		writeToFile(resultTree);
		
		
System.out.println("Working time of application "+(System.currentTimeMillis()-start));
	}



	private void writeToFile(TreeMap<String, Integer> resultTree) {
		//creating dir if doesn`t exist
		File dir = new File(WRTITER_FILE_DIR);
		if(!dir.exists()) dir.mkdir();
		
		StringBuilder dateTimeResult = new StringBuilder();
		try (PrintWriter writer = new PrintWriter(new File(WRITER_FILE)) ) {
			//writing data from resultTree to file according to format
		    for ( Entry<String, Integer> entry : resultTree.entrySet() ) {
	    	  	dateTimeResult.append(entry.getKey().substring(0,10)).append(" ").append(entry.getKey().substring(11,13))
		       			.append(".00-").append((Integer.parseInt(entry.getKey().substring(11,13))+1))
		       			.append(".00 Number of errors: ").append(entry.getValue()).append("\n")	;
	    	    writer.write(dateTimeResult.toString());
	    	    //setting stringbuilder to null
		        dateTimeResult.setLength(0);
		  }
			} catch (FileNotFoundException e) {
					System.err.println("Following file not found: "+ WRITER_FILE);
			} catch (Exception ex) {
				 new OtherException("Error during writing file to "+WRITER_FILE);
			}
		
	}
}

package com.smrtbcs.test.assignmentSmartbics.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Finder implements Runnable {

	private static final int MAX_BUFFER_SIZE = 65536;
	private static final String ERROR = "ERROR";

	@Override
	public void run() {
		long start=System.currentTimeMillis();
		
		String fileName=ProcessColtroller.DIR+ProcessColtroller.queueFiles.poll();
	
		try (BufferedReader buffered = new BufferedReader(new FileReader(fileName), MAX_BUFFER_SIZE/ProcessColtroller.NUM_OF_PROC)){
			buffered.lines()
			.filter(ln->ln.contains(ERROR)) // excluding warnings
			.forEach(el->putToMap(el));
			
		} catch ( IOException e) {
			System.err.println("Following file not found: "+ fileName+" "+e.getLocalizedMessage());
		}
		
		System.out.println("Working time of thread "+Thread.currentThread().getName()+": "+(System.currentTimeMillis()-start));  	
		
	}

	private void putToMap(String el) {
		String[] parsedLine=el.split(";");
		String dateString=parsedLine[0].substring(0, 13); //dateString - timeStamp to hour
		if(ProcessColtroller.resultMap.containsKey(dateString)) { //checking if map contains key=hour
			ProcessColtroller.resultMap.put(dateString, ProcessColtroller.resultMap.get(dateString)+1);
		} else if(!ProcessColtroller.resultMap.containsKey(dateString)){ //if map doesn`t contain create new key and put value to 1;
			ProcessColtroller.resultMap.put(dateString, 1);
		}
	}
	
}

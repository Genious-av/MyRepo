package application.tinkoff.finder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import application.tinkoff.assign.dto.ResultDTO;
import application.tinkoff.config.CodeResults;
import application.tinkoff.config.Constants;


@Component
public class FindInFiles  {
	/**
	 * {@link #log} - logger
	 * {@link #queue} - synchronized queue for all files where search is working
	 * {@link #resultFileList} - list for results
	 * {@link #listError} - list for errors log
	 */
	private static final Logger log = Logger.getLogger(FindInFiles.class);
	protected static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
	protected static List<String> resultFileList = Collections.synchronizedList(new ArrayList<>());
	protected static List<Exception> listError = Collections.synchronizedList(new ArrayList<>());
	protected static int Searched_Number;
	
	
	
	/**
	 * 
	 * @param number which is searched
	 * {@link #startSearch(int)} - function starting searching thread for each file in queue,
	 *  num of threads is controlled by executor
	 * @return {@link ResultDTO#ResultDTO()}
	 * 
	 */
	public ResultDTO startSearch (int number){
		
		resultFileList.clear();
		listError.clear();
		getListOfFiles();
		log.info("Got list of files to Search in " + Constants.DIR);
		Searched_Number=number;
		StringBuilder sb = new StringBuilder();
		ExecutorService executor = Executors.newFixedThreadPool(Constants.NUM_OF_PROCESSORS);
		int numOfFiles=queue.size();
		for(int i=0;i<numOfFiles;i++) {
			ThreadSearch threadSearch = new ThreadSearch();
			executor.execute(threadSearch);
			log.info("Started thread #:" +i);
		}
		log.info("Call for shutdown of all threads");
		
		executor.shutdown();
		while (!executor.isTerminated()) {}
		log.info("All threads terminated");
		
		CodeResults code = listError.size()!=0? CodeResults.resultEr: resultFileList.size()==0?
				CodeResults.resultNF : CodeResults.resultOk ;
		
		for(Exception ex: listError) sb.append(ex.getMessage()+ " ");
		ResultDTO resultDTO = new ResultDTO(code.getResult(),resultFileList, sb.toString());
		
		return resultDTO;
	}

	
	
	
	/**
	 *  {@link #getListOfFiles()} - collect all names of files from the {@link Constants#DIR} and put them to {@link #queue}
	 */
	private void getListOfFiles(){
		File folder = new File(Constants.DIR); 
		List<File> listOfFiles = Arrays.asList(folder.listFiles());
		queue.addAll(listOfFiles.stream()
						.map(fl -> fl.getName())
						.filter(fl->fl.substring(fl.length()-4,fl.length()).equals(".txt"))
						.collect(Collectors.toList()));
		log.info("got all files to search in "+queue);
		
	}
	
	
}      


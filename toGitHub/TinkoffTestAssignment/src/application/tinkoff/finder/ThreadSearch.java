package application.tinkoff.finder;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import application.tinkoff.config.Constants;

@Component
public class ThreadSearch implements Runnable{
	private static final Logger log = Logger.getLogger(FindInFiles.class);
	/**
	 * {@link #run()} - starting of multiThread
	 *
	 * @throws ArrayIndexOutOfBoundsException - thrown if file ends
	 * 
	 */
	@Override
	public void run() {
		String fileName = null;
		int res=0;
		Reader reader = null;
	  
	    fileName=FindInFiles.queue.poll();
        try {
			reader = new Reader(Constants.DIR+fileName);
		} catch (FileNotFoundException e1) {
			FindInFiles.listError.add(e1);
   	    	log.error("Caught IO exception: "+e1.getMessage());
		}
        while(true) {
            try {	
               res=reader.nextInt();
               	if(res==FindInFiles.Searched_Number) {
	            	  FindInFiles.resultFileList.add(fileName);
	            	  log.info("Found in file "+fileName);
	            	  break;}
            	} catch(ArrayIndexOutOfBoundsException  ex) {
            		log.info("End of file "+fileName);
            		try {
						reader.close();
					} catch (IOException e) {FindInFiles.listError.add(e);
           	    	log.error("Caught IO exception: "+e.getMessage());
           	    	}	    
           	    return;
           	    } catch (IOException | NullPointerException e) {
           	    	FindInFiles.listError.add(e);
           	    	log.error("Caught exception with file: "+fileName+" "+e.getMessage());
           	    	break;
           	   }
	}
	}
}


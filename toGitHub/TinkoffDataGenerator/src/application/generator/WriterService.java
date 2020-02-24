package application.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;


public class WriterService implements Runnable {
	private static final long SIZE_OF_FILE = 1073741824; //1gb
	private static final String DIR = "GeneratedData/";
	public static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(20);

	@Override
	public void run() {
			File file = new File(DIR+"fileN"+Math.random()+".txt");
			FileOutputStream fileOutputStream=null;
			
				try {
					fileOutputStream = new FileOutputStream(file);
				} catch (FileNotFoundException e1) {
					System.out.println(e1.getMessage());
				}
				while(file.length()<=SIZE_OF_FILE  ) {			
						try {
							fileOutputStream.write(queue.take().getBytes());
						} catch (InterruptedException | IOException e) {
							System.out.println(e.getMessage());
						}
				}
		}
}

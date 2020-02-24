package application.main;


import application.generator.ThreadGenerator;


public class GenerateDataFilesApp {

	private static final int NUM_OF_DATA_FILES = 20;

	public static void main(String[] args) throws InterruptedException {
		long from = System.currentTimeMillis();
			for(int i=0;i<NUM_OF_DATA_FILES;i++) {
				ThreadGenerator threadGenerator = new ThreadGenerator();
				Thread thread=new Thread(threadGenerator);
				thread.start();
			}
			System.out.println(System.currentTimeMillis()-from);
	
			
	}

}

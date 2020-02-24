package application.generator;

public class ThreadGenerator implements  Runnable{
	
	
	

	@Override
	public void run() {
		
		GeneratorService generator = new GeneratorService();
		WriterService writer = new WriterService();
		Thread generationThread = new Thread(generator);
		Thread writerThread = new Thread(writer);
		generationThread.start();
		writerThread.start();
		
		try {
			writerThread.join();
		} catch (InterruptedException e) {
			System.out.println("Items in queue "+WriterService.queue.size());
			
		}
		generationThread.interrupt();
		
		
		
	} 
	
}

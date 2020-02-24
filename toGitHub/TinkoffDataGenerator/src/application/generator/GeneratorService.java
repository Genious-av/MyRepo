package application.generator;


public class GeneratorService implements Runnable {
	
	private static final int MAX_ARRAY_OF_BUFFER=1048570; //1mb ->byte
		@Override
	public void run() {
		try {
			while(true) {
				addOneItemToQueue();
			}
		} catch (InterruptedException e) {
			WriterService.queue.clear();
			System.out.println("Items in queue "+WriterService.queue.size());
			System.out.println("The application is stopped");
		}
	}
	
	public void addOneItemToQueue() throws InterruptedException {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<MAX_ARRAY_OF_BUFFER;i++) {
			sb.append(getRandomInteger());
			sb.append(",");
		}
		WriterService.queue.put(sb.toString());
		sb.setLength(0);
}
	
		private int getRandomInteger() {
			return (int) (Math.random()*Integer.MAX_VALUE*(Math.random()>0.5?-1:1));
		}

}

	

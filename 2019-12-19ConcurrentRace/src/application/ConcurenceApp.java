package application;

import java.util.Arrays;

import threads.Incrementor;

public class ConcurenceApp {
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[3];
		for(int i=0;i<threads.length;i++) {
			threads[i]=new Incrementor();
		}
		
		for(int i=0;i<threads.length;i++) {
			threads[i].start();
		}
		
		for(int i=0;i<threads.length;i++) {
			threads[i].join();
		}
		
		System.out.println(Incrementor.commonResource.size());
	}
}

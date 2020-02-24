package threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicIntegerArray;

import com.sun.xml.internal.fastinfoset.CommonResourceBundle;

public class Incrementor extends Thread{
	public static ConcurrentSkipListSet<Object> commonResource = new ConcurrentSkipListSet<>();
	public static Object obj= new Object();

	@Override
	public void run() {
		for (int i=0;i<1000000;i++) {
			commonResource.add(i);
			
		}
		
	}
	
	
}

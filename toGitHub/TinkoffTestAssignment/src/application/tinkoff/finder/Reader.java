package application.tinkoff.finder;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * 
 * @author Alexander Gryaznov
 *
 */
public class Reader {
	/**
	 * {@link #log} logger object
	 * {@link #BUFFER_SIZE} - size of buffer, used to read from file
	 * {@link #DataInputStream} - input stream for file
	 * {@link #buffer} - array for bytes
	 * 
	 */
	private static final Logger log = Logger.getLogger(FindInFiles.class);
	final private int BUFFER_SIZE = 1 << 16; 
    private DataInputStream din; 
    private byte[] buffer; 
    private int bufferPointer, bytesRead; 
  
    
    /**
     * 
     * @param fileName - open file
     * @throws FileNotFoundException 
     * 
     */
    public Reader(String fileName) throws FileNotFoundException { 
       
			din = new DataInputStream(new FileInputStream(fileName));
		
        
        buffer = new byte[BUFFER_SIZE]; 
        bufferPointer = bytesRead = 0;
    } 
    /**
     * 
     * @return String of bytes 
     * @throws IOException in case of problems with file reading
     */
    
    public String readLine() throws IOException   { 
        byte[] buf = new byte[64]; // line length 
        int cnt = 0, c; 
        while ((c = read()) != -1) 
		{ 
		    if (c == '\n') 
		        break; 
		    buf[cnt++] = (byte) c; 
		}
        return new String(buf, 0, cnt); 
    } 
    /**
     * 
     * @return read number
     * @throws IOException in case of problems with file reading
     */
  
    public int nextInt() throws IOException  { 
        int ret = 0; 
        byte c;
		c = read();

      while (c <= ' ') 
		c = read(); 
      boolean neg = (c == '-'); 
      if (neg) 
		c = read(); 
      do
      { 
		ret = ret * 10 + c - '0'; 
      }  while ((c = read()) >= '0' && c <= '9'); 

      if (neg) 
		return -ret;
        return ret; 
    } 
	
   /**
    * @throws IOException 
 * @link {@link #fillBuffer()} put data from from file to buffer
    */
    private void fillBuffer() throws IOException  { 
       
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		
		
        if (bytesRead == -1) 
            buffer[0] = -1; 
    } 
    
   /**
    * 
    * @return  {@link #buffer} - value of buffer where point @link {@link #bufferPointer}
 * @throws IOException 
    */
    private byte read() throws IOException  { 
        if (bufferPointer == bytesRead) 
            fillBuffer(); 
        return buffer[bufferPointer++]; 
    } 

    
    /**
     *  {@link Reader#close} - closing of stream to prevent memory leak
     * @throws IOException 
     * 
     */
    public void close() throws IOException   { 
        if (din == null) 
            return; 
       
			din.close();
			log.info("Reader closed");
		
		
    }
}

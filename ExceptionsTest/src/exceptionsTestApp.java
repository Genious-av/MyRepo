import java.io.IOException;

public class exceptionsTestApp {
	
	
	public static void main(String[] args) {
		
	}
	

	
	public void giveExc() {
		
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
		}finally {
			try {
				throw new IOException();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

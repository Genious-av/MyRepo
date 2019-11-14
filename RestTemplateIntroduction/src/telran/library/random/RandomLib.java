package telran.library.random;
import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;



public class RandomLib {
	public static Random gen=new Random();
	private static  final String ALPHABET="ABCDEFGHIJKLMNOPRSTUWXYZabcdefghijklmnoprstuvwxyz0123456789";
	private static final int ALPHABET_LENGTH=59;
	
	
	public static int nextIntRange(int min, int max){	
				
		return min+gen.nextInt(max-min+1);
	}
	
	public static double nextDoubleRange(double min, double max){
		
		
		return min+gen.nextDouble()*(max-min);
	}
	
	public static boolean nextRandomBoolean(double trueProbability){
		return gen.nextDouble()<trueProbability;
	}
	
	public static String nextRandomString(int len){
		String result="";
		for (int i=0;i<len;i++){
			result=result+Character.toString(ALPHABET.charAt(gen.nextInt(ALPHABET_LENGTH)));
		}
		
		
		return result;
	}
	
	public static  long nextRandomLongVar1(int len) {
		long number=gen.nextLong();
		String s=Long.toString(number);
		int quantityDigits=s.length();
		while(quantityDigits!=len) {
		number=gen.nextLong();
		s=Long.toString(number);
		quantityDigits=s.length();
		}
		
		return number;
	}
	
	public static Long nextRandomLongVar2(int len) {
		String result="";
		int letter=0;
		
		for (int i=0;i<len;i++){
			letter=nextIntRange(48,57);
			if(letter==48 && i==0) i--;
				result=result+(char)letter;
		}
				
		
		Long res=Long.parseLong(result);
		
	
		return res;
	}

	
	
	public static String nextStringFromSet(String[] set){
		if(set==null|| set.length==0){
			return "ha-ha-ha";
		}
		return set[gen.nextInt(set.length)];
		
	}
	
	public static String nextRandomStringNew(int len){
		String result="";
		int letter=0;
		for (int i=0;i<len;i++){
			letter=nextIntRange(48,122);
			if((letter>=65&& letter<=90)||(letter>=97&& letter<=122)|| (letter>=48&&letter<=57) ){
				result=result+(char)letter;
			}
			else
				i--;
		}
		
		return result;
	}
	
	
	public static LocalDate randomLocalDate(LocalDate from, LocalDate to) {
		long epochDaysFrom=from.toEpochDay(); //return number of days from 01-01-1970
		long epochDaysTo=to.toEpochDay(); //return number of days from 01-01-1970
		
		LocalDate rd=LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(epochDaysFrom,epochDaysTo+1)); //create local date from 01-01-1970 of random number of days
		
		return rd;
		
	}
	
}

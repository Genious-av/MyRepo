package dto;

import java.util.Random;

public class Person {
	
	private static final int NUM_NAMES = 10;
	private static final int MAX_AGE = 120;
	private static final double MIN_WEIGHT = 40.;
	private static final double MAX_WEIGHT = 100.;
	private static final int MARRIAGE_AGE = 18;

	private static Random gen =new Random();
	
	private String name;
	private int age;
	private double weight;
	private boolean married;
	
	public Person() {
		super();
	}

	public Person(String name, int age, double weight, boolean married) {
		super();
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.married = married;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getWeight() {
		return weight;
	}

	public boolean isMarried() {
		return married;
	}
	
	

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", weight=" + weight + ", married=" + married + "]";
	}

	public static Person randomPerson() {
		String randomName = "name#"+gen.nextInt(NUM_NAMES);
		int randomAge = gen.nextInt(MAX_AGE);
		double randomWeight = MIN_WEIGHT + ((int)((MAX_WEIGHT - MIN_WEIGHT)*gen.nextDouble()*10)/10.);
		boolean randomMarried = randomAge < MARRIAGE_AGE ? false : gen.nextBoolean();
		
		return new Person (randomName, randomAge, randomWeight, randomMarried);
	}

}

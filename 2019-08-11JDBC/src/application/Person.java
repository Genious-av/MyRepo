package application;

public class Person {
 private int id;
 private String name;
 private int age;
 private double height;
 private boolean married;
 
public Person() {
	super();
}

public Person(int id, String name, int age, double height, boolean married) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	this.height = height;
	this.married = married;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public double getHeight() {
	return height;
}

public void setHeiaght(int height) {
	this.height = height;
}

public boolean isMarried() {
	return married;
}

public void setMarried(boolean married) {
	this.married = married;
}

@Override
public String toString() {
	return "Person [id=" + id + ", name=" + name + ", age=" + age + ", heiaght=" + height + ", married=" + married
			+ "]";
}
 
 
}

package application.service;
public interface IService {
	public void addCompany(String companyName);
	public boolean addEmployee(String firstName, String lastName, int age);
	public boolean setEmployeeToCompany(String firstName, String lastName, int CompanyID) ;
	public boolean setFatherToChild(int childId, String firstName, String lastName) ;
	public void addChild(String childName) ;
}

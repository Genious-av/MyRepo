package application.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import application.entities.ChildEntity;
import application.entities.CompanyEntity;
import application.entities.EmployeeEntity;
import application.entities.EmployeeId;
import application.repo.ChildJPARepo;
import application.repo.CompanyJPARepo;
import application.repo.EmployeeJPARepo;

@org.springframework.stereotype.Service
@org.springframework.transaction.annotation.Transactional
public class Service implements IService{
	
	@Autowired
	CompanyJPARepo companyRepo;
	
	@Autowired
	EmployeeJPARepo employeeRepo;
	
	@Autowired
	ChildJPARepo childRepo;
	
	public void addCompany(String companyName) {
		companyRepo.save(new CompanyEntity(companyName));
	}
	
	public boolean addEmployee(String firstName, String lastName, int age) {
		if(employeeRepo.existsById(new EmployeeId(firstName,lastName))) return false;
		
		return employeeRepo.save(new EmployeeEntity(firstName, lastName, age)) != null;
		
	}
	
	public boolean setEmployeeToCompany(String firstName, String lastName, int CompanyID) {
		if(!companyRepo.existsById(CompanyID)) return false;
		if(!employeeRepo.existsById(new EmployeeId(firstName,lastName))) return false;
		employeeRepo.getOne(new EmployeeId(firstName,lastName)).setCompany(companyRepo.getOne(CompanyID));
		return false;
		
		
	}
	
	public void addChild(String childName) {
		childRepo.save(new ChildEntity(childName));
	}
	
	public boolean setFatherToChild(int childId, String firstName, String lastName) {
		if(!childRepo.existsById(childId)) return false;
		if(!employeeRepo.existsById(new EmployeeId(firstName,lastName))) return false;
		childRepo.getOne(childId).setFather(employeeRepo.getOne(new EmployeeId(firstName,lastName)));
		return true;
	}
	
	
}

package com.rbhardwa.service;

import java.util.List;

import org.apache.maven.artifact.versioning.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.rbhardwa.pojo.Account;
import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Certificate;
import com.rbhardwa.pojo.Country;
import com.rbhardwa.pojo.Employee;

public class EmployeeService {
	
	static int accountNumber=0;

	public List<Employee> getEmployees(String branchName) {
		System.out.println("EmployeeService createEmployee called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Branch.class);
		cr.add(Restrictions.eq("branchName", branchName));
		Branch branch = (Branch)cr.list().get(0);
		List<Employee> employees = branch.getEmployees();
		tx.commit();
		session.close();
		return employees;
	}

	public Employee getEmployee(String branchName, int employeeId) {
		System.out.println("EmployeeService getEmployee called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
//		cr.createAlias("branch.employees", "employee");
//		cr.add(Restrictions.eq("branchName", branchName))
//		.add(Restrictions.and(
//				Restrictions.eq("employee.employeeId", employeeId)
//				));
		
		
		cr.add(Restrictions.eq("employeeId", employeeId));  
		//Branch branch = (Branch)cr.list().get(0);
		Employee employee = (Employee) cr.list().get(0);
		tx.commit();
		session.close();
		return employee;
	}

	public void createEmployee(String branchName,Employee employee) {
		System.out.println("EmployeeService createEmployee called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Branch.class);
		cr.add(Restrictions.eq("branchName", branchName));
		Branch branch = (Branch)cr.list().get(0);
		Account account = new Account();
		account.setAccount_no(accountNumber);
		accountNumber++;
		account.setBaseSalary(1000);
		account.setBonus(1000);
		account.setHra(1000);
		employee.setAccount(account);
		branch.getEmployees().add(employee);
		employee.setBranch(branch);
		session.save(account);
		session.save(employee);
		session.save(branch);
		tx.commit();
		session.close();
	}

	public void updateEmployee(int employeeId, Employee employee) {
		System.out.println("EmployeeService createEmployee called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.createQuery( "update Employee e set e.firstName = :firstName, e.lastName = :lastName where e.employeeId = :employeeId" )
        .setString( "firstName", employee.getFirstName())
        .setString( "lastName", employee.getLastName())
        .setInteger("employeeId", employeeId)
        .executeUpdate();
		tx.commit();
		session.close();
	}

	public void deleteEmployee(int employeeId) {
		System.out.println("EmployeeService createEmployee called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Employee where employeeId = " + employeeId);
		Employee employee = (Employee) query.list().get(0);
		session.delete(employee);

	}

	SessionFactory sessionFcatory;
	Session session;

	public Session getSession() {
		sessionFcatory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFcatory.openSession();
	}
}

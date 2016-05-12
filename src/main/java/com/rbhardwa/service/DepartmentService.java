package com.rbhardwa.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.rbhardwa.pojo.Asset;
import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Certificate;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.pojo.Employee;

public class DepartmentService {
	
	public Department getDepartments(int employeeId)
	{
		System.out.println("DepartmentService createDepartment called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		Employee employee  = (Employee)cr.list().get(0);
		Department department = employee.getDepartment();
		System.out.println("Test!!!!!!!!!!!!!!!!!");
		tx.commit();
		session.close();
		return department;

	}
	
	public Department getDepartment(int employeeId,String departmentName)
	{
		System.out.println("DepartmentService createDepartment called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Department.class);
//		cr.createAlias("employee.department", "department");
//		cr.add(Restrictions.eq("employeeId", employeeId))
//		  .add(Restrictions.eq("department.departmentName", departmentName));
//		Employee employee  = (Employee)cr.list().get(0);
		
		cr.add(Restrictions.eq("departmentName", departmentName));
		Department department = (Department) cr.list().get(0);
		tx.commit();
		session.close();
		return department;

	}
	
	public void createDepartment(int employeeId, Department department)
	{
		System.out.println("DepartmentService createDepartment called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		List<Employee> list = cr.list();
		Employee employee = list.get(0);
		employee.setDepartment(department);
		department.getEmployees().add(employee);
		session.saveOrUpdate(department);
		session.saveOrUpdate(employee);
		tx.commit();
		session.close();
	}
	
	public void updateDepartment(String departmentName,Department department)
	{
		System.out.println("DepartmentService updateDepartment called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.createQuery( "update Department d set d.departmentName = :newdepartmentName where d.departmentName = :olddepartmentName" )
        .setString( "newdepartmentName", department.getDepartmentName())
        .setString( "olddepartmentName", departmentName)
        .executeUpdate();
		tx.commit();
		session.close();
	}
	
	public void deleteDepartment(String departmentName)
	{
		System.out.println("DepartmentService createDepartment called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Department where departmentName = "+ departmentName);
		Department department = (Department) query.list().get(0);
		session.delete(department);
	}
	
	public List<Department> getDepartments()
	{
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Department.class);
		List<Department> departments = cr.list();
		tx.commit();
		session.close();
		return departments;

	}
	
	public Department getDepartment(String departmentName)
	{
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Department.class);
		cr.add(Restrictions.eq("departmentName", departmentName));
		Department department = (Department) cr.list().get(0);
		tx.commit();
		session.close();
		return department;

	}
	
	public void createDepartment(Department department)
	{
		System.out.println("DepartmentService createDepartment called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(department);
		tx.commit();
		session.close();
	}
	
	
	SessionFactory sessionFcatory;
	Session session;
	public Session getSession()
	{
		sessionFcatory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFcatory.openSession();
	}

}

package com.rbhardwa.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.rbhardwa.pojo.Address;
import com.rbhardwa.pojo.Country;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.pojo.Employee;

public class AddressService {

	public List<Address> getAddresss(int employeeId) {
		System.out.println("AddressService createAddress called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		Employee employee = (Employee) cr.list().get(0);
		List<Address> addresses = employee.getAddress();
		tx.commit();
		session.close();
		return addresses;

	}

	public Address getAddress(int employeeId,int addressId) {
		System.out.println("AddressService createAddress called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Address.class);
//		cr.createAlias("employee.address", "address");
//		cr.add(Restrictions.eq("employeeId", employeeId))
//		  .add(Restrictions.eq("address.addressId", addressId));
//		Employee employee = (Employee) cr.list().get(0);
		cr.add(Restrictions.eq("address.addressId", addressId));
		Address address = (Address) cr.list().get(0);
		tx.commit();
		session.close();
		return address;
	}

	public void createAddress(int employeeId, Address address) {
		System.out.println("AddressService createAddress called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		List<Employee> list = cr.list();
		Employee employee = list.get(0);
		employee.getAddress().add(address);
		address.setEmployee(employee);
		session.save(address);
		session.save(employee);
		tx.commit();
		session.close();
	}

	public void updateAddress(int addressId, Address address) {
		System.out.println("AddressService createAddress called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.createQuery( "update Address a set a.houseNumber = :houseNumber, a.street = :street, a.town = :town, a.city = :city, a.country = :country where a.addressId = :addressId" )
        .setInteger("houseNumber", address.getHouseNumber())
        .setString( "street", address.getStreet())
        .setString( "town", address.getTown())
        .setString( "city", address.getCity())
        .setString( "country", address.getCountry())
        .setInteger("addressId", addressId)
        .executeUpdate();
		tx.commit();
		session.close();
	}

	public void deleteAddress(int employeeId,int addressId) {
		System.out.println("AddressService createAddress called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Address.class);
		//cr.createAlias("employee.address", "address");
		cr.add(Restrictions.eq("addressId", addressId));
		//Employee employee = (Employee) cr.list().get(0);
		Address address = (Address) cr.list().get(0);
		session.delete(address);
		tx.commit();
		session.close();
	}

	SessionFactory sessionFcatory;
	Session session;

	public Session getSession() {
		sessionFcatory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFcatory.openSession();
	}

}

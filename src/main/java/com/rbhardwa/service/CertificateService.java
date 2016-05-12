package com.rbhardwa.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.rbhardwa.pojo.Asset;
import com.rbhardwa.pojo.Certificate;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.pojo.Employee;

public class CertificateService {

	public List<Certificate> getCertificates(int employeeId) {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		Employee employee = (Employee) cr.list().get(0);
		List<Certificate> certificates = employee.getCertificates();
		tx.commit();
		session.close();
		return certificates;
	}

	public Certificate getCertificate(int employeeId, int certificateId) {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Certificate.class);
//		cr.createAlias("employee.certificates", "certificate");
//		cr.add(Restrictions.eq("employeeId", employeeId))
//		  .add(Restrictions.eq("certificate.certificateId", certificateId));
//		Employee employee = (Employee) cr.list().get(0);
		
		cr.add(Restrictions.eq("certificateId", certificateId));
		Certificate certificate =  ( Certificate) cr.list().get(0);
		tx.commit();
		session.close();
		return certificate;
	}

	public void createCertificate(int employeeId, Certificate certificate) {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		Employee employee = (Employee) cr.list().get(0);
		employee.getCertificates().add(certificate);
		certificate.setEmployees(employee);
		session.save(certificate);
		session.save(employee);
		tx.commit();
		session.close();
	}

	public void updateCertificate(int certificateId, Certificate certificate) {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.createQuery( "update Certificate c set c.certificateName = :certificateName where c.certificateId = :certificateId" )
        .setString( "certificateName", certificate.getCertificateName())
        .setInteger("certificateId", certificateId)
        .executeUpdate();
		tx.commit();
		session.close();
	}

	public void deleteCertificate(int certificateId) {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Certificate where certificateId = " + certificateId);
		Certificate certificate = (Certificate) query.list().get(0);
		session.delete(certificate);

	}
	
	
	public List<Certificate> getCertificates() {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Certificate.class);
		List<Certificate> certificates =  cr.list();
		tx.commit();
		session.close();
		return certificates;
	}

	public Certificate getCertificate(int certificateId) {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Certificate.class);
		cr.add(Restrictions.eq("certificateId", certificateId));
		Certificate certificate = (Certificate) cr.list().get(0);
		tx.commit();
		session.close();
		return certificate;
	}
	
	public void createCertificate(Certificate certificate) {
		System.out.println("PersonService createPerson called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(certificate);
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

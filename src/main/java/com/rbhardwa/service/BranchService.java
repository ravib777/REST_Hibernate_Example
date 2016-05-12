package com.rbhardwa.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Country;
import com.rbhardwa.pojo.Employee;

public class BranchService {

	public List<Branch> getBranches(String countryName) {
		System.out.println("BranchService createBranch called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Country.class);
		cr.add(Restrictions.eq("countryName", countryName));
		Country country = (Country)cr.list().get(0);
		
//		for(Branch br : list)
//		{
//			br.getEmployees();
//		}
		
		List<Branch> branches =country.getBranches();
		tx.commit();
		session.close();
		return branches;
	}

	public Branch getBranch(String countryName, String branchName) {
		System.out.println("BranchService createBranch called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Branch.class);
//		cr.createAlias("country.branches", "branch");
//		cr.add(Restrictions.eq("countryName", countryName))
//		  .add(Restrictions.eq("branch.branchName", branchName));
//		Country country = (Country)cr.list().get(0);
		
		cr.add(Restrictions.eq("branchName", branchName));
		Branch branch = (Branch) cr.list().get(0);
		tx.commit();
		session.close();
		return branch;
	}

	public void createBranch(String countryName, Branch branch) {
		System.out.println("BranchService createBranch called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Country.class);
		cr.add(Restrictions.eq("countryName", countryName));
		Country country = (Country) cr.list().get(0);
		country.getBranches().add(branch);
		branch.setCountry(country);
		session.save(branch);
		session.save(country);
		tx.commit();
		session.close();
	}

	public void updateBranch(String branchName, Branch branch) {
		System.out.println("BranchService createBranch called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.createQuery( "update Branch b set b.branchName = :newName where b.branchName = :oldName" )
        .setString( "newName", branch.getBranchName())
        .setString( "oldName", branchName)
        .executeUpdate();
		tx.commit();
		session.close();
		
	
		
	}

	public void deleteBranch(String BranchName) {
		System.out.println("BranchService createBranch called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Branch where BranchName = "+ BranchName);
		Branch Branch = (Branch) query.list().get(0);
		session.delete(Branch);
	}

	SessionFactory sessionFcatory;
	Session session;

	public Session getSession() {
		sessionFcatory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFcatory.openSession();
	}
}

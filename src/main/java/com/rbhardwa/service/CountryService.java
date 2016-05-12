package com.rbhardwa.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Country;

public class CountryService {
	
	private SessionFactory sessionFcatory;
	private Session session;
	public List<Country> getCountries()
	{
		System.out.println("CountryService createCountry called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Country.class);
		List<Country> list = cr.list();
//		for (Country country : list)
//		{
//			List <Branch> branch = country.getBranches();
//			for(Branch br : branch)
//			{
//				br.getEmployees();
//			}
//		}
		tx.commit();
		session.close();
		return list;
	}
	
	public Country getCountry(String countryName)
	{
		System.out.println("CountryService createCountry called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Country.class);
		cr.add(Restrictions.eq("countryName", countryName));
		List<Country> list = cr.list();
		Country country = (Country)list.get(0);
//		List <Branch> branch = list.get(0).getBranches();
//		for(Branch br : branch)
//		{
//			br.getEmployees();
//		}
		tx.commit();
		session.close();
		return country;
	}
	
	public void createCountry(Country country)
	{
		System.out.println("CountryService createCountry called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(country);
		tx.commit();
		session.close();
	}
	
	public void updateCountry(String countryName,Country country)
	{
		System.out.println("CountryService createCountry called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.createQuery( "update Country c set c.countryName = :newName where c.countryName = :oldName" )
        .setString( "newName", country.getCountryName() )
        .setString( "oldName", countryName )
        .executeUpdate();
		tx.commit();
		session.close();
	}
	
	public void deleteCountry(String countryName)
	{
		System.out.println("CountryService createCountry called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Country where countryName = "+ countryName);
		Country country = (Country) query.list().get(0);
		session.delete(country);
	}
	

	public Session getSession()
	{
		sessionFcatory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFcatory.openSession();
	}

}

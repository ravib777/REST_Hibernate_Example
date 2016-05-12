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

import com.rbhardwa.pojo.Asset;
import com.rbhardwa.pojo.Country;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.pojo.Employee;

public class AssetService {

	public List<Asset> getAssets(int employeeId) {
		System.out.println("AssetService createAsset called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		Employee employee = (Employee) cr.list().get(0);
		Hibernate.initialize(employee);
		List<Asset> assets = employee.getAssets();
		tx.commit();
		session.close();
		return assets;

	}

	public Asset getAsset(int employeeId,int assetId) {
		System.out.println("AssetService createAsset called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Asset.class);
//		cr.createAlias("employee.assets", "asset");
//		cr.add(Restrictions.eq("employeeId", employeeId))
//		  .add(Restrictions.eq("asset.assetId", assetId));
		cr.add(Restrictions.eq("assetId", assetId));
		
		//Employee employee = (Employee) cr.list().get(0);
		Asset asset = (Asset) cr.list().get(0);
		tx.commit();
		session.close();
		return asset;
	}

	public void createAsset(int employeeId, Asset asset) {
		System.out.println("AssetService createAsset called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Criteria cr = session.createCriteria(Employee.class);
		cr.add(Restrictions.eq("employeeId", employeeId));
		List<Employee> list = cr.list();
		Employee employee = list.get(0);
		employee.getAssets().add(asset);
		asset.setEmployee(employee);
		session.save(asset);
		session.save(employee);
		tx.commit();
		session.close();
	}

	public void updateAsset(int assetId, Asset asset) {
		System.out.println("AssetService createAsset called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.createQuery( "update Asset a set a.assetType = :assetType, a.brand = :brand, a.price = :price where a.assetId = :assetId" )
        .setString( "assetType", asset.getAssetType())
        .setString( "brand", asset.getBrand())
        .setLong("price", asset.getPrice())
        .setInteger("assetId", assetId)
        .executeUpdate();
		tx.commit();
		session.close();
	}

	public void deleteAsset(int assetId) {
		System.out.println("AssetService createAsset called!!!!!");

		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Asset where assetId = "+assetId);
		Asset asset =  (Asset)query.list().get(0);
		session.delete(asset);
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

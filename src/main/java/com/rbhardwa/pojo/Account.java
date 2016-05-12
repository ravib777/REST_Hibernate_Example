package com.rbhardwa.pojo;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="REST_Account")
@XmlRootElement
public class Account {

	@Id
	private int account_no;
	private float baseSalary;
	private float hra;
	private float bonus;
	
	@OneToOne
	private Employee employee;
	
	
	public int getAccount_no() {
		return account_no;
	}
	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}
	public float getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(float base_salary) {
		this.baseSalary = base_salary;
	}
	public float getHra() {
		return hra;
	}
	public void setHra(float hra) {
		this.hra = hra;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	

}

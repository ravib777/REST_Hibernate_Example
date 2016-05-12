package com.rbhardwa.converter;

import org.json.JSONObject;

import com.rbhardwa.pojo.Account;
import com.rbhardwa.pojo.Address;

public class AccountJSonConverter {

	public static JSONObject convert(Account account) {
		JSONObject jsonAccount= new JSONObject();
		jsonAccount.put("account_no", account.getAccount_no());
		jsonAccount.put("baseSalary", account.getBaseSalary());
		jsonAccount.put("bonus", account.getBonus());
		jsonAccount.put("hra", account.getHra());
		
		return jsonAccount;
	}
}

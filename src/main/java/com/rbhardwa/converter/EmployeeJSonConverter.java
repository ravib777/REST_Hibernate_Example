package com.rbhardwa.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rbhardwa.pojo.Account;
import com.rbhardwa.pojo.Address;
import com.rbhardwa.pojo.Asset;
import com.rbhardwa.pojo.Certificate;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.pojo.Employee;
import com.rbhardwa.pojo.Employee;

public class EmployeeJSonConverter {
	public static JSONObject convert(Employee employee) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("employeeId", employee.getEmployeeId());
		jsonObject.put("firstName", employee.getFirstName());
		jsonObject.put("lastName", employee.getLastName());

		if (employee.getAccount() != null) {
			JSONObject jsonAccount = new JSONObject();
			Account account = employee.getAccount();
			jsonAccount = AccountJSonConverter.convert(account);

			jsonObject.put("account", jsonAccount);
		}
//
//		if (employee.getAddress() != null) {
//			JSONObject jsonAddresses = new JSONObject();
//			JSONArray jsonArray = new JSONArray();
//			List<Address> addresses = employee.getAddress();
//			for (Address address : addresses) {
//				jsonAddresses = AddressJSonConverter.convert(address);
//				jsonArray.put(jsonAddresses);
//			}
//			jsonObject.put("addresses", jsonArray);
//		}
//		if (employee.getAssets() != null) {
//				JSONObject jsonAssets = new JSONObject();
//				JSONArray jsonArray1 = new JSONArray();
//				List<Asset> assets = employee.getAssets();
//				for (Asset asset : assets) {
//					jsonAssets = AssetJSonConverter.convert(asset);
//					jsonArray1.put(jsonObject);
//				}
//				jsonObject.put("assets", jsonArray1);
//			}
//
//		if (employee.getCertificates() != null) {
//				JSONObject jsonCertificates = new JSONObject();
//				JSONArray jsonArray2 = new JSONArray();
//				List<Certificate> certificates = employee.getCertificates();
//				for (Certificate certificate : certificates) {
//					jsonCertificates = CertificateJSonConverter.convert(certificate);
//					jsonArray2.put(jsonObject);
//				}
//				jsonObject.put("certificates", jsonArray2);
//			}
//
//		if (employee.getDepartment() != null) {
//				JSONObject jsonDepartment = new JSONObject();
//				Department department = employee.getDepartment();
//				jsonDepartment=DepartmentJSonConverter.convert(department);
//				jsonObject.put("account", jsonDepartment);
//			}
		return jsonObject;
		}
		

}

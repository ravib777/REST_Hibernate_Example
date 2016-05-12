package com.rbhardwa.converter;

import org.json.JSONObject;

import com.rbhardwa.pojo.Address;
import com.rbhardwa.pojo.Employee;

public class AddressJSonConverter {
	
	public static JSONObject convert(Address address) {
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("addressId",address.getAddressId());
		jsonObject.put("houseNumber", address.getHouseNumber());
		jsonObject.put("street", address.getStreet());
		jsonObject.put("town", address.getTown());
		jsonObject.put("city", address.getCity());
		Employee employee = address.getEmployee();
		jsonObject.put("country", address.getCountry());
		if( employee!=null)
		{
			jsonObject.put("employeeId", employee.getEmployeeId());
		}
		return jsonObject;

	}
}

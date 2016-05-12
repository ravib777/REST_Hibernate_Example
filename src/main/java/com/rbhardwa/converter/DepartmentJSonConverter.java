package com.rbhardwa.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rbhardwa.pojo.Account;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.pojo.Employee;

public class DepartmentJSonConverter {
	public static JSONObject convert(Department department) {
		JSONObject jsonDepartment= new JSONObject();

		jsonDepartment.put("departmentId", department.getDepartmentId());
		jsonDepartment.put("departmentName", department.getDepartmentName());
		List<Employee> employees = department.getEmployees();
		if(!employees.isEmpty())
		{
			JSONArray jsonArray = new JSONArray();
			for(Employee employee : employees)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("employeeId", employee.getEmployeeId());
				jsonObject.put("firstName", employee.getFirstName());
				jsonObject.put("lastName", employee.getLastName());
				jsonArray.put(jsonObject);
			}
			jsonDepartment.put("employees", jsonArray);
		}
		return jsonDepartment;
	}
	
	
	public static JSONObject convertOnlyDepartment(Department department) {
		JSONObject jsonDepartment= new JSONObject();

		jsonDepartment.put("departmentId", department.getDepartmentId());
		jsonDepartment.put("departmentName", department.getDepartmentName());
		List<Employee> employees = department.getEmployees();
		
		return jsonDepartment;
	}

}

package com.rbhardwa.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Country;
import com.rbhardwa.pojo.Employee;

public class BranchJSonConverter {
	
	public static JSONObject convert(Branch branch)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("branchId", branch.getBranchId());
		jsonObject.put("branchName", branch.getBranchName());
		if(branch.getEmployees()!=null)
		{
			JSONObject jsonBranches = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			List <Employee> employees = branch.getEmployees();
			for(Employee employee : employees)
			{
				jsonBranches.put("employeedId", employee.getEmployeeId());
				jsonBranches.put("firstName", employee.getFirstName());
				jsonBranches.put("firstName", employee.getFirstName());
				jsonBranches.put("lastName", employee.getLastName());
				jsonArray.put(jsonBranches);
			}
			
			jsonObject.put("employees",jsonArray);
		}
		return jsonObject;
	}

}

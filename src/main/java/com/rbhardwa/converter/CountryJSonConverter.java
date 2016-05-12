package com.rbhardwa.converter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Country;

public class CountryJSonConverter {

	public static JSONObject convert(Country country)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("countryId", country.getCountryId());
		jsonObject.put("countryName", country.getCountryName());
		if(country.getBranches()!=null)
		{
			JSONObject jsonBranches = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			List <Branch> branches = country.getBranches();
			for(Branch branch : branches)
			{
				jsonBranches.put("brancheId", branch.getBranchId());
				jsonBranches.put("branchName", branch.getBranchName());
				jsonArray.put(jsonBranches);
			}
			
			jsonObject.put("branches",jsonArray);
		}
		return jsonObject;
	}
}

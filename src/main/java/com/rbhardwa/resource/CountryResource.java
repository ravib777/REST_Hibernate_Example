package com.rbhardwa.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rbhardwa.converter.CountryJSonConverter;
import com.rbhardwa.pojo.Country;
import com.rbhardwa.service.CountryService;

@Path("/countries")
public class CountryResource {
	
	private CountryService countryService = new CountryService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountries()
	{
		List<Country> countries = countryService.getCountries();
		JSONArray jsonArray = new JSONArray();
		for(Country country: countries)
		{
			jsonArray.put(CountryJSonConverter.convert(country));
		}
		return Response.status(200).entity(jsonArray.toString()).build();
	}


	@Context
	UriInfo context;
	
	@GET
	@Path("/{countryName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountry(@PathParam(value="countryName") String countryName )
	{
		Country country = countryService.getCountry(countryName);
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(CountryJSonConverter.convert(country));
		return Response.status(200).entity(jsonArray.toString()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCountry(Country country)
	{
		countryService.createCountry(country);
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(CountryJSonConverter.convert(country));
		return Response.status(200).entity(jsonArray.toString()).build();
	}
	
	@PUT
	@Path("/{countryName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCountry(@PathParam(value="countryName") String countryName, Country country)
	{
		countryService.updateCountry(countryName, country);
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(CountryJSonConverter.convert(country));
		return Response.status(200).entity(jsonArray.toString()).build();
	}
	
	@DELETE
	@Path("/{countryName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCountry(@PathParam(value="countryName") String countryName)
	{
		countryService.deleteCountry(countryName);
		return Response.status(200).entity("SuccessfullyDeleted "+ countryName).build();
	}
	
	@Path("/{countryName}/branches")
	public BranchResource getBranchResource()
	{
		return new BranchResource();
	}
	
}

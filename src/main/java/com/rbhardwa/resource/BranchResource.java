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

import com.rbhardwa.converter.BranchJSonConverter;
import com.rbhardwa.converter.CountryJSonConverter;
import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Country;
import com.rbhardwa.service.BranchService;

@Path("/")
public class BranchResource {

	private BranchService branchService = new BranchService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBranches(@PathParam("countryName") String countryName) {
		List<Branch> branches = branchService.getBranches(countryName);
		JSONArray jsonArray = new JSONArray();
		for (Branch branch : branches) {
			jsonArray.put(BranchJSonConverter.convert(branch));
		}

		return Response.status(200).entity(jsonArray.toString()).build();
	}

	@Context
	UriInfo context;

	@GET
	@Path("/{branchName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBranch(@PathParam("countryName") String countryName,
			@PathParam(value = "branchName") String branchName) {
		Branch branch = branchService.getBranch(countryName, branchName);
		JSONObject jsonObject = new JSONObject();
		jsonObject= BranchJSonConverter.convert(branch);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBranch(@PathParam("countryName") String countryName, Branch branch) {
		branchService.createBranch(countryName, branch);
		JSONObject jsonObject = new JSONObject();
		jsonObject= BranchJSonConverter.convert(branch);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@PUT
	@Path("/{branchName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBranch(@PathParam(value = "branchName") String branchName, Branch branch) {
		branchService.updateBranch(branchName, branch);
		JSONObject jsonObject = new JSONObject();
		jsonObject= BranchJSonConverter.convert(branch);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@DELETE
	@Path("/{branchName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBranch(@PathParam(value = "branchName") String branchName) {
		branchService.deleteBranch(branchName);
		return Response.status(200).entity("Branch Deleted " + branchName).build();
	}

	@Path("/{branchName}/employees")
	public EmployeeResource getEmployeeResource() {
		return new EmployeeResource();
	}
	//
	// @Path("/{branchName}/departments")
	// public DepartmentResource getDepartmentResource()
	// {
	// return new DepartmentResource();
	// }
	//
	// @Path("/{branchName}/certificates")
	// public CertificateResource getCertificateResource()
	// {
	// return new CertificateResource();
	// }
	//

}

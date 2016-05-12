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

import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import com.rbhardwa.converter.BranchJSonConverter;
import com.rbhardwa.converter.EmployeeJSonConverter;
import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Employee;
import com.rbhardwa.service.EmployeeService;

@Path("/")
public class EmployeeResource {

	private EmployeeService employeeService = new EmployeeService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees(@PathParam(value = "branchName") String branchName) {
		List<Employee> employees = employeeService.getEmployees(branchName);
		JSONArray jsonArray = new JSONArray();
		for (Employee employee : employees) {
			jsonArray.put(EmployeeJSonConverter.convert(employee));
		}

		return Response.status(200).entity(jsonArray.toString()).build();
	}

	@Context
	UriInfo context;

	@GET
	@Path("/{employeeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployee(@PathParam(value = "branchName") String branchName,@PathParam(value = "employeeId") int employeeId) {
		Employee employee = employeeService.getEmployee(branchName,employeeId);
		JSONObject jsonObject = new JSONObject();
		jsonObject= EmployeeJSonConverter.convert(employee);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmployee(@PathParam(value = "branchName") String branchName, Employee employee) {
		employeeService.createEmployee(branchName,employee);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject= EmployeeJSonConverter.convert(employee);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@PUT
	@Path("/{employeeId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmployee(@PathParam(value = "employeeId") int employeeId, Employee employee) {
		employeeService.updateEmployee(employeeId, employee);
		JSONObject jsonObject = new JSONObject();
		jsonObject= EmployeeJSonConverter.convert(employee);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@DELETE
	@Path("/{employeeId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBranch(@PathParam(value = "employeeId") int employeeId) {
		employeeService.deleteEmployee(employeeId);
		return Response.status(200).entity("Employee Deleted " + employeeId).build();
	}
	
	@Path("/{employeeId}/assets")
	public AssetResource getAssetResource()
	{
		return new AssetResource();
	}
	
	
	@Path("/{employeeId}/addresses")
	public AddressResource geAddressResource()
	{
		return new AddressResource();
	}
	
	
	
	@Path("/{employeeId}/certificates")
	public EmployeeCertificateResource getEmployeeCertificateResource()
	{
		return new EmployeeCertificateResource();
	}
	
	@Path("/{employeeId}/departments")
	public EmployeeDepartmentResource getEmployeeDepartmentResource()
	{
		return new EmployeeDepartmentResource ();
	}
	
	
}

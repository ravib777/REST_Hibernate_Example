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

import com.rbhardwa.converter.CertificateJSonConverter;
import com.rbhardwa.converter.DepartmentJSonConverter;
import com.rbhardwa.pojo.Certificate;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.pojo.Employee;
import com.rbhardwa.service.CertificateService;
import com.rbhardwa.service.DepartmentService;

@Path("/")
public class EmployeeDepartmentResource {
	
	private DepartmentService departmentService = new DepartmentService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
	public Response getDepartments(@PathParam(value = "employeeId") int employeeId)
	{
		Department departments = departmentService.getDepartments(employeeId);
		JSONObject jsonObject = new JSONObject();
		jsonObject= DepartmentJSonConverter.convertOnlyDepartment(departments);
		return Response.status(200).entity(jsonObject.toString()).build();

	}


	@Context
	UriInfo context;
	
	@GET
	@Path("/{departmentName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDepartment(@PathParam(value = "employeeId") int employeeId,@PathParam(value="departmentName") String departmentName )
	{
		Department department = departmentService.getDepartment(employeeId,departmentName);
		JSONObject jsonObject = new JSONObject();
		jsonObject= DepartmentJSonConverter.convert(department);
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createDepartment(@PathParam(value = "employeeId") int employeeId,Department department)
	{
		departmentService.createDepartment(employeeId,department);
		JSONObject jsonObject = new JSONObject();
		jsonObject= DepartmentJSonConverter.convert(department);
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@PUT
	@Path("/{departmentName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDepartment(@PathParam(value="departmentName") String departmentName,Department department)
	{
		departmentService.updateDepartment(departmentName, department);
		JSONObject jsonObject = new JSONObject();
		jsonObject= DepartmentJSonConverter.convert(department);
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@DELETE
	@Path("/{departmentName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartment(@PathParam(value="departmentName") String departmentName)
	{
		departmentService.deleteDepartment(departmentName);
		return Response.status(200).entity("Department Deleted " + departmentName).build();
	}

//	@Path("/{departmentName}/employees")
//	public EmployeeResource getEmployeeResource()
//	{
//		return new EmployeeResource();
//	}
}

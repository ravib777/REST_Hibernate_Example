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
import com.rbhardwa.pojo.Certificate;
import com.rbhardwa.service.CertificateService;


@Path("/certificates")
public class CertificateResource {

	
	private CertificateService certificateService = new CertificateService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCertificates()
	{
		List<Certificate> certificates = certificateService.getCertificates(); 
		JSONObject jsonObject= null;
		JSONArray parentObject= new JSONArray();
		for (Certificate certificate : certificates) {
			jsonObject= CertificateJSonConverter.convert(certificate);
			parentObject.put(jsonObject);
		}
		return Response.status(200).entity(parentObject.toString()).build();
	}


	@Context
	UriInfo context;
	
	@GET
	@Path("/{certificateId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCertificate(@PathParam(value="certificateId") int certificateId )
	{
		Certificate certificate = certificateService.getCertificate(certificateId);
		JSONObject jsonObject = new JSONObject();
		jsonObject= CertificateJSonConverter.convert(certificate);
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCertificate(Certificate certificate)
	{
		certificateService.createCertificate(certificate);
		JSONObject jsonObject = new JSONObject();
		jsonObject= CertificateJSonConverter.convert(certificate);
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@PUT
	@Path("/{certificateId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDepartment(@PathParam(value="certificateId") int certificateId,Certificate certificate)
	{
		certificateService.updateCertificate(certificateId, certificate);
		JSONObject jsonObject = new JSONObject();
		jsonObject= CertificateJSonConverter.convert(certificate);
		return Response.status(200).entity(jsonObject.toString()).build();
	}
	
	@DELETE
	@Path("/{certificateId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartment(@PathParam(value="certificateId") int certificateId)
	{
		certificateService.deleteCertificate(certificateId);
		return Response.status(200).entity("Certificate Deleted" + certificateId).build();
	}
	
//	@Path("/{certificateId}/employees")
//	public EmployeeResource getEmployeeResource()
//	{
//		return new EmployeeResource();
//	}
}

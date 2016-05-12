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

import com.rbhardwa.converter.AddressJSonConverter;
import com.rbhardwa.pojo.Address;
import com.rbhardwa.service.AddressService;

@Path("/")
public class AddressResource {

	private AddressService addressSerivce = new AddressService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddresss(@PathParam(value = "employeeId") int employeeId) {
		List<Address> addresses = addressSerivce.getAddresss(employeeId);
		JSONObject jsonObject = null;
		JSONArray parentObject = new JSONArray();
		Address address = null;
		if (!addresses.isEmpty()) {
			for (int i = 0; i < addresses.size(); i++) {
				address = addresses.get(i);
				jsonObject = AddressJSonConverter.convert(address);
				parentObject.put(jsonObject);
			}
		}
		return Response.status(200).entity(parentObject.toString()).build();
	}

	@Context
	UriInfo context;

	@GET
	@Path("/{addressId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddress(@PathParam(value = "employeeId") int employeeId,
			@PathParam(value = "addressId") int addressId) {
		Address address = addressSerivce.getAddress(employeeId, addressId);

		JSONObject jsonObject = AddressJSonConverter.convert(address);
		return Response.status(200).entity(jsonObject.toString()).build();

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAddress(@PathParam(value = "employeeId") int employeeId, Address address) {
		addressSerivce.createAddress(employeeId, address);
		JSONObject jsonObject = AddressJSonConverter.convert(address);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@PUT
	@Path("/{addressId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDepartment(@PathParam(value = "addressId") int addressId, Address address) {
		addressSerivce.updateAddress(addressId, address);
		JSONObject jsonObject = AddressJSonConverter.convert(address);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@DELETE
	@Path("/{addressId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartment(@PathParam(value = "employeeId") int employeeId,
			@PathParam(value = "addressId") int addressId) {
		addressSerivce.deleteAddress(employeeId, addressId);
		List<Address> addresses = addressSerivce.getAddresss(employeeId);
		JSONObject jsonObject = null;
		JSONArray parentObject = new JSONArray();
		for (Address address : addresses) {
			jsonObject = AddressJSonConverter.convert(address);
			parentObject.put(jsonObject);
		}
		return Response.status(200).entity(parentObject.toString()).build();
	}

}

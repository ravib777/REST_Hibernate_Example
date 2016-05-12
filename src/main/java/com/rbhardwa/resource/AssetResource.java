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

import com.rbhardwa.converter.AssetJSonConverter;
import com.rbhardwa.converter.BranchJSonConverter;
import com.rbhardwa.pojo.Asset;
import com.rbhardwa.pojo.Branch;
import com.rbhardwa.pojo.Department;
import com.rbhardwa.service.AssetService;

@Path("/")
public class AssetResource {

	private AssetService assetSerivce = new AssetService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssets(@PathParam(value = "employeeId") int employeeId) {
		List<Asset> assets = assetSerivce.getAssets(employeeId);
		JSONArray jsonArray = new JSONArray();
		for (Asset asset : assets) {
			jsonArray.put(AssetJSonConverter.convert(asset));
		}

		return Response.status(200).entity(jsonArray.toString()).build();
	}

	@Context
	UriInfo context;

	@GET
	@Path("/{assetId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAsset(@PathParam(value = "employeeId") int employeeId,
			@PathParam(value = "assetId") int assetId) {
		Asset asset = assetSerivce.getAsset(employeeId, assetId);
		JSONObject jsonObject = new JSONObject();
		jsonObject = AssetJSonConverter.convert(asset);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAsset(@PathParam(value = "employeeId") int employeeId, Asset asset) {
		assetSerivce.createAsset(employeeId, asset);
		JSONObject jsonObject = new JSONObject();
		jsonObject = AssetJSonConverter.convert(asset);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@PUT
	@Path("/{assetId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDepartment(@PathParam(value = "assetId") int assetId, Asset asset) {
		assetSerivce.updateAsset(assetId, asset);
		JSONObject jsonObject = new JSONObject();
		jsonObject = AssetJSonConverter.convert(asset);
		return Response.status(200).entity(jsonObject.toString()).build();
	}

	@DELETE
	@Path("/{assetId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteDepartment(@PathParam(value = "assetId") int assetId) {
		return Response.status(200).entity("Successfully Deleted " + assetId).build();
	}

}

package com.rbhardwa.converter;

import org.json.JSONObject;


import com.rbhardwa.pojo.Asset;

public class AssetJSonConverter {
	public static JSONObject convert(Asset asset) {
		JSONObject jsonAssets = new JSONObject();
		jsonAssets.put("assetId", asset.getAssetId());
		jsonAssets.put("assetType", asset.getAssetType());
		jsonAssets.put("brand", asset.getBrand());
		jsonAssets.put("price", asset.getPrice());
		return jsonAssets;
	}

}

package com.rbhardwa.converter;

import org.json.JSONObject;

import com.rbhardwa.pojo.Asset;
import com.rbhardwa.pojo.Certificate;

public class CertificateJSonConverter {

	public static JSONObject convert(Certificate certificate) {
		JSONObject jsonCertificates = new JSONObject();

		jsonCertificates = new JSONObject();
		jsonCertificates.put("certificateId", certificate.getCertificateId());
		jsonCertificates.put("certificateName", certificate.getCertificateName());
		return jsonCertificates;
	}
}

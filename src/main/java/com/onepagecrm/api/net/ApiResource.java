package com.onepagecrm.api.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.onepagecrm.api.models.BaseResource;


public class ApiResource extends BaseResource {
	
	public static final String CHARSET = "UTF-8";

	public enum RequestMethod {
		GET, POST, DELETE
	}

	public enum RequestType {
		NORMAL, MULTIPART
	}
	
	public static String urlEncode(String str) throws UnsupportedEncodingException {
		// Preserve original behavior that passing null for an object id will lead
		// to us actually making a request to /v1/foo/null
		if (str == null) {
			return null;
		}
		else {
			return URLEncoder.encode(str, CHARSET);
		}
	}
	
	private static String className(Class<?> clazz) {
		String className = clazz.getSimpleName().toLowerCase().replace("$", " ");

		// TODO: Delurk this, with invoiceitem being a valid url, we can't get too
		// fancy yet.
		if (className.equals("applicationfee")) {
			return "application_fee";
		} else if (className.equals("fileupload")) {
			return "file";
		} else if (className.equals("bitcoinreceiver")) {
			return "bitcoin_receiver";
		} else {
			return className;
		}
	}
	
	protected static <T> T multipartRequest(APIResource.RequestMethod method,
			String url, Map<String, Object> params, Class<T> clazz,
			RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return APIResource.stripeResponseGetter.request(method, url, params, clazz,
				APIResource.RequestType.MULTIPART, options);
	}

	protected static <T> T request(APIResource.RequestMethod method,
			String url, Map<String, Object> params, Class<T> clazz,
			RequestOptions options) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, CardException,
			APIException {
		return APIResource.stripeResponseGetter.request(method, url, params, clazz,
				APIResource.RequestType.NORMAL, options);
	}
}

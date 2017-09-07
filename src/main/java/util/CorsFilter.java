package util;

import java.util.HashMap;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * Really simple helper for enabling CORS in a spark application;
 */
public final class CorsFilter {

	private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();

	static {
		corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		corsHeaders.put("Access-Control-Allow-Origin", "*");
		corsHeaders.put("Access-Control-Allow-Headers", "x-auth-token, x-response-control");
		corsHeaders.put("Content-Length", "0");
		corsHeaders.put("Content-Type", "'application/json");
	}

	public final static void apply() {
		Filter filter = new Filter() {
			@Override
			public void handle(Request request, Response response) throws Exception {
				corsHeaders.forEach((key, value) -> {
					response.header(key, value);
				});
			}
		};
		Spark.after(filter);
	}
}

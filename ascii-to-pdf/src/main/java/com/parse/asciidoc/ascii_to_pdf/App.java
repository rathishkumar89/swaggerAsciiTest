package com.parse.asciidoc.ascii_to_pdf;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
	public JsonObject formatJson(String inputUrl) throws IOException {

		// Connect to the URL using java's native library
		URL url = new URL(inputUrl);
		HttpURLConnection request = null;
		try {
			request = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.connect();

		// Convert to a JSON object to print data
		JsonParser jp = new JsonParser(); // from gson
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request
				.getContent())); // Convert the input stream to a json element
		JsonObject rootobj = root.getAsJsonObject(); // May be an array, may be
														// an object.
		JsonElement apisArray = rootobj.get("apis"); // just grab the zipcode //
													// method stub
		rootobj.remove("apis");

		JsonArray tmpObj = formatJson(apisArray, inputUrl);
		rootobj.add("apis", tmpObj);
		System.out.println(rootobj);

		return rootobj;
	}

	private static JsonArray formatJson(JsonElement apiArray, String sURL2) {
		JsonArray array = apiArray.getAsJsonArray();
		JsonArray ja = new JsonArray();
		String name = "";
		String path = "path";

		for (int i = 0; i < array.size(); i++) {
			JsonObject jo = new JsonObject();
			JsonObject row = array.get(i).getAsJsonObject();
			String tempUrl = row.get(path).toString().replace("\"", "");
			System.out.println(tempUrl);
			//tempUrl = pathPrefix + tempUrl;
			System.out.println(tempUrl);
			name = row.get("description").toString().replace("\"", "");
			jo.addProperty("path", tempUrl);
			jo.addProperty("description", name);
			ja.add(jo);
		}
		return ja;

	}

}

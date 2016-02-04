package com.ascii.asciidoc.swaggerstaticdoc.core;


import static org.asciidoctor.AttributesBuilder.attributes;
import static org.asciidoctor.OptionsBuilder.options;
import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FrameJson {
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
		// Swagger2MarkupConverter.from(sURL).withDescriptions("src/docs/asciidoc").build().intoFolder("src/docs/asciidoc");
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
			// tempUrl = pathPrefix + tempUrl;
			System.out.println(tempUrl);
			name = row.get("description").toString().replace("\"", "");
			jo.addProperty("path", tempUrl);
			jo.addProperty("description", name);
			ja.add(jo);
		}
		return ja;

	}

	// make single file
	public void getSingleFile(String sourceUrl) {

		try {
			File outputDirectory = new File("build/docs/asciidoc/generated");
			Swagger2MarkupConverter.from(sourceUrl)
					.withMarkupLanguage(MarkupLanguage.ASCIIDOC).withSchemas("http://localhost:8080/web/api/api_json/").build()
					.intoFolder(outputDirectory.getAbsolutePath());

			FileInputStream fis1 = new FileInputStream(
					"build/docs/asciidoc/generated/overview.adoc"); // first
			// source
			// file
			FileInputStream fis2 = new FileInputStream(
					"build/docs/asciidoc/generated/paths.adoc"); // second
			// source
			// file
			FileInputStream fis3 = new FileInputStream(
					"build/docs/asciidoc/generated/definitions.adoc"); // first
			// source
			// file

			SequenceInputStream _tmpis = new SequenceInputStream(fis1, fis2);
			SequenceInputStream sis = new SequenceInputStream(_tmpis, fis3);
			FileOutputStream fos = new FileOutputStream(
					"build/docs/asciidoc/generated/Result.adoc"); // destination
			// file

			int temp;
			while ((temp = sis.read()) != -1) {
				System.out.print((char) temp); // to print at DOS prompt
				fos.write(temp); // to write to file

			}

			Asciidoctor asciidoctor = Asciidoctor.Factory.create();

			/*
			 * Map<String, Object> fileOptions = new HashMap<String, Object>();
			 * fileOptions.put("backend", "pdf"); fileOptions.put("in_place",
			 * true); fileOptions.put("theme","custom-theme.yml");
			 */
			// options.put("sourceHighlighter", "coderay");
			Attributes attributes = attributes().attribute("pdf-style",
					"./src/resources/themes/updated-theme.yml").get();
			Options options = options().backend("pdf").attributes(attributes)
					.get();
			File file = new File("build/docs/asciidoc/generated/Result.adoc");

			asciidoctor.convertFile(file, options);
			System.out.println("PDF created Successfully ");

			fos.close();
			_tmpis.close();
			sis.close();
			fis1.close();
			fis2.close();
			fis3.close();

		} catch (Exception e) {
			System.out.println("Exception occured " + e);
		}

	}
}

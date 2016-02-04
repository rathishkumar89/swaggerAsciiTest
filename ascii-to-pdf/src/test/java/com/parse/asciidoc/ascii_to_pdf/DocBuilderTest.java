package com.parse.asciidoc.ascii_to_pdf;

import org.junit.Test;

import com.ascii.asciidoc.swaggerstaticdoc.core.FrameJson;

public class DocBuilderTest {

	@Test
	public void PdfCreator() {
		try {
			String sourceUrl = "http://localhost:8080/web/api/api_json/api-docs.json";
			FrameJson json = new FrameJson();
			// String fName = UUID.randomUUID() + ".json";
			// File file = new File("input.json");
			/*
			 * // System.out.println(json.formatJson(sourceUrl)); PrintWriter
			 * out = new PrintWriter("input.json");
			 * out.write(json.formatJson(sourceUrl).toString()); out.close();
			 */
			// File file = new
			// File(DocBuilderTest.class.getResource("../input.json").getFile());
			// Parse the Contents of the URL to PDF
			json.getSingleFile(sourceUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

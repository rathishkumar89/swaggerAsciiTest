package com.parse.asciidoc.ascii_to_pdf;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;

import java.io.File;
import java.io.PrintWriter;

import org.junit.Test;

public class AsciiCreatorTest {

	@Test
	public void AsciiCreator() {
		try {
			String sourceUrl = "http://localhost:8080/web/api/api_json/api-docs.json";
			App json = new App();
			// String fName = UUID.randomUUID() + ".json";
			File file = new File("input.json");
			// System.out.println(json.formatJson(sourceUrl));
			PrintWriter out = new PrintWriter("input.json");
			out.write(json.formatJson(sourceUrl).toString());
			out.close();

			Swagger2MarkupConverter.from(file.getPath())
					.withMarkupLanguage(MarkupLanguage.ASCIIDOC).build()
					.intoFolder("src/docs/asciidoc/generated");
			// Then validate that three AsciiDoc files have been created
			String[] files = new File("src/docs/asciidoc/generated").list();

			// assertThat(files).hasSize(3)
			// .containsAll(Arrays.asList("definitions.adoc", "overview.adoc",
			// "paths.adoc"));
			// new File(fName).delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

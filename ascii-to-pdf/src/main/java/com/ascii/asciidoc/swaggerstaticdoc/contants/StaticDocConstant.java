package com.ascii.asciidoc.swaggerstaticdoc.contants;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Pradeep CH
 *
 */
public class StaticDocConstant {

	public static final String SUCCESS = "SUCCESS";
	public static final String IO_EXCEPTION = "IO_EXCEPTION";
	public static final String RUNTIME_EXCEPTION = "RUNTIME_EXCEPTION";
	public static final String INVALID_PARAM = "INVALID_PARAM";
	public static final String INVALID_PARAM_MSG = "Parameters are invalid";
	public static final String PDF = "pdf";
	public static final String EMPTY = "";
	public static final String SLASH = "/";
	public static final String PDF_FILE_FOLDER = "/pdf";
	public static final String COMBINED_ASCCIDOC_FOLDER = "/combinedascci";
	public static final String NO_FILES = "NO_FILES";
	public static final String NO_FILES_MSG = "No files found to convert";
	public static final List<String> ASCII_EXTENTIONS = Arrays.asList(".adoc",
			".asciidoc", ".asc");
	public static final String COMBINED_FILE_NAME = "File_"
			+ System.currentTimeMillis() + ASCII_EXTENTIONS.get(0);
	public static final char DOT='.';
	public static final String MARKS="marks";

}

package com.ascii.asciidoc.test;


import java.util.UUID;

public class KeyManager {
	private final static char UNDERSCORE='_';
	private final static char SEPERTOR='-';
	public static void main(String[] args) {

		// generate random UUIDs
		UUID idOne = UUID.randomUUID();
		UUID idTwo = UUID.randomUUID();
		log("UUID One: " + idOne);
		log("UUID Two: " + idTwo);
	}

	
	private static void log(Object aObject) {
		System.out.println(String.valueOf(aObject).replace(SEPERTOR, UNDERSCORE));
	}
}

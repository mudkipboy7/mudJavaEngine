package com.mudkipboy7.mudJavaEngine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class mudUtil {
	public static String getStringFromFile(String filePath) {
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {

		}
		if (reader != null)
			reader.lines().forEach(string -> System.out.println(string));

		return reader == null ? "" : reader.toString();

	}
}

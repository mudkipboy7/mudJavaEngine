package com.mudkipboy7.mudJavaEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;

public class FileGetter {

	public static CommentedConfig getTOML(InputStream inputStream) {
		if(inputStream == null) {
			return null;
		}
		TomlParser tomlParser = new TomlParser();
		return tomlParser.parse(inputStream);
	}

	public static CommentedConfig getTOML(String filename) {
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filename);
		return getTOML(inputStream);
	}

	public static InputStream getFileFromInternet(String loc) {
		InputStream inputStream = null;
		try {
			inputStream = new URL(loc).openStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			return null;
		}
		return inputStream;
	}
}

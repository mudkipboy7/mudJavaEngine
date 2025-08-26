package com.mudkipboy7.mudJavaEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Saving {
	static final String SAVE_NAME = "save";
	static final String SAVE_LOCATION = System.getProperty("user.home") + "\\Saved Games\\mudJavaEngine";

	public static String readSave(String saveName) {
		String OS = System.getProperty("os.name");
		if (OS.matches("Windows 10")) {
			String savePath = SAVE_LOCATION + "\\" + saveName + ".sav";
			String saveFile = "";
			try {
				saveFile = Files.readString(Paths.get(savePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return saveFile;

		}
		throw new RuntimeException("Game is being ran on an OS with undefined user location: " + "\"" + OS + "\"");

	}

	public static boolean writeSave(String saveName, String saveContents) {
		new File(SAVE_LOCATION).mkdirs();
		File file = new File(SAVE_LOCATION + "\\" + saveName + ".sav");
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(saveContents);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

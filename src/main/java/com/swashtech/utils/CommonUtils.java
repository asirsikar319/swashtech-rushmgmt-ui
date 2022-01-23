package com.swashtech.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {

	public static String getValueFromConfig(String key) throws FileNotFoundException, IOException {
		String workDir = System.getProperty("user.dir");
		System.err.println("workDir : " + workDir);
		String absPath = workDir + File.separator + "config" + File.separator + "config.properties";
		Properties prop = new Properties();
		prop.load(new FileInputStream(absPath));
		String value = prop.getProperty(key);
		return value;
	}

}

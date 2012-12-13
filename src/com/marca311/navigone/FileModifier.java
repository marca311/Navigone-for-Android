package com.marca311.navigone;
import java.io.*;

public class FileModifier {
	public static void checkDataFolder()
	{
		File DataFolder = new File("/data/data/com.marca311.navigone");
		
		if (DataFolder.exists() == true)
		{
			File routesFolder = new File("/data/data/com.marca311.navigone/routes");
			if (routesFolder.exists() == false)
			{
				routesFolder.mkdir();
			}
			File xmlFolder = new File("/data/data/com.marca311.navigone/routes/xmls");
			if (xmlFolder.exists() == false)
			{
				xmlFolder.mkdir();
			}
			File documentFolder = new File("/data/data/com.marca311.navigone/routes/documents");
			if (documentFolder.exists() == false)
			{
				documentFolder.mkdir();
			}
		}
	}
}

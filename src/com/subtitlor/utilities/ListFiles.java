package com.subtitlor.utilities;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;

public class ListFiles {
	
	public ArrayList<String> getNames(String path) {
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      fileNames.add(listOfFiles[i].getName());
		    	/*if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        System.out.println("Directory " + listOfFiles[i].getName());
		      }*/
		    }
		return fileNames;
	}
}

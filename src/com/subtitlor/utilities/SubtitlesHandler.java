package com.subtitlor.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;

import com.subtitlor.DAO.DaoFactory;
import com.subtitlor.DAO.OriginalDao;
import com.subtitlor.DAO.TranslationDao;
import com.subtitlor.beans.Language;
import com.subtitlor.beans.Original;
import com.subtitlor.beans.Translation;

public class SubtitlesHandler {
	
	private ArrayList<String> originalSubtitles = null;
	private ArrayList<String> translatedSubtitles = null;
	
	public SubtitlesHandler() {}
	
	
	public ArrayList<String> fromText(String filePath, String file) {
		// writes original from text file
		System.out.println("now in fromText");
		System.out.println(filePath);
		originalSubtitles = new ArrayList<String>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
					originalSubtitles.add(line);
			}
				
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return originalSubtitles;
	} 
	
	public ArrayList<String> toOriginal(String filePath, String file, Language language, OriginalDao originalDao) {
		
		
		System.out.println("now in toOriginal");
		
		ArrayList<String> text = new ArrayList<String>(); 
		
		Original original = new Original();
		
		// create the array from the text file
		originalSubtitles = fromText(filePath, file);
		int size = originalSubtitles.size(); // using this to avoid error at end of file: java.lang.IndexOutOfBoundsException: Index: 2082, Size: 2080
		// write array to DB. To do that I will create original objects and save them individually to DB
		for (int i = 0; i < originalSubtitles.size(); i++) {
			System.out.println("loop index i: " + i);
			System.out.println("array size: " + size);
			if (originalSubtitles.get(i).chars().allMatch(Character::isDigit) // checking that it is a number
					&& !originalSubtitles.get(i).equals("")) { // checking that it is not an empty field
				// this is the ID, the line that I will take as reference to create the objects
				original.setId(Integer.parseInt(originalSubtitles.get(i)));
				original.setFileName(file);
				if (i+1 < size) original.setTimeStamp(originalSubtitles.get(i+1));
				if (i+2 < size)
					{
					original.setOriginalLineOne(originalSubtitles.get(i+2));
					text.add(originalSubtitles.get(i+2));
					}
				
				original.setLanguage(language);
				// check end of file, if not check if there is a second line, 
				if (i + 5 > size) 
				{
					if (i+3 < size) {
					original.setOriginalLineTwo(originalSubtitles.get(i+3));
					text.add(originalSubtitles.get(i+3));
					}
				} else if (originalSubtitles.get(i+5).chars().allMatch(Character::isDigit)) {
					original.setOriginalLineTwo(originalSubtitles.get(i+3));
					text.add(originalSubtitles.get(i+3));
				}
				original.setOriginal(true);
			}
			
			// save object to DB if new object was created
			if (original.getFileName() != null) {
				System.out.println("saving to DB this object: " + original);
				originalDao.add(original);	
				
				// reinitialize original object
				original = new Original();
				
				System.out.println("now going back to beginning of loop" + original);
			}

		}
		
		return text;
		
	}
	
	public ArrayList<String> toTranslation(String filePath, String file, Language language, TranslationDao translationDao) { 
		
		System.out.println("now in toTranslation");
		
		Translation translation = new Translation();
		
		ArrayList<String> text = new ArrayList<String>();
		
		// create the array from the text file
		translatedSubtitles = fromText(filePath, file);
		
		System.out.println("wrote translated subtitles array, size= " + translatedSubtitles.size());
		
		int size = translatedSubtitles.size(); // using this to avoid error at end of file
				
		// write array to DB. To do that I will create translation objects and save them individually to DB
		for (int i = 0; i < translatedSubtitles.size(); i++) {
			System.out.println("loop index i: " + i);
			System.out.println("array size: " + size);
			
			if (translatedSubtitles.get(i).chars().allMatch(Character::isDigit)
					&& !originalSubtitles.get(i).equals("")) {
				// this is the ID, the line that I will take as reference to create the objects
				translation.setId(Integer.parseInt(translatedSubtitles.get(i)));
				translation.setFileName(file);
				if (i+1 < size) translation.setTimeStamp(translatedSubtitles.get(i+1));
				if (i+2 < size) {
				translation.setTranslatedLineOne("line " + i);
				text.add("line " + i);
				}
						
				// check if there is a second line
				if (i + 5 > size) 
				{
					if (i+3 < size) {
						translation.setTranslatedLineTwo("line " + i);
						text.add("line " + i);
						}
				} else if (translatedSubtitles.get(i+5).chars().allMatch(Character::isDigit)) {
						translation.setTranslatedLineTwo("line " + i);
						text.add("line " + i);
				}
				
				translation.setOriginal(false);
				translation.setLanguage(language);
			
				// save object to DB
				System.out.println(translation);
				translationDao.add(translation);	
			
				// reinitialize original object
				translation = new Translation(); 
				System.out.println(translation);
			
			}
		}
		
		return text;
	
}
	
	public ArrayList<String> getOriginalLines(ArrayList<Original> originalResults){
		ArrayList<String> lines = new ArrayList<String>();
		
		System.out.println("now in getOriginalLines");
		System.out.println("originalResults size: " + originalResults.size());
	
		for (Original o : originalResults) {
			System.out.println(o);
		}
		
		
		for (Original o : originalResults) {
			lines.add(o.getOriginalLineOne());
			if (o.getOriginalLineTwo() != null) lines.add(o.getOriginalLineTwo());
		}
		
		return lines; 
		
	}

	public ArrayList<String> getTranslatedLines(ArrayList<Translation> translationResults){
		ArrayList<String> lines = new ArrayList<String>();
		
		System.out.println("now in getTranslatedLines");
		
		for (Translation t : translationResults) {
			lines.add(t.getTranslatedLineOne());
			if (t.getTranslatedLineTwo() != null) lines.add(t.getTranslatedLineTwo());
		}
		
		for (String l : lines) {
			System.out.println(l);
		}
		
		return lines; 
		
	}
	
	public void saveTranslationUpdate (String file, String language, HashMap<Integer, String> translations, TranslationDao translationDao) {
		
		Translation translation = new Translation();
		
		System.out.println("now in saving translation updates");
		System.out.println("translations size: " + translations.size());
		
		// getting number of translation objects
		
		int count = translationDao.count(file, language);
		
		System.out.println("Number of translation objects: " + count);
		
		for ( int i = 1, j = 0; i <= count; i++, j++) {
			
			// get translation object
			
			System.out.println("index is " + i);
			System.out.println("file is " + file);
			System.out.println("language is " + language);
			
			translation = translationDao.getOneTranslation(i, file, Language.valueOf(language));
			System.out.println("translation object in handler: " + translation);
			
			// update line 1
			translation.setTranslatedLineOne(translations.get(j));
			
			// update line 2 if there is a line 2
			if (translation.getTranslatedLineTwo() != null) {
				j = j + 1;
				translation.setTranslatedLineTwo(translations.get(j));
			}
			
			// save object to DB
			translationDao.update(translation);
			
			// reinitialize object
			translation = new Translation();
			
		}
		
		
	}
	
	
	/*private ArrayList<Original> originalSubtitles = null;
	private ArrayList<Translation> translatedSubtitles = null;
	private int count;
	
	public SubtitlesHandler (String fileName) {
		originalSubtitles = new ArrayList<Original>();
		translatedSubtitles = new ArrayList<Translation>();
		
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}*/
	
	
	public ArrayList<String> getSubtitles() {
		return originalSubtitles;
	}
	
	public boolean isSubtitle (String string) {
		
		boolean allLetters = false;
		
		if (string.chars().allMatch(Character::isDigit)) {allLetters = false;}
		
			else if (string.contains("-->")) {allLetters = false;}
		
			else if (string.equals("")) {allLetters = false;}
		
			else allLetters = true;
		
		return allLetters;
			
	}
	
	
	
}

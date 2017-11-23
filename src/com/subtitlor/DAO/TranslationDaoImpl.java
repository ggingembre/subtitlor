package com.subtitlor.DAO;

import java.sql.*;
import java.util.*;

import com.subtitlor.beans.Language;
import com.subtitlor.beans.Translation;

public class TranslationDaoImpl implements TranslationDao {

	private DaoFactory daoFactory;

    TranslationDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public Translation getOneTranslation(long id, String fileName, Language language) {
		// get a specific translation object to test whether there is work in progress for this file in this language
		
		Translation translation = new Translation();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            
            statement = connection.prepareStatement("SELECT timeline, translatedlineone, translatedlinetwo,"
            		+ " original FROM translations WHERE id = ? and filename = ? and langue = ?");
            statement.setLong(1,  id); 
            statement.setString(2, fileName); 
            statement.setString(3, language.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String timeline = resultSet.getString("timeline");
                String translatedlineone = resultSet.getString("translatedlineone");
                String translatedlinetwo = resultSet.getString("translatedlinetwo");
                Boolean original = resultSet.getBoolean("original");
                
                translation.setId(id);
                translation.setFileName(fileName);
                translation.setTimeStamp(timeline);
                translation.setTranslatedLineOne(translatedlineone);
                translation.setTranslatedLineTwo(translatedlinetwo);
                translation.setLanguage(language);
                translation.setOriginal(original);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return translation;
	}
	
	@Override
	public ArrayList<Translation> getTranslations(String fileName, Language language) {
		// return all translations for a particular file in a particular language
		
		ArrayList<Translation> translations = new ArrayList<Translation>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            
            statement = connection.prepareStatement("SELECT id, timeline, translatedlineone, translatedlinetwo,"
            		+ " original FROM translations WHERE filename = ? and langue = ?"); 
            statement.setString(1, fileName); 
            statement.setString(2, language.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
            	String timeline = resultSet.getString("timeline");
                String translatedlineone = resultSet.getString("translatedlineone");
                String translatedlinetwo = resultSet.getString("translatedlinetwo");
                Boolean original = resultSet.getBoolean("original");
                
                Translation translation = new Translation();
                translation.setId(id);
                translation.setFileName(fileName);
                translation.setTimeStamp(timeline);
                translation.setTranslatedLineOne(translatedlineone);
                translation.setTranslatedLineTwo(translatedlinetwo);
                translation.setLanguage(language);
                translation.setOriginal(original);
                
                translations.add(translation);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return translations;
		
	}
	
	@Override
	public void add(Translation translation) {
		//add translation object to database
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO translations(id, filename, timeline, translatedlineone, translatedlinetwo,"
            		+ "langue) VALUES(?, ?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, translation.getId());
            preparedStatement.setString(2, translation.getFileName());
            preparedStatement.setString(3, translation.getTimeStamp());
            preparedStatement.setString(4, translation.getTranslatedLineOne());
            preparedStatement.setString(5, translation.getTranslatedLineTwo());
            preparedStatement.setString(6, translation.getLanguage().toString());

            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public ArrayList<Translation> listAll() {
		// get all translation objects from database
       
		ArrayList<Translation> translations = new ArrayList<Translation>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id, filename, timeline, translatedlineone, translatedlinetwo"
            		+ ", langue, original FROM translations;");

            while (resultat.next()) {
                long id = resultat.getLong("id");
                String filename = resultat.getString("filename");
                String timeline = resultat.getString("timeline");
                String translatedlineone = resultat.getString("translatedlineone");
                String translatedlinetwo = resultat.getString("translatedlinetwo");
                String langue = resultat.getString("langue");
                Boolean original = resultat.getBoolean("original");
                
                Translation translation = new Translation();
                translation.setId(id);
                translation.setFileName(filename);
                translation.setTimeStamp(timeline);
                translation.setTranslatedLineOne(translatedlineone);
                translation.setTranslatedLineTwo(translatedlinetwo);
                translation.setLanguage(Language.valueOf(langue));
                translation.setOriginal(original);
                
                translations.add(translation);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return translations;
	}

	@Override
	public void update(long id, Translation translation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String pathName) {
		// TODO Auto-generated method stub
		
	}



}

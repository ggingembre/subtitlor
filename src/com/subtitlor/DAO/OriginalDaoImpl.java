package com.subtitlor.DAO;


import java.sql.*;
import java.util.ArrayList;

import com.subtitlor.beans.Language;
import com.subtitlor.beans.Original;

public class OriginalDaoImpl implements OriginalDao {

	private DaoFactory daoFactory;

    OriginalDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
        
    }
	
	@Override
	public Original getOneOriginal(long id, String fileName) {
		// get a specific original object to test whether there is work in progress for this file
		
		System.out.println("now in getOneOriginal");
		
				Original original = new Original();
		        PreparedStatement statement = null;
		        ResultSet resultSet = null;
		        Connection connection = null;

		        try {
		        	connection = daoFactory.getConnection();
		            
		            statement = connection.prepareStatement("SELECT timeline, originallineone, originallinetwo, langue, original FROM originals WHERE id = ? and filename = ?;");
		            statement.setLong(1,  id); 
		            statement.setString(2, fileName);
		            resultSet = statement.executeQuery();

		            System.out.println("going through result set in getOneOriginal");
		            while (resultSet.next()) {
		            	String timeline = resultSet.getString("timeline");
		                String originallineone = resultSet.getString("originallineone");
		                String originallinetwo = resultSet.getString("originallinetwo");
		                Boolean originalB = resultSet.getBoolean("original");
		                String langue = resultSet.getString("langue");
		                
		                original.setId(id);
		                original.setFileName(fileName);
		                original.setTimeStamp(timeline);
		                original.setOriginalLineOne(originallineone);
		                original.setOriginalLineTwo(originallinetwo);
		                original.setLanguage(Language.valueOf(langue));
		                original.setOriginal(originalB);
		               
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } 
		        
		        System.out.println("now in getOneOriginal, printing result: " + original);
		        return original;
	}


	@Override
	public ArrayList<Original> getOriginals(String fileName) {
		
		// return all originals for a particular file
		
				ArrayList<Original> originals = new ArrayList<Original>();
		        PreparedStatement statement = null;
		        ResultSet resultSet = null;
		        Connection connection = null;

		        try {
		        	connection = daoFactory.getConnection();
		            
		            statement = connection.prepareStatement("SELECT id, timeline, originallineone, originallinetwo,"
		            		+ " langue, original FROM originals WHERE filename = ?;"); 
		            statement.setString(1, fileName);
		            resultSet = statement.executeQuery();

		            while (resultSet.next()) {
		                Long id = resultSet.getLong("id");
		            	String timeline = resultSet.getString("timeline");
		                String originallineone = resultSet.getString("originallineone");
		                String originallinetwo = resultSet.getString("originallinetwo");
		                Boolean originalB = resultSet.getBoolean("original");
		                String langue = resultSet.getString("langue");
		                
		                Original original = new Original();
		                original.setId(id);
		                original.setFileName(fileName);
		                original.setTimeStamp(timeline);
		                original.setOriginalLineOne(originallineone);
		                original.setOriginalLineTwo(originallinetwo);
		                original.setLanguage(Language.valueOf(langue));
		                original.setOriginal(originalB);
		                
		                originals.add(original);
		               
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		     }
		        
		        return originals;
		
	}
    
    
	@Override
	public void add(Original original) {
		// add original object to database
		System.out.println("adding original object to DB");
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
        	connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO originals(id, filename, timeline, originallineone, originallinetwo,"
            		+ "langue) VALUES(?, ?, ?, ?, ?, ?);");
            preparedStatement.setLong(1, original.getId());
            preparedStatement.setString(2, original.getFileName());
            preparedStatement.setString(3, original.getTimeStamp());
            preparedStatement.setString(4, original.getOriginalLineOne());
            preparedStatement.setString(5, original.getOriginalLineTwo());
            preparedStatement.setString(6, original.getLanguage().toString());

            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        	
	}
	
	@Override
	public ArrayList<Original> listAll() {
		// get all original objects from database
		ArrayList<Original> originals = new ArrayList<Original>();
        Statement statement = null;
        ResultSet resultat = null;
        Connection connection = null;

        try {
        	connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultat = statement.executeQuery("SELECT id, filename, timeline, originallineone, originallinetwo"
            		+ ", langue, original FROM originals;");

            while (resultat.next()) {
                long id = resultat.getLong("id");
                String filename = resultat.getString("filename");
                String timeline = resultat.getString("timeline");
                String originallineone = resultat.getString("originallineone");
                String originallinetwo = resultat.getString("originallinetwo");
                String langue = resultat.getString("langue");
                Boolean originalB = resultat.getBoolean("original");
                
                Original original = new Original();
                original.setId(id);
                original.setFileName(filename);
                original.setTimeStamp(timeline);
                original.setOriginalLineOne(originallineone);
                original.setOriginalLineTwo(originallinetwo);
                original.setLanguage(Language.valueOf(langue));
                original.setOriginal(originalB);
                
                originals.add(original);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        
        return originals;
		
	}

	@Override
	public void update(long id, Original original) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String pathName) {
		// TODO Auto-generated method stub
		
	}

}

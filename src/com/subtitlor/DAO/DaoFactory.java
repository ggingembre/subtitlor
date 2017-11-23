package com.subtitlor.DAO;

import java.sql.*;

public class DaoFactory {

	private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DaoFactory instance = new DaoFactory(
                "jdbc:mysql://localhost:3306/javaee?max-connections=32767", "root", "$MyLena2016");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Récupération du Dao
    public OriginalDao getOriginalDao() {
        return new OriginalDaoImpl(this);
    }

    public TranslationDao getTranslationDao() {
        return new TranslationDaoImpl(this);
    }
	
}

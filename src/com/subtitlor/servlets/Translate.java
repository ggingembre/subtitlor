package com.subtitlor.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.DAO.DaoFactory;
import com.subtitlor.DAO.OriginalDao;
import com.subtitlor.DAO.TranslationDao;
import com.subtitlor.beans.Language;
import com.subtitlor.beans.Translation;
import com.subtitlor.utilities.SubtitlesHandler;

/**
 * Servlet implementation class Translate
 */

@WebServlet("/Translate")
public class Translate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OriginalDao originalDao;
	private TranslationDao translationDao;
 
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.originalDao = daoFactory.getOriginalDao();
        this.translationDao = daoFactory.getTranslationDao();
    }
	
    public Translate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String language = request.getParameter("language");
		String file = request.getParameter("originalfile");
		int arraySize = Integer.parseInt(request.getParameter("arraylength"));
		HashMap<Integer, String> translations = new HashMap<Integer, String>();
		SubtitlesHandler handler = new SubtitlesHandler();
		Translation translation = new Translation();
		
		System.out.println(language);
		System.out.println(file);
		System.out.println(arraySize);
		System.out.println(request.getParameter("translation_1"));
		System.out.println(request.getParameter("translation_2"));
		System.out.println(request.getParameter("translation_3"));
		
		// create string array of translations
		for (int i = 0; i < arraySize; i++){
			translations.put(i, request.getParameter("translation_" + i));        
	            }
		
		System.out.println(translations.get(arraySize-1));
		
		// save translations in DB
		handler.saveTranslationUpdate(file, language, translations, translationDao);
		
		// show confirmation page
		this.getServletContext().getRequestDispatcher("/translate.jsp").forward(request, response);
		

	}

}

	package com.subtitlor.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.subtitlor.DAO.DaoFactory;
import com.subtitlor.DAO.OriginalDao;
import com.subtitlor.DAO.TranslationDao;
import com.subtitlor.beans.Language;
import com.subtitlor.beans.Original;
import com.subtitlor.beans.Translation;
import com.subtitlor.utilities.ListFiles;
import com.subtitlor.utilities.SubtitlesHandler;

@WebServlet("/EditSubtitle")
public class EditSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FOLDER_NAME = "/WEB-INF/originals/";
	private OriginalDao originalDao;
	private TranslationDao translationDao;
	
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.originalDao = daoFactory.getOriginalDao();
        this.translationDao = daoFactory.getTranslationDao();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		ListFiles listfiles = new ListFiles();
			
		/*SubtitlesHandler subtitles = new SubtitlesHandler(context.getRealPath(FILE_NAME));
		
		request.setAttribute("subtitles", subtitles.getSubtitles());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_subtitle.jsp").forward(request, response);
		*/
		
		List<String> languages = Stream.of(Language.values())
                .map(Language::name)
                .collect(Collectors.toList());
		
		System.out.println(languages);
		
		ArrayList<String> files = listfiles.getNames(context.getRealPath(FOLDER_NAME));
		
		request.setAttribute("files", files);
		request.setAttribute("languages", languages);
		
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String language = request.getParameter("language");
		String originalLanguage = request.getParameter("originalLanguage");
		Language langue = Language.valueOf(language);
		Language langueOriginale = Language.valueOf(originalLanguage);
		String file = request.getParameter("file");
		
		ServletContext context = getServletContext();
		String filePath = context.getRealPath("/WEB-INF/originals/" + file);
		System.out.println("in EditSubtitle, filePath is: " + filePath);
		SubtitlesHandler handler = new SubtitlesHandler();
		
		ArrayList<String> originals = new ArrayList<String>();
		ArrayList<String> translations = new ArrayList<String>();
		ArrayList<Translation> translationResults = new ArrayList<Translation>();
		ArrayList<Original> originalResults = new ArrayList<Original>();
		
		System.out.println("Original Language: " + langueOriginale);
		System.out.println("Target language: " + language);
		System.out.println(file);
		
		
		// check if original object exists in DB
		if ( originalDao.getOneOriginal(1, file).getFileName() == null) {
			//if does not exist record file in DB and create array of lines we want to pass to jsp file
			System.out.println("checking if original object exists in DB. Answer is: " + originalDao.getOneOriginal(1, file) );
			originals = handler.toOriginal(filePath, file, langueOriginale, originalDao);
			
		} else {
			// if exists get "lines" from DB
			System.out.println("now getting original lines from DB");
			originalResults = originalDao.getOriginals(file);	
			originals = handler.getOriginalLines(originalResults);
		}
		
		//Generate Array from text file and set request attribute 
		request.setAttribute("subtitles", originals);
		System.out.println("checking if original object exists in DB. Answer is: " + originalDao.getOneOriginal(1, file).getFileName() );
		System.out.println("file: " + file );
		System.out.println("filepath is: " + filePath );
		
		//check if translated version exists in DB
		System.out.println("translation " + file + " " + langue + " exists in DB: " + translationDao.getOneTranslation(1, file, langue) != null);
		if (translationDao.getOneTranslation(1, file, langue).getFileName() != null) {
			// get results (array of translation objects)
			System.out.println("now checking if translation exists in DB: " + translationDao.getOneTranslation(1, file, langue));
			translationResults = translationDao.getTranslations(file, langue);
			// populate translation array
			translations = handler.getTranslatedLines(translationResults);
			
		} else {
			//if does not exist create a blank one and return it
			System.out.println("now getting translated lines from DB");
			translations = handler.toTranslation(filePath, file, langue, translationDao);
		}
		
		request.setAttribute("translation", translations);
		
		request.setAttribute("originalfile", file);
		request.setAttribute("language", language);
		request.setAttribute("arraylength", translations.size());
		
		this.getServletContext().getRequestDispatcher("/edit_subtitle.jsp").forward(request, response);
		
	}

}

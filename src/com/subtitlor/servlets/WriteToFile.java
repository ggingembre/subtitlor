package com.subtitlor.servlets;

import java.io.*;
import java.util.ArrayList;

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

/**
 * Servlet implementation class WriteToFile
 */
@WebServlet("/WriteToFile")
public class WriteToFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OriginalDao originalDao;
	private TranslationDao translationDao;
 
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.originalDao = daoFactory.getOriginalDao();
        this.translationDao = daoFactory.getTranslationDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteToFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String language = request.getParameter("language");
		String file = request.getParameter("originalfile");
		String translatedFile = file + "_" + language + "srt";
		ArrayList<Translation> translations = new ArrayList<Translation>();
	
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=" + translatedFile);
        
    	OutputStream os = response.getOutputStream();

    	translations = translationDao.getTranslations(file, Language.valueOf(language));
		
		// write them to file
		byte[] contentInBytes = null;
		for (Translation t : translations) {
			
			contentInBytes = (String.valueOf(t.getId()) + System.getProperty("line.separator")).getBytes();
			os.write(contentInBytes);
			contentInBytes = null;
			
			contentInBytes = (t.getTimeStamp() + System.getProperty("line.separator")).getBytes();
			os.write(contentInBytes);
			contentInBytes = null;
			
			contentInBytes = (t.getTranslatedLineOne() + System.getProperty("line.separator")).getBytes();
			os.write(contentInBytes);
			contentInBytes = null;
			
			if (t.getTranslatedLineTwo() != null) {
				contentInBytes = (t.getTranslatedLineTwo() + System.getProperty("line.separator")).getBytes();
				os.write(contentInBytes);
				contentInBytes = null;
			}
			
			contentInBytes = (System.getProperty("line.separator")).getBytes();
			os.write(contentInBytes);
			contentInBytes = null;
		}	
		
		os.flush();
		os.close();

        /*
		SubtitlesHandler handler = new SubtitlesHandler();
		
		ServletContext context = getServletContext();
		
		String filePath = context.getRealPath(FOLDER_NAME) + file.replaceAll(".srt", "_") + language + ".srt";
		
		System.out.println("filepath= " + filePath);
		
		String originalFilePath = context.getRealPath("/WEB-INF/originals/") + file;
		
		System.out.println("original file path: " + originalFilePath);
		
		handler.toText(filePath, file, translationDao, language);
		
		this.getServletContext().getRequestDispatcher("/filecreated.jsp").forward(request, response);
		
		*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}

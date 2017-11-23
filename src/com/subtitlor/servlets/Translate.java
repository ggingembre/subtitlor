package com.subtitlor.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Translate
 */

@WebServlet("/Translate")
public class Translate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
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

		/*
		ArrayList<String> translations = (ArrayList<String>) request.getAttribute("translation");
		
		for (String t : translations) {
			System.out.println(t);
		}
		
		System.out.println(translations.get(0));
		System.out.println(translations.get(1));
		System.out.println(translations.get(2));
		
		*/
		
		System.out.println(language);
		System.out.println(file);
		
		this.getServletContext().getRequestDispatcher("/translate.jsp").forward(request, response);
		

	}

}

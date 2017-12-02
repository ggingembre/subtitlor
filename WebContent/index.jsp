<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Home</title>
<link rel="stylesheet" href="./resources/styles/NewFile.css" type="text/css">
</head>

<body>
    <h2>Welcome to our page to translate subtitles!</h2>
    <a href="/Subtitlor/Upload">Click here to upload a new file</a>
    <a href="/Subtitlor/EditSubtitle">Click here to edit subtitle</a>
    
    <br>
    <br>
    
    <p>Please choose a file to translate and a language in which you wish to translate:</p>
    
    <form method="Post" action="/Subtitlor/EditSubtitle">
    			<section>
  
   				<div>
   				<label for="fileSelect">File to translate: </label>
   				<select name="file">   
    				<c:forEach var="file" items="${files}">
         				<option value="${file}">${file}</option>
    				</c:forEach>
				</select>
				</div>

				<div>
    			<label for="languageSelect">Original language: </label>
    			<select name="originalLanguage">
    				<c:forEach items="${languages}" var="originalLanguage"	>
        				<option value="${originalLanguage}">${originalLanguage}</option>
    				</c:forEach>
				</select> 
				</div>

				<div>
    			<label for="languageSelect">Target language: </label>
    			<select name="language">
    				<c:forEach items="${languages}" var="language"	>
        				<option value="${language}">${language}</option>
    				</c:forEach>
				</select> 
				</div>
				<div>
				<input type="submit" value="Translate"/>
				</div>      
    			 
			</section>
	</form>
</body>
</html>
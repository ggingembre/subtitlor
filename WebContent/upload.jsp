<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
    <c:if test="${ !empty fichier }">
    <p><c:out value="Le fichier ${ fichier } (${ description }) a été uploadé !" /></p>
    <p>Uploadez un autre fichier: </p>
    
    </c:if>
    <form method="post" action="Upload" enctype="multipart/form-data">
        <p>
            <label for="description">Description du fichier : </label>
            <input type="text" name="description" id="description" path="description" />
        </p>
        <p>
            <label for="fichier">Fichier à envoyer : </label>
            <input type="file" name="fichier" id="fichier" path="fichier" />
        </p>
        <input type="submit" />
    </form>
   <br>
   <br>
    <a href="/Subtitlor/EditSubtitle">Back to home</a>
    
</body>
</html>
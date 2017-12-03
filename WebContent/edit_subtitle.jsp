<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Editer les sous-titres</title>
</head>
<body>
    <form method="post" action="/Subtitlor/Translate">    
        <input type="submit" value="save translation" style="position:fixed; top: 10px; right: 10px;" onclick="document.getElementById('loader').style.display = 'block';" />
        <input type="hidden" name="originalfile" id="originalfile" value="${originalfile}" />
        <input type="hidden" name="language" id="language" value="${language}" />
        <input type="hidden" name="arraylength" id="arraylength" value="${arraylength}" />
        
        <img id="loader" src="ajax-loader.gif" path="/Subtitlor/WebContent/ajax-loader.gif" style="display: none; position: fixed; top: 50%; right: 50%" />
        
        <table>
	        <c:forEach items="${ subtitles }" var="subtitle" varStatus="loop">
	        	<tr>
	        		<td style="text-align:right;"><c:out value="${ subtitle }" /></td>
	        		<td><input type="text" value="${ translation [loop.index] }" name="translation_${loop.index}" size="35" /></td>
	        	</tr>
	    	</c:forEach>
	    </table>
    </form>
</body>
</html>

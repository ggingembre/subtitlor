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
        <input type="submit" style="position:fixed; top: 10px; right: 10px;" />
        <input type="hidden" name="originalfile" id="originalfile" value="${originalfile}" />
        <input type="hidden" name="language" id="language" value="${language}" />
        
        <table>
	        <c:forEach items="${ subtitles }" var="subtitle" varStatus="loop">
	        	<tr>
	        		<td style="text-align:right;"><c:out value="${ subtitle }" /></td>
	        		<td><input type="text" value="${ translation [loop.index] }" name="${ translation [loop.index] }" size="35" /></td>
	        	</tr>
	    	</c:forEach>
	    </table>
    </form>
</body>
</html>

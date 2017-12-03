<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Translation Saved!</title>
</head>
<body>

<p>You have successfully saved your translation!</p> 

<form method="post" action="/Subtitlor/WriteToFile">    
        <input type="submit" value="Download Translation to File" style="position:fixed; top: 10px; right: 10px;" />
        <input type="hidden" name="originalfile" id="originalfile" value="${originalfile}" />
        <input type="hidden" name="language" id="language" value="${language}" />
</form>

    <br>
   <br>
   
   <center>
    <a href="/Subtitlor/EditSubtitle">Back to home</a>
   </center>
    
</body>
</html> 
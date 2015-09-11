<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= "Push11 Data -> I'm alive -> " +
            LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) %></title>
</head>
<body>

</body>
</html>
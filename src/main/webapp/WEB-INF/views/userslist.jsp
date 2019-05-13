<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Users list</title>
</head>
<body>
	<span>Users 목록</span>
    <ul>
    <c:forEach var="item" items="${result}">
        <li>ID : ${item.id} , Name : ${item.name}</li>
    </c:forEach>
    </ul>
</body>
</html>
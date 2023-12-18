<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz List</title>
    <link rel="stylesheet" href="../../../resources/css/quizlist.css">
</head>
<body>
<div class="container">
    <div class = title>MetaKakao</div>
    <h3>${mid} 님의 퀴즈 목록</h3>
        <table>
            <tbody>
            <c:forEach var="index" begin="0" end="${titles.size()}" varStatus="loop">
                <tr>
                    <td><a href="/quiz/game/${mid}/${index + 1}">${titles[index]}</a></td>
                    <td>${regDates[index]}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <button class="make_quiz" onclick="location.href='/quiz/myquiz';">
        <img src="../../../resources/assets/logo.png"/>
        <div>퀴즈 만들기</div>
    </button>
</div>

</body>
</html>

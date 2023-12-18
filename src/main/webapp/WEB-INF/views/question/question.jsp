
<%@ page import="com.project.metakakao.entity.Member" %>
<%@ page import="com.project.metakakao.dto.KakaoDTO" %>
<%@ page import="java.util.Objects" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
    <link rel="stylesheet" href="../../../resources/css/question.css" />
    <script>
        function toggleAnswerForm(index) {
            var form = document.getElementById("answer-form-" + index);
            form.style.display = form.style.display === "none" ? "block" : "none";
        }
        document.addEventListener('DOMContentLoaded', function() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/kakao/list', true); // '/kakao/friends'로 변경 가능
            xhr.onload = function() {
                if (xhr.status === 200) {
                    var friendList = JSON.parse(xhr.responseText);
                    displayFriends(friendList);
                } else {
                    console.log("친구 목록을 불러오는데 실패했습니다.");
                }
            };
            xhr.send();
        });

        function displayFriends(friendList) {
            var friendListDiv = document.getElementById('friendList');
            friendList.forEach(function(friend) {
                var div = document.createElement('div');
                div.textContent = friend.nickname; // 친구 정보 표시
                friendListDiv.appendChild(div);
            });
        }
    </script>
</head>
<body>
    <div class="background">
        <div class = userInfo>
            <%
                Member user = (Member) request.getAttribute("user");
                KakaoDTO kakaoDTO = (KakaoDTO) request.getAttribute("kakaoDTO");
                boolean owner = Objects.equals(kakaoDTO.getEmail(), user.getEmail());
                request.setAttribute("owner", owner); //owner를 request 속성으로 설정, 이렇게해야 jstl 사용가능
                session = request.getSession();
                String accessToken = (String) session.getAttribute("accessToken");
            %>
            ${kakaoDTO.nickname}님 환영합니다. |
            <%
                if (accessToken != null && !accessToken.isEmpty()) {
            %>로그아웃
            <%
            } else {
            %>
            로그인
            <%
                }
            %>
        </div>
        <div class ="profile">
            <button class="quiz" onclick="location.href='/quiz/${user.email}/list';">
                <img src="../../../resources/assets/logo.png"/>
                <div>Quiz?</div>
            </button>
            <div class="image">
                <img src = "${user.profileImgUrl}"/>
            </div>
            <div class ="profile-box">
                <div class="name"> ${user.nickname}</div>
                <div class="birth"> ${user.email}</div>
                <div class="birth">짜파게티 먹고싶다.</div>
            </div>
            <button class = "ans_complete">답변완료</button>
            <div id="friendList" style="display: none;">
                <!-- AJAX를 통해 불러온 친구 목록이 여기에 표시됩니다 -->
            </div>
            <button class="new_question" onclick="toggleFriendList()">친구</button>
            <script>
                function toggleFriendList() {
                    var friendListDiv = document.getElementById("friendList");
                    friendListDiv.style.display = friendListDiv.style.display === "none" ? "block" : "none";
                }
            </script>
        </div>
        <%
            if(!owner){
        %>
        <div class="new_contexnt">
            <form id="questionForm" action="/question/ask" method="post">
                <input type="hidden" name="hostID" value="${user.id}"/>
                <textarea id="userQuestion" name="content" placeholder="지금 ${user.nickname} 님에게 질문을 남겨보세요"></textarea>
                <button type="submit">등록</button>
            </form>
        </div>
        <%
            }
        %>
        <c:forEach var="question" items="${questionList}" varStatus="status">
            <div class="question-item" id="question-item-${status.index}">
                <form action="/question/delete" method="post">
                    <input type="hidden" name="questionId" value="${question.qno}"/>
                    <button type="submit" class="delete-btn">삭제</button>
                </form>
                <div class="question-content">
                        ${question.content}

                    <c:if test="${owner}">
                        <!-- 이미 존재하는 답변 표시 -->
                        <c:if test="${question.answer != null}">
                            <div class="existing-answer">
                                <p>${user.nickname}: ${question.answer.content}</p>
                                <button type="button" class="update-btn" onclick="toggleAnswerForm(${status.index})">수정하기</button>
                            </div>
                        </c:if>

                        <!-- 답변하기 버튼 및 폼 (답변이 없는 경우에만 표시) -->
                        <c:if test="${question.answer == null}">
                            <button type="button" class="answer-btn" onclick="toggleAnswerForm(${status.index})">답변하기</button>
                        </c:if>

                        <!-- 답변 폼 -->
                        <div class="answer-form" id="answer-form-${status.index}" style="display: none;">
                            <form action="/question/answer" method="post">
                                <input type="hidden" name="questionId" value="${question.qno}" />
                                <textarea name="answerContent" placeholder="답변을 여기에 작성하세요">${question.answer != null ? question.answer.content : ''}</textarea>
                                <button type="submit" class="submit-btn">답변 제출</button>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:forEach>

    </div>
</body>

</html>
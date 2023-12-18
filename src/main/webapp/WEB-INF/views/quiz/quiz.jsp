<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>quiz app</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="../../../resources/css/quiz.css">
</head>
<body>

<div class="grid">
    <h1>퀴즈</h1>
    <div id="quiz">
        <p id="question"></p>

        <div class="buttons">
            <button class="btn"></button>
            <button class="btn"></button>
        </div>

        <footer>
            <p id="progress">문제 x of y.</p>
        </footer>
    </div><!-- end quiz -->

</div><!-- end grid -->
<script>
    // 문제 객체
    function Question(text, choice, answer){
        this.text = text;
        this.choice = choice;
        this.answer = answer;
    }

    // 퀴즈 정보 객체
    function Quiz(questions){
        this.score = 0;				// 점수
        this.questions = questions;	// 질문
        this.questionIndex = 0;		// 질문 순서
    }

    // 정답 확인 메소드
    Quiz.prototype.correctAnswer = function(answer){
        return answer == this.questions[this.questionIndex].answer;
    };

    // -----------------------------------------------------------------------------------
    // 문제 데이터
    // q1~10, a1~10 받아오기
    let q1 = "${q1}"
    let q2 = "${q2}"
    let q3 = "${q3}"
    let q4 = "${q4}"
    let q5 = "${q5}"
    let q6 = "${q6}"
    let q7 = "${q7}"
    let q8 = "${q8}"
    let q9 = "${q9}"
    let q10 = "${q10}"
    let a1 = "${a1}"
    let a2 = "${a2}"
    let a3 = "${a3}"
    let a4 = "${a4}"
    let a5 = "${a5}"
    let a6 = "${a6}"
    let a7 = "${a7}"
    let a8 = "${a8}"
    let a9 = "${a9}"
    let a10 = "${a10}"



    console.log("질문 1:" + q1)
    var questions = [
        new Question(q1, ['O', 'X'], a1),
        new Question(q2, ['O', 'X'], a2),
        new Question(q3, ['O', 'X'], a3),
        new Question(q4, ['O', 'X'], a4),
        new Question(q5, ['O', 'X'], a5),
        new Question(q6, ['O', 'X'], a6),
        new Question(q7, ['O', 'X'], a7),
        new Question(q8, ['O', 'X'], a8),
        new Question(q9, ['O', 'X'], a9),
        new Question(q10, ['O', 'X'], a10)
    ];

    // 퀴즈 객체 생성
    var quiz = new Quiz(questions);

    // -----------------------------------------------------------------------------------
    // 문제 출력 함수
    function update_quiz(){
        var question = document.getElementById('question');
        var idx = quiz.questionIndex + 1;
        var choice = document.querySelectorAll('.btn');

        // 문제 출력
        question.innerHTML = '문제'+ idx + ') ' + quiz.questions[quiz.questionIndex].text;

        // 선택 항목 출력
        for(var i = 0; i < 2; i++){
            choice[i].innerHTML = quiz.questions[quiz.questionIndex].choice[i];
        }

        progress();
    }


    // 문제 진행 정보 표시(현재 문제 번호/총 문항수)
    function progress(){
        var progress = document.getElementById('progress');
        progress.innerHTML = '문제 ' + (quiz.questionIndex+1) + ' / ' + quiz.questions.length;
    }

    // -----------------------------------------------------------------------------------
    // 결과 표시
    function result(){
        var quiz_div = document.getElementById('quiz');	// 퀴즈 박스 컨테이너

        // 백분률 점수 계산
        var per = parseInt((quiz.score*100) / quiz.questions.length);

        // 표시할 텍스트 정보와 변수
        var txt =	'<h1>결과</h1>' +
            '<h2 id="score"> 당신의 점수: ' + quiz.score + '/' +
            quiz.questions.length + '<br><br>' + per + '점</h2>';

        quiz_div.innerHTML = txt;	// 결과 텍스트 출력

        // 점수 별 결과 텍스트 출력
        if(per < 60){
            txt += '<h2 style="color:red">좀더 분발하세요</h2>';
            quiz_div.innerHTML = txt;
        } else if(per >= 60 && per < 80){
            txt += '<h2 style="color:red">무난한 점수네요</h2>';
            quiz_div.innerHTML = txt;
        } else if(per >= 80){
            txt += '<h2 style="color:red">훌륭합니다</h2>'
            quiz_div.innerHTML = txt;
        }
    } // end result


    // -----------------------------------------------------------------------------------
    var btn = document.querySelectorAll('.btn');	// .btn 객체

    // 입력 및 정답 확인 함수
    function checkAnswer(i){
        // 선택버튼(.btn) 이벤트 리스너
        btn[i].addEventListener('click', function(){
            var answer = btn[i].innerText;

            if(quiz.correctAnswer(answer)){
                alert('정답입니다!');
                quiz.score++;
            } else{ alert('틀렸습니다!'); }

            // 다음 문제로 진행 및 결과 처리
            if(quiz.questionIndex < quiz.questions.length-1){
                quiz.questionIndex++;
                update_quiz();
            } else {
                // 결과 화면
                result();
            }
        });
    } // end checkAnswer

    // 4개의 버튼 이벤트리스너 지정
    for(var i = 0; i < btn.length; i++){
        checkAnswer(i);
    }

    update_quiz();

</script>


<%--<script src="../../../resources/js/app.js"></script>--%>

</body>
</html>
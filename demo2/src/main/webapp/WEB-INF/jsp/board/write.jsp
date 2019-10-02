<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<html>
<head>
    <title>글 쓰기</title>
    
    <style type="text/css">
        #title{
            height : 30;
            width : 700;
            font-family :'돋움';
            font-size : 20;
            text-align :center;
        }
        #content{
            height :700;
            width : 700;
            font-family :'돋움';
            font-size : 20;
            text-align :center;
        }
    </style>

</head>
<body>
    <form method="post" id="authForm" action = "http://localhost:8080/board/write-action">
        <div>
            <label for="title">제목</label>
            <input type="text " id="title" name="title" placeholder="title" required>
            <br>
            <br>
            <label for="content">내용</label>
            <input type="text" id="content" name="content" placeholder="content" required>
        </div>
        <button type="submit" >저장</button><br><br>
    </form>
</body>
</html>


    
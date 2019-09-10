<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JK Tutorials</title>
    </head>

    <body>
       <form method="post" id="authForm" action = "http://localhost:8080/input">
        <div>
            <label for="name">이름</label>
            <input type="text " id="name" name="name" placeholder="name" >
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" placeholder="email" >
        </div>
        <button type="submit" >저장</button><br><br>
       </form>
        <form method = "post" action = "http://localhost:8080/query">
        <div>
          <label for="name">이름</label>  
            <input type="text " id="name" name="name" placeholder="name" >
        </div>
           <button type="submit">한 명만 조회</button><br><br>
        </form>
        <form method = "post" action = "http://localhost:8080/queryAll">
          <button type="submit">모두 조회</button>
        </form>
    </body>

    </html>
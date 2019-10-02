<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
   <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <style>
            #notice{
                text-align: center;
                padding-top: 250px;
            }
        </style>
        <title>성공</title>
    </head>

    <body>
    <div id = "notice">
    <label>
       <h1>성공</h1>
       <input Type = button value = 확인 onClick="location.href='http://localhost:8080/board/list?pageNum=1'">
    </label>
    </div>
    </body>

    </html>

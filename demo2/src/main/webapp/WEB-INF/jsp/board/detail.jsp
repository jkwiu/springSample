<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>글 내용 확인</title>
    
        <style type="text/css">
            #wrap {
                width: 800px;
                margin: 0 auto 0 auto;
            }
            #topForm{
                text-align: right;
            }
            #board, #pageForm, #searchForm{
                text-align: center;
            }            
            #bList{
                text-align: center;
            }
        </style>

</head>
<body>   
 
<div id="wrap">
    <br>
        <div id="topForm">
                <input type="button" value="목록"" onclick="window.location.href='http://localhost:8080/board/list?pageNum=1'">
        </div>
    <br>
        <div id="board">
        <table BORDER="1" CELLPADDING="3" CELLSPACING="1">
            <tr>
                <th width="200">제 목</TH>
                <td width = " 2000">
                    ${queryOne.title}
                </td>
            </tr>
            <tr>
                <th>내 용</th>
                <td height = "500">
                    ${queryOne.content}
                </td>
            </tr>
            <tr>
                <th>조회수</th>
                <td width = " 2000">
                    ${queryOne.count}
                </td>
            </tr>
            <tr>
                <th>작성일</th>
                <td width = " 2000">
                    ${queryOne.regDate}
                </td>
            </tr>
        </table>
        <br>
            <div style="text-align:right">
                <form method = "post" action = "http://localhost:8080/board/delete" style="display:inline">
                    <input name="no" type = "hidden" value="${queryOne.no}">
                    <input type="submit" value="삭제">
                </form>

                <form method = "post" action = "http://localhost:8080/board/modify" style="display:inline">
                    <input name="no" type = "hidden" value="${queryOne.no}">
                    <input type="submit" value="수정">
                </form>
            </div>
    </div>
</div>
    </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>전체 게시글</title>
    
    <style type="text/css">
        #head {
            font-size: 30px;
            padding-top: 20px;
            text-align: center;
        }
        #wrap {
            width: 800px;
            margin: 0 auto;
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
        #jk{
            text-align: center;
        }
    </style>
    <div id="head">
        <label>게 시 판</label>
    </div>
</head>
<body>   
 
<div id="wrap">
    <br>
    <div id="topForm">
        <input Type = button value = 목록 onClick="location.href='http://localhost:8080/board/list?pageNum=1'">
        <input type="button" value="글쓰기" onclick="window.location.href='http://localhost:8080/board/write'">
    </div>
    <br>
    <div id="board">
        <table class="content" BORDER="1" CELLPADDING="3" CELLSPACING="1">
        <tr>
            <th>번호</th>
            <th>제 목</th>
            <th>조회수</th>
            <th>작성일</th>
        </tr>
        <c:forEach items="${queryAll}" var="list" varStatus="status">
        <tr>
            <td width = "100" align=center valign=middle>
                ${list.no}
            </td>
            <td width = "2000" style="padding-left:10px">
                <a href = "http://localhost:8080/board/detail?no=${list.no}"> ${list.title}</a>
            </td>
            <td width = "150" align=center valign=middle>
                ${list.count}
            </td>
            <td width = "300" align=center valign=middle>
                ${list.regDate}
            </td>
        </tr>
        </c:forEach>
        </table>
    </div>
</div>
<div class="jk" id="jk">
    <table class="pager" style="margin-left:auto; margin-right:auto;padding-top:10px;">
        <tr>
            <td>
                <a href = "http://localhost:8080/board/list?pageNum=1&value=${searchWord}">[맨 처음]</a>
            </td>
            <td>
                <a href = "http://localhost:8080/board/list?pageNum=${prePage}&value=${searchWord}">◀</a>
            </td>
            <c:forEach items="${pn}" var="pageNum" begin = "${startPageNum}" end = "${lastPageNum}">
            <td>
                    <c:choose>
                        <c:when test="${pageNum eq nextPage-1 && lastPageNum eq pageNum}">
                            <label style="font-size:30px;">${pageNum}</label>
                        </c:when>
                        <c:otherwise>
                            <a href = "http://localhost:8080/board/list?pageNum=${pageNum}&value=${searchWord}">${pageNum}</a>
                        </c:otherwise>
                    </c:choose>
            </td>
            </c:forEach>
            <td>
                <a href = "http://localhost:8080/board/list?pageNum=${nextPage}&value=${searchWord}">▶</a>
            </td>
            <td>
                <a href = "http://localhost:8080/board/list?pageNum=${lastPage}&value=${searchWord}">[맨 끝]</a>
            </td>
        </tr>
    </table>
    <form method = "post" action = "http://localhost:8080/board/list?pageNum=0" style="padding-top:10px">
        <label>제목+내용</label>  
        <input type="text" id="value" name="value" value="${searchWord}" >
        <input type="submit" value="검색">
    </form>
</div>
</body>
</html>
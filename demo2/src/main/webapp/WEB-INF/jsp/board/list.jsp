<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>전체 게시글</title>
    
    <style type="text/css">
        #wrap {
            width: 800px;
            margin: 0 auto 0 auto;
        }
        #topForm{
            text-align :right;
        }
        #board, #pageForm, #searchForm{
            text-align :center;
        }
        
        #bList{
            text-align :center;
        }
    </style>

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
     <TABLE BORDER="1" CELLPADDING="3" CELLSPACING="1">
     <TR>
        <TH>No.</TH>
        <TH>TITLE</TH>
        <TH>COUNT</TH>
        <TH>REG-DATE</TH>
    </TR>
      <c:forEach items="${queryAll}" var="list" varStatus="status">
      <TR>
        <TD width = "50">
            ${list.no}
         </TD>
        <TD width = " 2000">
           <a href = "http://localhost:8080/board/detail?no=${list.no}"> ${list.title}</a>
        </TD>

        <TD width = "50">
            ${list.count}
        </TD>
        <TD width = "300">
            ${list.regDate}
        </TD>
    </TR>
    </c:forEach>
      </TABLE>
    </div>
    </form>
</div>
    <TABLE>
        <TD>
            <TR>
                <%-- 첫 페이지로 이동 --%>
                <a href = "http://localhost:8080/board/list?pageNum=1">[맨 처음]</a>
            </TR>
            <TR>
                <%-- 이전 페이지로 이동 --%>
                <a href = "http://localhost:8080/board/list?pageNum=${prePage}">◀</a>
            </TR>
        <TD>
    <c:forEach items="${pn}" var="pageNum" begin = "${startPageNum}" end = "${lastPageNum}">
        <TD>
            <TR>
                <a href = "http://localhost:8080/board/list?pageNum=${pageNum}">${pageNum} </a>
            </TR>
        </TD>
    </c:forEach>
        <TD>
            <TR>
                <%-- 다음 페이지로 이동 --%>
                <a href = "http://localhost:8080/board/list?pageNum=${nextPage}">▶</a>
            </TR>
            <TR>
                <%-- 맨 끝으로 이동 --%>
                <a href = "http://localhost:8080/board/list?pageNum=${lastPageNumber}">[맨 끝]</a>
            </TR>
        <TD>
    </TABLE>
    <form method = "post" action = "http://localhost:8080/board/list?pageNum=0">
        <label>제목+내용</label>  
        <input type="text" id="value" name="value" >
        <input type="submit" value="검색">
    </form>

 
</body>
</html>


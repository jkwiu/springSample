<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
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
            <input type="button" value="글쓰기" onclick="window.location.href='http://localhost:8080/write'">
    </div>
    <br>
    <div id="board">
        <table id="bList" width="800" border="3" bordercolor="lightgray">
            <tr heigh="30">
                <td>글번호</td>
                <td>제목</td>
                <td>작성일</td>
                <td>조회수</td>
            </tr>    
            <tr>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
            </tr>
        </table>
    </div>
    <div id = "searchForm">
        <form method="post" action="http://localhost:8080/query" >
            <div>
                <label for = "No">No</label>
                <input type="int" id="No" name="No" placeholder="input Number Order"/>
                
            </div>
            <button type = "submit">조회</button>
        </form>
    </div>
</div>    
 
</body>
</html>


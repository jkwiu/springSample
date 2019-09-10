<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
   <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JK Tutorials</title>
    </head>

    <body>

    <TABLE BORDER="1" CELLPADDING="3" CELLSPACING="1">
     <TR>
        <TH>No.</TH>
        <TH>NAME</TH>
        <TH>E-MAIL</TH>
    </TR>
      <c:forEach items="${who}" var="list" varStatus="status">
      <TR>
         <TD>
            ${status.count}
         </TD>
        <TD>
            ${list.name}
        </TD>
        <TD>
            ${list.email}
        </TD>
    </TR>
      </c:forEach>
      </TABLE>

       <h1>성공</h1>
    </body>

    </html>
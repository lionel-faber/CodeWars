<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>
<title>Code Wars</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="prism.css">
    <script type="text/javascript" src="jquery-2.0.3.js"></script>
    <script type="text/javascript" src="jquery.countdownTimer.js"></script>
    <link rel="stylesheet" type="text/css" href="jquery.countdownTimer.css" >
    <script type="text/javascript" src="js.js"></script>
</head>
<body>
    <div id ="header">
        <!--div id="logo">
            <%-- Add logo here --%>
        </div-->
        <h1><center>
            Code Wars Prelims
        </center></h1>

    </div>
    <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/quiz"
    user="root" password="password"/>
    <sql:query dataSource="${snapshot}" var="result">
        SELECT * from question order by RAND();
    </sql:query>
    <form action="servlet_tier1" method="post">
        <div id="form_block">
            <div id="form1">
               <b>Name:</b><input type="text" name="name" size="30">
            </div>
            <br>
            <div id="form2">
               <b>Phone Number:</b><input type="text" name="phone_no">
            </div>  
         </div>
         <span id="ms_timer"></span>
         <script>
		$(function(){
					  $('#ms_timer').countdowntimer({
						  minutes :20,
						  seconds : 10,
						  size : "lg",
						  timeUp : timeisUp,

					  });

			 function timeisUp() {
				 alert("Times up")
				 $("#inp").trigger("click");
        }

			});
        </script>
         <div id="box">
        <c:forEach var="row" items="${result.rows}">
        <br>
            <font size="4">
            <b><pre><c:out value="${row.q_data}"/>
                </pre></b>
            </font><br>
            <input type="radio" name="group${row.q_no}" value="e" checked="checked" style="display: none">
            <input type="radio" name="answ${row.q_no}" value="${row.crct_optn}" checked="checked" style="display: none">
            <input type="radio" name="group${row.q_no}" value="a">
            <c:out value="${row.option_a}"/><br>
            <input type="radio" name="group${row.q_no}" value="b">
            <c:out value="${row.option_b}"/><br>
            <input type="radio" name="group${row.q_no}" value="c">
            <c:out value="${row.option_c}"/><br>
            <input type="radio" name="group${row.q_no}" value="d">
            <c:out value="${row.option_d}"/><br>
    </c:forEach>
    <center>
        <input id="inp" type ="submit" value ="Submit"><br><br>
    </center>
     <script src="prism.js"></script>
    </div>
</form>
</body>
</html> 

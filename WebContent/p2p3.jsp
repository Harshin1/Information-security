<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>

header {
border-radius:4px;
  background-color: #5bc5a7;
  color: #fff;
  text-shadow: 0 -1px 0 #39a385;
  font-size: 30px;
  width: auto;
  padding: 10px;
  margin: 0 auto; 
  text-align: center;
}
body {
  background-color: #ffffcc
}
nav {
  list-style: none;
      text-align: center;
      margin: 20px;
}
nav a {
border-radius:4px;
  background-color: #ff652f;
       font-family: 'Oswald', sans-serif;
      font-size: 20px;
       padding: 10px;
            text-decoration: none;
            color: #fff;
}
#home_form {
 text-align: left;
 padding-top: 50px;
 padding-left:200px;
}
#auth
{
border-radius:6px;
border:none;
background-color: #ff652f;
 font-family: Verdana, Geneva, sans-serif;
font-size: 15px;
text-decoration: none;
color: #fff;
}
p {
  font-size: 20px;
}

input
{
border-color:#5bc5a7
}
nav a:hover {
  text-decoration: underline;
}
button{
border-radius:6px;
border:none;
background-color: #ff652f;
font-family: sans-serif;
font-size: 18px;
text-decoration: none;
color: #fff;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>P2P</title>
</head>
<body>
<header>Peer to Peer File Transfer</header>
  <nav>
    <a href="p2p.jsp">Download Files</a>
      <a href="AccessViolate.jsp">Access Violation Details</a>
    
    <a href="Graph.jsp">Data Analysis</a>
  </nav>								
<form id="home_form"  action="${pageContext.request.contextPath}/ClientServlet" method="post">

<div id="peerlist" >

<p>${LowScoreError}</p>
<input name="feedback" />
<input  id="auth" name="save" type="submit" value="Submit Feedback"/>
</div>
</form>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  background-color: #ffffcc;
 
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
#my_form {
 text-align: center;
 padding-top: 100px;
}
#my_form1 {
 text-align: center;
 padding-top: 50px;
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
#auth
{
border-radius:6px;
border:none;
background-color: #ff652f;
font-family: sans-serif;
font-size: 18px;
text-decoration: none;
color: #fff;
}
button{
border-radius:6px;
border:none;
background-color: #ff652f;
font-family: sans-serif;
font-size: 18px;
text-decoration: none;
color: #fff;
}</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Analysis</title>
</head>
<body>
 <header>Peer to Peer File Transfer</header>
  <nav>
   <a href="p2p.jsp">Download Files</a>
      <a href="AccessViolate.jsp">Access Violation Details</a>
    
  
  </nav>

<form id="my_form" action="${pageContext.request.contextPath}/GraphServlet" method="post">
<p>Search for the files</p>
<input name="Graph" id="auth" type="submit"  value="Request Frequency Graph"/>


<p>Search for the files</p>
<input name="Graph2" id="auth" type="submit"  value="Dispatch Frequency Graph"/>


</form>
</body>
</html>
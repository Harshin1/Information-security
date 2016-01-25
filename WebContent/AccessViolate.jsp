<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@ page import="java.sql.*" %>


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
 padding-top: 20px;
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
}

table, td, th {
    border: 1px solid #5bc5a7;
    padding:10px;
}

th {
    background-color: #5bc5a7;
    color: white;
}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hack Privileges</title>
</head>
<body>
 <header>Peer to Peer File Transfer</header>
  <nav>
   <a href="p2p.jsp">Download Files</a>
      <a href="Graph.jsp">Data Analysis</a>
  
  </nav>

<form id="my_form" action="${pageContext.request.contextPath}/GraphServlet" method="post">
<p>List of requesters who tried to hack privileges given to them!!</p>

 <% Connection conn = DriverManager.getConnection
	   			   ("jdbc:mysql://infosecurity.ccepwwlnrdlg.us-west-2.rds.amazonaws.com:3306/infosecurity", "debuggers", "debuggers");
 Statement stmt = conn.createStatement(); 
 
	String query = "SELECT * FROM infosecurity.accesslist;";
	 ResultSet resultset = stmt.executeQuery(query);	  
 
 %>
  <TABLE  align="center">
      <TR>
                <TH>File Name</TH>
                <TH>Type of Modification</TH>
                <TH>Date & Time</TH>
                <TH>Peer Name</TH>
               
            </TR>
            <% while(resultset.next()){ %>
            <TR>
                <TD> <%= resultset.getString(1) %></td>
                <TD> <%= resultset.getString(2) %></TD>
                <TD> <%= resultset.getString(3) %></TD>
                <TD> <%= resultset.getString(4) %></TD>
              
            </TR>
            <% } %>
            </TABLE>


</form>
</body>
</html>
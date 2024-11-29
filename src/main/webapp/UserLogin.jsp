<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link href="CSS//login.css" rel="stylesheet" type="text/css">
    <link href="CSS/header.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="HomePage.jsp">Home</a></li>
                <li><a href="about.jsp">About Us</a></li>
               	<li><a href="gallery.html">Gallery</a></li>
                <li><a href="admin.jsp">Admin Login</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <%  
            String successMessage = (String) request.getAttribute("registerSuccess");
            if (successMessage != null) { 
        %>
            <div style='color: green;'>
                <%= successMessage %>
            </div>
        <% 
            }
            String loginError = (String) request.getAttribute("message");
            if (loginError != null) { 
        %>
            <div style='color: red;'>
                <%= loginError %>
            </div>
        <% 
            }
        %>
        <h1>User Login</h1>

        <form action="LoginServlet" method="POST">
            <div class="form-group">
                <label for="number">Mobile number:</label> 
                <input type="text" id="num" name="number">
            </div>
            <div class="form-group">
                <label for="password">Password:</label> 
                <input type="text" name="password">
            </div>
            <input type="submit" value="Login">
            <h4>Don't have an account?</h4>
            <a href="createAccount.jsp" style="color: blue;">Click here to Register</a>
        </form>
    </div>
</body>
</html>

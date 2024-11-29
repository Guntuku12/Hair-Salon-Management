<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Account</title>
    <link href="CSS/createAccount.css" rel="stylesheet" type="text/css">
    <link href="CSS/header.css" rel="stylesheet" type="text/css"> 
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="HomePage.jsp">Home</a></li>
                <li><a href="about.jsp">About Us</a></li>
                <li><a href="UserLogin.jsp">User Login</a></li>
               	<li><a href="gallery.html">Gallery</a></li>
                <li><a href="admin.jsp">Admin Login</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2 style="text-align:center; margin-top: 10px;">Your beauty is our passion! Get registered for more.</h2> <!-- Moved h2 tag -->
        <h1>Create Account</h1>
      
        
        <% if (request.getAttribute("emailError") != null) { %>
    <p style="color: red;"><%= request.getAttribute("emailError") %></p>
<% } %>

<% if (request.getAttribute("mobileError") != null) { %>
    <p style="color: red;"><%= request.getAttribute("mobileError") %></p>
<% } %>

<% if (request.getAttribute("adharError") != null) { %>
    <p style="color: red;"><%= request.getAttribute("adharError") %></p>
<% } %>

<% if (request.getAttribute("duplicateError") != null) { %>
    <p style="color: red;"><%= request.getAttribute("duplicateError") %></p>
<% } %>

<% if (request.getAttribute("registerSuccess") != null) { %>
    <p style="color: green;"><%= request.getAttribute("registerSuccess") %></p>
<% } %>

<% if (request.getAttribute("registerError") != null) { %>
    <p style="color: red;"><%= request.getAttribute("registerError") %></p>
<% } %>
        
        <form action="userRegister" method="POST">
            <div class="form-group">
                <label for="fname">First Name:</label> 
                <input type="text" name="fname" required>
            </div>
            <div class="form-group">
                <label for="lname">Last Name:</label> 
                <input type="text" name="lname" required>
            </div>
            <div class="form-group">
                <label for="phone">Contact:</label> 
                <input type="text" name="phone" required>
            </div>
            <div class="form-group">
                <label for="mail">Email Address:</label> 
                <input type="text" name="mail" required>
            </div>
            <div class="form-group">
                <label for="age">Age:</label>
                <input type="text" name="age" placeholder="Age must be > 0" required>
            </div>
               <div class="form-group">
                <label for="adhar">Adhaar Number :</label>
                <input type="text" name="adhar" required>
            </div>
             <div class="form-group">
                <label for=place >Place:</label>
                <input type="text" name="place" required>
            </div>
            <div class="form-group">
                <label for="password"> Set Password:</label> 
                <input type="text" name="password" placeholder="Password must be 8 to 14 characters" required>
            </div>
            <input type="submit" value="Register">
        </form>
    </div>
    <footer>
        <p>&copy; 2024 Rudra Hair Saloon. All rights reserved.</p>
    </footer>
</body>
</html>

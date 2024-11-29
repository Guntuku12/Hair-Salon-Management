<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <style>
        body {
            background: linear-gradient(rgba(255, 54, 23, 0.7), rgba(255, 100, 255, 0.7));
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
            width: 300px;
             text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
             text-align: left;
        }
        input[type="text"], select {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            margin-bottom: 15px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        h1 {
            margin: 20px 0;
       
    </style>
</head>
<body>
   <div class="container"> 
   
        <h1>Admin Login</h1>
        <% String adminError=(String)request.getAttribute("adminError");
        if(adminError!=null){%>
         <div style='color:red;'> <%= adminError %> </div>
        <%
            } 
        %>
        <form action="AdminServlet" method="POST">
          <input type="hidden" name="action" value="login">
            <div class="form-group">
                <label for="number">Mobile number:</label>
                <input type="text" id="num" name="number">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="text" name="password">
            </div>
            <input type="submit" value="Login">
            
        </form>
    </div>
</body>
</html>

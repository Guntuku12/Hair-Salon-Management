<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Queries</title>
    <style>
     header {
            background-color: #333;
            color: #fff;
            padding: 13px 0;
            width: 100%;
        }
        nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 20px;
        }
        nav ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            align-items: center;
        }
        nav ul li {
            margin-right: 15px;
        }
        nav ul li a {
            color: white;
            font-family: Sans-serif;
            background-color: #666;
            border: 3px solid #ddd;
            border-radius: 100px;
            box-shadow: 2px 10px 10px rgba(0, 0, 0, 0.1);
            cursor: pointer;
            padding: 8px 16px;
            text-align: left;
            text-decoration: none;
        }
        .navbar-heading {
            color: #ff00ff;
            font-size: 1.5em;
            font-weight: bold;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .small-column {
            width: 15%;
        }
        .large-column {
            width: 65%;
            word-wrap: break-word;
            vertical-align: top;
        }
        .time-column {
            width: 15%;
        }
    </style>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Admin Dashboard</a></li>
            </ul>
            <h3 class="navbar-heading">Contacted Queries</h3>
        </nav>
    </header>

    <table border="1">
        <tr>
            <th class="small-column">Name</th>
            <th class="small-column">Email</th>
            <th class="time-column">Time</th>
            <th class="large-column">Message</th>
            <th class="large-column">Reply</th>
        </tr>

        <%
            List<Map<String, String>> queries = (List<Map<String, String>>) request.getAttribute("queries");
            if (queries != null && !queries.isEmpty()) {
                for (Map<String, String> query : queries) {
                    String status = query.get("status");
        %>
        <tr>
            <td><%= query.get("name") %></td>
            <td><%= query.get("email") %></td>
            <td><%= query.get("date_time") %></td>
            <td><%= query.get("message") %></td>


            <td>
                <% 
                    if ("unread".equalsIgnoreCase(status)) {
                %> <form action="submitReply" method="post">
                        <!-- Reply input field -->
                        <input type="text" name="reply" placeholder="Enter your reply">

                        <!-- Hidden fields to capture email and name -->
                        <input type="hidden" name="email" value="<%= query.get("email") %>">
                        <input type="hidden" name="name" value="<%= query.get("name") %>">

                        <!-- Submit button for sending the reply -->
                        <input type="submit" name="send" value="Send Reply">
                    </form>
                <% 
                    } else if ("read".equalsIgnoreCase(status)) {
                %>
                    <!-- If status is read, show "Replied" text -->
                    Replied
                <% 
                    }
                %>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" style="text-align: center;">No queries available</td>
        </tr>
        <%
            }
        %>
    
    </table>
</body>
</html>

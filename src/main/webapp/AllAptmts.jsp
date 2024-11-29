<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Appointments</title>
    <link href="CSS/header.css" rel="stylesheet" type="text/css">
    <style>
        h1 { text-align: center; color: #333; }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th { background-color: #f2f2f2; }
        tr:hover { background-color: #f1f1f1; }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
        }
        button:hover { background-color: #45a049; }
    </style>
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="adminDashboard.jsp">Back to Admin Page</a></li>
            </ul>
        </nav>
    </header>

    <h1> All Appointment List</h1>

    <%
        List<String[]> appointments = (List<String[]>) request.getAttribute("appointments");/*getting requested All Appointments from allAppointment.java*/
        if (appointments == null) {
    %>
        <p>Error retrieving appointments. Please try again later.</p>
    <%
        } else if (appointments.isEmpty()) {
    %>
        <p>No confirmed appointments found.</p>
    <%
        } else {
    %>
        <table>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Place</th>
                <th>Haircut</th>
                <th>Cost</th>
                <th>Status</th>
                <th>Time</th>
            </tr>
            <%
                for (String[] details : appointments) {
            %>
            <tr>
                <td><%= details[0] %></td>
                <td><%= details[1] %></td>
                <td><%= details[2] %></td>
                <td><%= details[3] %></td>
                <td><%= details[4] %></td>
                <td><%= details[5] %></td>
                <td><%= details[6] %></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        }
    %>
</body>
</html>

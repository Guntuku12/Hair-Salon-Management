<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>

<html>
<head>
<link href="CSS/header.css" rel="stylesheet" type="text/css">
<title>Appointments</title>
<style>
h1{ text-align: center; 
    color: #333; }
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

th {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #f1f1f1;
}

button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 8px 12px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}
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

	<h1>Appointments Done List</h1>

	<%
        List<String[]> appointments = (List<String[]>) request.getAttribute("appointments");
        if (appointments == null) {
    %>
	<p>Error retrieving appointments. Please try again later.</p>
	<%
        } else if (appointments.isEmpty()) {
    %>
	<p>No pending appointments Requests found.</p>
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
			
			<th>Time</th>
			<th>Status</th>
			
		</tr>
		<%
                for (String[] details : appointments) {
                    String name = details[0];
                    String email = details[1];
                    String place = details[2];
                    String haircut = details[3];
                    String cost = details[4];
                    
                    String time = details[5];
            %>
		<tr>
			<td><%= name %></td>
			<td><%= email %></td>
			<td><%= place %></td>
			<td><%= haircut %></td>
			<td><%= cost %></td>
			<td><%= time %></td>
		<td> Service done </td>

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

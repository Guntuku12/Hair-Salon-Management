<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<link href="CSS/adminDash.css" rel="stylesheet" type="text/css">

</head>
<body>
 <script type="text/javascript">
        <% 
            String responseMessage = (String) request.getAttribute("responseMessage");
            if (responseMessage != null && !responseMessage.isEmpty()) {
        %>
            alert('<%= responseMessage %>');
        <% 
            }
        %>
    </script>
 <div class="sidebar">
        <a href="#" onClick="scrollToChange()">Change Password</a>
        <a href="#" onClick="scrollToQuery()">Queries</a> 
        <a href="ServiceCompletionList">Service Completion List</a>
        
        <a href="HomePage.jsp">Logout</a>
    </div>

    <!-- Main Content Section -->
    <div class="main-content">
        <div class="navbar">
            <div class="navbar-row">
         <a href="RetrieveAppointments">Appointment Request</a>

                <a href="confirmedAptmts">Confirmed Appointments</a>
            </div>
            <div class="navbar-row">
                <a href="allAppointments">All Appointments</a>  
                
            </div>
        </div>
          <br> <br><br><h3> This Website Desgined by Sanjay Guntuku &copy; 2024, All Rights Reserved </h3>
    </div>
    <script>
    
        function scrollToQuery() {
            const queries = document.getElementsByClassName('query'); 
            if (queries.length > 0) {
                queries[0].style.display = 'block'; 
                queries[0].scrollIntoView({
                    behavior: 'smooth' 
                });
            }
        }

        function scrollToChange() {
            const passwordSection = document.getElementById('passwordSection');
            passwordSection.style.display = 'block'; 
            passwordSection.scrollIntoView({
                behavior: 'smooth'
            });
        }

        function hideSection(sectionId) {
            document.getElementById(sectionId).style.display = 'none';
        }
    </script>
    
    <!-- Password Section -->
    <section id="passwordSection" style="display: none;">
        <form action="AdminServlet" method="post">
            <input type="hidden" name="action" value="passwordChange">
            <div>
                <label for="old_pass">Old Password:</label>
                <input type="text" id="old_pass" name="old_pass" required>
            </div>
            <div>
                <label for="new_pass">New Password:</label>
                <input type="text" id="new_pass" name="new_pass" required>
            </div>
            <div>
                <label for="con_pass">Confirm Password:</label>
                <input type="text" id="con_pass" name="con_pass" required>
            </div>
            <button type="submit" onclick="hideSection('passwordSection')">Submit</button>
        </form>
    </section>
    <!-- Query Section -->
   <section class="query">
     <button onclick="window.location.href='queryGet?status=unread'">Unread Queries</button><!-- sending to getQueryContact Sevlet -->
    <button onclick="window.location.href='queryGet?status=read'">Read Queries</button> 
</section>
</body>
</html>
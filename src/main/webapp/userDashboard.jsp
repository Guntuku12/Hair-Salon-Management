<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Dashboard</title>
    <link href="CSS/UserD.css" rel="stylesheet" type="text/css">
    <link href="CSS/header.css" rel="stylesheet" type="text/css">
    <script>
        function showSection(sectionId) {
            document.getElementById('passwordSection').style.display = 'none';
            document.getElementById('mobileSection').style.display = 'none';
            const section = document.getElementById(sectionId);
            section.style.display = 'block';
            section.scrollIntoView({ behavior: 'smooth' });
        }
        
        function hideSection(sectionId) {
            document.getElementById(sectionId).style.display = 'none';
        }
    </script>
</head>
<body>
    <header>
        <nav>
            <ul>
               
            </ul>
        </nav>
    </header>

    <div class="content">
        <% String nm = (String) session.getAttribute("name"); if (nm != null) { %>
            <h1>Welcome <%= " " + nm %></h1>
        <% } %>
        <h5>We provide services by high-class professionals in our salon.</h5>
    </div>

    <div class="navbar">
        
        <a href="services.jsp">View Services</a>
        <a href="services.jsp">Book Appointment</a>
        <a href="myAppointments">My Appointments</a>
       
    </div>
<div class="sidebar">
        <button onclick="showSection('passwordSection')">Change Password</button>
        <button onclick="showSection('mobileSection')">Change Mobile</button>
 <button onclick="window.location.href='UserLogout'">Logout</button>
    </div>
    <div class="information">
        <h4>Meet our experts to get valuable hair and beauty tips at our salon.<br>Experience personalized consultations and discover the best treatments tailored just for you!</h4>
    </div>

    <section id="passwordSection" style="display: none; width: 500px;">
        <form action="userUpdateServlet" method="post">
            <input type="hidden" name="action" value="passwordUpdate">
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
    
    <!-- Mobile Change Section -->
    <section id="mobileSection" style="display: none; width: 500px;">
        <form action="userUpdateServlet" method="post">
            <input type="hidden" name="action" value="mobileUpdate">
            <div>
                <label for="old_mob">Old Mobile Number:</label>
                <input type="text" id="old_mob" name="old_mob" required>
            </div>
            <div>
                <label for="new_mob">New Mobile Number:</label>
                <input type="text" id="new_mob" name="new_mob" required>
            </div>
            <button type="submit" onclick="hideSection('mobileSection')">Submit</button>
        </form>
    </section>
    <footer>
        <p>&copy; 2024 Rudra Hair Saloon. All rights reserved.</p>
    </footer>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Online Salon Application</title>
<link href="CSS/HomePage.css" rel="stylesheet" type="text/css">
<link href="CSS/header.css" rel="stylesheet" type="text/css">
<!-- calling symbol -->
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
   <header>
        <nav>
            <ul>
                <li><a href="HomePage.jsp">Home</a></li>
                <li><a href="about.jsp">About Us</a></li>
                <li><a href="UserLogin.jsp">User Login</a></li>
                <li><a href="gallery.html">Gallery</a></li>
                <li><a href="#" onclick="scrollToContact()">Contact Us</a></li>
                <li><a href="admin.jsp">Admin Login</a></li>
            </ul>
        </nav>
         <div class="contact-info">
        <i class="fas fa-phone"></i> 
        <span class="contact-number">+91 9010225296</span>
    </div>
   </header>
    <h1 id="welcome">Welcome to Rudra Hair Salon!</h1>
    
    <div class="description">Your beauty is our passion. Explore our services and meet our talented workers.</div>
    <div class="contact-info">
        <div class="time">
            <h4>Salon Timings:</h4>
            <p>
           <time>10:00</time> to <time>20:00</time> on Mon-Fri
            </p>
            <p>
            <time>8:30</time> to <time>22:30</time> on Sat & Sun
            </p>
        </div>
        <div class="address">
            <h4>Address:</h4>
            <p>
                Rudra Hair Salon, 1-56<br>
                Road No.1,Jawahar Colony<br>
                Chandanagar, Hyderabad, 500050<br>
                India
            </p>
        </div>
        <div class="map">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3805.211106955694!2d78.31569537369214!3d17.497433199628517!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3bcb9297af79a471%3A0xdeb37c3c57dbd047!2s1%2F56B%2C%20Rd%20Number%201%2C%20Jawahar%20Colony%2C%20Chanda%20Nagar%2C%20Hyderabad%2C%20Telangana%20500050!5e0!3m2!1sen!2sin!4v1729995443933!5m2!1sen!2sin"
                    width="600" height="450" style="border: 0;" allowfullscreen=""
                    loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>
    </div>

    <section id="contact">
        <h2>Contact Us</h2>
        
        <form action="ContactServlet" method="post" >
            <div>
                <label for="name">Your Name:</label> 
                <input type="text" id="name" name="name" required>
            </div>
            <div>
                <label for="mail">Email:</label> 
                <input type="text" id="mail" name="email" required>
            </div>
           
            <div>
                <label for="body">Message:</label>
                <textarea id="body" placeholder="Write your query" name="body" required></textarea>
            </div>
            <button type="submit" onClick="hideContactSection()">Submit</button>
        </form>
    </section>
    <footer>
        <p>&copy; 2024 Rudra Hair Salon. All rights reserved</p>
    </footer>
    
    <script>
        function scrollToContact() {
            const contactSection = document.getElementById('contact');
            contactSection.style.display = 'block';
            contactSection.scrollIntoView({
                behavior: 'smooth'
            });
        }  
        function hideContactSection() {
            
            document.getElementById('contact').style.display = 'none';
        }
    </script>
</body>
</html>

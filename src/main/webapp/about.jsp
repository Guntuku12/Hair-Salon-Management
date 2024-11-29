<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>About</title>
    <link href="CSS/header.css" rel="stylesheet" type="text/css">
    <style>
        body {
            display: flex;
            flex-direction: column;
            background-image: linear-gradient(to left top, #1bcb18, #56d461, #81db92, #ace1bc, #dbe3de);
            min-height: 100vh; 
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0; 
            color: #333;
            overflow-y: auto; 
        }
        .container {
            display: flex; 
            max-width: 1200px;
            margin: 20px auto; 
            padding: 15px;  
            background-color: linear-gradient(to left top, #1bcb18, #56d461, #81db92, #ace1bc, #dbe3de);
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            flex-grow: 1; 
        }

        .content {
            flex: 1; 
            margin-right: 20px; 
        }

        .address {
            width: 300px; 
            background-image: linear-gradient(to bottom, #cad8ca, #d1e0d3, #d9e7dc, #e0efe5, #e8f7ee);
            padding: 10px;  
            border-radius: 5px;
            box-shadow: rgba(0, 0, 0, 0.2);
        }

        h4 {
           color: #A52A2A;
            margin-bottom: 10px;
        }

        p {
            font-size: 16px;
            line-height: 1.6;
            margin: 0 0 10px 0; 
        }

        .image {
            display: flex;
            flex-direction: column; 
            align-items: flex-start;
            margin-top: 30px; 
        }

        .image-container {
            display: flex; 
            align-items: center; 
            margin-bottom: 20px; 
            margin-left: 20px; 
        }
        .image img {
            width: 250px;
            height: 300px;
            object-fit: cover;
            border-radius: 5px;
        }
        .image-description {
            font-size: 18px; 
            color: #555;
            margin-left: 130px; 
            height: 260px; 
            display: flex; 
            align-items: center; 
        }

        .experts-heading {
            text-align: center;
            color: black;
            margin: 20px auto; 
            font-size: 24px; 
            position: relative; 
            z-index: 1; 
            background: transparent;
        }

        footer {
            text-align: center;
            padding: 10px 0;
            background-color: #333;
            color: #FFD700;
        }
    </style>
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
        <div class="content">
            <h4>Looking for a barber or a Hairstylist specialized in taking care of your hair and Skin in HYDERABAD?</h4>
            <p>RUDRA welcomes you for a unique and unforgettable salon experience.</p>
            <p>RUDRA offers solutions and the latest trends in haircuts, colour, hair care, and styling for a total look.</p>
            <p>We also provide Facial Massage.</p>
            <p>Trust the professional expertise of your hairdresser to achieve the best look for you.</p>
        </div>
        <div class="address">
            <h4>Address:</h4>
            <p>
                Rudra Hair Salon<br>
                1-56, Road No.1<br>
                Jawahar Colony<br>
                Chandanagar, Hyderabad, 500050<br>
                India
            </p>
        </div>
    </div>

    <h2 class="experts-heading">Meet Our Experts</h2> 

    <div class="image">
        <div class="image-container">
            <img src="Images/alim.jpeg" alt="Expert Alim">
            <p class="image-description">Expert Alim - Specializes in men's haircuts and styling. Offering a diverse range of looks for every age group.<br> His talent lies in creating attractive hairstyles that complement your features, along with expertly crafted beard styles that enhance your overall appearance. Whether you're looking for a classic cut or a modern trend, <br>Alim has the skills to deliver a polished look that suits your individuality.</p>
        </div>
        <div class="image-container">
            <img src="Images/jaweed.jpeg" alt="Expert Jaweed">
            <p class="image-description">Expert Jaweed - Renowned for his trendy hair color and innovative techniques. <br>He brings a fresh perspective to hair styling. With a deep understanding of color theory, he expertly applies the latest trends to create stunning looks tailored to your unique features.<br> Jaweed not only focuses on aesthetics; he also emphasizes hair health, offering personalized solutions to keep your hair vibrant, healthy, and dandruff-free. <br>His expertise extends to recommending treatments that nourish your hair, ensuring it remains strong and radiant. <br>Trust Jaweed to transform your look while prioritizing the health and vitality of your hair.</p>
        </div>
        <div class="image-container">
            <img src="Images/rudra.jpeg" alt="Expert Rudra">
            <p class="image-description">Expert Rudra-A specialist in grooming hairstyles& Facial looks  for special occasions,<br> he possesses an exceptional talent for creating looks that elevate your overall appearance. In addition to his hairstyling expertise,<br> he offers rejuvenating facials designed to enhance your complexion, brighten your skin, and highlight your best features<br> keeping your skin healthy he also give the advises to maintain your skin healthy and bright always.. <br>Trust Rudra to make you look and feel your absolute best for any event.</p>
        </div>
        <div class="image-container">
            <img src="Images/saloonName.jpeg" alt="Salon Image">
            <p class="image-description">Rudra Hair Salon - Your destination for premium hair care and prosthetic looks..<br>
            Book an appointment and achieve the looks you've always promised yourself!</p>
        </div>
    </div>
    <footer>
        <p>&copy; 2024 Rudra Hair Saloon. All rights reserved</p>
    </footer>
</body>
</html>

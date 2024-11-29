<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Services List</title>
<link href="CSS/header.css" rel="stylesheet" type="text/css">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
}
.container {
	width: 100%;
	margin: 20px auto;
}

.row {
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: #fff;
	margin-bottom: 10px;
	padding: 10px;
	border-radius: 5px;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.column {
	flex: 1;
	text-align: center;
	padding: 10px;
}

.column img {
	max-width: 100px;
	border-radius: 5px;
}

.button {
	background-color: #28a745;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	onClick
	="UserDashboard.jsp";
}

.button:hover {
	background-color: #218838;
}

#price {
	font-weight: bold;
	color: #333;
}

#price:before {
	content: "\20B9";
}

.modal {
    display: none; 
    position: fixed; 
    z-index: 1; 
    left: 0;
    top: 0;
    width: 100%; 
    height: 100%; 
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.7);
}


.modal-content {
    background-color: #fff;
    margin: 15% auto; 
    padding: 20px;
    border: 1px solid #888;
    width: 90%; 
    max-width: 400px;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3); 
}

h2 {
    text-align: center; 
    color: #333; 
}

.input-container {
    display: flex; 
    justify-content: center; 
    margin: 15px 0; 
}

/* Labels */
label {
    margin: 0 10px 5px; 
    font-weight: bold; 
}

/* Input fields */
input[type="date"],
input[type="time"] {
    width: 60%; 
    padding: 10px;
    margin: 7px; 
    border: 1px solid #ccc;
    border-radius: 5px; 
    font-size: 20px; 
}

/* Button */
button {
    width: 100%; 
    background-color: #4CAF50; /* Green */
    color: white;
    padding: 10px;
    border: none;
    border-radius: 5px;
    font-size: 16px; /* Font size for button */
    cursor: pointer;
    
}
.close-btn{
font-size: font-size: 16px;}
</style>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li><a href="userDashboard.jsp">Back to User Page</a></li>
			</ul>
		</nav>
	</header>
	<form id="serviceForm" action="bookAppointment" method="post">
	<div class="container">
		<div class="row">
            <div class="column">1.</div>
            <div class="column">Simple HairCut for Boy</div>
            <div class="column">
                <img src="ServiceImages/simpleBoy.jpeg" alt="Image 1">
            </div>
            <div class="column" id="price">100.00</div>
            <div class="column">
                <input type="hidden" id="serviceName" name="serviceName">
                <input type="hidden" id="servicePrice" name="servicePrice">
                <button type="button" class="button" onclick="bookService('Simple HairCut for Boy', '100.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">2.</div>
			<div class="column">Deluxe HairCut for Boy</div>
			<div class="column">
				<img src="ServiceImages/stylishBoy.jpeg" alt="service Image 2">
			</div>
			<div class="column" id="price">150.00</div>
			 <div class="column">
                <input type="hidden" id="serviceName" name="serviceName">
                <input type="hidden" id="servicePrice" name="servicePrice">
                <button type="button" class="button" onclick="bookService('Deluxe HairCut for Boy', '150.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">3.</div>
			<div class="column">Simple Haircut for Men</div>
			<div class="column">
				<img src="ServiceImages/simpleMen.jpeg" alt="service Image 3">
			</div>
			<div class="column" id="price">150.00</div>
			<div class="column">
                <input type="hidden" id="serviceName" name="serviceName">
                <input type="hidden" id="servicePrice" name="servicePrice">
                <button type="button" class="button" onclick="bookService('Simple HairCut for Men', '150.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">4.</div>
			<div class="column">Deluxe Haircut for Men</div>
			<div class="column">
				<img src="ServiceImages/StylishMen.jpeg" alt="service Image 4">
			</div>
			<div class="column" id="price">200.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Stylish HairCut for Men">
				<input type="hidden" name="servicePrice" value="200.00">
				  <button type="button" class="button" onclick="bookService('Deluxe HairCut for Men', '200.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">5.</div>
			<div class="column">Beard Trim</div>
			<div class="column">
				<img src="ServiceImages/beard.jpeg" alt="service Image 4">
			</div>
			<div class="column" id="price">50.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Beard Trim">
				<input type="hidden" name="servicePrice" value="50.00">
				  <button type="button" class="button" onclick="bookService('Beard Trim', '50.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">6.</div>
			<div class="column">Hair Color for Men</div>
			<div class="column">
				<img src="ServiceImages/HairColor.jpeg" alt="service Image 4">
			</div>
			<div class="column" id="price">100.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Hair Color">
				<input type="hidden" name="servicePrice" value="100.00">
				  <button type="button" class="button" onclick="bookService('Hair color', '100.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">7.</div>
			<div class="column">Facial Spa</div>
			<div class="column">
				<img src="ServiceImages/SkinCare.jpeg" alt="service Image 4">
			</div>
			<div class="column" id="price">300.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Facial Spa">
				<input type="hidden" name="servicePrice" value="300.00">
				 <button type="button" class="button" onclick="bookService('Facial Spa', '300.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">8.</div>
			<div class="column">Hair Spa</div>
			<div class="column">
				<img src="ServiceImages/HairMassage.jpeg" alt="service Image 4">
			</div>
			<div class="column" id="price">250.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Hair Spa">
				<input type="hidden" name="servicePrice" value="250.00">
				 <button type="button" class="button" onclick="bookService('Hair Spa', '300.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">9.</div>
			<div class="column">Hair Straightening</div>
			<div class="column">
				<img src="ServiceImages/Hair Straight.jpeg" alt="service Image 4">
			</div>
			<div class="column" id="price">1000.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Hair Spa">
				<input type="hidden" name="servicePrice" value="250.00">
				 <button type="button" class="button" onclick="bookService('Hair Straightening', '1000.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">10.</div>
			<div class="column">Haircut+Beard</div>
			<div class="column" id="price">230.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Haircut+ Beard">
				<input type="hidden" name="servicePrice" value="230.00">
				 <button type="button" class="button" onclick="bookService('HairCut+Beard', '230.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">11.</div>
			<div class="column">Haircut+ Beard+ color</div>
			<div class="column" id="price">300.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Haircut+ Beard+ color">
				<input type="hidden" name="servicePrice" value="300.00">
				 <button type="button" class="button" onclick="bookService('Haircut+ Beard+ color', '300.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">12.</div>
			<div class="column">Haircut+ Beard+ Facial Spa</div>
			<div class="column" id="price">500.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Haircut+ Beard+ Facial Spa">
				<input type="hidden" name="servicePrice" value="500.00">
			 <button type="button" class="button" onclick="bookService('Haircut+ Beard+ Facial Spa', '500.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">13.</div>
			<div class="column">Haircut+ Beard+ Hair Spa</div>
			<div class="column" id="price">400.00</div>
		<div class="column">
				<input type="hidden" name="serviceName" value="Haircut+ Beard+ Hair Spa">
				<input type="hidden" name="servicePrice" value="400.00">
				 <button type="button" class="button" onclick="bookService('Haircut+ Beard+ Hair Spa', '400.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">14.</div>
			<div class="column">Haircut + Beard + Color+ Hair Spa</div>
			<div class="column" id="price">500.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="Haircut+ Beard+ Color+ Hair Treatment">
				<input type="hidden" name="servicePrice" value="500.00">
				 <button type="button" class="button" onclick="bookService('Haircut + Beard + Color+ Hair Spa', '500.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">15.</div>
			<div class="column">Hair Spa+ Skin Spa</div>
			<div class="column" id="price">500.00</div>
		<div class="column">
				<input type="hidden" name="serviceName" value="Hair Spa+ Skin Spa">
				<input type="hidden" name="servicePrice" value="500.00">
				 <button type="button" class="button" onclick="bookService('Hair Spa+ Skin Spa', '500.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">16.</div>
			<div class="column">All Services</div>
			<div class="column" id="price">800.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="All Services">
				<input type="hidden" name="servicePrice" value="800.00">
				 <button type="button" class="button" onclick="bookService('All Services', '800.00')">Book</button>
            </div>
		</div>
		<div class="row">
			<div class="column">17.</div>
			<div class="column">Special Occasion Hair Style</div>
			<div class="column" id="price">1200.00</div>
			<div class="column">
				<input type="hidden" name="serviceName" value="All Services">
				<input type="hidden" name="servicePrice" value="1200.00">
				 <button type="button" class="button" onclick="bookService('Special Occasion Hair Style', '1200.00')">Book</button>
            </div>
		</div>
	</div>	
	<div id="appointmentModal" class="modal" style="display:none;">
    <div class="modal-content">
      <button class="close-btn" onclick="redirectToServices()">×</button>
        <h2>Schedule Your Appointment</h2>
        <label for="appointmentDate">Date:</label>
        <input type="date" id="appointmentDate" name="appointmentDate" required>
        <label for="appointmentTime">Time:</label>
        <input type="time" id="appointmentTime" name="appointmentTime" required>
        <button type="button" onclick="submitAppointment()">Confirm Appointment</button>
    </div>
</div>
</form>
<script>
function bookService(name, price) {
    document.getElementById('serviceName').value = name;
    document.getElementById('servicePrice').value = price;

    document.getElementById('appointmentModal').style.display = 'block';

    document.getElementById('appointmentModal').scrollIntoView({ behavior: 'smooth' });
}

function submitAppointment() {
    const appointmentDate = document.getElementById('appointmentDate').value;
    const appointmentTime = document.getElementById('appointmentTime').value;

    const dateInput = document.createElement('input');
    dateInput.type = 'hidden';
    dateInput.name = 'appointmentDate';
    dateInput.value = appointmentDate;
    const timeInput = document.createElement('input');
    timeInput.type = 'hidden';
    timeInput.name = 'appointmentTime';
    timeInput.value = appointmentTime;
    document.getElementById('serviceForm').appendChild(dateInput);
    document.getElementById('serviceForm').appendChild(timeInput);
    document.getElementById('serviceForm').submit();
}
function redirectToServices() {
    window.location.href = "services.jsp";
}
</script>
</html>

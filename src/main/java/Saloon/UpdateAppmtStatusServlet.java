package Saloon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/UpdateStatusServlet")
public class UpdateAppmtStatusServlet extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String newStatus = req.getParameter("status");
		String serviceName = req.getParameter("service");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Connection con = null;
		PreparedStatement pst = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
			String query = "UPDATE appointments SET status = ? WHERE email = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, newStatus);
			pst.setString(2, email);
			int rowsAffected = pst.executeUpdate();

			out.println("<html><body>");
			if (rowsAffected > 0) {
				out.println("<script>alert('Status updated successfully for " + email + "');</script>");

				String message = getAppointmentMessage(newStatus, serviceName);
				String subject = "Appointment Update for Rudra Salon";
				String to = email;
				String from = "rudrasena995@gmail.com";

				sendEmail(message, subject, to, from);
			} else {
				out.println("<script>alert('No appointment found for the provided email.');</script>");
			}
			out.println("<script>window.location='adminDashboard.jsp';</script>");
			out.println("</body></html>");

		} catch (Exception e) {
			e.printStackTrace();
			out.println("<html><body><script>alert('An error occurred while updating status. please try again');"
					+ "window.location='adminDashboard.jsp';</script></body></html>");
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
				out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	private String getAppointmentMessage(String newStatus, String serviceName) {
		String message = "";

		if (newStatus.equals("accepted")) {
			message = "We are pleased to confirm your appointment for service name " + serviceName + "! Please arrive at the salon on time.";
		} else if (newStatus.equals("rejected")) {
			message = "Unfortunately, we cannot approve your appointment for service name " + serviceName + " at this time, as our expertise is unavailable.";
		}
		return message;
	}
	private void sendEmail(String message, String subject, String to, String from) {
	    // SMTP server information
	    String host = "smtp.gmail.com";
	    Properties prop = new Properties();
	    prop.put("mail.smtp.host", host);
	    prop.put("mail.smtp.port", "465");
	    prop.put("mail.smtp.ssl.enable", "true"); // Enable SSL
	    prop.put("mail.smtp.auth", "true"); // Enable authentication

	    // Step 1: Get the session object with authentication
	    Session session = Session.getInstance(prop, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication("rudrasena995@gmail.com", "gaxq sbnb jxjl plsv"); // Your email and app password
	        }
	    });
	    session.setDebug(true); 
	    try {
	        MimeMessage m = new MimeMessage(session);

	        m.setFrom(new InternetAddress(from));
	        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        m.setSubject(subject);
	        m.setText(message);

	        // Send the message
	        Transport.send(m);
	        System.out.println("Email sent successfully.");

	    } catch (Exception e) {
	        e.printStackTrace();
	        
	        System.out.println("Failed to send email.");
	    }
	}}

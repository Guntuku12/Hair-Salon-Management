package Saloon;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/submitReply")
public class submitReplyContact extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 PrintWriter out = resp.getWriter();
    	 
    	  String message = req.getParameter("reply"); 
          String email = req.getParameter("email");
          String name = req.getParameter("name");   
    
          message = "Dear " + name + ",\n\n" + message;
            String subject = " Query Response from Rudra Hair Saloon";
			String to = email;
			String from = "rudrasena995@gmail.com";
			
          System.out.println("Reply for " + name + " (" + email + "): " + message);
          String response= sendEmail(message, subject, to, from);
          req.setAttribute("responseMessage", response);

          req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);
    }

	private String sendEmail(String message, String subject, String to, String from) {
	  
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
	    String reply="";
	    try {
	        MimeMessage m = new MimeMessage(session);

	        m.setFrom(new InternetAddress(from));
	        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        m.setSubject(subject);
	        m.setText(message);

	        // Send the message
	        Transport.send(m);
	        System.out.println("Reply for email "+to+"sent successfully" );
	       
	       reply="Reply for email "+to+"sent successfully" ;

	    } catch (Exception e) {
	        e.printStackTrace();
	        reply=" Failed to send email.";
	        System.out.println("Failed to send email.");
	    }
		return reply;
	}}


  
   
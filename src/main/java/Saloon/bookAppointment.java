package Saloon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class bookAppointment extends HttpServlet
  {
	//These are retrieved from services.jsp
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    PrintWriter out = resp.getWriter();
	    String serviceName = req.getParameter("serviceName");
	    String servicePrice = req.getParameter("servicePrice"); 
	    String Date = req.getParameter("appointmentDate");
        String Time = req.getParameter("appointmentTime");
        String doApp=Date+" "+Time;
	    HttpSession session = req.getSession(false);
	    Connection con = null;
	    PreparedStatement pst = null;

	    if (session != null) {
	        String uname = (String) session.getAttribute("name");
	        String phone = (String) session.getAttribute("phone");
	        String email = (String) session.getAttribute("email");
	        String address = (String) session.getAttribute("address");
	        System.out.println(" "+serviceName + " " + servicePrice + " " + uname + " " + email + " " + phone);
	        
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
	            String query = "INSERT INTO appointments(name, email, place, haircut, cost, time) VALUES(?, ?, ?, ?, ?,?)";
	            pst = con.prepareStatement(query);
	            
	            pst.setString(1, uname);      
	            pst.setString(2, email);  
	            pst.setString(3, address);    
	            pst.setString(4, serviceName);       
	            pst.setString(5, servicePrice); 
	            pst.setString(6, doApp);

	            int row = pst.executeUpdate();
	            if (row == 1) {
	                out.println("<script>alert('Appointment Request Sent Successfully!!'); window.location.href='userDashboard.jsp';</script>");
	            } else {
	                out.println("<script>alert('Error in Booking Appointment. Please try again.'); window.location.href='userDashboard.jsp';</script>");
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	            out.println("<script>alert('Error in Booking Appointment. Please try again.');</script>");
	            req.getRequestDispatcher("userDashboard.jsp").forward(req, resp);
	        } finally {
	            try {
	                if (pst != null) pst.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
  }

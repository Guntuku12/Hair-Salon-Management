package Saloon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/myAppointments")
public class myAppointments extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 HttpSession session = req.getSession(false);
		    Connection con = null;
		    PreparedStatement pst = null;
		    ResultSet rs = null;
		    if (session != null) {
		       
		        String phone = (String) session.getAttribute("phone");
		        String email = (String) session.getAttribute("email");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
			String query = "SELECT name, email, haircut, cost,  time ,status FROM appointments WHERE email = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			rs = pst.executeQuery();
			List<String[]> appointmentDetails = new ArrayList<>();
			while (rs.next()) {
				String[] details = new String[6];
				details[0] = rs.getString("name");
				details[1] = rs.getString("email");
				details[2] = rs.getString("haircut");
				details[3] = rs.getString("cost");
				details[4] = rs.getString("status");
				details[5] = rs.getString("time");
				appointmentDetails.add(details);
			}
			req.setAttribute("appointments", appointmentDetails);

			RequestDispatcher dispatcher = req.getRequestDispatcher("MyApmtsUser.jsp");
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in catch");

		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		    }	}
}

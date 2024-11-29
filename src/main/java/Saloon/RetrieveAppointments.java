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

@SuppressWarnings("serial")
@WebServlet("/RetrieveAppointments")
public class RetrieveAppointments extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
			String query = "SELECT name, email, place, haircut, cost, status, time FROM appointments WHERE status = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, "pending");
			rs = pst.executeQuery();
			List<String[]> appointmentDetails = new ArrayList<>();
			while (rs.next()) {
				String[] details = new String[7];
				details[0] = rs.getString("name");
				details[1] = rs.getString("email");
				details[2] = rs.getString("place");
				details[3] = rs.getString("haircut");
				details[4] = rs.getString("cost");
				details[5] = rs.getString("status");
				details[6] = rs.getString("time");
				appointmentDetails.add(details);
			}
			req.setAttribute("appointments", appointmentDetails);

			RequestDispatcher dispatcher = req.getRequestDispatcher("apppointments.jsp");
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
	}
}

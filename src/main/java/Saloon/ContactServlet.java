package Saloon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContactServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ContactServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String mail = req.getParameter("email");
		String body = req.getParameter("body");
		String alertMessage = null;

		if (!mail.endsWith("@gmail.com")) {
			alertMessage = "Please Provide a valid mail address";
		} else {
			Connection con = null;
			PreparedStatement pst = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");

				LocalDateTime currentDateTime = LocalDateTime.now();
				Timestamp timestamp = Timestamp.valueOf(currentDateTime);
				String query = "INSERT INTO Contact (name, email, message, date_time) VALUES (?, ?, ?, ?)";
				pst = con.prepareStatement(query);
				pst.setString(1, name);
				pst.setString(2, mail);
				pst.setString(3, body);
				pst.setTimestamp(4, timestamp); // Set the timestamp directly

				int rs = pst.executeUpdate();
				if (rs > 0) {
					alertMessage = "Thanks for your query!! Our team will get back to you";
				} else {
					alertMessage = "Error!! Please try after some time";
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				alertMessage = "Error!! Please try after some time";
			} finally {
				try {
					if (pst != null) {
						pst.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<script>alert('" + alertMessage + "');</script>");
		req.getRequestDispatcher("HomePage.jsp").include(req, resp);
	}
}

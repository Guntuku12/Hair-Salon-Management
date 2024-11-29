package Saloon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ServiceCompletionList")
public class ServiceCompletionList extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
            
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currentTime = now.format(formatter);

            String query = "SELECT name, email, place, haircut, cost, status, time FROM appointments WHERE status = 'accepted' AND time < ?";
            pst = con.prepareStatement(query);

            pst.setString(1, currentTime);
           
            rs = pst.executeQuery();
            List<String[]> appointmentDetails = new ArrayList<>();
            while (rs.next()) {
                String[] details = new String[7];
                details[0] = rs.getString("name");
                details[1] = rs.getString("email");
                details[2] = rs.getString("place");
                details[3] = rs.getString("haircut");
                details[4] = rs.getString("cost");
               
                details[5] = rs.getString("time");
                appointmentDetails.add(details);
            }
            req.setAttribute("appointments", appointmentDetails);
            RequestDispatcher dispatcher = req.getRequestDispatcher("ServiceCompletionList.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            resp.getWriter().println("<script>alert('Error retrieving appointments. Please try again.');</script>");
            RequestDispatcher dispatcher = req.getRequestDispatcher("adminDashboard.jsp");
            dispatcher.forward(req, resp);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

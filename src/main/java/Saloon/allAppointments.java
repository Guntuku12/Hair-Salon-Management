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
@WebServlet("/allAppointments")
public class allAppointments extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Hair_Saloon";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password@123";
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String[]> appointmentDetails = retrieveAppointments();
        if (appointmentDetails != null) {
            req.setAttribute("appointments", appointmentDetails);
            forwardRequest(req, resp, "AllAptmts.jsp");
        } else {
            showErrorAndRedirect(req, resp, "Error retrieving appointments. Please try again.", "adminDashboard.jsp");
        }
    }
    private List<String[]> retrieveAppointments() {
        List<String[]> appointmentDetails = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pst = con.prepareStatement("SELECT name, email, place, haircut, cost, status, time FROM appointments");
             ResultSet rs = pst.executeQuery()) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return appointmentDetails;
    }
    private void forwardRequest(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }
    private void showErrorAndRedirect(HttpServletRequest req, HttpServletResponse resp, String errorMessage, String page) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().println("<script>alert('" + errorMessage + "');</script>");
        forwardRequest(req, resp, page);
    }
}

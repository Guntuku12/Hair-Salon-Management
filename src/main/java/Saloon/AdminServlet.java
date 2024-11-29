package Saloon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String URL = "jdbc:mysql://localhost:3306/Hair_Saloon"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "password@123"; 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
        	Connection con = createConnection();
            if ("login".equals(action)) {
                handleLogin(req, resp, con);
            } else if ("passwordChange".equals(action)) {
                handlePasswordChange(req, resp, con);
            }
        } catch (Exception e) {
            e.printStackTrace();
            displayAlertAndRedirect("An error occurred. Please try again later.", "admin.jsp", req, resp);
        }
    }
    
    private Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    //for admin logins
    
    private void handleLogin(HttpServletRequest req, HttpServletResponse resp, Connection con) throws ServletException, IOException {
        String phone = req.getParameter("number");
        String password = req.getParameter("password");

        String query = "SELECT phone, password FROM admin WHERE phone = ? AND password = ?";
        
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, phone);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                req.getRequestDispatcher("adminDashboard.jsp").forward(req, resp);
            } else {
                displayAlertAndRedirect("Error!! mobile number & password mismatch.", "admin.jsp", req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            displayAlertAndRedirect(" Please try again.", "admin.jsp", req, resp);
        }
    }
    
    private void handlePasswordChange(HttpServletRequest req, HttpServletResponse resp, Connection con) throws ServletException, IOException {
        String oldPass = req.getParameter("old_pass");
        String newPass = req.getParameter("new_pass");
        String confPass = req.getParameter("con_pass");

        if (!newPass.equals(confPass)) {
            displayAlertAndRedirect("The new password and confirm password must match.", "adminDashboard.jsp", req, resp);
            return;
        }

        String query = "SELECT email FROM admin WHERE password = ?";
        
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, oldPass);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                updatePassword(con, email, newPass, req, resp);
            } else {
                displayAlertAndRedirect("Please enter your old password correctly!", "adminDashboard.jsp", req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            displayAlertAndRedirect("An error encountered. Please try again.", "adminDashboard.jsp", req, resp);
        }
    }

    private void updatePassword(Connection con, String email, String newPass, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String updateQuery = "UPDATE admin SET password = ? WHERE email = ?";

        try (PreparedStatement pst = con.prepareStatement(updateQuery)) {
            pst.setString(1, newPass);
            pst.setString(2, email);
            int rows = pst.executeUpdate();

            if (rows == 1) {
                displayAlertAndRedirect("Password changed successfully!", "adminDashboard.jsp", req, resp);
            } else {
                displayAlertAndRedirect("Error updating password. Please try again.", "adminDashboard.jsp", req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            displayAlertAndRedirect("An error encountered.Please try again.", "adminDashboard.jsp", req, resp);
        }
    }

    private void displayAlertAndRedirect(String message, String page, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<script>alert('" + message + "');</script>");
        req.getRequestDispatcher(page).include(req, resp);
    }
}

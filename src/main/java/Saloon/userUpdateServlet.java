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

public class userUpdateServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Hair_Saloon";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password@123";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                switch (action) {
                    case "mobileUpdate":
                        handleMobileUpdate(req, con, out);
                        break;
                    case "passwordUpdate":
                        handlePasswordUpdate(req, con, out);
                        break;
                    default:
                        out.println("<script>alert('Invalid action!');</script>");
                        break;
                }
            }
        } catch (ClassNotFoundException e) {
            out.println("<script>alert('An error occurred. Please try again later.');</script>");
        } catch (SQLException e) {
            e.printStackTrace(); 
            out.println("<script>alert('An error occurred. Please try again later.');</script>");
        } finally {
            req.getRequestDispatcher("userDashboard.jsp").include(req, resp);
        }
    }

    private void handleMobileUpdate(HttpServletRequest req, Connection con, PrintWriter out) throws SQLException {
        String oldNum = req.getParameter("old_mob");
        String newNum = req.getParameter("new_mob");
        String regex = "^\\d{10}$";

        if (newNum.matches(regex)) {
            String query = "SELECT email FROM User_Registration WHERE phone = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, oldNum);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String mail = rs.getString("email");
                        updateMobileNumber(con, newNum, mail, out);
                    } else {
                        out.println("<script>alert('The mobile number entered is incorrect. Please try again.');</script>");
                    }
                }
            }
        } else {
            out.println("<script>alert('Mobile number must be 10 digits. Please try again.');</script>");
        }
    }

    private void updateMobileNumber(Connection con, String newNum, String mail, PrintWriter out) throws SQLException {
        String sql = "UPDATE User_Registration SET phone = ? WHERE email = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newNum);
            pst.setString(2, mail);
            int rows = pst.executeUpdate();

            if (rows == 1) {
                out.println("<script>alert('Mobile number changed successfully');</script>");
            } else {
                out.println("<script>alert('Error updating mobile number. Please try again.');</script>");
            }
        }
    }

    private void handlePasswordUpdate(HttpServletRequest req, Connection con, PrintWriter out) throws SQLException {
        String oldPass = req.getParameter("old_pass");
        String newPass = req.getParameter("new_pass");
        String confirmPass = req.getParameter("con_pass");

        String query = "SELECT email FROM User_Registration WHERE upassword = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, oldPass);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String mail = rs.getString("email");
                    if (newPass.equals(confirmPass)) {
                        updatePassword(con, newPass, mail, out);
                    } else {
                        out.println("<script>alert('The new password and confirm password must match.');</script>");
                    }
                } else {
                    out.println("<script>alert('Please enter your old password correctly!');</script>");
                }
            }
        }
    }

    private void updatePassword(Connection con, String newPass, String mail, PrintWriter out) throws SQLException {
        String sql = "UPDATE User_Registration SET upassword = ? WHERE email = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newPass);
            pst.setString(2, mail);
            int rows = pst.executeUpdate();

            if (rows == 1) {
                out.println("<script>alert('Password changed successfully!');</script>");
            } else {
                out.println("<script>alert('Error updating password. Please try again.');</script>");
            }
        }
    }
}

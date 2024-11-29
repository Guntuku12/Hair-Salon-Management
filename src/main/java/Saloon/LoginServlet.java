package Saloon;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("number");
        String pass = req.getParameter("password");
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
            String query = "SELECT user_name, phone, email ,place FROM User_Registration WHERE phone = ? AND upassword = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, num);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            if (rs.next()) {
                String uname = rs.getString("user_name");
                String mobileNum=rs.getString("phone");
                String email=rs.getString("email");
                String address=rs.getString("place");
                HttpSession session=req.getSession();
                session.setAttribute("name", uname);
                session.setAttribute("phone", mobileNum);
                session.setAttribute("email",email );
                session.setAttribute("address",address );
                req.getRequestDispatcher("userDashboard.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Error!! mobile number & password Mismatch");
                RequestDispatcher rd = req.getRequestDispatcher("UserLogin.jsp");
                rd.include(req, resp);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            req.setAttribute("message", "Error!! mobile number & password Mismatch");
            RequestDispatcher rd = req.getRequestDispatcher("UserLogin.jsp");
            rd.include(req, resp);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

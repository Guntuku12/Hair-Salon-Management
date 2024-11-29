package Saloon;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@jakarta.servlet.annotation.WebServlet("/queryGet")
public class GetQueryContact extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("status");
        List<Map<String, String>> queries = new ArrayList<>();

        if (status != null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
                PreparedStatement stmt = conn.prepareStatement(
                        "SELECT name, email, message, date_time FROM contact WHERE status = ?");
                stmt.setString(1, status);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, String> query = new HashMap<>();
                    query.put("name", rs.getString("name"));
                    query.put("email", rs.getString("email"));
                    query.put("status",status);
                    query.put("date_time", rs.getTimestamp("date_time").toString().substring(0, 16));
                    query.put("message", rs.getString("message"));

                    queries.add(query);
                    System.out.println(queries);
                }

                // Update status from 'unread' to 'read'
                if ("unread".equalsIgnoreCase(status)) {
                    PreparedStatement pstmt = conn.prepareStatement(
                            "UPDATE contact SET status = 'read' WHERE status = 'unread'");
                    pstmt.executeUpdate();
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Set queries as a request attribute and forward to JSP
        request.setAttribute("queries", queries);
        request.getRequestDispatcher("queryGet.jsp").forward(request, response);
    }
}

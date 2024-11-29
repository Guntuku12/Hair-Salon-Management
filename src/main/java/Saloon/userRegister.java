package Saloon;

import java.io.IOException;
import java.io.PrintWriter;
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

public class userRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;
/*
    // Method to validate connection
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String fullName = firstName + " " + lastName;
        String mobileNumber = req.getParameter("phone");
        String email = req.getParameter("mail");
        String adhar = req.getParameter("adhar");
        String place = req.getParameter("place");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        String phonePattern = "^\\d{10}$";
        String adharPattern = "^\\d{12}$";

        if (email.endsWith("@gmail.com") && mobileNumber.matches(phonePattern) && adhar.matches(adharPattern)) {
            try {
                boolean isDuplicate = checkDuplicateUser(mobileNumber, email);
                if (isDuplicate==true) {
                    req.setAttribute("duplicateError", "An entry already exists with these details.");
                    req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
                } else {
                    boolean isRegistered = registerUser(fullName, mobileNumber, email, age, adhar, place, password);
                    if (isRegistered==true) {
                        req.setAttribute("registerSuccess", "Account created successfully! Now proceed to Login.");
                        req.getRequestDispatcher("UserLogin.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("registerError", "Account creation failed.");
                        req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                req.setAttribute("registerError", "Account creation failed due to an error.");
                req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
            }
        } else {
            // If validation fails
            req.setAttribute("errorMessage", "Error! Email must end with @gmail.com, mobile number must be 10 digits, and adhar number must be 12 digits.");
            req.getRequestDispatcher("createAccount.jsp").include(req, resp);
        }
    }

    private boolean checkDuplicateUser(String mobileNumber, String email) throws SQLException, ClassNotFoundException {
        String query = "SELECT phone, email FROM User_Registration WHERE phone = ? OR email = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pst = con.prepareStatement(query);
            pst.setString(1, mobileNumber);
            pst.setString(2, email);

            rs = pst.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        }
    }

    // registering a new user
    private boolean registerUser(String fullName, String mobileNumber, String email, int age, String adhar, String place, String password) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO User_Registration (user_name, phone, email, age, adhar, place, upassword) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, fullName);
            pstmt.setString(2, mobileNumber);
            pstmt.setString(3, email);
            pstmt.setInt(4, age);
            pstmt.setString(5, adhar);
            pstmt.setString(6, place);
            pstmt.setString(7, password);

            int rowsAffected = pstmt.executeUpdate();
           if (rowsAffected == 1) {
                return true; 
            } else {
                return false; 
            }
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }*/
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/Hair_Saloon", "root", "password@123");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String firstName = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String fullName = firstName + " " + lastName;
        String mobileNumber = req.getParameter("phone");
        String email = req.getParameter("mail");
        String adhar = req.getParameter("adhar");
        String place = req.getParameter("place");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));

        // Validation patterns
        String phonePattern = "^\\d{10}$";
        String adharPattern = "^\\d{12}$";
        String emailPattern =  ".*@gmail\\.com$";

        boolean hasError = false;

        // Validate email
        if (!email.matches(emailPattern)) {
            req.setAttribute("emailError", "Invalid Email! Email must end with '@gmail.com'.");
            hasError = true;
        }

        // Validate mobile number
        if (!mobileNumber.matches(phonePattern)) {
            req.setAttribute("mobileError", "Invalid Mobile Number! Mobile number must be exactly 10 digits.");
            hasError = true;
        }

        // Validate Aadhaar
        if (!adhar.matches(adharPattern)) {
            req.setAttribute("adharError", "Invalid Aadhaar Number! Aadhaar number must be exactly 12 digits.");
            hasError = true;
        }

        if (hasError) {
            // If any validation fails, forward back to createAccount.jsp with error messages
            req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
            return;
        }

        try {
            boolean isDuplicate = checkDuplicateUser(mobileNumber, email);
            if (isDuplicate) {
                req.setAttribute("duplicateError", "An entry already exists with these details.");
                req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
            } else {
                boolean isRegistered = registerUser(fullName, mobileNumber, email, age, adhar, place, password);
                if (isRegistered) {
                    req.setAttribute("registerSuccess", "Account created successfully! Now proceed to Login.");
                    req.getRequestDispatcher("UserLogin.jsp").forward(req, resp);
                } else {
                    req.setAttribute("registerError", "Account creation failed.");
                    req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            req.setAttribute("registerError", "Account creation failed due to an error.");
            req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
        }
    }

    // Check for duplicate user based on mobile number or email
    private boolean checkDuplicateUser(String mobileNumber, String email) throws SQLException, ClassNotFoundException {
        String query = "SELECT phone, email FROM User_Registration WHERE phone = ? OR email = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pst = con.prepareStatement(query);
            pst.setString(1, mobileNumber);
            pst.setString(2, email);
            rs = pst.executeQuery();
            return rs.next();
        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        }
    }

    // Register a new user in the database
    private boolean registerUser(String fullName, String mobileNumber, String email, int age, String adhar, String place, String password)
            throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO User_Registration (user_name, phone, email, age, adhar, place, upassword) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, fullName);
            pstmt.setString(2, mobileNumber);
            pstmt.setString(3, email);
            pstmt.setInt(4, age);
            pstmt.setString(5, adhar);
            pstmt.setString(6, place);
            pstmt.setString(7, password);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected == 1;
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }
}


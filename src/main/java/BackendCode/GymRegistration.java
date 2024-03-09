package BackendCode;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/GymRegisterForm")
public class GymRegistration extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<h2>Welcome to Register Servlet</h2>");
		String name = request.getParameter("user_name");
		String age =  request.getParameter("user_age");
		String weight = request.getParameter("user_weight");
		String locality = request.getParameter("user_locality");
		String email = request.getParameter("user_email");
		String phone = request.getParameter("user_phoneN");
		String gender = request.getParameter("user_gender");
	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gymweb","root","Rishi@9838");
	        System.out.println("success");
	        PreparedStatement preparedStatement = connection.prepareStatement("insert into register values(?,?,?,?,?,?,?)");
	        preparedStatement.setString(1,name);
	        preparedStatement.setString(2,age);
	        preparedStatement.setString(3,weight);
	        preparedStatement.setString(4,locality);
	        preparedStatement.setString(5,email);
	        preparedStatement.setString(6,phone);
	        preparedStatement.setString(7,gender);

	        int count = preparedStatement.executeUpdate();
	        if(count > 0) {
	        	response.setContentType("text/html");
	        	
				out.print("<h3 style= 'color:green'>User registered successfully </h3>");
				
				RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
				rd.include(request, response);
	        }
	        else {
	        	response.setContentType("text/html");
	        	
				out.print("<h3 style= 'color:red'>User not registered due to some error </h3>");
				
				RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
				rd.include(request, response);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");
        	
			out.print("<h3 style= 'color:red'> Exception Occured :"+e.getMessage()+"</h3>");
			
			RequestDispatcher rd = request.getRequestDispatcher("/Home.jsp");
			rd.include(request, response);
		}
	
	}

}

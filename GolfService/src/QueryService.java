import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class QueryService extends HttpServlet {
	private static final long serialVersionUID = -5616899811848789885L;
	Connection db; // This is the shared JDBC database connection

	public void init() throws ServletException {
		// Use those init params to establish a connection to the database
		// If anything goes wrong, log it, wrap the exception and re-throw it
		db = JdbcHelper.getConnection();
	}

	/** Close the database connection when the servlet is unloaded */
	public void destroy() {
		try {
			db.close();
		} // Try to close the connection
		catch (SQLException e) {
		} // Ignore errors; at least we tried!
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html"); // We're outputting HTML
		PrintWriter out = response.getWriter(); // Where to output it to
		// See if a query was specified in this request.
		String query = request.getParameter("sql");
		String method = request.getParameter("method");
		String returnstr = "";
		if (query != null) {
			if(method.equals("query")){
				returnstr = JdbcHelper.query(db, query);				
			}else if(method.equals("update")){
				returnstr = JdbcHelper.update(db, query);				
			}
		}
		out.print(returnstr);
	}

	
}
